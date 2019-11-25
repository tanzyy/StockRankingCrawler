package Financial;

import Utils.Constants;
import Utils.ExcelAction;
import VO.ExcelProp;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndMFDataExperiments {

    private static  String OUT_FILE_LOC     = "/Reports/IndMFData";
    private static  String BACK_FILE_LOC    = "/Backup";

    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;

        new IndMFDataExperiments().processFund(Constants.TEST_OUT_FILE_IND_MF,"52Week", 0);
    }


    private void processFund(String outFile, String sheetName, int sheetIndex) {

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        IndMFProcessorExperiment indMFProcessor = new IndMFProcessorExperiment();
        indMFProcessor.init();

        List<RankInfo> currentRankingData = new ArrayList<>();

        if("SmallCap".equalsIgnoreCase(sheetName)) {
            for(Map.Entry<String, String> entry : Constants.smallCapMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getTestMFData(entry.getKey(), entry.getValue()));
            }
        } else if("MultiCap".equalsIgnoreCase(sheetName)) {
            for(Map.Entry<String, String> entry : Constants.multiCapMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getTestMFData(entry.getKey(), entry.getValue()));
            }
        } else {
            for(Map.Entry<String, String> entry : Constants.testMap.entrySet()) {
                currentRankingData.add(indMFProcessor.getOtherMFDataFromEconomicTimes(entry.getKey(), entry.getValue()));
            }
        }

//        excelProp.setSheetName(sheetName);
//        excelProp.setSheetIndex(sheetIndex);
//
//        List<String> attributes = new ArrayList<>();
//        attributes.add("nav");
//        new ExcelAction().writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, false, attributes);
    }

}
