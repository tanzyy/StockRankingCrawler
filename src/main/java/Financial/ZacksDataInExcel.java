package Financial;

import Utils.CommonUtils;
import Utils.Constants;
import Utils.ExcelOpener;
import VO.RankInfo;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by i852841 on 5/12/18.
 */


/**
 * Todo
 * 1. Write Unit Tests
 * 2. Recognize name of file by input
 * 3. Use loggers rather sout
 * 4. One click for all crawlers
 * 5. Thread based
 * 6. Color if there was change in previous ranking
 */
public class ZacksDataInExcel {

    private static final int    DATA_SIZE_IN_MB         = 100;
    private static final String ZACKS_SEPARATOR         = "-";
    private static final String SEPARATOR_ZACKS_PRICE   = " ";
    private static final String ZACKS_MAIN_URL          = "https://www.zacks.com/stock/quote/";
    private static final String ZACKS_RANK_UNAVAILABLE  = "UN";
    private static final String OUT_FILE_LOC            = "/Users/i852841/Desktop/Personal/Finance_Learn_Reports/ZacksRank/";
    private static final String BACK_FILE_LOC           = "/Users/i852841/Desktop/Personal/Finance_Learn_Reports/ZacksRank/Backup/";
    //private static final String OUT_FILE_LOC     = "/Users/i852841/Downloads/";
    //private static final String BACK_FILE_LOC    = "/Users/i852841/Downloads/";

    private String rankDate;

    public void init() {

        setRankDate(CommonUtils.getMonthDay());
    }

    // Return object
    public RankInfo getData(String symbol) {

        Document document;
        RankInfo rankInfo = new RankInfo();
        rankInfo.setSymbol(symbol);

        try {
            String targetURL = ZACKS_MAIN_URL + symbol.toUpperCase() + "?q=" + symbol;
            System.out.println(targetURL);

            document         = Jsoup.connect(targetURL).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Element rank     = document.select("div.zr_rankbox").first().select("p.rank_view").first();
            Element price    = document.select("div.ribbon_value").first().select("p.last_price").first();
            String rankData  = filterRank(rank.text());
            String priceData = filterPrice(price.text());

            if(rankData != null && rankData.length() != 0 && priceData != null && priceData.length() != 0) {
                rankInfo.setRank(rankData);
                rankInfo.setPrice(priceData);
            }
                //result = symbol + SEPARATOR_SYMBOL_RANK + rankData + SEPARATOR_RANK_PRICE + priceData;

        } catch(Exception e) {
            System.out.println(String.format("Error occurred for [%s] with error %s ", symbol,  e));
        }

        if(rankInfo.getRank() == null) {
            //result = symbol + SEPARATOR_SYMBOL_RANK + 0;
            rankInfo.setRank("0");
            rankInfo.setPrice("0");
        }

        System.out.println(rankInfo);
        return rankInfo;
    }

    public String getPrice(String symbol) {

        Document document;
        String result = "";

        try {
            String targetURL = ZACKS_MAIN_URL + symbol.toUpperCase() + "?q=" + symbol;
            System.out.println(targetURL);

            document = Jsoup.connect(targetURL).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Element rank = document.select("div.ribbon_value").first().select("p.last_price").first();
            System.out.println(rank.text());

        } catch(Exception e) {
            System.out.println(String.format("Error occurred for [%s] with error %s ", symbol,  e));
        }

        return result;
    }

