package Utils;

import Financial.ZacksRatings;
import VO.ExcelProp;
import VO.RankInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static String OUT_LOC       = "ZacksTest.xlsx";
    private static String BACK_LOC      = "ZacksTest.xlsx";

    private static final String OUT_FILE_LOC     = "/Users/i852841/Downloads";
    private static final String BACK_FILE_LOC    = "/Users/i852841/Downloads/Backup";


    @BeforeClass
    public static void init() {

        String currentUserDownloadDir = System.getProperty("user.home") + File.separator + "Downloads";
        System.out.println(currentUserDownloadDir);
        OUT_LOC = currentUserDownloadDir;
        BACK_LOC = currentUserDownloadDir;

        String resourcePath  = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        String targetFile1    = resourcePath + "ZacksTest_FileExistWithChange.xlsx";
        System.out.println(targetFile1);

        String targetFile2    = resourcePath + "ZacksTest_FileExistWOChange.xlsx";
        System.out.println(targetFile2);

        String targetFile3    = resourcePath + "ZacksTest_ETF_FileExist.xlsx";
        System.out.println(targetFile2);

        FileUtils.copyFile(targetFile1, currentUserDownloadDir);
        FileUtils.copyFile(targetFile2, currentUserDownloadDir);
        FileUtils.copyFile(targetFile3, currentUserDownloadDir);
    }

    @Test
    public void testETFFileNotExist() {

        String symbols = "PXSG,FYC,SLYG";
        String outFile = "ZacksTest_ETF_NotExist.xlsx";

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksRatings.getETFData(symbol));
        }

        ExcelProp excelProp = new ExcelProp();
        excelProp.setSheetName("Small CAP");
        excelProp.setWorkBookName(outFile);
        excelProp.setSheetIndex(1);

        zacksRatings.writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC);

        Assert.assertTrue( true );
    }

    @Test
    public void testETFFileExist() {

        String symbols = "PXSG,FYC,SLYG";
        String outFile = "ZacksTest_ETF_FileExist.xlsx";

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksRatings.getETFData(symbol));
        }

        ExcelProp excelProp = new ExcelProp();
        excelProp.setSheetName("Small CAP");
        excelProp.setWorkBookName(outFile);
        excelProp.setSheetIndex(1);

        zacksRatings.writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC);

        Assert.assertTrue( true );
    }

    @Test
    public void testGetETFData() {

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.getETFData("FYC");

        Assert.assertTrue( true );
    }


    @Test
    public void testWriteToXLFileDoesNotExist() {

        String OUT_FILE_TEST = "ZacksTest_FileNotExist.xlsx";

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        RankInfo rankInfo1 = new RankInfo("mu", "1", "$100");
        RankInfo rankInfo2 = new RankInfo("pu", "3", "$50.49");
        RankInfo rankInfo3 = new RankInfo("tu", "0", "0");
        RankInfo rankInfo4 = new RankInfo("su", "5", "$1050");

        List<RankInfo> fetchedData = new ArrayList<>();
        fetchedData.add(rankInfo1);
        fetchedData.add(rankInfo2);
        fetchedData.add(rankInfo3);
        fetchedData.add(rankInfo4);

        zacksRatings.writeToXL(OUT_FILE_TEST, fetchedData, OUT_LOC, BACK_LOC);

        //assertTrue( true );
    }

    @Test
    public void testWriteToXLFileExistWithChange() {

        String OUT_FILE_TEST = "ZacksTest_FileExistWithChange.xlsx";

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

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

        zacksRatings.writeToXL(OUT_FILE_TEST, fetchedData, OUT_LOC, BACK_LOC);

        Assert.assertTrue(true );
    }

    @Test
    public void testWriteToXLFileExistWOChange() {

        String OUT_FILE_TEST = "ZacksTest_FileExistWOChange.xlsx";

        ZacksRatings zacksRatings = new ZacksRatings();
        zacksRatings.init();

        RankInfo rankInfo1 = new RankInfo("mu", "1", "$100");
        RankInfo rankInfo2 = new RankInfo("pu", "3", "$50.49");
        RankInfo rankInfo3 = new RankInfo("tu", "0", "0");
        RankInfo rankInfo4 = new RankInfo("su", "5", "$1050");

        List<RankInfo> fetchedData = new ArrayList<>();
        fetchedData.add(rankInfo1);
        fetchedData.add(rankInfo2);
        fetchedData.add(rankInfo3);
        fetchedData.add(rankInfo4);

        zacksRatings.writeToXL(OUT_FILE_TEST, fetchedData, OUT_LOC, BACK_LOC);

        Assert.assertTrue( true );
    }

    /**
     * With valid symbol
     */
    @Test
    public void testGetRankInfo() {

        ZacksRatings zacksRatings = new ZacksRatings();

        zacksRatings.init();
        zacksRatings.getData("mu");
        Assert.assertTrue( true );
    }

    /**
     * With invalid symbol
     */
    @Test
    public void testGetRankInfoWithInvalid() {

        ZacksRatings zacksRatings = new ZacksRatings();

        zacksRatings.init();
        zacksRatings.getData("muu");
        Assert.assertTrue( true );
    }

    /**
     * With valid symbol, but data unavailable.
     */
    @Test
    public void testGetRankInfoDataUn() {

        ZacksRatings zacksRatings = new ZacksRatings();

        zacksRatings.init();
        zacksRatings.getData("goog");
        Assert.assertTrue( true );
    }

    /**
     * Verifies getRankStrByRankInfo().
     */
    @Test
    public void testGetRankStrByRankInfo() {

        ZacksRatings zacksRatings = new ZacksRatings();
        RankInfo rankInfo = new RankInfo();
        rankInfo.setSymbol("test_symbol");
        rankInfo.setRank("2");
        rankInfo.setPrice("$100");

        Assert.assertEquals("Buy        ($100)", zacksRatings.getRankStrByRankInfo(rankInfo));

        rankInfo.resetRankInfo("", "0", "0");
        Assert.assertEquals("UN         (0)", zacksRatings.getRankStrByRankInfo(rankInfo));
    }


    /**
     * Verifies getRankByStr().
     */
    @Test
    public void testGetRankByStr() {

        ZacksRatings zacksRatings = new ZacksRatings();

        Assert.assertEquals("2", zacksRatings.getRankNumByStr("Buy        ($100)"));
        Assert.assertEquals("1", zacksRatings.getRankNumByStr("StrongBuy (0)"));
        Assert.assertEquals("0", zacksRatings.getRankNumByStr("UN (0)"));
    }

    @Test
    public void testGetPrice() {

        ZacksRatings zacksRatings = new ZacksRatings();

        zacksRatings.init();
        zacksRatings.getPrice("qqq");
        Assert.assertTrue( true );
    }

    @Test
    public void testGetYear() {

        System.out.println(CommonUtils.getYear());
        Assert.assertTrue( true );
    }

    @Test
    public void testRandom() {

        String str = "$53.39 USD";
        String result = str.split(" ")[0];
        Assert.assertEquals("$53.39", result);

//        String currentUsersHomeDir = System.getProperty("user.home");
//        System.out.println(currentUsersHomeDir + File.separator + "Downloads");

    }
}
