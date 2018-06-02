package Utils;

import Financial.MarketBeatRatings;
import VO.RankInfo;
import org.apache.poi.ss.formula.functions.Rank;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by i852841 on 5/26/18.
 */
public class MarketBeatTests {

    String[] arrTicker1   = {"MU", "NTNX", "SNES", "COST"};
    List<String> tickers1 = Arrays.asList(arrTicker1);
    private static final String OUT_FILE_LOC     = "/Users/i852841/Downloads";
    private static final String BACK_FILE_LOC    = "/Users/i852841/Downloads/Backup";

    @Test
    public void testMBE2ENewFile() {

        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();

        //1. Init
        marketBeatRatings.init();

        //2. Get rank data by firms
        Map<String, RankInfo> result15587 =  marketBeatRatings.getDataByResearchFirm("15587", false, tickers1);
        Map<String, RankInfo> result12026 =  marketBeatRatings.getDataByResearchFirm("12026", false, tickers1);
        Map<String, RankInfo> result3308  =  marketBeatRatings.getDataByResearchFirm("3308", false, tickers1);
        Map<String, RankInfo> result572   =  marketBeatRatings.getDataByResearchFirm("572", false, tickers1);

        //3. Merge the maps
        List<Map<String, RankInfo>> result = new ArrayList<>();
        result.add(result15587);
        result.add(result12026);
        result.add(result3308);
        result.add(result572);
        Map<String, List<RankInfo>> mergedData = marketBeatRatings.mergeMapData(result);

        //4. Write to file
        //Check if ticker ratings are available, if not skip write to file.


        //marketBeatRatings.writeToXL("MU", "MU.xlsx", mergedData.get("MU"), OUT_FILE_LOC, BACK_FILE_LOC);

        marketBeatRatings.writeToXL(arrTicker1[0], arrTicker1[0] + ".xlsx", mergedData.get(arrTicker1[0]), OUT_FILE_LOC, BACK_FILE_LOC);
        marketBeatRatings.writeToXL(arrTicker1[1], arrTicker1[1] + ".xlsx", mergedData.get(arrTicker1[1]), OUT_FILE_LOC, BACK_FILE_LOC);
        marketBeatRatings.writeToXL(arrTicker1[2], arrTicker1[2] + ".xlsx", mergedData.get(arrTicker1[2]), OUT_FILE_LOC, BACK_FILE_LOC);
        marketBeatRatings.writeToXL(arrTicker1[3], arrTicker1[3] + ".xlsx", mergedData.get(arrTicker1[3]), OUT_FILE_LOC, BACK_FILE_LOC);
    }

    @Test
    public void testDataByOneResearchFirmForAll() {

        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();
        marketBeatRatings.getDataByResearchFirm("572", true, null);
    }

    @Test
    public void testDataByOneResearchFirmForSelected() {

        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();
        marketBeatRatings.getDataByResearchFirm("15587", false, tickers1);
    }

    @Test
    public void testGetTicker() {
        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();
        System.out.println("[" + marketBeatRatings.getTicker("E. W. Scripps (SSP)") + "]");
        System.out.println("[" + marketBeatRatings.getTicker("RELX Group (RELX)") + "]");
        System.out.println("[" + marketBeatRatings.getTicker("Taro Pharmaceutical Industries (TARO)") + "]");
    }

    @Test
    public void testMergeMapData() {

        RankInfo rankInfo11 = new RankInfo("s11", "r11", "p11");
        RankInfo rankInfo21 = new RankInfo("s21", "r21", "p21");
        RankInfo rankInfo12 = new RankInfo("s12", "r12", "p12");
        RankInfo rankInfo31 = new RankInfo("s31", "r31", "p31");
        RankInfo rankInfo32 = new RankInfo("s32", "r32", "p32");
        RankInfo rankInfo33 = new RankInfo("s33", "r33", "p33");
        RankInfo rankInfo34 = new RankInfo("s34", "r34", "p34");

        Map<String, RankInfo> map1 = new HashMap<>();
        Map<String, RankInfo> map2 = new HashMap<>();
        Map<String, RankInfo> map3 = new HashMap<>();

        map1.put("ticker1", rankInfo11);
        map1.put("ticker2", rankInfo12);
        map2.put("ticker3", rankInfo21);
        map2.put("ticker2", rankInfo21);
        map3.put("ticker1", rankInfo31);
        map3.put("ticker2", rankInfo32);
        map3.put("ticker3", rankInfo33);
        map3.put("ticker4", rankInfo34);

        List<Map<String, RankInfo>> result = new ArrayList<>();
        result.add(map1);
        result.add(map2);
        result.add(map3);

        MarketBeatRatings mbr = new MarketBeatRatings();
        mbr.mergeMapData(result);
    }


    @Test
    public void testGetAllKeys() {

        RankInfo rankInfo11 = new RankInfo("s11", "r11", "p11");
        RankInfo rankInfo21 = new RankInfo("s21", "r21", "p21");
        RankInfo rankInfo12 = new RankInfo("s12", "r12", "p12");

        Map<String, RankInfo> map1 = new HashMap<>();
        Map<String, RankInfo> map2 = new HashMap<>();

        map1.put("ticker1", rankInfo11);
        map1.put("ticker2", rankInfo12);
        map2.put("ticker3", rankInfo21);
        map2.put("ticker2", rankInfo21);

        List<Map<String, RankInfo>> result = new ArrayList<>();
        result.add(map1);
        result.add(map2);

        MarketBeatRatings mbr = new MarketBeatRatings();
        mbr.getAllKeys(result);
    }

    @Test
    public void testRandom() {

    }

}
