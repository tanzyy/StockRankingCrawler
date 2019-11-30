package Financial;

import Utils.CommonUtils;
import Utils.Constants;
import Utils.ExcelAction;
import Utils.ExcelActionMF;
import VO.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndMFData2 {

    /**
     * TODO Make it in property file
     */
    private static DataTypeToExtract dataToExtract = DataTypeToExtract.DAILY_NAV;



    private static  String OUT_FILE_LOC     = "/Reports/IndMFData";
    private static  String BACK_FILE_LOC    = "/Backup";

    private enum DataTypeToExtract {
        DAILY_NAV,
        OTHER_ATTRIBUTES,
        FIFTY_TWO_WEEK
    };


    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;


        String targetDataExtract =
                  MFInfo.MF_PROPERTY.benchmark.toString()                   + Constants.COMMA
                + MFInfo.MF_PROPERTY.aum.toString()                         + Constants.COMMA
                + MFInfo.MF_PROPERTY.expenseRatio.toString()                + Constants.COMMA
                + MFInfo.MF_PROPERTY.manager.toString()                     + Constants.COMMA
                + MFInfo.MF_PROPERTY.peRatio.toString()                     + Constants.COMMA
                + MFInfo.MF_PROPERTY.pbRatio.toString()                     + Constants.COMMA
                + MFInfo.MF_PROPERTY.returns.toString()                     + Constants.COMMA
                + MFInfo.MF_PROPERTY.holdingSectorTop5.toString()        + Constants.COMMA
                + MFInfo.MF_PROPERTY.holdingMarketCap.toString();

        //+ MFInfo.MF_PROPERTY.holdingTotalStocks.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentStockTop5.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentStockTop10.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentSectorTop3.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingPercentSectorTop5.toString()          + Constants.COMMA
        //+ MFInfo.MF_PROPERTY.holdingTotalSector.toString()          + Constants.COMMA

        String targetSheetIndex = "1,2,3,4,5,6,7,8,9";

        DataAttributes dataAttributes = new DataAttributes();
        dataAttributes.setDataExtraction(targetDataExtract);
        dataAttributes.setTargetDataSheetIndexList(targetSheetIndex);
        dataAttributes.setMF(true);
        dataAttributes.setHighlightChange(true);
        dataAttributes.setNumeric(false);
        dataAttributes.setBaseURL(Constants.url_economictimes);
        dataAttributes.setTargetDataURI(Constants.MF_IND_URI_ECONOMICTIMES.FACTS.getURI());
        dataAttributes.setSymbolSchemeMap(Constants.ECONOMIC_TIMES_SCHEME_ID_MAP);


        ExcelProp excelProp = new ExcelProp();
        excelProp.setFileLoc(OUT_FILE_LOC);
        excelProp.setBackFileLoc(BACK_FILE_LOC);
        excelProp.setWorkBookName(Constants.OUT_FILE_IND_MF_SMALL_CAP);



        DataSelection dataSelection = new DataSelection();
        dataSelection.setBaseUrl(dataAttributes.getBaseURL());

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
            MFInfo mfinfo = indMFProcessor2.getMFDataFromEconomicTimes(dataSelection);
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

    private void processFunds(ExcelProp excelProp) {

        String targetDataExtract = MFInfo.MF_PROPERTY.nav.toString();
        DataAttributes dataAttributes = new DataAttributes();
        dataAttributes.setDataExtraction(targetDataExtract);
        dataAttributes.setMF(true);
        dataAttributes.setHighlightChange(true);
        dataAttributes.setNumeric(true);

        String targetDataURI = Constants.MF_IND_URI_ECONOMICTIMES.NAV.getURI();
        DataSelection dataSelection = new DataSelection();
        dataSelection.setBaseUrl(Constants.url_economictimes);

        for(Map.Entry<String, String> entry : Constants.ECONOMIC_TIMES_SCHEME_ID_MAP.entrySet()) {

            //TODO Set symbol
            dataSelection.setSymbol(entry.getKey());

            List<String> urlAttributes = new ArrayList<>();
            String[] fromMap = entry.getValue().split(Constants.COMMA);
            urlAttributes.add(fromMap[0]);
            urlAttributes.add(targetDataURI);
            urlAttributes.add(fromMap[1]);

            dataSelection.setUrlAttributes(urlAttributes);
            dataSelection.setTargetURL();

            //TODO make it generic
            dataSelection.setQueries(
                    CommonUtils.getListByCommaSeparatedString(
                            Constants.MF_IND_QUERY_ECONOMICTIMES.nav.toString()
                    )
            );

            //System.out.println("The target URL is " + dataSelection.getTargetURL());

            //Get MF Data
            IndMFProcessor2 indMFProcessor2 = new IndMFProcessor2();
            indMFProcessor2.init();
            MFInfo mfinfo = indMFProcessor2.getMFDataFromEconomicTimes(dataSelection);

            System.out.println(mfinfo);


            //getMFData(String url, DataAttributes dataAttributes)
        }

//        ExcelProp excelProp = new ExcelProp();
//        excelProp.setFileLoc(OUT_FILE_LOC);
//        excelProp.setBackFileLoc(BACK_FILE_LOC);
//        excelProp.setWorkBookName(Constants.OUT_FILE_IND_MF_SMALL_CAP_NEW);
//        excelProp.setSheetName("NAV");
//        excelProp.setSheetIndex(0);
//        new IndMFData2().processFunds(excelProp);

    }
}
