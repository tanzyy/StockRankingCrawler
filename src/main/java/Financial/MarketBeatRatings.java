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
public class MarketBeatRatings extends BaseRatings {

    private static final Logger LOG = Logger.getLogger(MarketBeatRatings.class);
    private static final int    DATA_SIZE_IN_MB     = 100;
    private static final String MB_RESEARCHER_URI   = "https://www.marketbeat.com/ratings/by-issuer/";
    private static final String MB_NASDAQ_STOCK_URI = "https://www.marketbeat.com/stocks/NASDAQ/";
    private static final String MB_NYSE_STOCK_URI   = "https://www.marketbeat.com/stocks/NYSE/";
    private static final String MB_TICKER_NA        = "NA";
    private static final int    MB_DATE_RANGE       = 7;
    private static  final String MB_RATINGS_URI     = "/price-target/";

    private String rankDate;
    private String rankYear;

    public void init() {

        setRankDate(CommonUtils.getMonthDay());
        setRankYear(CommonUtils.getYear());

        //Add Moffett Nathanson-- https://www.marketbeat.com/ratings/by-issuer/17096/
        //Updated for At&T on Nov 26

        researchFirmOrderList.add(ResearcherID.NEEDHAM.getId());
        researchFirmOrderList.add(ResearcherID.CS.getId());
        researchFirmOrderList.add(ResearcherID.MORNING.getId());
        researchFirmOrderList.add(ResearcherID.Z.getId());
        researchFirmOrderList.add(ResearcherID.GS.getId());
        researchFirmOrderList.add(ResearcherID.MORGAN.getId());
        researchFirmOrderList.add(ResearcherID.KC.getId());
        researchFirmOrderList.add(ResearcherID.ARGUS.getId());
        researchFirmOrderList.add(ResearcherID.PIPER.getId());
        researchFirmOrderList.add(ResearcherID.VE.getId());
        researchFirmOrderList.add(ResearcherID.SIDOTI.getId());
        researchFirmOrderList.add(ResearcherID.JEFFERIES.getId());
        researchFirmOrderList.add(ResearcherID.STIFEL.getId());
        researchFirmOrderList.add(ResearcherID.VETR.getId());
        researchFirmOrderList.add(ResearcherID.FUNDAMENTAL.getId());
        researchFirmOrderList.add(ResearcherID.JPMORGAN.getId());
        researchFirmOrderList.add(ResearcherID.RBC.getId());
        researchFirmOrderList.add(ResearcherID.CITI.getId());
        researchFirmOrderList.add(ResearcherID.BOFA.getId());
        researchFirmOrderList.add(ResearcherID.WFC.getId());
        researchFirmOrderList.add(ResearcherID.BIDASK.getId());
        researchFirmOrderList.add(ResearcherID.ROTH.getId());
        researchFirmOrderList.add(ResearcherID.JANNEYSCOTT.getId());
        researchFirmOrderList.add(ResearcherID.BLAIR.getId());
        researchFirmOrderList.add(ResearcherID.STEPHENS.getId());
        researchFirmOrderList.add(ResearcherID.BARCLAYS.getId());
        researchFirmOrderList.add(ResearcherID.BENCHMARK.getId());
        researchFirmOrderList.add(ResearcherID.EVERCORE.getId());
        researchFirmOrderList.add(ResearcherID.OPPENHEIMER.getId());
        researchFirmOrderList.add(ResearcherID.GUGGENHEIM.getId());
        researchFirmOrderList.add(ResearcherID.SUSQUEHANNA.getId());
        researchFirmOrderList.add(ResearcherID.NOMURA.getId());
    }

    public List<String> researchFirmOrderList = new ArrayList<>();

