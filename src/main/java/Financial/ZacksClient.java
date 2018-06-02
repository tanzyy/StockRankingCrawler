package Financial;

import Utils.Constants;
import VO.RankInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i852841 on 5/17/18.
 */
public class ZacksClient {

    private static final String SYMBOLS_TANZY_WATCH      = "amzn,goog,googl,nflx,aapl,nvda,shop,tsla,adbe,anet,ba,mu,ter,wmt,tgt,edit,exas,t,tmus,cmcsa,ebay,baba,amat,crus";
    private static final String OUT_FILE_TANZY_WATCH     = "TanzyWatch.xlsx";

    private static final String SYMBOLS_TANZY_INVESTED   = "sq,roku,irbt,wb,hibb,ibn,cost,amd,intc,f,gm,abb,rrc,kmi,teva,alb,sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc";
    private static final String OUT_FILE_TANZY_INVESTED  = "TanzyInvested.xlsx";

    private static final String SYMBOLS_TANZY_TO_INVEST  = "m,etsy,ntnx,curo,brk.b,qqq,lb,t,ba,pvtl,irt,rng,ge,tndm,tzoo,snap";
    private static final String OUT_FILE_TANZY_TO_INVEST = "TanzyToInvest.xlsx";

    private static final String SYMBOLS_TANZY_PENNY      = "gern,rad,jagx,aprn,opgn,zsan,cris,nete,sgyp,agen,avxl,ohrp,snes,insy,chk,ftr,mobl,nvax,nihd";
    private static final String OUT_FILE_TANZY_PENNY     = "TanzyPenny.xlsx";

    private static final String SYMBOLS_TANZY_GROWTH     = "abcd,gluu,llnw,crox,tbbk,spar,usat,cdxs,tndm";
    private static final String OUT_FILE_TANZY_GROWTH    = "TanzyGrowth.xlsx";

    private static final String SYMBOLS_TANZY_REPORT     = "abcd,gluu,gern,sq,ntnx,roku,irbt,jd,m,etsy,curo,brk.b";
    private static final String OUT_FILE_TANZY_REPORT    = "TanzyReport.xlsx";

    private static final String OUT_FILE_LOC     = "/Users/i852841/Desktop/Personal/Finance_Learn_Reports/ZacksRank";
    private static final String BACK_FILE_LOC    = "/Users/i852841/Desktop/Personal/Finance_Learn_Reports/ZacksRank/Backup";


    static boolean forReport = false;

    public static void main(String[] args) {

        if(forReport)
            new ZacksClient().processData(SYMBOLS_TANZY_TO_INVEST, OUT_FILE_TANZY_TO_INVEST);
        else {
            new ZacksClient().processData(SYMBOLS_TANZY_INVESTED, OUT_FILE_TANZY_INVESTED);
            new ZacksClient().processData(SYMBOLS_TANZY_WATCH, OUT_FILE_TANZY_WATCH);
            new ZacksClient().processData(SYMBOLS_TANZY_TO_INVEST, OUT_FILE_TANZY_TO_INVEST);
            new ZacksClient().processData(SYMBOLS_TANZY_PENNY, OUT_FILE_TANZY_PENNY);
            new ZacksClient().processData(SYMBOLS_TANZY_GROWTH, OUT_FILE_TANZY_GROWTH);
            new ZacksClient().processData(SYMBOLS_TANZY_REPORT, OUT_FILE_TANZY_REPORT);
        }
    }

    private void processData(String symbols, String outFile) {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
        zacksDataInExcel.init();

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksDataInExcel.getData(symbol));
        }

        zacksDataInExcel.writeToXL(outFile, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC);
    }
}
