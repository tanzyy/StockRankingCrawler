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
    public static final String OUT_DIR_NEW             = "/New";
    public static final String OUT_DIR_TANZY_BIO       = "/Tanzy_Bio";
    public static final String OUT_DIR_TANZY_IOT       = "/Tanzy_IOT";
    public static final String OUT_DIR_TANZY_STABLE    = "/Tanzy_Stable";
    public static final String OUT_DIR_TANZY_MID_SPEC  = "/Tanzy_MidSpec";
    public static final String OUT_DIR_TANZY_SML_SPEC  = "/Tanzy_SmlSpec";
    public static final String OUT_DIR_TANZY_INDEX        = "/Tanzy_Index";
    public static final String OUT_DIR_TANZY_CURRENTWATCH = "/Tanzy_CurrentWatch";
    public static final String OUT_DIR_TANZY_NEWIPO       = "/Tanzy_NewIPO";
    public static final String OUT_DIR_T_INV_LARGE        = "/TInvLarge";
    public static final String OUT_DIR_T_INV_SMALL        = "/TInvSmall";
    public static final String OUT_DIR_T_INV_BASE         = "/TInvBase";
    public static final String OUT_DIR_T_INV_LS           = "/TInvLifeSceince";


    public static final String OUT_DIR_T_INV19_LARGE          = "/T_INV19_LARGE";
    public static final String OUT_DIR_T_INV19_SMALL          = "/T_INV19_SMALL";
    public static final String OUT_DIR_T_INV19_SUP_GROWTH     = "/T_INV19_SUP_GROWTH";
    public static final String OUT_DIR_T_INV19_BASE           = "/T_INV19_BASE";
    public static final String OUT_DIR_T_INV19_ROBI100_PER    = "/T_INV19_ROBI100_PER";
    public static final String OUT_DIR_T_INV19_LIFE           = "/T_INV19_LIFE";
    public static final String OUT_DIR_T_INV19_REIT           = "/T_INV19_REIT";


    public static final String SYMBOLS_TANZY_STABLE       = "v,ma,ba,pep,ko,awk,gsk,jnj,jci,aapl,wmt,msft,fb";
    public static final String SYMBOLS_TANZY_MID_SPEC     = "zen,rng,nlsn";
    public static final String SYMBOLS_TANZY_SML_SPEC     = "gluu,riot,blnk,ovid,lac,usat,swch,sybx,trxc";
    public static final String SYMBOLS_TANZY_INDEX        = "amzn,googl,fb,msft,aapl,nflx,nvda,v,tsla,gm,adbe,sap,ba,ko,m,cost,tgt,wmt,hd,low,pg,wm,azn,abbv,gsk,pfe,unh,ibm,t,cmcsa,csco,anet,intc,cy,amat,blk,sq,roku,irbt,shop,ntnx,zen,ge,snap,lac,psec,jd,avxl,gern,trxc,aprn,nio,jagx,cris,gluu,ovid,akao,blnk,nlnk";
    public static final String SYMBOLS_TANZY_CURRENTWATCH = "swch,zts,x,sum,hain,flmn,arlo,awk,jci,rng,nlsn,vtsmx,any,etsy";
    public static final String SYMBOLS_TANZY_NEWIPO       = "flmn,arlo,pvtl,curo,nbev,sfix,docu,dbx";


    public static final String SYMBOLS_T_INV19_LARGE       = "amzn,googl,fb,msft,aapl,nflx,nvda,v,blk,tsla,sq,ntnx,pvtl,ba,gm,sap,t,cy,amat,ko,x,pcg,sftby,jd,gbt,acb,iipr,nlsn,myok,edit";

    public static final String SYMBOLS_T_INV19_SMALL       = "ladr,cim,htgc,psec,lb,sig,ge,hpq,snap,gluu,swch,usat,sien,inov,bdsi,ino,gern,avxl,insy,nvax,ovid,akao,je,llnw,lac,trxc,aprn,nio,riot,rad,ftr,blnk,nlnk,jagx,cris,any,vtvt,egan,trvg,smrt,ipci,mark,oncs,avgr,mosy";

    public static final String SYMBOLS_T_INV19_SUP_GROWTH  = "zen,roku,mdb,shop,dxc,twlo,irbt,rng,twou,prmw,fnko,ntn,iep";

    public static final String SYMBOLS_T_INV19_BASE        = "intu,adbe,anet,csco,panw,chtr,intc,lrcx,vmw,cgc,apha,cron,zg,atvi,docu,spot,wm,mmm,cat,wmt,cost,tgt,low,m,etsy,lulu,baba,techy,dis,ibm,twtr,dbx,fdx,gsk,pfe,lgnd,azn,abbv,jnj";

    public static final String SYMBOLS_T_INV19_ROBI100_PER = "arql,bdsi,sien,myok,hiiq,boom,prmw,ovid,ino,merc,aldx,vktx,sybx";

    public static final String SYMBOLS_T_INV19_LIFE        = "veev,ahsax,dhr,a,tmo,ilmn,tech";

    public static final String SYMBOLS_T_INV19_REIT        = "dlr,o,amt,vtr,spg,ladr,cim,htgc";




    public static final String SYMBOLS_T_INV_LARGE        =
            "amzn,googl,fb,msft,aapl,nflx,nvda,v,tsla,sq,roku,ntnx,zen,pvtl,ba,gm,sap,t,cy,amat,ko,swch,x,pcg,sftby,jd,botz,xbi,sret,gbt";

    public static final String SYMBOLS_T_INV_SMALL          =
            "psec,snap,gluu,usat,gern,avxl,ovid,je,llnw,lac,trxc,aprn,nio,riot,rad,ftr,akao,blnk,nlnk,jagx,cris,any,vtvt,egan,trvg,smrt,ipci,mark,oncs,avgr,mosy";

    public static final String SYMBOLS_T_INV_BASE          =
    "tlry,cgc,acb,rng,twlo,ge,ibm,adbe,blk,etsy,m,cost,tgt,wmt,hd,low,pg,wm,azn,abbv,gsk,pfe,unh,cmcsa,csco,anet,intc,blk,irbt,shop,dis,brk-b,lulu,mdb,twou";

    public static final String SYMBOLS_T_INV_LS            = "dhr,a,tmo,ilmn,tech";

    public static final String SYMBOLS_TANZY_INVESTED     = "sq,roku,irbt,ntnx,abcd,curo,lb,gluu,ge,t,gern,wb,amd,intc,gm,abb,teva,alb," +
            "sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc,snap,psec,glad,llnw";

    public static final String SYMBOLS_TANZY_TO_INVEST    = "etsy,pvtl,irt,rng,tndm,conn,amma,edit,hcp,incy,aoi,hclp,i,hznp,uctt,aray,ades,ko,usb,bk,flex,nuan,veri,ssys,abbv";

    public static final String SYMBOLS_TANZY_WATCH        = "amzn,goog,googl,nflx,aapl,nvda,fb,shop,tsla,adbe,anet,ba,mu,wmt," +
            "tgt,exas,ebay,baba,amat,crus,twtr,spot,docu,ibm,vmw,gsk";

    public static final String SYMBOLS_TANZY_WATCH2       = "Dcph,nvax,ftr,nly,mnk,cy,ddd,qd,mat,ctrl,ctl,trvg,tzoo,trip,yelp,mtch,pypl,avgo,vrtx,algn,txn,low,gme,big,rost,blk," +
            "vslr,abbv,fate,lmt,bl,fdx,cmcsa,hznp,dbx,cat,fdc,phm,alna,tol,symc,tmus,m,ibn,cost,f,rrc,kmi";


    public static final String SYMBOLS_TEST_1            = "amzn,nflx,aapl";
    public static final String SYMBOLS_TEST_2            = "nvda,fb,shop";
    public static final String SYMBOLS_TEST_3            = "sq,roku,mdb";
    public static final String SYMBOLS_TEST_4            = "adbe,msft,tsla";

    public static final String SYMBOLS_TANZY_PENNY        = "rad,jagx,aprn,opgn,zsan,cris,nete,sgyp,agen,avxl,ohrp,snes,insy,chk,ftr,mobl,nvax,nihd,jcp,smrt,grpn,xene";
    public static final String SYMBOLS_TANZY_GROWTH       = "crox,tbbk,spar,usat,cdxs,tndm,abbv,xene,incy,md,evhc,mnk,swch";
    public static final String SYMBOLS_TANZY_REPORT       = "abcd,gluu,gern,sq,ntnx,roku,irbt,jd,m,etsy,curo,brk.b";
    public static final String SYMBOLS_TANZY_REIT         = "irt,hcp,kim,mort,pld";
    public static final String SYMBOLS_TANZY_ETF_SMALL    = "pxsg,fyc,slyg";
    public static final String SYMBOLS_TANZY_ETF_THEMATIC = "botz,arkg,ahsax";
    public static final String SYMBOLS_TANZY_ETF_WATCH    = "mort,qqq,spy,vym,vti,vtv,ita,dia,iwm";
    public static final String SYMBOLS_TANZY_ETF_EMERGING = "iif,sret,sptm,spem,emfm,iemg";
    public static final String SYMBOLS_TANZY_ETF_INDEXES  = "xes,arkg,spy,qqq,xlk,xlf,xlre,xle,xlc,xlv,xlp,xly,xli,xlb,xlu,spdv,mj,schd,vt,soxx,soxl,ihi,kbe,efa,voo,vti,ivv,ibb,eem,sly,mdy,mdyg,slyg";
    public static final String SYMBOLS_D3L                = "nbr,slb,xes,hclp,gdxs,itub,lmfa,ueps,gst,vedl,staf,gol";

    public static final String SYMBOLS_NEW                = "dlr,o,amt,vtr,spg,ladr,cim,htgc,amlp";
    public static final String SYMBOLS_TANZY_BIO          = "axon,cort,hznp,innt,jnce,ziop,sgmo";
    public static final String SYMBOLS_TANZY_IOT          = "swir,cy,mrvl,stm,cree,baba,nvda,ibm";

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
    public static final String OUT_FILE_NEW             = "New.xlsx";
    public static final String OUT_FILE_TANZY_BIO       = "TanzyBio.xlsx";
    public static final String OUT_FILE_TANZY_IOT       = "TanzyIOT.xlsx";
    public static final String OUT_FILE_TANZY_STABLE    = "TanzyStable.xlsx";
    public static final String OUT_FILE_TANZY_MIDSPEC   = "TanzyMIDSpec.xlsx";
    public static final String OUT_FILE_TANZY_SMLSPEC   = "TanzySMLSpec.xlsx";
    public static final String OUT_FILE_TANZY_INDEX         = "TanzyIndex.xlsx";
    public static final String OUT_FILE_TANZY_CURRENTWATCH  = "TanzyCurrentWatch.xlsx";
    public static final String OUT_FILE_TANZY_NEWIPO        = "TanzyNewIPO.xlsx";
    public static final String OUT_FILE_T_INV_LARGE         = "TInvLarge.xlsx";
    public static final String OUT_FILE_T_INV_SMALL         = "TInvSmall.xlsx";
    public static final String OUT_FILE_T_INV_BASE          = "TInvBase.xlsx";
    public static final String OUT_FILE_T_INV_LS            = "TInvLifeSceince.xlsx";
    public static final String OUT_FILE_TANZY_TEST          = "Tanzy_TEST.xlsx";
    public static final String OUT_FILE_TANZY_INV_2019      = "Tanzy_Inv2019.xlsx";

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