    /**
     *
     * @param issuer
     * @param isAll
     * @param tickers
     * @return Map of RanK Data with key as Ticker
     */
    public Map<String, RankInfo> getDataByResearchFirm(String issuer, boolean isAll, List<String> tickers) {

        Document document;
        Map<String, RankInfo> result = new HashMap<>();

        //Set UN as part of
        result = createBaseTickerData(result, tickers, issuer);

        try {
            String targetURL = MB_RESEARCHER_URI + issuer;
            LOG.info(targetURL);

            document      = Jsoup.connect(targetURL).userAgent("Mozilla").maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Elements rows = document.select("tbody").select("tr");

            for(Element row : rows) {

                Elements columns = row.select("td");
                //LOG.debug("Current columns are - " + columns.toString());

                String dateStr = columns.get(0).text();
                if(dateStr != null && dateStr.trim().length() == 0)
                    continue;

                int rangeVal = CommonUtils.isDateInRange(dateStr.trim(), MB_DATE_RANGE);

                if(rangeVal == 1) {

                    String ticker = getTicker(columns.get(3).text());
                    if((!isAll && tickers.contains(ticker)) || isAll) {
                        System.out.println("Got value for " + ticker + "::: " + columns.text());
                        setValues(columns, ticker, result);
                    }
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

    /**
     *
     * @param ticker
     * @return Map of RanK Data with key as Ticker
     */
    public Map<String, RankInfo>  getDataByTicker(String ticker) {

        Document document;
        Map<String, RankInfo> result = new HashMap<>();

        try {
            String targetNasdaqURL = MB_NASDAQ_STOCK_URI + ticker + MB_RATINGS_URI;
            String targetNYSEURL   = MB_NYSE_STOCK_URI + ticker + MB_RATINGS_URI;
            LOG.info(String.format("Ticker [%s], NASDAQ URL [%s] and NYSE URL [%s] ", ticker, targetNasdaqURL, targetNYSEURL));

            document = Jsoup.connect(targetNYSEURL).userAgent("Mozilla").maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            if(document == null) {
                LOG.debug("NYSE data is null, hitting NASDAQ");
                document = Jsoup.connect(targetNasdaqURL).userAgent("Mozilla").maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            }

            Elements rows = null;
            try {
                rows = document.getElementsByClass("ratingstable").first().select("tbody").select("tr");
            } catch (NullPointerException e) {
                LOG.warn("NPE while fetching data from NYSE URL. so calculating from NASDAQ URL.");
                document = Jsoup.connect(targetNasdaqURL).userAgent("Mozilla").maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
                rows = document.getElementsByClass("ratingstable").first().select("tbody").select("tr");
            }

            for(Element row : rows) {

                Elements columns = row.select("td");
                //LOG.debug("Current columns are - " + columns.toString());
                setTickerValues(columns, result);
            }

        } catch(Exception e) {
            LOG.error(String.format("Error occurred for [%s] ", ticker), e);
        }

        result.forEach((k, v) -> System.out.println(String.format("For [%s], the research firm [%s] and Data is [%s]", ticker, k, v)));
        return result;
    }


    /**
     *
     * @param result
     * @param tickers
     * @param issuer
     * @return Returns Mpa of Ticker and having RankInfo with all values as UN except researcher as researcher name.
     */
    private Map<String, RankInfo> createBaseTickerData(Map<String, RankInfo> result, List<String> tickers, String issuer) {

        for(String ticker : tickers) {
            result.put(ticker, new RankInfo(ResearcherID.getNameByID(issuer)));
        }

        return result;
    }

    public String getTicker(String mbTickerData) {

        //LOG.debug("Ticker Data is: " + mbTickerData);
        String result = MB_TICKER_NA;

        try {
            result = mbTickerData.substring(mbTickerData.indexOf(Constants.SEPARATOR_OPEN_BRACE) + 1,
                    mbTickerData.indexOf(Constants.SEPARATOR_CLOSE_BRACE));
        } catch (Exception e) {
            LOG.error("Error occurred while parsing ticker string " + mbTickerData, e);
        }

        return result.toLowerCase();
    }

    private void setValues(Elements columns, String ticker, Map<String, RankInfo> resultMap) {

        RankInfo rankInfo = new RankInfo();

        rankInfo.setDateReported(columns.get(0).text());
        rankInfo.setResearchFirm(columns.get(1).text());

        rankInfo.setAction(columns.get(2).text());
        rankInfo.setRatingVal(columns.get(2).text());

        rankInfo.setRank(columns.get(4).text());
        rankInfo.setSpeculatedPrice(columns.get(5).text());

        resultMap.put(ticker, Constants.UNAVAILABLE.equalsIgnoreCase(resultMap.get(ticker).getRank()) ? rankInfo : resultMap.get(ticker));
    }

    private void setTickerValues(Elements columns, Map<String, RankInfo> resultMap) {

        RankInfo rankInfo = new RankInfo();

        rankInfo.setDateReported(columns.get(0).text());
        rankInfo.setResearchFirm(columns.get(1).text());

        rankInfo.setAction(columns.get(2).text());
        rankInfo.setRatingVal(columns.get(2).text());

        rankInfo.setRank(columns.get(3).text());
        rankInfo.setSpeculatedPrice(columns.get(4).text());

        resultMap.put(columns.get(1).text(), resultMap.get(columns.get(1).text()) == null ? rankInfo : resultMap.get(columns.get(1).text()));
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

    /**
     *
     * @param listOfTickerRankMap
     * @return Map of Tickers and respective rank data. If rank data does not found, sets to UN.
     */
    public Map<String, List<RankInfo>> mergeMapData(List<Map<String, RankInfo>> listOfTickerRankMap) {

        //Get all Tickers
        Set<String> tickers = getAllKeys(listOfTickerRankMap);

        //Result for Ticker and List of Rank Data.
        Map<String, List<RankInfo>> resultMap = new HashMap<>();

        for(Map<String, RankInfo> tickerRankMap : listOfTickerRankMap) {

            for(String ticker : tickers) {

                //Handles null data scenario
                if(tickerRankMap.get(ticker) == null) {
                    continue;
                }else {
                    List<RankInfo> rankInfoList = new ArrayList<>();

                    //Set only latest data
                    if(resultMap.get(ticker) == null) {
                        rankInfoList.add(tickerRankMap.get(ticker));

                    } else {
                        rankInfoList = resultMap.get(ticker);
                        rankInfoList.add(tickerRankMap.get(ticker));
                    }

                    resultMap.put(ticker, rankInfoList);
                }
            }
        }

        resultMap.forEach((k, v) -> System.out.println("Size [" + v.size() + "] Key [" + k + "] with Value [" + v + "]"));
        return resultMap;
    }

    /**
     *
     * @param maps
     * @return Ticker's set - Only got available from a particular researcher
     */
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

        //No need to write if no data found for the given ticker
        if(allFetchedData == null || (allFetchedData != null && allFetchedData.size() == 0)) {
            LOG.warn(String.format("For [%s] , no data found.", ticker));
            return;
        }

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
            LOG.info("List data before sorting for ticker " + ticker);
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
            LOG.error("Error occurred while sorting the data " + e);
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


//    private void shiftColumns(String inputFile, String outputFile) {
//
//        int sheetIndex = 0;
//        int columnIndex = 1;
//
//        ExcelOpener op = new ExcelOpener(inputFile, outputFile);
//        try {
//            op.open();
//        } catch (InvalidFormatException e) {
//            LOG.error("InvalidFormatException while Shifting Column ", e);
//        } catch (IOException e) {
//            LOG.error("IOException while Shifting Column ", e);
//        }
//
//        op.insertNewColumnBefore(sheetIndex, columnIndex);
//
//        op.close();
//    }

    private void insertNewRankColumn(String targetFile, List<RankInfo> allFetchedData) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            LOG.info(String.format("In File [%s], Row count is [%s]", targetFile, rowCount));

            //START: Update date
            Row firstRow   = sheet.getRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(getRankDate());
            //END: Update date

            for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                Row currentRow = sheet.getRow(rowIndex);
                if(currentRow == null) {
                    currentRow = sheet.createRow(rowIndex);
                    Cell researchFirmCell = currentRow.createCell(0);
                    String firm = ResearcherID.getNameByID(researchFirmOrderList.get(rowIndex - 1));
                    researchFirmCell.setCellValue(firm);
                }

                Cell currentCell         = currentRow.createCell(1);
                RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);
                String currentCellStr    = getRankStrByRankInfo(currentRankInfo);

                CellStyle style = workbook.createCellStyle();
                if(currentRankInfo.getRatingVal() == RankInfo.RatingState.RED.getState()) {

                    style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    currentCell.setCellStyle(style);

                } else if(currentRankInfo.getRatingVal() == RankInfo.RatingState.GREEN.getState()) {
                    style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    currentCell.setCellStyle(style);
                }

                LOG.info(String.format("For Symbol [%s] , CurrentCellStr [%s] ", currentRow.getCell(0), currentCellStr));

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

                String firm = ResearcherID.getNameByID(researchFirmOrderList.get(rowIndex - 1));
                researchFirmCell.setCellValue(firm);
                RankInfo currentRankInfo = getFirmFromRankInfo(firm, allFetchedData);
                //In case no data for current ticker by current firm in given date range
                if(currentRankInfo == null)
                    rankCell.setCellValue(Constants.UNAVAILABLE);
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

        if(Constants.UNAVAILABLE.equalsIgnoreCase(rankInfo.getRank()))
            result.append(Constants.UNAVAILABLE);

        else
            result.append(rankInfo.getDateReported())
                .append(Constants.SEPARATOR_COMMA)
                .append(rankInfo.getAction())
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

    public enum ResearcherID {

        NEEDHAM("Needham & Company LLC", "15587"),
        CS("Credit Suisse Group", "12026"),
        MORNING("Morningstar", "3308"),
        Z("Zacks Investment Research", "572"),
        GS("Goldman Sachs Group", "8"),
        MORGAN("Morgan Stanley", "71"),
        KC("KeyCorp", "5573"),
        ARGUS("Argus", "273"),
        PIPER("Piper Jaffray Companies", "56"),
        VE("ValuEngine", "28687"),
        SIDOTI("Sidoti", "166"),
        JEFFERIES("Jefferies Financial Group", "149"),
        STIFEL("Stifel Nicolaus", "51"),
        VETR("Vetr", "18847"),
        FUNDAMENTAL("Fundamental Research", "7001"),
        JPMORGAN("JPMorgan Chase & Co.", "43"),
        RBC("Royal Bank of Canada", "86"),
        CITI("Citigroup", "1"),
        BOFA("Bank of America", "17831"),
        WFC("Wells Fargo & Co", "21"),
        BIDASK("BidaskClub", "29019"),
        ROTH("Roth Capital", "275"),
        JANNEYSCOTT("Janney Montgomery Scott", "329"),   //for curo
        BLAIR("William Blair", "213"),                   //for curo
        STEPHENS("Stephens", "132"),                     //for curo
        BARCLAYS("Barclays", "4"),
        BENCHMARK("Benchmark", "739"),
        EVERCORE("Evercore ISI", "1745"),
        OPPENHEIMER("Oppenheimer Funds", "52"),
        GUGGENHEIM("Guggenheim", "4701"),
        SUSQUEHANNA("Susquehanna Bancshares", "1069"),
        NOMURA("Nomura", "25862")
        ;

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getIDByName(String name) {
            return id;
        }

        ResearcherID(String name, String id) {
            this.id = id;
            this.name = name;
        }

        private static final Map<String, String> NAME_ID_MAP = new HashMap<>();
        private static final Map<String, String> ID_NAME_MAP = new HashMap<>();

        static {

            for(ResearcherID researcherID : ResearcherID.values()) {
                NAME_ID_MAP.put(researcherID.getId(), researcherID.getName());
                ID_NAME_MAP.put(researcherID.getId(), researcherID.getName());
            }
        }

        public static String getNameByID(String ID) {
            return NAME_ID_MAP.get(ID);
        }
    }

    /**
     * 1. Backup file
     * 2. Shift columns
     * 3. Insert Row
     * 4. Delete temp file
     * @param
     */
    public void updateXL(String ticker, Map<String, RankInfo> allFetchedData, String outLOC, String backLOC) {

        //No need to write if no data found for the given ticker
        if(allFetchedData == null || (allFetchedData != null && allFetchedData.size() == 0)) {
            LOG.warn(String.format("For [%s] , no data found.", ticker));
            return;
        }

        String fileName = ticker.toUpperCase() + Constants.EXTENSION_XLSX;

        String fileWithLOC       = outLOC + File.separator + fileName;
        File targetFileHandler   = new File(fileWithLOC);

        String backupFileWithLOC = backLOC + File.separator + CommonUtils.getCurrentTime() + Constants.SEPARATOR_UNDERSCORE + fileName;
        File backupFileHandler   = new File(backupFileWithLOC);

        //File tempFileHandler = new File(outLOC + File.separator + Constants.TEMP_PREFIX + fileName);

        //Do backup
        if(targetFileHandler.exists()) {
            try {
                Files.copy(targetFileHandler.toPath(), backupFileHandler.toPath());
                //targetFileHandler.renameTo(tempFileHandler);
            } catch (IOException e) {
                LOG.error("IOException while BackingUp File  " + targetFileHandler, e);
            }
        }

        updateXLData(fileWithLOC, allFetchedData);
    }

    private void updateXLData(String targetFile, Map<String, RankInfo> allFetchedData) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            LOG.info(String.format("In File [%s], Row count is [%s]", targetFile, rowCount));

            for(int rowIndex=1; rowIndex<=rowCount; rowIndex++){

                Row currentRow           = sheet.getRow(rowIndex);
                Cell firm                = currentRow.getCell(0);
                Cell currentCell         = currentRow.getCell(1);
                RankInfo currentRankInfo = allFetchedData.get(firm.toString());

                if(currentRankInfo != null && Constants.UNAVAILABLE.equals(currentCell.toString())) {

                    String currentCellStr    = getRankStrByRankInfo(currentRankInfo);

                    if(currentRankInfo.getRatingVal() == RankInfo.RatingState.RED.getState()) {
                        CellStyle style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        currentCell.setCellStyle(style);

                    } else if(currentRankInfo.getRatingVal() == RankInfo.RatingState.GREEN.getState()) {
                        CellStyle style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        currentCell.setCellStyle(style);
                    }

                    LOG.info(String.format("For Symbol [%s] , CurrentCellStr [%s] ", currentRow.getCell(0), currentCellStr));

                    currentCell.setCellValue(currentCellStr);
                } else {
                    LOG.debug(String.format("For firm [%s], data is already available [%s] or fetched data [%s] is null", firm, currentCell, currentRankInfo));
                }
            }

            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
            LOG.error("Error while Creating New Sheet  " + targetFile, ex);
        }
    }

}
