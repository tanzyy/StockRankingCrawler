package Financial;

import Utils.Constants;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MarketBeatClient {

    private static final String OUT_DIR_TANZY_WATCH     = "/TanzyWatch";
    private static final String OUT_DIR_TANZY_WATCH2    = "/TanzyWatch2";
    private static final String OUT_DIR_TANZY_INVESTED  = "/TanzyInvested";
    private static final String OUT_DIR_TANZY_TO_INVEST = "/TanzyToInvest";
    private static final String OUT_DIR_TANZY_PENNY     = "/TanzyPenny";
    private static final String OUT_DIR_TANZY_GROWTH    = "/TanzyGrowth";
    private static final String OUT_DIR_TANZY_REPORT    = "/TanzyReport";
    private static final String OUT_DIR_TANZY_REIT      = "/TanzyREIT";
    private static final String OUT_DIR_TANZY_ETF       = "/Tanzy_ETF";


    private static final String SYMBOLS     = "mu";


    private static  String OUT_FILE_LOC = "/Desktop/Personal/Finance_Learn_Reports/MarketBeatRank";
    private static  String BACK_DIR     = "/Backup";

    public static void main(String[] args) {
        OUT_FILE_LOC  = System.getProperty("user.home") + OUT_FILE_LOC;

        //new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_INVESTED, OUT_DIR_TANZY_INVESTED, false);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_WATCH, OUT_DIR_TANZY_WATCH, true);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_WATCH2, OUT_DIR_TANZY_WATCH2, true);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_GROWTH, OUT_DIR_TANZY_GROWTH, true);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_TO_INVEST, OUT_DIR_TANZY_TO_INVEST, true);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_PENNY, OUT_DIR_TANZY_PENNY, true);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_REPORT, OUT_DIR_TANZY_REPORT, true);
        new MarketBeatClient().processData(ZacksClient.SYMBOLS_TANZY_REIT, OUT_DIR_TANZY_REIT, true);
    }

    private void processData(String symbols, String dirName, boolean toUpdate) {

        //0. Get list from comma separated value.
        List<String> tickers = new ArrayList<>();
        for(String str : symbols.split(Constants.SEPARATOR_COMMA))
            tickers.add(str);

        MarketBeatRatings marketBeatRatings = new MarketBeatRatings();

        //1. Init
        marketBeatRatings.init();

        //2. Get rank data by firms
        List<Map<String, RankInfo>> result = new ArrayList<>();
        for(String id : marketBeatRatings.researchFirmOrderList) {
            result.add(marketBeatRatings.getDataByResearchFirm(id, false, tickers));
        }

        //3. Merge the maps
        Map<String, List<RankInfo>> mergedData = marketBeatRatings.mergeMapData(result);

        for(String ticker : tickers)
            marketBeatRatings.writeToXL(ticker, ticker.toUpperCase() + ".xlsx", mergedData.get(ticker),
                    OUT_FILE_LOC + dirName, OUT_FILE_LOC + dirName + BACK_DIR);

        if(toUpdate) {
            for(String ticker : tickers) {
                Map<String, RankInfo> resultMap = marketBeatRatings.getDataByTicker(ticker);
                marketBeatRatings.updateXL(ticker, resultMap,OUT_FILE_LOC + dirName, OUT_FILE_LOC + dirName + BACK_DIR);
            }

        }
    }

}
