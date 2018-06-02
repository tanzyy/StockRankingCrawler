package Financial;

import Utils.CommonUtils;
import Utils.Constants;
import Utils.ExcelOpener;
import VO.RankInfo;
import org.apache.log4j.Logger;
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
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by i852841 on 5/26/18.
 */
public class MarketBeatRatings {

    private static final Logger LOG = Logger.getLogger(MarketBeatRatings.class);
    private static final int    DATA_SIZE_IN_MB         = 100;
    private static final String MB_RESEARCHER_URI = "https://www.marketbeat.com/ratings/by-issuer/";
    private static final String MB_TICKER_NA = "NA";
    private static final int MB_DATE_RANGE = 10;

    private String rankDate;
    private String rankYear;

    public void init() {

        setRankDate(CommonUtils.getMonthDay());
        setRankYear(CommonUtils.getYear());

        researchFirmOrderList.add("Needham & Company LLC");
        researchFirmOrderList.add("Credit Suisse Group");
        researchFirmOrderList.add("Morningstar");
        researchFirmOrderList.add("Zacks Investment Research");
    }

    private List<String> researchFirmOrderList = new ArrayList<>();

    public Map<String, RankInfo> getDataByResearchFirm(String issuer, boolean isAll, List<String> tickers) {

        Document document;
        Map<String, RankInfo> result = new HashMap<>();

        try {
            String targetURL = MB_RESEARCHER_URI + issuer;
            LOG.info(targetURL);

            document      = Jsoup.connect(targetURL).userAgent("Mozilla").maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Elements rows = document.select("tbody").select("tr");

            for(Element row : rows) {

                Elements columns = row.select("td");
                System.out.println("Current columns are - " + columns.toString());

                String dateStr = columns.get(0).text();
                if(dateStr != null && dateStr.trim().length() == 0)
                    continue;

                int rangeVal = CommonUtils.isDateInRange(dateStr.trim(), MB_DATE_RANGE);

                if(rangeVal == 1) {

                    String ticker = getTicker(columns.get(3).text());
                    if((!isAll && tickers.contains(ticker)) || isAll) {
                        setValues(columns, ticker, result);
                    }
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                } else if(rangeVal == -1)
                    break;
                else
                    continue;
            }

        } catch(Exception e) {
            LOG.error(String.format("Error occurred for [%s] with error %s ", issuer,  e));
        }

        result.forEach((k, v) -> LOG.info("Key [" + k + "] with value [" + v + "]"));
        return result;
    }


    public List<Map<String, RankInfo>> getDataByResearchFirmV1(String issuer, boolean isAll, List<String> tickers) {

        Document document;
        List<Map<String, RankInfo>> result = new ArrayList<>();

        try {
            String targetURL = MB_RESEARCHER_URI + issuer;
            LOG.info(targetURL);

            document      = Jsoup.connect(targetURL).userAgent("Mozilla").maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Elements rows = document.select("tbody").select("tr");

            for(Element row : rows) {

                Elements columns = row.select("td");
                System.out.println("Current columns are - " + columns.toString());

                String dateStr = columns.get(0).text();
                if(dateStr != null && dateStr.trim().length() == 0)
                    continue;

                int rangeVal = CommonUtils.isDateInRange(dateStr.trim(), MB_DATE_RANGE);

                if(rangeVal == 1) {

                    String ticker = getTicker(columns.get(3).text());
                    if((!isAll && tickers.contains(ticker)) || isAll) {
                        setValuesV1(columns, ticker, result);
                    }
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                } else if(rangeVal == -1)
                    break;
                else
                    continue;
            }

        } catch(Exception e) {
            LOG.error(String.format("Error occurred for [%s] with error %s ", issuer,  e));
        }

        result.forEach(s -> LOG.info(s));
        return result;
    }

    public String getTicker(String mbTickerData) {

        String result = MB_TICKER_NA;

        try {
            result = mbTickerData.substring(mbTickerData.indexOf(Constants.SEPARATOR_OPEN_BRACE) + 1,
                    mbTickerData.indexOf(Constants.SEPARATOR_CLOSE_BRACE));
        } catch (Exception e) {
            LOG.error("Error occurred while parsing ticker string " + mbTickerData, e);
        }

        return result;
    }

    private void setValues(Elements columns, String ticker, Map<String, RankInfo> resultMap) {

        RankInfo rankInfo = new RankInfo();

        rankInfo.setResearchFirm(columns.get(1).text());
        rankInfo.setAction(columns.get(2).text());
        rankInfo.setRank(columns.get(4).text());
        rankInfo.setSpeculatedPrice(columns.get(5).text());
        resultMap.put(ticker, resultMap.get(ticker) == null ? rankInfo : resultMap.get(ticker));

    }

