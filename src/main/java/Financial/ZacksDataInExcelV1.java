package Financial;

import Utils.ExcelOpener;
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
public class ZacksDataInExcelV1 {

    private static final int    DATA_SIZE_IN_MB         = 100;
    private static final String ZACKS_SEPARATOR         = "-";
    private static final String SEPARATOR_SYMBOL_RANK   = ":";
    public static final String SEPARATOR_COMMA          = ",";
    private static final String SEPARATOR_UNDERSCORE    = "_";
    private static final String SEPARATOR_ZACKS_PRICE   = " ";
    private static final String EXTENSION_CSV           = ".csv";
    private static final String ZACKS_MAIN_URL          = "https://www.zacks.com/stock/quote/";
    private static final String ZACKS_RANK_UNAVAILABLE  = "UN";
    private static final String TEMP_PREFIX             = "TEMP_";
    private static final String SEPARATOR_RANK_PRICE    = " -- ";

    private static final String OUT_FILE_LOC     = "/Users/i852841/Downloads/";
    private static final String BACK_FILE_LOC    = "/Users/i852841/Downloads/";

    public static final SimpleDateFormat sdf   = new SimpleDateFormat("MMM_dd");
    public static final SimpleDateFormat tsdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    private String rankDate;

    public void init() {
        getCurrentTimeInString();
    }

    // Return object
    public String getData(String symbol) {

        Document document;
        String result = "";

        try {
            String targetURL = ZACKS_MAIN_URL + symbol.toUpperCase() + "?q=" + symbol;
            System.out.println(targetURL);

            document         = Jsoup.connect(targetURL).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Element rank     = document.select("div.zr_rankbox").first().select("p.rank_view").first();
            Element price    = document.select("div.ribbon_value").first().select("p.last_price").first();
            String rankData  = filterRank(rank.text());
            String priceData = filterPrice(price.text());

            if(rankData != null && rankData.length() != 0 && priceData != null && priceData.length() != 0)
                result = symbol + SEPARATOR_SYMBOL_RANK + rankData + SEPARATOR_RANK_PRICE + priceData;

        } catch(Exception e) {
            System.out.println(String.format("Error occurred for [%s] with error %s ", symbol,  e));
        }

        if(result != null && result.length() == 0) {
            result = symbol + SEPARATOR_SYMBOL_RANK + 0;
        }

        System.out.println(result);
        return result;
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
    public void writeToXL(String fileName, List<String> allFetchedData) {

        String fileWithLOC       = OUT_FILE_LOC + fileName;
        File targetFile          = new File(fileWithLOC);

        String backupfileWithLOC = BACK_FILE_LOC + getCurrentTime() + SEPARATOR_UNDERSCORE + fileName;
        File backupFileHandler   = new File(backupfileWithLOC);

        File tempFileHandler = new File(OUT_FILE_LOC + TEMP_PREFIX + fileName);


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
            shiftColumns(OUT_FILE_LOC + TEMP_PREFIX + fileName, OUT_FILE_LOC + fileName);

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

    private void insertNewRankColumn(String targetFile, List<String> allFetchedData) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            System.out.println("Row count is [" + rowCount + "]");

            //START:: Cell Style
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //END: Cell Style

            //START: Update date
            Row firstRow   = sheet.getRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(getRankDate());
            //END: Update date


            for(int rowIndex=1; rowIndex<=rowCount; rowIndex++){

                Row currentRow    = sheet.getRow(rowIndex);
                Cell currentCell  = currentRow.createCell(1);
                String currentCellVal = allFetchedData.get(rowIndex - 1).split(SEPARATOR_SYMBOL_RANK)[1];

                Cell previousCell = currentRow.getCell(2);
                Integer previousCellVal = Integer.valueOf(getRankNum(previousCell.toString()));
                System.out.println(String.format(
                        "PreviouscellStr [%s] ,  PreviouscellVal [%s] , CurrentCell [%s] ", previousCell, previousCellVal, currentCellVal));

                if(previousCellVal != Integer.valueOf(currentCellVal))
                    currentCell.setCellStyle(style);

                currentCell.setCellValue(getRankStr(currentCellVal));
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

    private void createNewRankColumn(String targetFile, List<String> allFetchedData) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("data");

            Row firstRow = sheet.createRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(getRankDate());

            System.out.println("firstRowSecondCell " + firstRowSecondCell);


            for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                String[] splitData = allFetchedData.get(rowIndex - 1).split(SEPARATOR_SYMBOL_RANK);
                Row currentRow     = sheet.createRow((rowIndex));
                Cell symbolCell    = currentRow.createCell(0);
                Cell rankCell      = currentRow.createCell(1);

                symbolCell.setCellValue(splitData[0].toUpperCase());
                System.out.println("Symbol : " + splitData[0].toUpperCase());

                rankCell.setCellValue(getRankStr(splitData[1]));
                System.out.println("Value  : " + getRankStr(splitData[1]));
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

    public void getCurrentTimeInString() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        setRankDate(sdf.format(timestamp));
    }

    public String getCurrentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return tsdf.format(timestamp);
    }

    /**
     * Get the number of commas so that we can that many commas for non existing record so all the ranks captured at same date will be on same column
     * @param inStr
     * @return
     */
    private String getNumberOfCommas(String inStr) {

        String[] splits = inStr.split(SEPARATOR_COMMA);
        StringBuilder commas = new StringBuilder();

        for(String split : splits)
            commas.append(SEPARATOR_COMMA);

        return commas.toString();
    }

    private String getRankStr(String rankData) {

        String[] splitRankData = rankData.split(SEPARATOR_RANK_PRICE);

        StringBuilder result = new StringBuilder();
        switch(splitRankData[0]) {

            case "1" : result.append("Strong Buy");   break;
            case "2" : result.append("Buy");         break;
            case "3" : result.append("Hold");        break;
            case "4" : result.append("Sell");        break;
            case "5" : result.append("Strong Sell"); break;
            default  : result.append(ZACKS_RANK_UNAVAILABLE);  break;
        }

        return result.append(SEPARATOR_RANK_PRICE).append(splitRankData[1]).toString();
    }

    private String getRankNum(String rankStr) {

        switch(rankStr) {

            case "Strong Buy" : return "1";
            case "Buy" : return "2";
            case "Hold" : return "3";
            case "Sell" : return "4";
            case "Strong Sell" : return "5";
            default  : return "0";
        }
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }


    private void processData(String symbols, String outFile, String backupFileName) {

        List<String> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(SEPARATOR_COMMA)) {
            currentRankingData.add(getData(symbol));
        }

        //String backupFile = backupFileName + SEPARATOR_UNDERSCORE +getRankDate() + EXTENSION_CSV;
        //writeToFile(currentRankingData, outFile, backupFile);
        //updateFirstRow(new File(OUT_FILE_LOC + outFile), true);
        writeToXL(outFile, currentRankingData);
    }

