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


    static boolean firstTime = false;
    static boolean isOld     = false;
    static boolean isETF     = false;

    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;
        BACK_FILE_LOC = OUT_FILE_LOC + BACK_FILE_LOC;

        if(isETF) {
            new ZacksClient().processETFData(Constants.SYMBOLS_NEW, Constants.OUT_FILE_TANZY_ETF, "Indexes", 4);

        } else {
            if(firstTime) {
                new ZacksClient().processData(Constants.SYMBOLS_NEW, Constants.OUT_FILE_NEW);
//                new ZacksClient().processData(Constants.SYMBOLS_TEST_1, Constants.OUT_FILE_TANZY_TEST, "INV_TEST1", 0);
//                new ZacksClient().processData(Constants.SYMBOLS_TEST_2, Constants.OUT_FILE_TANZY_TEST, "INV_TEST2", 1);
//                new ZacksClient().processData(Constants.SYMBOLS_TEST_3, Constants.OUT_FILE_TANZY_TEST, "INV_TEST3", 2);
//                new ZacksClient().processData(Constants.SYMBOLS_TEST_4, Constants.OUT_FILE_TANZY_TEST, "INV_TEST4", 3);

            } else if(isOld) {

                new ZacksClient().processData(Constants.SYMBOLS_T_INV_LARGE, Constants.OUT_FILE_T_INV_LARGE);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV_SMALL, Constants.OUT_FILE_T_INV_SMALL);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV_BASE,  Constants.OUT_FILE_T_INV_BASE);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV_LS,    Constants.OUT_FILE_T_INV_LS);


                new ZacksClient().processData(Constants.SYMBOLS_TANZY_INDEX, Constants.OUT_FILE_TANZY_INDEX);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_NEWIPO, Constants.OUT_FILE_TANZY_NEWIPO);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_CURRENTWATCH, Constants.OUT_FILE_TANZY_CURRENTWATCH);

                new ZacksClient().processData(Constants.SYMBOLS_TANZY_STABLE, Constants.OUT_FILE_TANZY_STABLE);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_MID_SPEC, Constants.OUT_FILE_TANZY_MIDSPEC);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_SML_SPEC, Constants.OUT_FILE_TANZY_SMLSPEC);

                new ZacksClient().processData(Constants.SYMBOLS_TANZY_INVESTED, Constants.OUT_FILE_TANZY_INVESTED);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_WATCH, Constants.OUT_FILE_TANZY_WATCH);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_TO_INVEST, Constants.OUT_FILE_TANZY_TO_INVEST);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_PENNY, Constants.OUT_FILE_TANZY_PENNY);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_GROWTH, Constants.OUT_FILE_TANZY_GROWTH);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_REPORT, Constants.OUT_FILE_TANZY_REPORT);

                new ZacksClient().processData(Constants.SYMBOLS_TANZY_REIT, Constants.OUT_FILE_TANZY_REIT);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_WATCH2, Constants.OUT_FILE_TANZY_WATCH2);
                //new ZacksClient().processData(Constants.SYMBOLS_D3L, Constants.OUT_FILE_D3L);

                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_SMALL,    Constants.OUT_FILE_TANZY_ETF, "SmallCap", 0);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_THEMATIC, Constants.OUT_FILE_TANZY_ETF, "Thematic", 1);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_WATCH,    Constants.OUT_FILE_TANZY_ETF, "Watch",    2);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_EMERGING, Constants.OUT_FILE_TANZY_ETF, "Emerging", 3);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_INDEXES,  Constants.OUT_FILE_TANZY_ETF, "Indexes",  4);

                new ZacksClient().processData(Constants.SYMBOLS_TANZY_BIO, Constants.OUT_FILE_TANZY_BIO);
                new ZacksClient().processData(Constants.SYMBOLS_TANZY_IOT, Constants.OUT_FILE_TANZY_IOT);

            } else {

                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_LARGE,       Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_LARGE",      0);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_SMALL,       Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_SMALL",      1);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_SUP_GROWTH,  Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_SUP_GROWTH", 2);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_BASE,        Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_BASE",       3);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_ROBI100_PER, Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_ROBI100_PER",4);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_LIFE,        Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_LIFE",       5);
                new ZacksClient().processData(Constants.SYMBOLS_T_INV19_REIT,        Constants.OUT_FILE_TANZY_INV_2019, "T_INV19_REIT",       6);

                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_SMALL,    Constants.OUT_FILE_TANZY_ETF, "SmallCap", 0);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_THEMATIC, Constants.OUT_FILE_TANZY_ETF, "Thematic", 1);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_WATCH,    Constants.OUT_FILE_TANZY_ETF, "Watch",    2);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_EMERGING, Constants.OUT_FILE_TANZY_ETF, "Emerging", 3);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_INDEXES,  Constants.OUT_FILE_TANZY_ETF, "Indexes",  4);
                new ZacksClient().processETFData(Constants.SYMBOLS_TANZY_ETF_BOND_MLP, Constants.OUT_FILE_TANZY_ETF, "BondMLP",  5);
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

    private void processData(String symbols, String outFile, String sheetName, int sheetIndex) {

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksRatings.getData(symbol));
        }

        excelProp.setSheetName(sheetName);
        excelProp.setSheetIndex(sheetIndex);

        zacksRatings.writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, false);
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

        zacksRatings.writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC, true);
    }
}
