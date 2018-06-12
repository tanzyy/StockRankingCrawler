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

    public static final String SYMBOLS_TANZY_WATCH      = "amzn,goog,googl,nflx,aapl,nvda,fb,shop,tsla,adbe,anet,ba,mu,wmt,tgt,exas,ebay,baba,amat,crus,twtr,spot,docu,ibm,vmw,gsk";
    public static final String SYMBOLS_TANZY_WATCH2     = "Dcph,nvax,ftr,nly,mnk,cy,ddd,qd,mat,ctrl,ctl,trvg,tzoo,trip,yelp,mtch,pypl,avgo,vrtx,algn,txn,low,gme,big,rost,blk,vslr,abbv,fate,lmt,bl,fdx,cmcsa,hznp,dbx,cat,fdc,phm,alna,tol,symc,tmus";
    private static final String OUT_FILE_TANZY_WATCH     = "TanzyWatch.xlsx";
    private static final String OUT_FILE_TANZY_WATCH2    = "TanzyWatch2.xlsx";

    public static final String SYMBOLS_TANZY_INVESTED   = "sq,roku,irbt,ntnx,abcd,curo,lb,m,gluu,ge,t,gern,wb,ibn,cost,amd,intc,f,gm,abb,rrc,kmi,teva,alb,sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc";
    private static final String OUT_FILE_TANZY_INVESTED  = "TanzyInvested.xlsx";

    public static final String SYMBOLS_TANZY_TO_INVEST  = "etsy,pvtl,irt,rng,tndm,snap,conn,amma,edit,psec";
    private static final String OUT_FILE_TANZY_TO_INVEST = "TanzyToInvest.xlsx";

    public static final String SYMBOLS_TANZY_PENNY      = "rad,jagx,aprn,opgn,zsan,cris,nete,sgyp,agen,avxl,ohrp,snes,insy,chk,ftr,mobl,nvax,nihd,jcp,smrt,grpn,xene";
    private static final String OUT_FILE_TANZY_PENNY     = "TanzyPenny.xlsx";

    public static final String SYMBOLS_TANZY_GROWTH     = "llnw,crox,tbbk,spar,usat,cdxs,tndm";
    private static final String OUT_FILE_TANZY_GROWTH    = "TanzyGrowth.xlsx";

    public static final String SYMBOLS_TANZY_REPORT     = "abcd,gluu,gern,sq,ntnx,roku,irbt,jd,m,etsy,curo,brk.b";
    private static final String OUT_FILE_TANZY_REPORT    = "TanzyReport.xlsx";

    public static final String SYMBOLS_TANZY_REIT     = "IRT,HCP,KIM,MORT,PLD";
    private static final String OUT_FILE_TANZY_REIT    = "TanzyREIT.xlsx";

    public static final String SYMBOLS_TANZY_ETF_SMALL    = "PXSG,FYC,SLYG";
    public static final String SYMBOLS_TANZY_ETF_THEMATIC = "BOTZ,ARKG";
    public static final String SYMBOLS_TANZY_ETF_WATCH    = "MORT,QQQ,SPY,VYM,VTI,VTV,ITA,DIA,IWM";
    private static final String OUT_FILE_TANZY_ETF         = "Tanzy_ETF.xlsx";

    private static  String OUT_FILE_LOC     = "/Desktop/Personal/Finance_Learn_Reports/ZacksRank";
    private static  String BACK_FILE_LOC    = "/Desktop/Personal/Finance_Learn_Reports/ZacksRank/Backup";


    static boolean forReport = false;
    static boolean isETF     = false;

    public static void main(String[] args) {

        OUT_FILE_LOC  = System.getProperty("user.home") + OUT_FILE_LOC;
        BACK_FILE_LOC = System.getProperty("user.home") + BACK_FILE_LOC;

        if(isETF) {
            new ZacksClient().processETFData(SYMBOLS_TANZY_ETF_SMALL, OUT_FILE_TANZY_ETF, "SmallCap", 0);
            new ZacksClient().processETFData(SYMBOLS_TANZY_ETF_THEMATIC, OUT_FILE_TANZY_ETF, "Thematic", 1);
            new ZacksClient().processETFData(SYMBOLS_TANZY_ETF_WATCH, OUT_FILE_TANZY_ETF, "Watch", 2);
        } else {
            if(forReport) {
                new ZacksClient().processData(SYMBOLS_TANZY_REIT, OUT_FILE_TANZY_REIT);
                new ZacksClient().processData(SYMBOLS_TANZY_WATCH2, OUT_FILE_TANZY_WATCH2);
            }
            else {
                new ZacksClient().processData(SYMBOLS_TANZY_INVESTED, OUT_FILE_TANZY_INVESTED);
                new ZacksClient().processData(SYMBOLS_TANZY_WATCH, OUT_FILE_TANZY_WATCH);
                new ZacksClient().processData(SYMBOLS_TANZY_TO_INVEST, OUT_FILE_TANZY_TO_INVEST);
                new ZacksClient().processData(SYMBOLS_TANZY_PENNY, OUT_FILE_TANZY_PENNY);
                new ZacksClient().processData(SYMBOLS_TANZY_GROWTH, OUT_FILE_TANZY_GROWTH);
                new ZacksClient().processData(SYMBOLS_TANZY_REPORT, OUT_FILE_TANZY_REPORT);

                new ZacksClient().processData(SYMBOLS_TANZY_REIT, OUT_FILE_TANZY_REIT);
                new ZacksClient().processData(SYMBOLS_TANZY_WATCH2, OUT_FILE_TANZY_WATCH2);

                new ZacksClient().processETFData(SYMBOLS_TANZY_ETF_SMALL, OUT_FILE_TANZY_ETF, "SmallCap", 0);
                new ZacksClient().processETFData(SYMBOLS_TANZY_ETF_THEMATIC, OUT_FILE_TANZY_ETF, "Thematic", 1);
                new ZacksClient().processETFData(SYMBOLS_TANZY_ETF_WATCH, OUT_FILE_TANZY_ETF, "Watch", 2);
            }
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

    private void processETFData(String symbols, String outFile, String sheetName, int sheetIndex) {

        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
        zacksDataInExcel.init();

        ExcelProp excelProp = new ExcelProp();
        excelProp.setWorkBookName(outFile);

        List<RankInfo> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(Constants.SEPARATOR_COMMA)) {
            currentRankingData.add(zacksDataInExcel.getETFData(symbol));
        }

        excelProp.setSheetName(sheetName);
        excelProp.setSheetIndex(sheetIndex);

        zacksDataInExcel.writeToOneXL(excelProp, currentRankingData, OUT_FILE_LOC, BACK_FILE_LOC);
    }
}