    /**
     * 1. Backup file
     * 2. Shift columns
     * 3. Insert Row
     * 4. Delete temp file
     * @param fileName
     */
    public void writeToXL(String fileName, List<RankInfo> allFetchedData) {

        String fileWithLOC       = OUT_FILE_LOC + fileName;
        File targetFile          = new File(fileWithLOC);

        String backupfileWithLOC = BACK_FILE_LOC + CommonUtils.getCurrentTime() + Constants.SEPARATOR_UNDERSCORE + fileName;
        File backupFileHandler   = new File(backupfileWithLOC);

        File tempFileHandler = new File(OUT_FILE_LOC + Constants.TEMP_PREFIX + fileName);

        //Do backup
        //Rename actual file to temp file
        if(targetFile.exists()) {
            try {
                Files.copy(targetFile.toPath(), backupFileHandler.toPath());
                targetFile.renameTo(tempFileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(tempFileHandler.exists()) {
            System.out.println(fileName + " exists.");
            //Shift columns
            shiftColumns(OUT_FILE_LOC + Constants.TEMP_PREFIX + fileName, OUT_FILE_LOC + fileName);

            //Insert New Rank data
            insertNewRankColumn(fileWithLOC, allFetchedData);

            //Delete Temp file
            tempFileHandler.delete();

        } else {
            System.out.println(fileName + " does not exist, creating new one.");
            createNewRankColumn(fileWithLOC, allFetchedData);
        }
    }


    private void shiftColumns(String inputFile, String outputFile) {

        int sheetIndex = 0;
        int columnIndex = 1;

        ExcelOpener op = new ExcelOpener(inputFile, outputFile);
        try {
            op.open();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        op.insertNewColumnBefore(sheetIndex, columnIndex);

        op.close();
    }

    private void insertNewRankColumn(String targetFile, List<RankInfo> allFetchedData) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            System.out.println("Row count is [" + rowCount + "]");

            //START: Update date
            Row firstRow   = sheet.getRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(getRankDate());
            //END: Update date

            for(int rowIndex=1; rowIndex<=rowCount; rowIndex++){

                Row currentRow           = sheet.getRow(rowIndex);
                Cell currentCell         = currentRow.createCell(1);
                RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);
                String currentCellStr    = getRankStrByRankInfo(currentRankInfo);
                Integer currentCellVal   = Integer.valueOf(getRankNumByStr(currentCellStr));

                Cell previousCell        = currentRow.getCell(2);
                Integer previousCellVal  = Integer.valueOf(getRankNumByStr(previousCell.toString()));

                System.out.println(String.format(
                       "For Symbol [%s] ,  PreviousCellStr [%s] ,  PreviousCellVal [%s] , CurrentCellStr [%s] ,  CurrentCellVal [%s]",
                        currentRow.getCell(0), previousCell, previousCellVal, currentCellStr, currentCellVal));

                if(currentCellVal != 0 && previousCellVal != 0) {

                    if(previousCellVal > currentCellVal) {

                        CellStyle style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        currentCell.setCellStyle(style);

                    } else if(previousCellVal < currentCellVal) {

                        CellStyle style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        currentCell.setCellStyle(style);
                    }

                } else if(currentCellVal != 0 && previousCellVal == 0) { //New Coverage

                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    currentCell.setCellStyle(style);

                } else if(currentCellVal == 0 && previousCellVal != 0) { //No More Coverage
                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    currentCell.setCellStyle(style);
                }

                currentCell.setCellValue(currentCellStr);
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(targetFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
            ex.printStackTrace();
        }
    }


    private void createNewRankColumn(String targetFile, List<RankInfo> allFetchedData) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("data");

            Row firstRow = sheet.createRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(getRankDate());

            System.out.println("firstRowSecondCell " + firstRowSecondCell);

            for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                Row currentRow     = sheet.createRow((rowIndex));
                Cell symbolCell    = currentRow.createCell(0);
                Cell rankCell      = currentRow.createCell(1);

                RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);

                symbolCell.setCellValue(currentRankInfo.getSymbol());
                System.out.println("Symbol : " + currentRankInfo.getSymbol());

                rankCell.setCellValue(getRankStrByRankInfo(currentRankInfo));
            }

            FileOutputStream outputStream = new FileOutputStream(targetFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param toFilter
     * @return
     */
    private String filterRank(String toFilter) {
        //3-Hold     3
        return (toFilter.split(ZACKS_SEPARATOR))[0];
    }

    /**
     * @param toFilter $53.39 USD
     * @return $53.39
     */
    private String filterPrice(String toFilter) {
        return (toFilter.split(SEPARATOR_ZACKS_PRICE))[0];
    }


    /**
     * Builds the rank data column in- Rank (Price)
     * @param rankInfo
     * @return
     */
    public String getRankStrByRankInfo(RankInfo rankInfo) {

        StringBuilder result = new StringBuilder();
        switch(rankInfo.getRank()) {

            case "1" : result.append("StrongBuy ");            break;
            case "2" : result.append("Buy       ");                   break;
            case "3" : result.append("Hold      ");                  break;
            case "4" : result.append("Sell      ");                  break;
            case "5" : result.append("StrongSell");           break;
            default  : result.append(ZACKS_RANK_UNAVAILABLE + "        ");  break;
        }

        return result
                .append(Constants.SEPARATOR_WHITE_SPACE)
                .append(Constants.SEPARATOR_OPEN_BRACE)
                .append(rankInfo.getPrice())
                .append(Constants.SEPARATOR_CLOSE_BRACE)
                .toString();
    }

    /**
     * Fetches the rank from column "Rank Data
     * @param rankStr as "Rank (Price)"
     * @return "Rank" as number
     */
    public String getRankNumByStr(String rankStr) {

       return getRankNum(rankStr.split(Constants.SEPARATOR_WHITE_SPACE)[0]);
    }


    private String getRankStr(String rankData) {

        String[] splitRankData = rankData.split(Constants.SEPARATOR_RANK_PRICE);

        StringBuilder result = new StringBuilder();
        switch(splitRankData[0]) {

            case "1" : result.append("StrongBuy  ");           break;
            case "2" : result.append("Buy        ");           break;
            case "3" : result.append("Hold       ");           break;
            case "4" : result.append("Sell       ");           break;
            case "5" : result.append("StrongSell ");           break;
            default  : result.append(ZACKS_RANK_UNAVAILABLE);  break;
        }

        return result.append(Constants.SEPARATOR_RANK_PRICE).append(splitRankData[1]).toString();
    }

    private String getRankNum(String rankStr) {

        switch(rankStr.trim()) {

            case "StrongBuy" : return "1";
            case "Buy" : return "2";
            case "Hold" : return "3";
            case "Sell" : return "4";
            case "StrongSell" : return "5";
            default  : return "0";
        }
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }
}















