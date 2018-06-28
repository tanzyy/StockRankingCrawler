package Financial;

import Utils.Constants;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MarketBeatClient {

    private static  String OUT_FILE_LOC  = "/Reports/MarketBeatRank";
    private static  String BACK_DIR      = "/Backup";
    private static final boolean FOR_NEW = false;

    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.dir") + OUT_FILE_LOC;

        if(FOR_NEW)
            new MarketBeatClient().processData(Constants.SYMBOLS_NEW,             Constants.OUT_DIR_NEW,              true);
        else {
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_INVESTED,  Constants.OUT_DIR_TANZY_INVESTED,   false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_WATCH,     Constants.OUT_DIR_TANZY_WATCH,      false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_WATCH2,    Constants.OUT_DIR_TANZY_WATCH2,     false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_GROWTH,    Constants.OUT_DIR_TANZY_GROWTH,     false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_TO_INVEST, Constants.OUT_DIR_TANZY_TO_INVEST,  false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_PENNY,     Constants.OUT_DIR_TANZY_PENNY,      false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_REPORT,    Constants.OUT_DIR_TANZY_REPORT,     false);
            new MarketBeatClient().processData(Constants.SYMBOLS_TANZY_REIT,      Constants.OUT_DIR_TANZY_REIT,       false);
            new MarketBeatClient().processData(Constants.SYMBOLS_D3L,             Constants.OUT_DIR_D3L,              false);
        }
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

        //To get historical ratings for each ticker and update the recent most column
        if(toUpdate) {
            for(String ticker : tickers) {
                Map<String, RankInfo> resultMap = marketBeatRatings.getDataByTicker(ticker);
                marketBeatRatings.updateXL(ticker, resultMap,OUT_FILE_LOC + dirName, OUT_FILE_LOC + dirName + BACK_DIR);
            }

        }
    }

}