    public static void main(String[] args) {

        ZacksDataInExcelV1 zacksData             = new ZacksDataInExcelV1();
        zacksData.init();

        List<String> allData = new ArrayList<>();
        allData.add("M:2");
        allData.add("ETSY:3");
        allData.add("NTNX:2");
        allData.add("CURO:1");
        allData.add("BRKB:0");
        allData.add("QQQ:5");

        zacksData.writeToXL("out1.xlsx", allData);

//        zacksData.processData(SYMBOLS_TANZY_TO_INVEST, OUT_FILE_TANZY_TO_INVEST, "");


//        String execute = "TANZY_WATCH";
//        String execute = "TANZY_INVESTED";
//       String execute = "TANZY_TO_INVEST";
//        String execute = "TANZY_PENNY";
//        String execute = "TANZY_GROWTH";
//
//        switch(execute) {
//
//            case "TANZY_WATCH"     : zacksData.processData(SYMBOLS_TANZY_WATCH, OUT_FILE_TANZY_WATCH, "TanzyWatch");         break;
//            case "TANZY_INVESTED"  : zacksData.processData(SYMBOLS_TANZY_INVESTED, OUT_FILE_TANZY_INVESTED, "TanzyInvested");   break;
//            case "TANZY_TO_INVEST" : zacksData.processData(SYMBOLS_TANZY_TO_INVEST, OUT_FILE_TANZY_TO_INVEST, "TanzyToInvest"); break;
//            case "TANZY_PENNY"     : zacksData.processData(SYMBOLS_TANZY_PENNY, OUT_FILE_TANZY_PENNY, "TanzyPenny");         break;
//            case "TANZY_GROWTH"    : zacksData.processData(SYMBOLS_TANZY_GROWTH, OUT_FILE_TANZY_GROWTH, "TanzyGrowth");       break;
//        }
    }
}















