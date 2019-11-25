package Financial;

import Utils.Constants;
import Utils.ExcelAction;
import VO.ExcelProp;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndMFData {

    private static  String OUT_FILE_LOC     = "/Reports/IndMFData";
    private static  String BACK_FILE_LOC    = "/Backup";

    private enum DataTypeToExtract {
        DAILY_NAV,
        OTHER_ATTRIBUTES,
        FIFTY_TWO_WEEK
    };

    private static DataTypeToExtract dataToExtract = DataTypeToExtract.FIFTY_TWO_WEEK;

    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;


        if(DataTypeToExtract.DAILY_NAV.equals(dataToExtract)) {
            new IndMFData().processFund(Constants.OUT_FILE_IND_MF_SMALL_CAP,"SmallCap", 0);
            //new IndMFData().processFund(Constants.OUT_FILE_IND_MF,"MultiCap", 1);

        } else if(DataTypeToExtract.OTHER_ATTRIBUTES.equals(dataToExtract)) {
            new IndMFData().processFundForOtherAttributes(Constants.OUT_FILE_IND_MF_SMALL_CAP);

        } else if(DataTypeToExtract.FIFTY_TWO_WEEK.equals(dataToExtract)) {
            new IndMFData().processFiftyTwoWeek(Constants.OUT_FILE_IND_MF_SMALL_CAP,"52Week", 7);
        }
    }

    private void processFundForOtherAttributes(String outFile) {

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        IndMFProcessor indMFProcessor = new IndMFProcessor();
        indMFProcessor.init();

        List<RankInfo> currentRankingData = new ArrayList<>();

        for(Map.Entry<String, String> entry : Constants.smallCapMapOther.entrySet()) {
            currentRankingData.add(indMFProcessor.getOtherMFDataFromEconomicTimes(entry.getKey(), entry.getValue()));
        }

        List<String> otherAttributes = new ArrayList();
        otherAttributes.add("underlyingIndex");
        otherAttributes.add("aum");
        otherAttributes.add("expenseRatio");
        otherAttributes.add("manager");
        otherAttributes.add("peRatio");
        otherAttributes.add("pbRatio");

        int sheetIndex = 1;
        for(String attribute : otherAttributes) {
            excelProp.setSheetName(attribute.toUpperCase());
            excelProp.setSheetIndex(sheetIndex);

            List<String> attributes = new ArrayList<>();
            attributes.add(attribute);
            new ExcelAction().writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, false, attributes, false);

            sheetIndex++;
        }
    }

    private void processFund(String outFile, String sheetName, int sheetIndex) {

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        IndMFProcessor indMFProcessor = new IndMFProcessor();
        indMFProcessor.init();

        List<RankInfo> currentRankingData = new ArrayList<>();

        if("SmallCap".equalsIgnoreCase(sheetName)) {
            for(Map.Entry<String, String> entry : Constants.smallCapMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getMFDataFromEconomicTimes(entry.getKey(), entry.getValue()));
            }
        } else if("MultiCap".equalsIgnoreCase(sheetName)) {
            for(Map.Entry<String, String> entry : Constants.multiCapMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getMFDataFromEconomicTimes(entry.getKey(), entry.getValue()));
            }
        }

        excelProp.setSheetName(sheetName);
        excelProp.setSheetIndex(sheetIndex);

        List<String> attributes = new ArrayList<>();
        attributes.add("nav");
        new ExcelAction().writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, false, attributes, true);
    }


    private void processFiftyTwoWeek(String outFile, String sheetName, int sheetIndex) {

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        IndMFProcessor indMFProcessor = new IndMFProcessor();
        indMFProcessor.init();

        List<RankInfo> currentRankingData = new ArrayList<>();

        for(Map.Entry<String, String> entry : Constants.smallCapFiftyTwoWeek.entrySet()) {
            currentRankingData.add(indMFProcessor.getMFDataFiftyTwoWeek(entry.getKey(), entry.getValue()));
        }

        excelProp.setSheetName(sheetName);
        excelProp.setSheetIndex(sheetIndex);

        List<String> attributes = new ArrayList<>();
        attributes.add("fiftyTwoWeek");
        new ExcelAction().writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, false, attributes, false);
    }

}
