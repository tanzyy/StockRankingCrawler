package Financial;

import Utils.CommonUtils;
import Utils.Constants;
import Utils.ExcelAction;
import Utils.ExcelActionMF;
import VO.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndMFData2 {

    private static  String OUT_FILE_LOC     = "/Reports/IndMFData";
    private static  String BACK_FILE_LOC    = "/Backup";

    private static  String targetDataExtract;
    private static  String targetSheetIndex;
    private static  String baseURL;
    private static  String targetDataURI;
    //private static  String workBookName;
    //private static  Map<String, String> targetSchemesMap;

    private DataAttributes dataAttributes;
    private ExcelProp      excelProp;
    private DataSelection  dataSelection;
    private Map<String, Map<String, String>> targetMFTypeAndSchemesMap = new HashMap<>();

    public static void main(String[] args) {

        new IndMFData2().processFund();

    }

    private void setEconomicTimeData() {

        targetMFTypeAndSchemesMap.put(Constants.OUT_FILE_IND_MF_SMALL_CAP, Constants.ECONOMIC_TIMES_SCHEME_ID_MAP_SML);
        targetMFTypeAndSchemesMap.put(Constants.OUT_FILE_IND_MF_MULTI_CAP, Constants.ECONOMIC_TIMES_SCHEME_ID_MAP_MULTI);
    }

    private void processFund() {

        prepareData();

        for(Map.Entry<String, Map<String, String>> targetMFTypeAndSchemes : targetMFTypeAndSchemesMap.entrySet()) {

            dataAttributes.setSymbolSchemeMap(targetMFTypeAndSchemes.getValue());
            excelProp.setWorkBookName(targetMFTypeAndSchemes.getKey());

            List<MFInfo> mfInfoList = new ArrayList<>();
            for(Map.Entry<String, String> entry : dataAttributes.getSymbolSchemeMap().entrySet()) {

                dataSelection.setSymbol(entry.getKey());

                List<String> urlAttributes = new ArrayList<>();
                //TODO Make it generic
                String[] fromMap = entry.getValue().split(Constants.COMMA);
                urlAttributes.add(fromMap[0]);
                urlAttributes.add(dataAttributes.getTargetDataURI());
                urlAttributes.add(fromMap[1]);

                dataSelection.setUrlAttributes(urlAttributes);
                dataSelection.setTargetURL();
                dataSelection.setQueries(dataAttributes.getDataExtractionList());

                //Get MF Data
                IndMFProcessor2 indMFProcessor2 = new IndMFProcessor2();
                indMFProcessor2.init();
                MFInfo mfinfo = indMFProcessor2.getMFData(dataSelection);
                mfInfoList.add(mfinfo);

                System.out.println(mfinfo);

            }

            int count = 0;
            for(String targetData : dataAttributes.getDataExtractionList()) {

                excelProp.setSheetName(targetData);
                excelProp.setSheetIndex(Integer.valueOf(dataAttributes.getTargetDataSheetIndexList().get(count)));
                count++;

                new ExcelActionMF().writeToOneXL(dataAttributes, excelProp, mfInfoList);
            }

        }
    }

    private void prepareData() {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;

        //Get Data for economic times
        economicTimeData();

        dataAttributes = new DataAttributes();
        dataAttributes.setDataExtraction(targetDataExtract);
        dataAttributes.setTargetDataSheetIndexList(targetSheetIndex);
        dataAttributes.setMF(true);
        dataAttributes.setHighlightChange(true);
        dataAttributes.setNumeric(false);
        dataAttributes.setBaseURL(baseURL);
        //TODO Check for null
        dataAttributes.setTargetDataURI(targetDataURI);
        //dataAttributes.setSymbolSchemeMap(targetSchemesMap);

        excelProp = new ExcelProp();
        excelProp.setFileLoc(OUT_FILE_LOC);
        excelProp.setBackFileLoc(BACK_FILE_LOC);

        //TODO Make it parallel : Same data input for different workbook
        //excelProp.setWorkBookName(workBookName);

        dataSelection = new DataSelection();
        dataSelection.setBaseUrl(dataAttributes.getBaseURL());

        setEconomicTimeData();
    }


    /**
     * Returns data set to extract and save in excel
     * Basically, the data to extract from target page and respective sheet index.
     */
    private static void economicTimeData() {

        targetDataExtract =
                          MFInfo.MF_PROPERTY.benchmark.toString()                   + Constants.COMMA
                        + MFInfo.MF_PROPERTY.aum.toString()                         + Constants.COMMA
                        + MFInfo.MF_PROPERTY.expenseRatio.toString()                + Constants.COMMA
                        + MFInfo.MF_PROPERTY.manager.toString()                     + Constants.COMMA
                        + MFInfo.MF_PROPERTY.peRatio.toString()                     + Constants.COMMA
                        + MFInfo.MF_PROPERTY.pbRatio.toString()                     + Constants.COMMA
                        + MFInfo.MF_PROPERTY.returns.toString()                     + Constants.COMMA
                        + MFInfo.MF_PROPERTY.holdingSectorTop5.toString()           + Constants.COMMA
                        + MFInfo.MF_PROPERTY.holdingMarketCap.toString();

        //+ MFInfo.MF_PROPERTY.holdingTotalStocks.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentStockTop5.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentStockTop10.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentSectorTop3.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentSectorTop5.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingTotalSector.toString()          + Constants.COMMA

        targetSheetIndex = "1,2,3,4,5,6,7,8,9";

        baseURL          = Constants.url_economictimes;

        targetDataURI    = Constants.MF_IND_URI_ECONOMICTIMES.FACTS.getURI();


//        //TODO will be variable from type of MF.
//        targetSchemesMap = Constants.ECONOMIC_TIMES_SCHEME_ID_MAP_SML;
//        workBookName     = Constants.OUT_FILE_IND_MF_SMALL_CAP;
    }


    //Working - 2
//    public static void main(String[] args) {
//
//        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
//        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;
//
//
//        String targetDataExtract =
//                  MFInfo.MF_PROPERTY.benchmark.toString()                   + Constants.COMMA
//                + MFInfo.MF_PROPERTY.aum.toString()                         + Constants.COMMA
//                + MFInfo.MF_PROPERTY.expenseRatio.toString()                + Constants.COMMA
//                + MFInfo.MF_PROPERTY.manager.toString()                     + Constants.COMMA
//                + MFInfo.MF_PROPERTY.peRatio.toString()                     + Constants.COMMA
//                + MFInfo.MF_PROPERTY.pbRatio.toString()                     + Constants.COMMA
//                + MFInfo.MF_PROPERTY.returns.toString()                     + Constants.COMMA
//                + MFInfo.MF_PROPERTY.holdingSectorTop5.toString()        + Constants.COMMA
//                + MFInfo.MF_PROPERTY.holdingMarketCap.toString();
//
//        //+ MFInfo.MF_PROPERTY.holdingTotalStocks.toString()          + Constants.COMMA
//        //+ MFInfo.MF_PROPERTY.holdingPercentStockTop5.toString()          + Constants.COMMA
//        //+ MFInfo.MF_PROPERTY.holdingPercentStockTop10.toString()          + Constants.COMMA
//        //+ MFInfo.MF_PROPERTY.holdingPercentSectorTop3.toString()          + Constants.COMMA
//        //+ MFInfo.MF_PROPERTY.holdingPercentSectorTop5.toString()          + Constants.COMMA
//        //+ MFInfo.MF_PROPERTY.holdingTotalSector.toString()          + Constants.COMMA
//
//        String targetSheetIndex = "1,2,3,4,5,6,7,8,9";
//
//        DataAttributes dataAttributes = new DataAttributes();
//        dataAttributes.setDataExtraction(targetDataExtract);
//        dataAttributes.setTargetDataSheetIndexList(targetSheetIndex);
//        dataAttributes.setMF(true);
//        dataAttributes.setHighlightChange(true);
//        dataAttributes.setNumeric(false);
//        dataAttributes.setBaseURL(Constants.url_economictimes);
//        dataAttributes.setTargetDataURI(Constants.MF_IND_URI_ECONOMICTIMES.FACTS.getURI());
//        dataAttributes.setSymbolSchemeMap(Constants.ECONOMIC_TIMES_SCHEME_ID_MAP);
//
//
//        ExcelProp excelProp = new ExcelProp();
//        excelProp.setFileLoc(OUT_FILE_LOC);
//        excelProp.setBackFileLoc(BACK_FILE_LOC);
//        excelProp.setWorkBookName(Constants.OUT_FILE_IND_MF_SMALL_CAP);
//
//
//
//        DataSelection dataSelection = new DataSelection();
//        dataSelection.setBaseUrl(dataAttributes.getBaseURL());
//
//        List<MFInfo> mfInfoList = new ArrayList<>();
//        for(Map.Entry<String, String> entry : dataAttributes.getSymbolSchemeMap().entrySet()) {
//
//            dataSelection.setSymbol(entry.getKey());
//
//            List<String> urlAttributes = new ArrayList<>();
//            //TODO Make it generic
//            String[] fromMap = entry.getValue().split(Constants.COMMA);
//            urlAttributes.add(fromMap[0]);
//            urlAttributes.add(dataAttributes.getTargetDataURI());
//            urlAttributes.add(fromMap[1]);
//
//            dataSelection.setUrlAttributes(urlAttributes);
//            dataSelection.setTargetURL();
//            dataSelection.setQueries(dataAttributes.getDataExtractionList());
//
//            //Get MF Data
//            IndMFProcessor2 indMFProcessor2 = new IndMFProcessor2();
//            indMFProcessor2.init();
//            MFInfo mfinfo = indMFProcessor2.getMFDataFromEconomicTimes(dataSelection);
//            mfInfoList.add(mfinfo);
//
//            System.out.println(mfinfo);
//
//        }
//
//        int count = 0;
//        for(String targetData : dataAttributes.getDataExtractionList()) {
//
//            excelProp.setSheetName(targetData);
//            excelProp.setSheetIndex(Integer.valueOf(dataAttributes.getTargetDataSheetIndexList().get(count)));
//            count++;
//
//            new ExcelActionMF().writeToOneXL(dataAttributes, excelProp, mfInfoList);
//        }
//    }

    //Working - 1
//    public static void main(String[] args) {
//
//        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
//        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;
//
//
//        String targetDataExtract = MFInfo.MF_PROPERTY.benchmark.toString() + Constants.COMMA
//                                   + MFInfo.MF_PROPERTY.expenseRatio.toString();
//
//        DataAttributes dataAttributes = new DataAttributes();
//        dataAttributes.setDataExtraction(targetDataExtract);
//        dataAttributes.setMF(true);
//        dataAttributes.setHighlightChange(true);
//        dataAttributes.setNumeric(false);
//        dataAttributes.setBaseURL(Constants.url_economictimes);
//        dataAttributes.setTargetDataURI(Constants.MF_IND_URI_ECONOMICTIMES.FACTS.getURI());
//        dataAttributes.setSymbolSchemeMap(Constants.ECONOMIC_TIMES_SCHEME_ID_MAP);
//
//
//
//        DataSelection dataSelection = new DataSelection();
//        dataSelection.setBaseUrl(dataAttributes.getBaseURL());
//
//        for(Map.Entry<String, String> entry : dataAttributes.getSymbolSchemeMap().entrySet()) {
//
//            dataSelection.setSymbol(entry.getKey());
//
//            List<String> urlAttributes = new ArrayList<>();
//            //TODO Make it generic
//            String[] fromMap = entry.getValue().split(Constants.COMMA);
//            urlAttributes.add(fromMap[0]);
//            urlAttributes.add(dataAttributes.getTargetDataURI());
//            urlAttributes.add(fromMap[1]);
//
//            dataSelection.setUrlAttributes(urlAttributes);
//            dataSelection.setTargetURL();
//            dataSelection.setQueries(dataAttributes.getDataExtractionList());
//
//            //Get MF Data
//            IndMFProcessor2 indMFProcessor2 = new IndMFProcessor2();
//            indMFProcessor2.init();
//            MFInfo mfinfo = indMFProcessor2.getMFDataFromEconomicTimes(dataSelection);
//
//            System.out.println(mfinfo);
//
//        }
//
//    }

}
