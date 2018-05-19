package Utils;

import Financial.ZacksDataInExcel;
import VO.RankInfo;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    private static final String SYMBOLS_TEST  = "mu,baba";
    private static final String OUT_FILE_TEST = "ZacksTest.xlsx";


    public void testWriteToXLFileDoesNotExist() {

        String OUT_FILE_TEST = "ZacksTest_FileNotExist.xlsx";

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
        zacksDataInExcel.init();

        RankInfo rankInfo1 = new RankInfo("mu", "1", "$100");
        RankInfo rankInfo2 = new RankInfo("pu", "3", "$50.49");
        RankInfo rankInfo3 = new RankInfo("tu", "0", "0");
        RankInfo rankInfo4 = new RankInfo("su", "5", "$1050");

        List<RankInfo> fetchedData = new ArrayList<>();
        fetchedData.add(rankInfo1);
        fetchedData.add(rankInfo2);
        fetchedData.add(rankInfo3);
        fetchedData.add(rankInfo4);

        zacksDataInExcel.writeToXL(OUT_FILE_TEST, fetchedData);

        assertTrue( true );
    }

    public void testWriteToXLFileExistWithChange() {

//        String resourcePath  = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String targetFile    = resourcePath + "ZacksTest_FileExistWithChange.xlsx";
//
//        System.out.println(targetFile);
//
//        System.out.println((new File(targetFile)).exists());

        String OUT_FILE_TEST = "ZacksTest_FileExistWithChange.xlsx";

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
        zacksDataInExcel.init();

        RankInfo rankInfo1 = new RankInfo("mu", "1", "$100");
        RankInfo rankInfo2 = new RankInfo("pu", "3", "$50.49");
        RankInfo rankInfo3 = new RankInfo("tu", "0", "0");
        RankInfo rankInfo4 = new RankInfo("su", "5", "$1050");
        RankInfo rankInfo5 = new RankInfo("lu", "3", "$10");

        List<RankInfo> fetchedData = new ArrayList<>();
        fetchedData.add(rankInfo1);
        fetchedData.add(rankInfo2);
        fetchedData.add(rankInfo3);
        fetchedData.add(rankInfo4);
        fetchedData.add(rankInfo5);

        zacksDataInExcel.writeToXL(OUT_FILE_TEST, fetchedData);

        assertTrue( true );
    }

    public void testWriteToXLFileExistWOChange() {

        String OUT_FILE_TEST = "ZacksTest_FileExistWOChange.xlsx";

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
        zacksDataInExcel.init();

        RankInfo rankInfo1 = new RankInfo("mu", "1", "$100");
        RankInfo rankInfo2 = new RankInfo("pu", "3", "$50.49");
        RankInfo rankInfo3 = new RankInfo("tu", "0", "0");
        RankInfo rankInfo4 = new RankInfo("su", "5", "$1050");

        List<RankInfo> fetchedData = new ArrayList<>();
        fetchedData.add(rankInfo1);
        fetchedData.add(rankInfo2);
        fetchedData.add(rankInfo3);
        fetchedData.add(rankInfo4);

        zacksDataInExcel.writeToXL(OUT_FILE_TEST, fetchedData);

        assertTrue( true );
    }

    /**
     * With valid symbol
     */
    public void testGetRankInfo() {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();

        zacksDataInExcel.init();
        zacksDataInExcel.getData("mu");
        assertTrue( true );
    }

    /**
     * With invalid symbol
     */
    public void testGetRankInfoWithInvalid() {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();

        zacksDataInExcel.init();
        zacksDataInExcel.getData("muu");
        assertTrue( true );
    }

    /**
     * With valid symbol, but data unavailable.
     */
    public void testGetRankInfoDataUn() {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();

        zacksDataInExcel.init();
        zacksDataInExcel.getData("goog");
        assertTrue( true );
    }

    /**
     * Verifies getRankStrByRankInfo().
     */
    public void testGetRankStrByRankInfo() {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
        RankInfo rankInfo = new RankInfo();
        rankInfo.setSymbol("test_symbol");
        rankInfo.setRank("2");
        rankInfo.setPrice("$100");

        assertEquals("Buy        ($100)", zacksDataInExcel.getRankStrByRankInfo(rankInfo));

        rankInfo.resetRankInfo("", "0", "0");
        assertEquals("UN         (0)", zacksDataInExcel.getRankStrByRankInfo(rankInfo));
    }


    /**
     * Verifies getRankByStr().
     */
    public void testGetRankByStr() {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();

        assertEquals("2", zacksDataInExcel.getRankNumByStr("Buy        ($100)"));
        assertEquals("1", zacksDataInExcel.getRankNumByStr("StrongBuy (0)"));
        assertEquals("0", zacksDataInExcel.getRankNumByStr("UN (0)"));
    }


    public void testGetPrice() {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();

        zacksDataInExcel.init();
        zacksDataInExcel.getPrice("mu");
        assertTrue( true );
    }

    public void testRandom() {

        String str = "$53.39 USD";

        String result = str.split(" ")[0];

        assertEquals("$53.39", result);
    }
}
