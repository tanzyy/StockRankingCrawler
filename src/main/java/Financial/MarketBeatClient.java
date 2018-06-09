package Financial;

import Utils.Constants;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MarketBeatClient {

    private static final String SYMBOLS_TANZY_WATCH     = "amzn,goog,googl,nflx,aapl,nvda,fb,shop,tsla,adbe,anet,ba,mu,wmt,tgt,exas,ebay,baba,amat,crus,twtr,spot,docu,ibm,vmw,gsk";
    private static final String SYMBOLS_TANZY_WATCH2    = "dcph,nvax,ftr,nly,mnk,cy,ddd,qd,mat,ctrl,ctl,trvg,tzoo,trip,yelp,mtch,pypl,avgo,vrtx,algn,txn,low,gme,big,rost,blk,vslr,abbv,fate,lmt,bl,fdx,cmcsa,hznp,dbx,cat,fdc,phm,alna,tol,symc,tmus";
    private static final String OUT_DIR_TANZY_WATCH     = "/TanzyWatch";
    private static final String OUT_DIR_TANZY_WATCH2    = "/TanzyWatch2";

    private static final String SYMBOLS_TANZY_INVESTED  = "sq,roku,irbt,ntnx,abcd,curo,lb,m,gluu,ge,t,gern,wb,ibn,cost,amd,intc,f,gm,abb,rrc,kmi,teva,alb,sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc";
    private static final String OUT_DIR_TANZY_INVESTED  = "/TanzyInvested";

    private static final String SYMBOLS_TANZY_TO_INVEST = "etsy,pvtl,irt,rng,tndm,snap,conn,amma,edit,psec";
    private static final String OUT_DIR_TANZY_TO_INVEST = "/TanzyToInvest";

    private static final String SYMBOLS_TANZY_PENNY     = "rad,jagx,aprn,opgn,zsan,cris,nete,sgyp,agen,avxl,ohrp,snes,insy,chk,ftr,mobl,nvax,nihd,jcp,smrt,grpn,xene";
    private static final String OUT_DIR_TANZY_PENNY     = "/TanzyPenny";

    private static final String SYMBOLS_TANZY_GROWTH    = "llnw,crox,tbbk,spar,usat,cdxs,tndm";
    private static final String OUT_DIR_TANZY_GROWTH    = "/TanzyGrowth";

    private static final String SYMBOLS_TANZY_REPORT    = "abcd,gluu,gern,sq,ntnx,roku,irbt,jd,m,etsy,curo,brk.b";
    private static final String OUT_DIR_TANZY_REPORT    = "/TanzyReport";

    private static final String SYMBOLS_TANZY_REIT         = "IRT,HCP,KIM,MORT,PLD";
    private static final String OUT_DIR_TANZY_REIT         = "/TanzyREIT";
    private static final String SYMBOLS_TANZY_ETF_SMALL    = "PXSG,FYC,SLYG";
    private static final String SYMBOLS_TANZY_ETF_THEMATIC = "BOTZ,ARKG";
    private static final String SYMBOLS_TANZY_ETF_WATCH    = "MORT,QQQ,SPY,VYM,VTI,VTV,ITA,DIA,IWM";
    private static final String OUT_DIR_TANZY_ETF          = "/Tanzy_ETF";


    private static final String SYMBOLS     = "mu";


    private static  String OUT_FILE_LOC = "/Desktop/Personal/Finance_Learn_Reports/MarketBeatRank";
    private static  String BACK_DIR     = "/Backup";

    public static void main(String[] args) {
        OUT_FILE_LOC  = System.getProperty("user.home") + OUT_FILE_LOC;

        new MarketBeatClient().processData(SYMBOLS_TANZY_INVESTED, OUT_DIR_TANZY_INVESTED);
    }

    private void processData(String symbols, String dirName) {

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
    }

}
