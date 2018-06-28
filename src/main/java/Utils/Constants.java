package Utils;

import java.text.SimpleDateFormat;

/**
 * Created by i852841 on 5/19/18.
 */
public class Constants {

    //Config constants
    public static final String OUT_DIR_TANZY_WATCH     = "/TanzyWatch";
    public static final String OUT_DIR_TANZY_WATCH2    = "/TanzyWatch2";
    public static final String OUT_DIR_TANZY_INVESTED  = "/TanzyInvested";
    public static final String OUT_DIR_TANZY_TO_INVEST = "/TanzyToInvest";
    public static final String OUT_DIR_TANZY_PENNY     = "/TanzyPenny";
    public static final String OUT_DIR_TANZY_GROWTH    = "/TanzyGrowth";
    public static final String OUT_DIR_TANZY_REPORT    = "/TanzyReport";
    public static final String OUT_DIR_TANZY_REIT      = "/TanzyREIT";
    public static final String OUT_DIR_TANZY_ETF       = "/Tanzy_ETF";
    public static final String OUT_DIR_D3L             = "/D3L";

    public static final String SYMBOLS_TANZY_WATCH        = "amzn,goog,googl,nflx,aapl,nvda,fb,shop,tsla,adbe,anet,ba,mu,wmt," +
            "tgt,exas,ebay,baba,amat,crus,twtr,spot,docu,ibm,vmw,gsk";
    public static final String SYMBOLS_TANZY_WATCH2       = "Dcph,nvax,ftr,nly,mnk,cy,ddd,qd,mat,ctrl,ctl,trvg,tzoo,trip,yelp,mtch,pypl,avgo,vrtx,algn,txn,low,gme,big,rost,blk," +
            "vslr,abbv,fate,lmt,bl,fdx,cmcsa,hznp,dbx,cat,fdc,phm,alna,tol,symc,tmus,m,ibn,cost,f,rrc,kmi";
    public static final String SYMBOLS_TANZY_INVESTED     = "sq,roku,irbt,ntnx,abcd,curo,lb,gluu,ge,t,gern,wb,amd,intc,gm,abb,teva,alb," +
            "sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc,snap,psec,glad,llnw";
    public static final String SYMBOLS_TANZY_TO_INVEST    = "etsy,pvtl,irt,rng,tndm,conn,amma,edit,hcp,incy";
    public static final String SYMBOLS_TANZY_PENNY        = "rad,jagx,aprn,opgn,zsan,cris,nete,sgyp,agen,avxl,ohrp,snes,insy,chk,ftr,mobl,nvax,nihd,jcp,smrt,grpn,xene";
    public static final String SYMBOLS_TANZY_GROWTH       = "crox,tbbk,spar,usat,cdxs,tndm";
    public static final String SYMBOLS_TANZY_REPORT       = "abcd,gluu,gern,sq,ntnx,roku,irbt,jd,m,etsy,curo,brk.b";
    public static final String SYMBOLS_TANZY_REIT         = "IRT,HCP,KIM,MORT,PLD";
    public static final String SYMBOLS_TANZY_ETF_SMALL    = "PXSG,FYC,SLYG";
    public static final String SYMBOLS_TANZY_ETF_THEMATIC = "BOTZ,ARKG";
    public static final String SYMBOLS_TANZY_ETF_WATCH    = "MORT,QQQ,SPY,VYM,VTI,VTV,ITA,DIA,IWM";
    public static final String SYMBOLS_D3L    = "NBR,SLB,XES,HCLP";

    public static final String OUT_FILE_TANZY_WATCH     = "TanzyWatch.xlsx";
    public static final String OUT_FILE_TANZY_WATCH2    = "TanzyWatch2.xlsx";
    public static final String OUT_FILE_TANZY_INVESTED  = "TanzyInvested.xlsx";
    public static final String OUT_FILE_TANZY_TO_INVEST = "TanzyToInvest.xlsx";
    public static final String OUT_FILE_TANZY_PENNY     = "TanzyPenny.xlsx";
    public static final String OUT_FILE_TANZY_GROWTH    = "TanzyGrowth.xlsx";
    public static final String OUT_FILE_TANZY_REPORT    = "TanzyReport.xlsx";
    public static final String OUT_FILE_TANZY_REIT      = "TanzyREIT.xlsx";
    public static final String OUT_FILE_TANZY_ETF       = "Tanzy_ETF.xlsx";
    public static final String OUT_FILE_D3L             = "D3L.xlsx";


    public static final String TEMP_PREFIX             = "TEMP_";
    public static final String SEPARATOR_RANK_PRICE    = " -- ";
    public static final String SEPARATOR_OPEN_BRACE    = "(";
    public static final String SEPARATOR_CLOSE_BRACE   = ")";
    public static final String SEPARATOR_WHITE_SPACE   = " ";
    public static final String SEPARATOR_COMMA         = ",";
    public static final String SEPARATOR_UNDERSCORE    = "_";
    public static final String EXTENSION_CSV           = ".csv";
    public static final String ZERO_VALUE              = "0";
    public static final String UNAVAILABLE             = "UN";
    public static final String EXTENSION_XLSX          = ".xlsx";

    public static final SimpleDateFormat sdf   = new SimpleDateFormat("MMM_dd");
    public static final SimpleDateFormat tsdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    public static final SimpleDateFormat ysdf  = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat dsdf  = new SimpleDateFormat("M/dd/yyyy");

    public static final String RATING_RAISES_TARGET      = "Raises Target";
    public static final String RATING_SET_PRICE_TARGET   = "Set Price Target";
    public static final String RATING_BOOST_PRICE_TARGET = "Boost Price Target";
    public static final String RATING_UPGRADES           = "Upgrades";
    public static final String RATING_LOWERS_TARGET      = "Lowers Target";
    public static final String RATING_DOWNGRADES         = "Downgrades";
    public static final String RATING_REITERIATES        = "Reiterates";
    public static final String RATING_INITIATES          = "Initiates";
}
