package Financial;

import Utils.Constants;
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
    private static  boolean isSinglePageMultiQuery;


    private DataAttributes dataAttributes;
    private ExcelProp      excelProp;
    private DataSelection  dataSelection;

    private Map<String, Map<String, String>> targetMFTypeAndSchemesMap = new HashMap<>();

    public static void main(String[] args) {


//        IndMFData2.setEconomicTimeData();
//
//        IndMFData2 indMFData2 = new IndMFData2();
//        indMFData2.setEconomicTimeMFTypeAndScheme();
//
//        indMFData2.processFund();

        IndMFData2.setInvestingData();
        IndMFData2 indMFData2 = new IndMFData2();
        indMFData2.setInvestingMFTypeAndScheme();
        indMFData2.processFund();

    }

    private void processFund() {

        prepareData();

        //Loop for each type of MF and respective schemes
        for(Map.Entry<String, Map<String, String>> targetMFTypeAndSchemes : targetMFTypeAndSchemesMap.entrySet()) {

            dataAttributes.setSymbolSchemeMap(targetMFTypeAndSchemes.getValue());
            excelProp.setWorkBookName(targetMFTypeAndSchemes.getKey());

            MFInfo mfinfo           = null;
            List<MFInfo> mfInfoList = new ArrayList<>();

            if(isSinglePageMultiQuery) {

                dataSelection.setMfPropertiesToExtract(dataAttributes.getDataExtractionList());
                dataSelection.setTargetURL();
                IndMFProcessor2 indMFProcessor2 = new IndMFProcessor2();
                indMFProcessor2.init();
                mfInfoList = indMFProcessor2.getSinglePageMultiQueryMFData(dataSelection, dataAttributes);

            } else {

                for(Map.Entry<String, String> entry : dataAttributes.getSymbolSchemeMap().entrySet()) {

                    dataSelection.setSymbol(entry.getKey());

                    if(entry.getValue() != null && !entry.getValue().isEmpty() && entry.getValue().length() > 0) {

                        List<String> urlAttributes = new ArrayList<>();
                        //TODO Make it generic
                        String[] fromMap = entry.getValue().split(Constants.COMMA);
                        urlAttributes.add(fromMap[0]);

                        //Check if separate URI needed
                        if(dataAttributes.getTargetDataURI() != null)
                            urlAttributes.add(dataAttributes.getTargetDataURI());

                        if(fromMap.length > 1)
                            urlAttributes.add(fromMap[1]);

                        dataSelection.setUrlAttributes(urlAttributes);

                        //Prepare final URL by combining URI and scheme id.
                        dataSelection.setTargetURL();

                    } else {
                        dataSelection.setTargetURL();
                    }

                    dataSelection.setMfPropertiesToExtract(dataAttributes.getDataExtractionList());

                    //Get MF Data
                    IndMFProcessor2 indMFProcessor2 = new IndMFProcessor2();
                    indMFProcessor2.init();
                    mfinfo = indMFProcessor2.getMFData(dataSelection);
                    mfInfoList.add(mfinfo);
                }

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

        dataAttributes = new DataAttributes();
        dataAttributes.setDataExtraction(targetDataExtract);
        dataAttributes.setTargetDataSheetIndexList(targetSheetIndex);
        dataAttributes.setMF(true);
        dataAttributes.setHighlightChange(true);
        dataAttributes.setNumeric(false);
        dataAttributes.setBaseURL(baseURL);
        dataAttributes.setTargetDataURI(targetDataURI);

        excelProp = new ExcelProp();
        excelProp.setFileLoc(OUT_FILE_LOC);
        excelProp.setBackFileLoc(BACK_FILE_LOC);

        dataSelection = new DataSelection();
        dataSelection.setBaseUrl(dataAttributes.getBaseURL());
        dataSelection.setSinglePageMultiQuery(isSinglePageMultiQuery);
    }


    /**
     * Returns data set to extract and save in excel
     * Basically, the data to extract from target page and respective sheet index.
     */
    private static void setEconomicTimeData() {

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
    }

    private void setEconomicTimeMFTypeAndScheme() {

        targetMFTypeAndSchemesMap.put(Constants.OUT_FILE_IND_MF_SMALL_CAP, Constants.ECONOMIC_TIMES_SCHEME_ID_MAP_SML);
        targetMFTypeAndSchemesMap.put(Constants.OUT_FILE_IND_MF_MULTI_CAP, Constants.ECONOMIC_TIMES_SCHEME_ID_MAP_MULTI);
    }

    private static void setInvestingData() {

        targetDataExtract = MFInfo.MF_PROPERTY.nav.toString();
        targetSheetIndex  = "0";

        baseURL          = Constants.url_investing;
        targetDataURI    = Constants.MF_IND_URI_INVESTING.NAV.getURI();
        isSinglePageMultiQuery = true;
    }

    private void setInvestingMFTypeAndScheme() {

        targetMFTypeAndSchemesMap.put(Constants.OUT_FILE_IND_MF_INDEX, Constants.INVESTING_SCHEME_ID_MAP);
    }

}