    private void setValuesV1(Elements columns, String ticker, List<Map<String, RankInfo>> result) {
        Map<String, RankInfo> resultMap = new HashMap<>();
        RankInfo rankInfo               = new RankInfo();

        rankInfo.setResearchFirm(columns.get(1).text());
        rankInfo.setRank(columns.get(4).text());
        rankInfo.setSpeculatedPrice(columns.get(5).text());
        resultMap.put(ticker, resultMap.get(ticker) == null ? rankInfo : resultMap.get(ticker));
        result.add(resultMap);
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public String getRankYear() {
        return rankYear;
    }

    public void setRankYear(String rankYear) {
        this.rankYear = rankYear;
    }


    public Map<String, List<RankInfo>> mergeMapData(List<Map<String, RankInfo>> listOfMaps) {

        //Get all keys
        Set<String> keys = getAllKeys(listOfMaps);

        Map<String, List<RankInfo>> resultMap = new HashMap<>();

        for(Map<String, RankInfo> currMap : listOfMaps) {

            for(String key : keys) {

                if(currMap.get(key) == null) {
                    continue;
                }else {
                    List<RankInfo> rankInfoList = new ArrayList<>();

                    if(resultMap.get(key) == null) {
                        rankInfoList.add(currMap.get(key));

                    } else {
                        rankInfoList = resultMap.get(key);
                        rankInfoList.add(currMap.get(key));
                    }

                    resultMap.put(key, rankInfoList);
                }
            }
        }

        resultMap.forEach((k, v) -> System.out.println("Size [" + v.size() + "] Key [" + k + "] with Value [" + v + "]"));
        return resultMap;
    }

    public Set<String> getAllKeys(List<Map<String, RankInfo>> maps) {

        Set<String> resultSet = new HashSet<>();

        for(Map<String, RankInfo> map : maps) {
            resultSet.addAll(map.keySet());
        }

        StringBuilder sbr = new StringBuilder("Tickers are [");
        for (String key : resultSet) {
            sbr.append(key).append(Constants.SEPARATOR_COMMA);
        }
        sbr.append("]");
        LOG.info(sbr.toString());

        return resultSet;
    }

    /**
     * 1. Backup file
     * 2. Shift columns
     * 3. Insert Row
     * 4. Delete temp file
     * @param fileName
     */
    public void writeToXL(String ticker, String fileName, List<RankInfo> allFetchedData, String outLOC, String backLOC) {

        String fileWithLOC       = outLOC + File.separator + fileName;
        File targetFile          = new File(fileWithLOC);

        String backupFileWithLOC = backLOC + File.separator + CommonUtils.getCurrentTime() + Constants.SEPARATOR_UNDERSCORE + fileName;
        File backupFileHandler   = new File(backupFileWithLOC);

        File tempFileHandler = new File(outLOC + File.separator + Constants.TEMP_PREFIX + fileName);

        //Do backup
        //Rename actual file to temp file
        if(targetFile.exists()) {
            try {
                Files.copy(targetFile.toPath(), backupFileHandler.toPath());
                targetFile.renameTo(tempFileHandler);
            } catch (IOException e) {
                LOG.error("IOException while BackingUp File  " + targetFile, e);
            }
        }

        try {
            LOG.info("List data before sorting");
            allFetchedData.forEach(s -> LOG.info(s));

            //Sort the data
            allFetchedData.sort(new Comparator<RankInfo>() {
                @Override
                public int compare(RankInfo o1, RankInfo o2) {
                    return Integer.compare(researchFirmOrderList.indexOf(o1.getResearchFirm()), researchFirmOrderList.indexOf(o2.getResearchFirm()));
                }
            });

            LOG.info("List data after sorting");
            allFetchedData.forEach(t -> LOG.info(t));

        } catch (Exception e) {
            LOG.error("Error occured while sorting the data " + e);
        }


        if(tempFileHandler.exists()) {
            LOG.info(fileName + " exists.");
            //Shift columns
            shiftColumns(outLOC+ File.separator + Constants.TEMP_PREFIX + fileName, outLOC + File.separator + fileName);

            //Insert New Rank data
            insertNewRankColumn(fileWithLOC, allFetchedData);

            //Delete Temp file
            tempFileHandler.delete();

        } else {
            LOG.info(fileName + " does not exist, creating new one.");
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
            LOG.error("InvalidFormatException while Shifting Column ", e);
        } catch (IOException e) {
            LOG.error("IOException while Shifting Column ", e);
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
            LOG.info("Row count is [" + rowCount + "]");

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
               //Integer currentCellVal   = Integer.valueOf(getRankNumByStr(currentCellStr));
               Integer currentCellVal   = null;

                Cell previousCell        = currentRow.getCell(2);
                //Integer previousCellVal  = Integer.valueOf(getRankNumByStr(previousCell.toString()));
                Integer previousCellVal  = null;

                LOG.info(String.format(
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
            LOG.error("Error Inserting New Column  " + targetFile, ex);
        }
    }


    private void createNewRankColumn(String targetFile, List<RankInfo> allFetchedData) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet(getRankYear());

            Row firstRow = sheet.createRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(getRankDate());

            LOG.info("firstRowSecondCell " + firstRowSecondCell);

            for(int rowIndex=1; rowIndex<=researchFirmOrderList.size(); rowIndex++){

                Row currentRow        = sheet.createRow((rowIndex));
                Cell researchFirmCell = currentRow.createCell(0);
                Cell rankCell         = currentRow.createCell(1);

                String firm = researchFirmOrderList.get(rowIndex - 1);
                researchFirmCell.setCellValue(firm);
                RankInfo currentRankInfo = getFirmFromRankInfo(firm, allFetchedData);
                //In case no data for current ticker by current firm in given date range
                if(currentRankInfo == null)
                    rankCell.setCellValue(Constants.ZERO_VALUE);
                else
                    rankCell.setCellValue(getRankStrByRankInfo(currentRankInfo));
            }

            FileOutputStream outputStream = new FileOutputStream(targetFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            LOG.error("Error while Creating New Sheet  " + targetFile, ex);
        }
    }


    private String getRankStrByRankInfo(RankInfo rankInfo) {

        StringBuilder result = new StringBuilder();

        //Check for Null Values
        result.append(rankInfo.getAction())
                .append(Constants.SEPARATOR_COMMA)
                .append(rankInfo.getRank())
                .append(Constants.SEPARATOR_COMMA)
                .append(rankInfo.getSpeculatedPrice());

        return result.toString();
    }

    private RankInfo getFirmFromRankInfo(String firm, List<RankInfo> rankInfoList) {

        for(RankInfo rankInfo : rankInfoList) {

            if(firm.equalsIgnoreCase(rankInfo.getResearchFirm()))
                return rankInfo;
        }

        return null;
    }
}
