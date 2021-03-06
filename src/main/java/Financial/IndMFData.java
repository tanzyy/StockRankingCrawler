package Financial;

import Utils.Constants;
import Utils.ExcelAction;
import VO.ExcelProp;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndMFData {

    /**
     * TODO Make it in property file
     *
     * Other TODO
     * 1. Update processFund method and make NAV agnostic
     * 2. Error handling
     * 3. Make fund houses same for all category
     * 4. Email facility
     * 5. Explore AWS
     *
     * 6. Other attribute : Holdings Number, Sectors Number, Top 3 sector holdings, Top 5 sector holdings
     * 7. 1 month return, 3 month return, 6 month return
     * 8. Market Cap Exposure- Giant, Large. Medium, Small, Tiny
     * 8. Indexes NAV
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


        if(DataTypeToExtract.DAILY_NAV.equals(dataToExtract)) {
            new IndMFData().processFund(Constants.OUT_FILE_IND_MF_SMALL_CAP,"NAV", 0);
            new IndMFData().processFund(Constants.OUT_FILE_IND_MF_MULTI_CAP,"NAV", 0);
            new IndMFData().processFund(Constants.OUT_FILE_IND_MF_MID_CAP  ,"NAV", 0);

        } else if(DataTypeToExtract.OTHER_ATTRIBUTES.equals(dataToExtract)) {
            new IndMFData().processFundForOtherAttributes(Constants.OUT_FILE_IND_MF_SMALL_CAP);
            new IndMFData().processFundForOtherAttributes(Constants.OUT_FILE_IND_MF_MID_CAP);

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

        if(Constants.OUT_FILE_IND_MF_SMALL_CAP.equalsIgnoreCase(outFile)) {

            for(Map.Entry<String, String> entry : Constants.smallCapMapOther.entrySet()) {
                currentRankingData.add(indMFProcessor.getOtherMFDataFromEconomicTimes(entry.getKey(), entry.getValue()));
            }
        } else if(Constants.OUT_FILE_IND_MF_MID_CAP.equalsIgnoreCase(outFile)) {

            for(Map.Entry<String, String> entry : Constants.midCapOther.entrySet()) {
                currentRankingData.add(indMFProcessor.getOtherMFDataFromEconomicTimes(entry.getKey(), entry.getValue()));
            }
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

        if(Constants.OUT_FILE_IND_MF_SMALL_CAP.equalsIgnoreCase(outFile)) {

            for(Map.Entry<String, String> entry : Constants.smallCapMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getMFDataNAVFromEconomicTimes(entry.getKey(), entry.getValue()));
            }

        } else if(Constants.OUT_FILE_IND_MF_MULTI_CAP.equalsIgnoreCase(outFile)) {

            for(Map.Entry<String, String> entry : Constants.multiCapMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getMFDataNAVFromEconomicTimes(entry.getKey(), entry.getValue()));
            }
        } else if(Constants.OUT_FILE_IND_MF_MID_CAP.equalsIgnoreCase(outFile)) {

            for(Map.Entry<String, String> entry : Constants.midCapNAV.entrySet()) {
                currentRankingData.add(indMFProcessor.getMFDataNAVFromEconomicTimes(entry.getKey(), entry.getValue()));
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
