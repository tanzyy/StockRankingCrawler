package Utils;

import Financial.MarketBeatRatings;
import VO.RankInfo;
import org.apache.poi.ss.formula.functions.Rank;
import org.junit.Test;

import java.util.*;

/**
 * Created by i852841 on 5/26/18.
 */
public class MarketBeatTests {

    //String[] arrTicker1   = {"MU", "NTNX", "LB", "T"};
    String[] arrTicker1   = {"MU","NTNX","WFC"};
    List<String> tickers1 = Arrays.asList(arrTicker1);
    private static final String OUT_FILE_LOC     = System.getProperty("user.home") + "/Downloads";
    private static final String BACK_FILE_LOC    = System.getProperty("user.home") + "//Downloads/Backup";

    @Test
    public void testMBE2ENewFile() {

        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();

        //1. Init
        marketBeatRatings.init();

        //2. Get rank data by firms
        Map<String, RankInfo> result1   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.NEEDHAM.getId(), false, tickers1);
        Map<String, RankInfo> result2   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.CS.getId(), false, tickers1);
        Map<String, RankInfo> result3   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.MORNING.getId(), false, tickers1);
        Map<String, RankInfo> result4   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.Z.getId(), false, tickers1);
        Map<String, RankInfo> result5   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.GS.getId(), false, tickers1);
        Map<String, RankInfo> result6   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.MORGAN.getId(), false, tickers1);
        Map<String, RankInfo> result7   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.KC.getId(), false, tickers1);
        Map<String, RankInfo> result8   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.ARGUS.getId(), false, tickers1);
        Map<String, RankInfo> result9   =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.PIPER.getId(), false, tickers1);
        Map<String, RankInfo> result10  =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.VE.getId(), false, tickers1);
        Map<String, RankInfo> result11  =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.SIDOTI.getId(), false, tickers1);
        Map<String, RankInfo> result12  =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.JEFFERIES.getId(), false, tickers1);
        Map<String, RankInfo> result13  =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.STIFEL.getId(), false, tickers1);
        Map<String, RankInfo> result14  =  marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.VETR.getId(), false, tickers1);

        //3. Merge the maps
        List<Map<String, RankInfo>> result = new ArrayList<>();
        result.add(result1);
        result.add(result2);
        result.add(result3);
        result.add(result4);
        result.add(result5);
        result.add(result6);
        result.add(result7);
        result.add(result8);
        result.add(result9);
        result.add(result10);
        result.add(result11);
        result.add(result12);
        result.add(result13);
        result.add(result14);

        Map<String, List<RankInfo>> mergedData = marketBeatRatings.mergeMapData(result);

        //4. Write to file
        //Check if ticker ratings are available, if not skip write to file.

        marketBeatRatings.writeToXL(arrTicker1[0], arrTicker1[0] + ".xlsx", mergedData.get(arrTicker1[0]), OUT_FILE_LOC, BACK_FILE_LOC);
        //marketBeatRatings.writeToXL(arrTicker1[1], arrTicker1[1] + ".xlsx", mergedData.get(arrTicker1[1]), OUT_FILE_LOC, BACK_FILE_LOC);
        //marketBeatRatings.writeToXL(arrTicker1[2], arrTicker1[2] + ".xlsx", mergedData.get(arrTicker1[2]), OUT_FILE_LOC, BACK_FILE_LOC);
        //marketBeatRatings.writeToXL(arrTicker1[3], arrTicker1[3] + ".xlsx", mergedData.get(arrTicker1[3]), OUT_FILE_LOC, BACK_FILE_LOC);
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
    public void testDataByOneResearchFirmForSelected2() {

        String[] arrTicker1   = {"rds.a"};
        List<String> tickers1 = Arrays.asList(arrTicker1);
        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();
        marketBeatRatings.getDataByResearchFirm(MarketBeatRatings.ResearcherID.PIPER.getId(), false, tickers1);
    }

    @Test
    public void testGetTicker() {
        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();
        System.out.println("[" + marketBeatRatings.getTicker("E. W. Scripps (SSP)") + "]");
        System.out.println("[" + marketBeatRatings.getTicker("RELX Group (RELX)") + "]");
        System.out.println("[" + marketBeatRatings.getTicker("Taro Pharmaceutical Industries (TARO)") + "]");
        System.out.println("[" + marketBeatRatings.getTicker("Royal Dutch Shell plc ADR Class A (RDS.A)") + "]");
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
    public void testResearcherID() {

        //MarketBeatRatings mb = new MarketBeatRatings();
        //mb.init();

        System.out.println(MarketBeatRatings.ResearcherID.getNameByID("12026"));

    }

    @Test
    public void testRandom() {

        //System.out.println(RankInfo.RatingState.RED.getState());
        //System.out.println(MarketBeatRatings.ResearcherID.NEEDHAM.getId());
        //System.out.println(MarketBeatRatings.ResearcherID.NEEDHAM.getName());

        MarketBeatRatings mb = new MarketBeatRatings();
//        mb.init();
//
//        for(String data : mb.researchFirmOrderList) {
//            System.out.println(data);
//            System.out.println(MarketBeatRatings.ResearcherID.);
//        }

        String SYMBOLS_TANZY_INVESTED  =
                "sq,roku,irbt,ntnx,abcd,curo,lb,m,gluu,ge,t,gern,wb,ibn,cost,amd,intc,f,gm,abb,rrc,kmi,teva,alb,sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc";

        List<String> tickers = new ArrayList<>();
        for(String str : SYMBOLS_TANZY_INVESTED.split(Constants.SEPARATOR_COMMA))
            tickers.add(str);

        System.out.println(tickers.contains("rds.a"));

    }

}
