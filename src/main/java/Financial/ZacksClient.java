package Financial;

import Utils.Constants;
import VO.ExcelProp;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i852841 on 5/17/18.
 */
public class ZacksClient {

    private static  String OUT_FILE_LOC     = "/Reports/ZacksRank";
    private static  String BACK_FILE_LOC    = "/Backup";


    static boolean forReport = false;
    static boolean isETF     = false;

    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;

        if(isETF) {
            new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_SMALL, Constants.OUT_FILE_TANZY_ETF, "SmallCap", 0);
            new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_THEMATIC, Constants.OUT_FILE_TANZY_ETF, "Thematic", 1);
            new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_WATCH, Constants.OUT_FILE_TANZY_ETF, "Watch", 2);
        } else {
            if(forReport) {
//                new ZacksClient().processData(Constants.SYMBOLS_TANZY_REIT, Constants.OUT_FILE_TANZY_REIT);
//                new ZacksClient().processData(Constants.SYMBOLS_TANZY_WATCH2, Constants.OUT_FILE_TANZY_WATCH2);
                new ZacksClient().processData(Constants.SYMBOLS_D3L, Constants.OUT_FILE_D3L);
            }
            else {
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_INVESTED, Constants.OUT_FILE_TANZY_INVESTED);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_WATCH, Constants.OUT_FILE_TANZY_WATCH);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_TO_INVEST, Constants.OUT_FILE_TANZY_TO_INVEST);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_PENNY, Constants.OUT_FILE_TANZY_PENNY);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_GROWTH, Constants.OUT_FILE_TANZY_GROWTH);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_REPORT, Constants.OUT_FILE_TANZY_REPORT);

                new ZacksClient().processData(Constants.SYMBOLS_TANZY_REIT, Constants.OUT_FILE_TANZY_REIT);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_WATCH2, Constants.OUT_FILE_TANZY_WATCH2);
                new ZacksClient().processData(Constants.SYMBOLS_D3L, Constants.OUT_FILE_D3L);

                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_SMALL, Constants.OUT_FILE_TANZY_ETF, "SmallCap", 0);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_THEMATIC, Constants.OUT_FILE_TANZY_ETF, "Thematic", 1);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_WATCH, Constants.OUT_FILE_TANZY_ETF, "Watch", 2);
            }
        }
    }

    private void processData(String symbols, String outFile) {

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksRatings.getData(symbol));
        }

        zacksRatings.writeToXL(outFile, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC);
    }

    private void processETFData(String symbols, String outFile, String sheetName, int sheetIndex) {

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksRatings.getETFData(symbol));
        }

        excelProp.setSheetName(sheetName);
        excelProp.setSheetIndex(sheetIndex);

        zacksRatings.writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC);
    }
}
