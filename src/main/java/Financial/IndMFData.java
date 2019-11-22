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

    public static void main(String[] args) {

//        List<String> URLs = Constants.smallCapURLList;
//        URLs.forEach(System.out::println);

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;

        new IndMFData().processSmallCapFund(Constants.OUT_FILE_IND_MF,"SmallCap", 0);
    }


    private void processSmallCapFund(String outFile, String sheetName, int sheetIndex) {

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        IndMFProcessor indMFProcessor = new IndMFProcessor();
        indMFProcessor.init();

        List<RankInfo> currentRankingData = new ArrayList<>();

        for(Map.Entry<String, String> entry : Constants.smallCapMap.entrySet()) {
            currentRankingData.add(indMFProcessor.getMFData(entry.getKey(), entry.getValue()));
        }

        excelProp.setSheetName(sheetName);
        excelProp.setSheetIndex(sheetIndex);

        List<String> attributes = new ArrayList<>();
        attributes.add("nav");
        new ExcelAction().writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, true, attributes);
    }

}
