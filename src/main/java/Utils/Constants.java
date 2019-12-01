package Utils;

import VO.MFInfo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by i852841 on 5/19/18.
 */
public class Constants {

    public static final String COMMA = ",";


    //################################################################ Currently Used: Start ################################################################

    public static final String SYMBOLS_T_INV19_LARGE       = "amzn,googl,fb,msft,aapl,nflx,nvda,v,blk,tsla,sq,ntnx,pvtl,ba,gm,sap,t,cy,amat,ko,x,pcg,sftby,jd," +
            "gbt,acb,iipr,nlsn,myok,edit,met,sum,hiiq,dis,etfc,wm,bmy,twou,zuo,slp,cvs";

    public static final String SYMBOLS_T_INV19_SMALL       = "ladr,cim,htgc,psec,lb,sig,ge,hpq,snap,gluu,swch,usat,sien,inov,bdsi,ino,gern,avxl,insy,nvax,ovid,akao,je,llnw," +
            "lac,trxc,aprn,nio,riot,rad,ftr,blnk,nlnk,jagx,cris,any,vtvt,egan,trvg,smrt,ipci,mark,oncs,avgr,mosy,crbp,dlph,hx,boxl,nvta";

    public static final String SYMBOLS_T_INV19_SUP_GROWTH  = "zen,roku,mdb,shop,dxc,twlo,irbt,rng,twou,prmw,fnko,ntn,iep";

    public static final String SYMBOLS_T_INV19_BASE        = "intu,adbe,anet,csco,panw,chtr,intc,lrcx,vmw,cgc,apha,cron,zg,atvi,docu,spot,wm,mmm,cat,wmt,cost,tgt,low,m,etsy," +
            "lulu,baba,tcehy,dis,ibm,twtr,dbx,fdx,gsk,pfe,lgnd,azn,abbv,jnj,unh,avgo,asml,rev,cmp,gis,ades,nke";

    public static final String SYMBOLS_T_INV19_ROBI100_PER = "arql,bdsi,sien,myok,hiiq,boom,prmw,ovid,ino,merc,aldx,vktx,sybx,qnst,crbp";

    public static final String SYMBOLS_T_INV19_LIFE        = "veev,ahsax,dhr,a,tmo,ilmn,tech";

    public static final String SYMBOLS_T_INV19_REIT        = "dlr,o,amt,vtr,spg,ladr,cim,htgc,stor,agnc,nly,nrz";

    public static final String SYMBOLS_TANZY_ETF_SMALL    = "pxsg,fyc,slyg,vempx,vscix";
    public static final String SYMBOLS_TANZY_ETF_THEMATIC = "botz,arkg,ahsax,kre,kbe,ung,mj,ipo,eos";
    public static final String SYMBOLS_TANZY_ETF_WATCH    = "mort,qqq,spy,vym,vti,vtv,ita,dia,iwm";
    public static final String SYMBOLS_TANZY_ETF_EMERGING = "iif,sret,sptm,spem,emfm,iemg,vemix,vtpsx";
    public static final String SYMBOLS_TANZY_ETF_INDEXES  = "xes,arkg,spy,qqq,xlk,xlf,xlre,xle,xlc,xlv,xlp,xly,xli,xlb,xlu,spdv,mj,schd,vt,soxx,soxl,ihi,kbe,efa,voo,vti," +
            "ivv,ibb,eem,sly,mdy,mdyg,slyg";
    public static final String SYMBOLS_TANZY_ETF_BOND_MLP = "fihbx,shyg,angl,cik,ghy,hyg,amlp,mlpa,mlpx,ymlp,mlpy,amj,mlpn,tlt,agg,reet";


    //################################################################ Currently Used: End ################################################################


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
    public static final SimpleDateFormat tsdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss.SSS");
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


    //India Investments

    public static final String OUT_FILE_IND_MF           = "IndMF.xlsx";
    public static final String OUT_FILE_IND_MF_SMALL_CAP = "IndMF_SmallCap.xlsx";
    public static final String OUT_FILE_IND_MF_SMALL_CAP_NEW = "NEW_IndMF_SmallCap.xlsx";
    public static final String OUT_FILE_IND_MF_MULTI_CAP = "IndMF_MultiCap.xlsx";
    public static final String OUT_FILE_IND_MF_MID_CAP   = "IndMF_MidCap.xlsx";
    public static final String OUT_FILE_IND_MF_INDEX     = "IndMF_Index.xlsx";

     public static Map<String, String> smallCapMap = new LinkedHashMap<String, String>() {{
         put("HDFC"         , "https://economictimes.indiatimes.com/hdfc-small-cap-fund--direct-plan/mffactsheet/schemeid-16617.cms");
         put("L&T"          , "https://economictimes.indiatimes.com/l%26t-emerging-businesses-fund-direct-growth-/mffactsheet/schemeid-26133.cms");
         put("Axis"         , "https://economictimes.indiatimes.com/axis-small-cap-fund-direct-growth-/mffactsheet/schemeid-22335.cms");
         put("SBI"          , "https://economictimes.indiatimes.com/sbi-small-cap-fund-direct-growth-/mffactsheet/schemeid-15787.cms");
         put("Kotak"        , "https://economictimes.indiatimes.com/Kotak%20Small%20Cap%20Fund%20Direct-Growth/mffactsheet/schemeid-16382.cms");
         put("Nipon"        , "https://economictimes.indiatimes.com/nippon-india-small-cap-fund--direct-plan/mffactsheet/schemeid-16182.cms");
         put("DSP"          , "https://economictimes.indiatimes.com/dsp-small-cap-direct-plan-growth-/mffactsheet/schemeid-16411.cms");
         put("ICICI"        , "https://economictimes.indiatimes.com/icici-prudential-smallcap-fund-direct-plan-growth-/mffactsheet/schemeid-17116.cms");
         put("Tata"         , "https://economictimes.indiatimes.com/tata-small-cap-fund-direct-growth-/mffactsheet/schemeid-37991.cms");
         put("Birla"        , "https://economictimes.indiatimes.com/aditya-birla-sun-life-small-cap-fund-direct-growth-/mffactsheet/schemeid-15935.cms");
         put("Motilal"      , "https://economictimes.indiatimes.com/motilal-oswal-nifty-smallcap-250-index-fund-direct-growth-/mffactsheet/schemeid-40244.cms");
         put("IDBI"         , "https://economictimes.indiatimes.com/idbi-small-cap-fund-direct-growth-/mffactsheet/schemeid-34272.cms");
         put("Sundaram"     , "https://economictimes.indiatimes.com/sundaram-small-cap-fund-direct-growth-/mffactsheet/schemeid-16112.cms");
         put("Invesco"      , "https://economictimes.indiatimes.com/invesco-india-smallcap-fund-direct-growth-/mffactsheet/schemeid-37843.cms");
         put("EdelWeiss"    , "https://economictimes.indiatimes.com/edelweiss-small-cap-fund-direct-growth-/mffactsheet/schemeid-38851.cms");
         put("Franklin"     , "https://economictimes.indiatimes.com/franklin-india-smaller-companies-direct-fund-growth-/mffactsheet/schemeid-16010.cms");
         put("BOI Axa"      , "https://economictimes.indiatimes.com/boi-axa-small-cap-fund-direct-growth-/mffactsheet/schemeid-38283.cms");
         put("Canara Robeco", "https://economictimes.indiatimes.com/canara-robeco-small-cap-fund-direct-growth-/mffactsheet/schemeid-38817.cms");
         put("HSBC"         , "https://economictimes.indiatimes.com/hsbc-small-cap-equity-fund-direct-growth-/mffactsheet/schemeid-16325.cms");
         put("Principal"    , "https://economictimes.indiatimes.com/principal-small-cap-fund-direct-growth-/mffactsheet/schemeid-39797.cms");
         put("Quant"        , "https://economictimes.indiatimes.com/quant-small-cap-fund-direct-plan-growth-/mffactsheet/schemeid-17366.cms");
         put("Union"        , "https://economictimes.indiatimes.com/union-small-cap-fund-direct-growth-/mffactsheet/schemeid-26860.cms");
     }};

    public static Map<String, String> multiCapMap = new LinkedHashMap<String, String>() {{
        put("HDFC"         , "https://economictimes.indiatimes.com/hdfc-focused-30-fund-direct-plan-growth-/mffactsheet/schemeid-16021.cms");
        put("L&T"          , "https://economictimes.indiatimes.com/l%26t-focused-equity-fund-direct-growth-/mffactsheet/schemeid-38083.cms");
        put("Axis"         , "https://economictimes.indiatimes.com/axis-focused-25-fund--direct-plan/mffactsheet/schemeid-15684.cms");
        put("SBI"          , "https://economictimes.indiatimes.com/sbi-focused-equity-fund--direct-plan/mffactsheet/schemeid-16324.cms");
        put("Kotak"        , "https://economictimes.indiatimes.com/kotak-standard-multicap-fund--direct-plan/mffactsheet/schemeid-17140.cms");
        put("Nipon"        , "https://economictimes.indiatimes.com/nippon-india-multi-cap-fund-direct-growth-/mffactsheet/schemeid-16068.cms");
        put("DSP"          , "https://economictimes.indiatimes.com/dsp-equity-direct-plan-growth-/mffactsheet/schemeid-16386.cms");
        put("ICICI"        , "https://economictimes.indiatimes.com/icici-prudential-multicap-fund-direct-plan-growth-/mffactsheet/schemeid-15874.cms");
        put("Tata"         , "https://economictimes.indiatimes.com/tata-retirement-savings-fund-moderate-plan-direct-growth-/mffactsheet/schemeid-17017.cms");
        put("Birla"        , "https://economictimes.indiatimes.com/aditya-birla-sun-life-equity-fund-direct-growth-/mffactsheet/schemeid-15741.cms");
        put("Motilal"      , "https://economictimes.indiatimes.com/motilal-oswal-multicap-35-fund--direct-plan/mffactsheet/schemeid-26125.cms");
        put("IDBI"         , "https://economictimes.indiatimes.com/idbi-diversified-equity-fund-direct-growth-/mffactsheet/schemeid-25144.cms");
        put("Sundaram"     , "https://economictimes.indiatimes.com/sundaram-equity-fund-direct-growth-/mffactsheet/schemeid-40260.cms");
        put("Invesco"      , "https://economictimes.indiatimes.com/invesco-india-multicap-fund-direct-growth-/mffactsheet/schemeid-16811.cms");
        put("EdelWeiss"    , "https://economictimes.indiatimes.com/edelweiss-multi-cap-fund-direct-growth-/mffactsheet/schemeid-29263.cms");
        put("Franklin"     , "https://economictimes.indiatimes.com/franklin-india-focused-equity-fund-direct-growth-/mffactsheet/schemeid-15990.cms");
        put("Canara Robeco", "https://economictimes.indiatimes.com/canara-robeco-equity-diversified-fund-direct-growth-/mffactsheet/schemeid-16588.cms");
        put("Principal"    , "https://economictimes.indiatimes.com/principal-multi-cap-growth-fund-direct-growth-/mffactsheet/schemeid-16580.cms");
        put("Quant"        , "https://economictimes.indiatimes.com/quant-active-fund-direct-growth-/mffactsheet/schemeid-17361.cms");
        put("Union"        , "https://economictimes.indiatimes.com/union-multi-cap-fund-direct-growth-/mffactsheet/schemeid-16412.cms");

        //Expense - .26 , NIFTY 200
        put("Mirae"        , "https://economictimes.indiatimes.com/mirae-asset-focused-fund-direct-growth-/mffactsheet/schemeid-39823.cms");

        put("ParagParikh"  , "https://economictimes.indiatimes.com/parag-parikh-long-term-equity-fund-direct-growth-/mffactsheet/schemeid-19701.cms");
    }};

    public static Map<String, String> smallCapMapOther = new LinkedHashMap<String, String>() {{
        put("HDFC"         , "https://economictimes.indiatimes.com/hdfc-small-cap-fund--direct-plan/fund-factsheet/schemeid-16617.cms");
        put("L&T"          , "https://economictimes.indiatimes.com/lt-emerging-businesses-fund--direct-plan/fund-factsheet/schemeid-26133.cms");
        put("Axis"         , "https://economictimes.indiatimes.com/axis-small-cap-fund--direct-plan/fund-factsheet/schemeid-22335.cms");
        put("SBI"          , "https://economictimes.indiatimes.com/sbi-small-cap-fund--direct-plan/fund-factsheet/schemeid-15787.cms");
        put("Kotak"        , "https://economictimes.indiatimes.com/kotak-small-cap-fund--direct-plan/fund-factsheet/schemeid-16382.cms");
        put("Nipon"        , "https://economictimes.indiatimes.com/nippon-india-small-cap-fund--direct-plan/fund-factsheet/schemeid-16182.cms");
        put("DSP"          , "https://economictimes.indiatimes.com/dsp-small-cap-fund--direct-plan/fund-factsheet/schemeid-16411.cms");
        put("ICICI"        , "https://economictimes.indiatimes.com/icici-prudential-smallcap-fund--direct-plan/fund-factsheet/schemeid-17116.cms");
        put("Tata"         , "https://economictimes.indiatimes.com/tata-small-cap-fund--direct-plan/fund-factsheet/schemeid-37991.cms");
        put("Birla"        , "https://economictimes.indiatimes.com/aditya-birla-sun-life-small-cap-fund--direct-plan/fund-factsheet/schemeid-15935.cms");
        put("Motilal"      , "https://economictimes.indiatimes.com/motilal-oswal-nifty-smallcap-250-index-fund--direct-plan/fund-factsheet/schemeid-40244.cms");
        put("IDBI"         , "https://economictimes.indiatimes.com/idbi-small-cap-fund--direct-plan/fund-factsheet/schemeid-34272.cms");
        put("Sundaram"     , "https://economictimes.indiatimes.com/sundaram-small-cap-fund--direct-plan/fund-factsheet/schemeid-16112.cms");
        put("Invesco"      , "https://economictimes.indiatimes.com/invesco-india-smallcap-fund--direct-plan/fund-factsheet/schemeid-37843.cms");
        put("EdelWeiss"    , "https://economictimes.indiatimes.com/edelweiss-small-cap-fund--direct-plan/fund-factsheet/schemeid-38851.cms");
        put("Franklin"     , "https://economictimes.indiatimes.com/franklin-india-smaller-companies-fund--direct-plan/fund-factsheet/schemeid-16010.cms");
        put("BOI Axa"      , "https://economictimes.indiatimes.com/boi-axa-small-cap-fund--direct-plan/fund-factsheet/schemeid-38283.cms");
        put("Canara Robeco", "https://economictimes.indiatimes.com/canara-robeco-small-cap-fund--direct-plan/fund-factsheet/schemeid-38817.cms");
        put("HSBC"         , "https://economictimes.indiatimes.com/hsbc-small-cap-equity-fund--direct-plan/fund-factsheet/schemeid-16325.cms");
        put("Principal"    , "https://economictimes.indiatimes.com/principal-small-cap-fund--direct-plan/fund-factsheet/schemeid-39797.cms");
        put("Quant"        , "https://economictimes.indiatimes.com/quant-small-cap-fund--direct-plan/fund-factsheet/schemeid-17366.cms");
        put("Union"        , "https://economictimes.indiatimes.com/union-small-cap-fund--direct-plan/fund-factsheet/schemeid-26860.cms");
    }};

    public static Map<String, String> smallCapFiftyTwoWeek = new LinkedHashMap<String, String>() {{
        put("HDFC"         , "https://www.personalfn.com/factsheet/hdfc-small-cap-fund-g-direct-plan");
        put("L&T"          , "https://www.personalfn.com/factsheet/hdfc-small-cap-fund-g-direct-plan");
        put("Axis"         , "https://www.personalfn.com/factsheet/axis-small-cap-fund-g-direct-plan");
        put("SBI"          , "https://www.personalfn.com/factsheet/sbi-small-cap-fund-g-direct-plan");
        put("Kotak"        , "https://www.personalfn.com/factsheet/kotak-small-cap-fund-g-direct-plan");
        put("Nipon"        , "https://www.personalfn.com/factsheet/nippon-india-small-cap-fund-g-direct-plan");
        put("DSP"          , "https://www.personalfn.com/factsheet/dsp-small-cap-fund-g-direct-plan");
        put("ICICI"        , "https://www.personalfn.com/factsheet/icici-pru-smallcap-fund-g-direct-plan");
        put("Tata"         , "https://www.personalfn.com/factsheet/tata-small-cap-fund-g-direct-plan");
        put("Birla"        , "https://www.personalfn.com/factsheet/aditya-birla-sl-small-cap-fund-g-direct-plan");
        put("Motilal"      , "https://www.personalfn.com/factsheet/motilal-oswal-nifty-smallcap-250-index-fund-g-direct-plan");
        put("IDBI"         , "https://www.personalfn.com/factsheet/idbi-small-cap-fund-g-direct-plan");
        put("Sundaram"     , "https://www.personalfn.com/factsheet/sundaram-small-cap-fund-g-direct-plan");
        put("Invesco"      , "https://www.personalfn.com/factsheet/invesco-india-smallcap-fund-g-direct-plan");
        put("EdelWeiss"    , "https://www.personalfn.com/factsheet/edelweiss-small-cap-fund-g-direct-plan");
        put("Franklin"     , "https://www.personalfn.com/factsheet/franklin-india-smaller-cos-fund-g-direct-plan");
        put("BOI Axa"      , "https://www.personalfn.com/factsheet/franklin-india-smaller-cos-fund-g-direct-plan");
        put("Canara Robeco", "https://www.personalfn.com/factsheet/canara-rob-small-cap-fund-g-direct-plan");
        put("Mirae"        , "https://www.personalfn.com/factsheet/hsbc-small-cap-equity-fund-g-direct-plan");
        put("Principal"    , "https://www.personalfn.com/factsheet/principal-small-cap-fund-g-direct-plan");
        put("Quant"        , "https://www.personalfn.com/factsheet/quant-small-cap-fund-g-direct-plan");
        put("UTI"          , "https://www.personalfn.com/factsheet/union-small-cap-fund-g-direct-plan");
    }};

    public static Map<String, String> midCapNAV = new LinkedHashMap<String, String>() {{
        put("HDFC"         , "https://economictimes.indiatimes.com/hdfc-mid-cap-opportunities-fund/mffactsheet/schemeid-5067.cms");
        put("L&T"          , "https://economictimes.indiatimes.com/lt-midcap-fund--direct-plan/mffactsheet/schemeid-16280.cms");
        put("Axis"         , "https://economictimes.indiatimes.com/axis-midcap-fund--direct-plan/mffactsheet/schemeid-15690.cms");
        put("SBI"          , "https://economictimes.indiatimes.com/sbi-magnum-midcap-fund--direct-plan/mffactsheet/schemeid-16235.cms");
        put("Kotak"        , "https://economictimes.indiatimes.com/kotak-emerging-equity-fund--direct-plan/mffactsheet/schemeid-17134.cms");
        put("Nipon"        , "https://economictimes.indiatimes.com/nippon-india-growth-fund--direct-plan/mffactsheet/schemeid-16083.cms");
        put("DSP"          , "https://economictimes.indiatimes.com/dsp-midcap-fund--direct-plan/mffactsheet/schemeid-16438.cms");
        put("ICICI"        , "https://economictimes.indiatimes.com/icici-prudential-midcap-fund--direct-plan/mffactsheet/schemeid-15960.cms");
        put("Tata"         , "https://economictimes.indiatimes.com/tata-midcap-growth-fund--direct-plan/mffactsheet/schemeid-16717.cms");
        put("Birla"        , "https://economictimes.indiatimes.com/aditya-birla-sun-life-mid-cap-fund--direct-plan/mffactsheet/schemeid-15870.cms");
        put("Motilal"      , "https://economictimes.indiatimes.com/motilal-oswal-large-and-midcap-fund--direct-plan/mffactsheet/schemeid-40338.cms");
        put("IDBI"         , "https://economictimes.indiatimes.com/idbi-midcap-fund--direct-plan/mffactsheet/schemeid-33333.cms");
        put("Sundaram"     , "https://economictimes.indiatimes.com/sundaram-mid-cap-fund--direct-plan/mffactsheet/schemeid-16125.cms");
        put("Invesco"      , "https://economictimes.indiatimes.com/invesco-india-mid-cap-fund--direct-plan/mffactsheet/schemeid-16807.cms");
        put("EdelWeiss"    , "https://economictimes.indiatimes.com/edelweiss-mid-cap-fund--direct-plan/mffactsheet/schemeid-16586.cms");
        put("Franklin"     , "https://economictimes.indiatimes.com/franklin-india-prima-fund--direct-plan/mffactsheet/schemeid-16003.cms");
        put("BOI Axa"      , "https://economictimes.indiatimes.com/boi-axa-mid-small-cap-equity-debt-fund--direct-plan/mffactsheet/schemeid-32609.cms");
        put("Canara Robeco", "https://economictimes.indiatimes.com/canara-robeco-emerging-equities-fund--direct-plan/mffactsheet/schemeid-16581.cms");
        put("Mirae"        , "https://economictimes.indiatimes.com/mirae-asset-midcap-fund--direct-plan/mffactsheet/schemeid-40048.cms");
        put("Principal"    , "https://economictimes.indiatimes.com/principal-midcap-fund--direct-plan/mffactsheet/schemeid-40417.cms");
        put("Quant"        , "https://economictimes.indiatimes.com/quant-mid-cap-fund--direct-plan/mffactsheet/schemeid-17375.cms");
        put("UTI"          , "https://economictimes.indiatimes.com/uti-mid-cap-fund--direct-plan/mffactsheet/schemeid-15920.cms");
    }};

    public static Map<String, String> midCapOther = new LinkedHashMap<String, String>() {{
        put("HDFC"         , "https://economictimes.indiatimes.com/hdfc-mid-cap-opportunities-fund/fund-factsheet/schemeid-5067.cms");
        put("L&T"          , "https://economictimes.indiatimes.com/lt-midcap-fund--direct-plan/fund-factsheet/schemeid-16280.cms");
        put("Axis"         , "https://economictimes.indiatimes.com/axis-midcap-fund--direct-plan/fund-factsheet/schemeid-15690.cms");
        put("SBI"          , "https://economictimes.indiatimes.com/sbi-magnum-midcap-fund--direct-plan/fund-factsheet/schemeid-16235.cms");
        put("Kotak"        , "https://economictimes.indiatimes.com/kotak-emerging-equity-fund--direct-plan/fund-factsheet/schemeid-17134.cms");
        put("Nipon"        , "https://economictimes.indiatimes.com/nippon-india-growth-fund--direct-plan/fund-factsheet/schemeid-16083.cms");
        put("DSP"          , "https://economictimes.indiatimes.com/dsp-midcap-fund--direct-plan/fund-factsheet/schemeid-16438.cms");
        put("ICICI"        , "https://economictimes.indiatimes.com/icici-prudential-midcap-fund--direct-plan/fund-factsheet/schemeid-15960.cms");
        put("Tata"         , "https://economictimes.indiatimes.com/tata-midcap-growth-fund--direct-plan/fund-factsheet/schemeid-16717.cms");
        put("Birla"        , "https://economictimes.indiatimes.com/aditya-birla-sun-life-mid-cap-fund--direct-plan/fund-factsheet/schemeid-15870.cms");
        put("Motilal"      , "https://economictimes.indiatimes.com/motilal-oswal-large-and-midcap-fund--direct-plan/fund-factsheet/schemeid-40338.cms");
        put("IDBI"         , "https://economictimes.indiatimes.com/idbi-midcap-fund--direct-plan/fund-factsheet/schemeid-33333.cms");
        put("Sundaram"     , "https://economictimes.indiatimes.com/sundaram-mid-cap-fund--direct-plan/fund-factsheet/schemeid-16125.cms");
        put("Invesco"      , "https://economictimes.indiatimes.com/invesco-india-mid-cap-fund--direct-plan/fund-factsheet/schemeid-16807.cms");
        put("EdelWeiss"    , "https://economictimes.indiatimes.com/edelweiss-mid-cap-fund--direct-plan/fund-factsheet/schemeid-16586.cms");
        put("Franklin"     , "https://economictimes.indiatimes.com/franklin-india-prima-fund--direct-plan/fund-factsheet/schemeid-16003.cms");
        put("BOI Axa"      , "https://economictimes.indiatimes.com/boi-axa-mid-small-cap-equity-debt-fund--direct-plan/fund-factsheet/schemeid-32609.cms");
        put("Canara Robeco", "https://economictimes.indiatimes.com/canara-robeco-emerging-equities-fund--direct-plan/fund-factsheet/schemeid-16581.cms");
        put("HSBC"         , "https://economictimes.indiatimes.com/mirae-asset-midcap-fund--direct-plan/fund-factsheet/schemeid-40048.cms");
        put("Principal"    , "https://economictimes.indiatimes.com/principal-midcap-fund--direct-plan/fund-factsheet/schemeid-40417.cms");
        put("Quant"        , "https://economictimes.indiatimes.com/quant-mid-cap-fund--direct-plan/fund-factsheet/schemeid-17375.cms");
        put("UTI"          , "https://economictimes.indiatimes.com/uti-mid-cap-fund--direct-plan/fund-factsheet/schemeid-15920.cms");
    }};


    public static final String TEST_OUT_FILE_IND_MF  = "TestIndMF.xlsx";
    public static Map<String, String> testMap = new LinkedHashMap<String, String>() {{
        //put("Axis"         , "https://www.personalfn.com/factsheet/axis-small-cap-fund-g-direct-plan");
        put("HDFC"         , "https://in.investing.com/indices/india-indices");
        //put("HDFC"         , "https://economictimes.indiatimes.com/hdfc-small-cap-fund--direct-plan/fund-factsheet/schemeid-16617.cms");

    }};



    public enum MF_IND_URI_ECONOMICTIMES {

        //NAV, 52 Week
        NAV("mffactsheet"),

        //1,3,6 Months Return, Benchmark, ER, AUM, FundManager, Top 5 Sector exposure, Market Cap exposure, PE, PB
        FACTS("fund-factsheet"),

        //Number of Holdings, Top5, Top10 Holdings Percentage, Top3, Top5 Sectors Holdings
        HOLDINGS("mffundinfo");

      private final String uri;

      MF_IND_URI_ECONOMICTIMES(String uri) {
          this.uri = uri;
      }

      public String getURI() {
          return this.uri;
      }
    }

    public enum MF_IND_URI_INVESTING {

        //NAV, 52 Week
        NAV;

        public String getURI() {
            return null;
        }
    }

    public enum MF_IND_QUERY_ECONOMICTIMES {

        nav(MFInfo.MF_PROPERTY.nav, "#mainSection > div > div > div > div.schemeDetails > div.navData > span.latestNav.pdlt20 > span.navValue"),
        fiftyTwoWeek(MFInfo.MF_PROPERTY.fiftyTwoWeek, "query2"),
        benchmark(MFInfo.MF_PROPERTY.benchmark, "#mainPage > div:nth-child(1) > div.subSec.mright40.factSheetSec > ul > li.benchmarkName > span.val.bold"),
        expenseRatio(MFInfo.MF_PROPERTY.expenseRatio, "#mainPage > div:nth-child(1) > div.subSec.mright40.factSheetSec > ul > li:nth-child(4) > span.val.bold"),
        aum(MFInfo.MF_PROPERTY.aum, "#mainPage > div:nth-child(1) > div.subSec.mright40.factSheetSec > ul > li:nth-child(3) > span.val.bold"),
        manager(MFInfo.MF_PROPERTY.manager, "#mainPage > div:nth-child(1) > div.subSec.mright40.factSheetSec > ul > li:nth-child(5) > span.val.bold"),
        //Fund vs Benchmark
        peRatio(MFInfo.MF_PROPERTY.peRatio, "#mainPage > div:nth-child(11) > div.subSec.mright40.valMetrics > ul:nth-child(3) > li:nth-child(1) > span.val,#mainPage > div:nth-child(11) > div.subSec.mright40.valMetrics > ul:nth-child(3) > li:nth-child(2) > span.val"),
        pbRatio(MFInfo.MF_PROPERTY.pbRatio, "#mainPage > div:nth-child(11) > div.subSec.mright40.valMetrics > ul:nth-child(5) > li:nth-child(1) > span.val,#mainPage > div:nth-child(11) > div.subSec.mright40.valMetrics > ul:nth-child(5) > li:nth-child(2) > span.val"),
        //Returns 1,3 & 6 Months, 1 Year, 3 Year, 5 Year
        returns(MFInfo.MF_PROPERTY.returns, "#mainPage > div.fullSec.trailReturns > div > div.data > div:nth-child(1) > div.fundData,#mainPage > div.fullSec.trailReturns > div > div.data > div:nth-child(2) > div.fundData,#mainPage > div.fullSec.trailReturns > div > div.data > div:nth-child(3) > div.fundData,#mainPage > div.fullSec.trailReturns > div > div.data > div:nth-child(4) > div.fundData,#mainPage > div.fullSec.trailReturns > div > div.data > div:nth-child(5) > div.fundData,#mainPage > div.fullSec.trailReturns > div > div.data > div:nth-child(6) > div.fundData"),
        holdingSectorTop5(MFInfo.MF_PROPERTY.holdingSectorTop5, "#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(1) > div.w50.flt,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(1) > div.w25.flt.txtCenter,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(2) > div.w50.flt\n" +
        ",#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(2) > div.w25.flt.txtCenter,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(3) > div.w50.flt,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(3) > div.w25.flt.txtCenter,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(4) > div.w50.flt,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(4) > div.w25.flt.txtCenter,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(5) > div.w50.flt,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(5) > div.w25.flt.txtCenter"),
        holdingTotalStocks(MFInfo.MF_PROPERTY.holdingTotalStocks, "#mainPage > div:nth-child(4) > div:nth-child(2) > ul > li:nth-child(1) > span.val"),
        holdingTotalSector(MFInfo.MF_PROPERTY.holdingTotalSector, "#mainPage > div:nth-child(4) > div:nth-child(2) > ul > li:nth-child(5) > span.val"),
        holdingPercentStockTop5(MFInfo.MF_PROPERTY.holdingPercentStockTop5, "#mainPage > div:nth-child(4) > div:nth-child(2) > ul > li:nth-child(2) > span.val"),
        holdingPercentStockTop10(MFInfo.MF_PROPERTY.holdingPercentStockTop10, "#mainPage > div:nth-child(4) > div:nth-child(2) > ul > li:nth-child(3) > span.val"),
        holdingPercentSectorTop3(MFInfo.MF_PROPERTY.holdingPercentSectorTop3, "#mainPage > div:nth-child(4) > div:nth-child(2) > ul > li:nth-child(6) > span.val"),
        holdingPercentSectorTop5(MFInfo.MF_PROPERTY.holdingPercentSectorTop5, "#mainPage > div:nth-child(4) > div:nth-child(2) > ul > li:nth-child(7) > span.val"),
        //Giant, Large, Mid, Small, Micro
        holdingMarketCap(MFInfo.MF_PROPERTY.holdingMarketCap, "#mainPage > div:nth-child(9) > div.subSec.mright40.top5Sec.mrktCap > div.data > div:nth-child(1) > div.w20.flt.txtCenter,#mainPage > div:nth-child(9) > div.subSec.mright40.top5Sec.mrktCap > div.data > div:nth-child(2) > div.w20.flt.txtCenter,#mainPage > div:nth-child(9) > div.subSec.mright40.top5Sec.mrktCap > div.data > div:nth-child(3) > div.w20.flt.txtCenter,#mainPage > div:nth-child(9) > div.subSec.mright40.top5Sec.mrktCap > div.data > div:nth-child(4) > div.w20.flt.txtCenter,#mainPage > div:nth-child(9) > div.subSec.mright40.top5Sec.mrktCap > div.data > div:nth-child(5) > div.w20.flt.txtCenter");

        private final MFInfo.MF_PROPERTY mfInfoProperty;
        private final String             query;

        MF_IND_QUERY_ECONOMICTIMES(MFInfo.MF_PROPERTY mfInfoProperty, String query) {
            this.query          = query;
            this.mfInfoProperty = mfInfoProperty;
        }

        public String getQuery() {
            return this.query;
        }

        public String getMFProperty() {
            return this.mfInfoProperty.toString();
        }
    }

    /**
     * If second argument is null, that signifies its single page multi query
     *
     */
    public enum MF_IND_QUERY_INVESTING {

        nav(MFInfo.MF_PROPERTY.nav, "");

        private final MFInfo.MF_PROPERTY mfInfoProperty;
        private final String             query;

        MF_IND_QUERY_INVESTING(MFInfo.MF_PROPERTY mfInfoProperty, String query) {
            this.query          = query;
            this.mfInfoProperty = mfInfoProperty;
        }

        public String getQuery() {
            return this.query;
        }

        public String getMFProperty() {
            return this.mfInfoProperty.toString();
        }
    }


    /**
     * 1 - Fund name like hdfc-small-cap-fund--direct-plan
     * 2 - URI like mffactsheet
     * 3 - Scheme ID like schemeid-16617
     * All above examples are part of
     * https://economictimes.indiatimes.com/hdfc-small-cap-fund--direct-plan/mffactsheet/schemeid-16617.cms
     */
    public static String url_economictimes = "https://economictimes.indiatimes.com/${arg1}/${arg2}/${arg3}.cms";
    //public static String url_investing     = "https://in.investing.com/indices/india-indices";
    public static String url_investing     = "https://in.investing.com/indices/india-indices?&majorIndices=on&primarySectors=on&additionalIndices=on&otherIndices=on";


    public static final Map<String, String> ECONOMIC_TIMES_SCHEME_ID_MAP_SML = new LinkedHashMap<String, String>(){{

        put("HDFC"         , "hdfc-small-cap-fund--direct-plan,schemeid-16617");
        put("L&T"          , "l%26t-emerging-businesses-fund-direct-growth-,schemeid-26133");
        put("Axis"         , "axis-small-cap-fund-direct-growth-,schemeid-22335");
//        put("SBI"          , "sbi-small-cap-fund-direct-growth-,schemeid-15787");
//        put("Kotak"        , "Kotak%20Small%20Cap%20Fund%20Direct-Growth,schemeid-16382");
//        put("Nipon"        , "nippon-india-small-cap-fund--direct-plan,schemeid-16182");
//        put("DSP"          , "dsp-small-cap-direct-plan-growth-,schemeid-16411");
//        put("ICICI"        , "icici-prudential-smallcap-fund-direct-plan-growth-,schemeid-17116");
//        put("Tata"         , "tata-small-cap-fund-direct-growth-,schemeid-37991");
//        put("Birla"        , "aditya-birla-sun-life-small-cap-fund-direct-growth-,schemeid-15935");
//        put("Motilal"      , "motilal-oswal-nifty-smallcap-250-index-fund-direct-growth-,schemeid-40244");
//        put("IDBI"         , "idbi-small-cap-fund-direct-growth-,schemeid-34272");
//        put("Sundaram"     , "sundaram-small-cap-fund-direct-growth-,schemeid-16112");
//        put("Invesco"      , "invesco-india-smallcap-fund-direct-growth-,schemeid-37843");
//        put("EdelWeiss"    , "edelweiss-small-cap-fund-direct-growth-,schemeid-38851");
//        put("Franklin"     , "franklin-india-smaller-companies-direct-fund-growth-,schemeid-16010");
//        put("BOI Axa"      , "boi-axa-small-cap-fund-direct-growth-,schemeid-38283");
//        put("Canara Robeco", "canara-robeco-small-cap-fund-direct-growth-,schemeid-38817");
//        put("HSBC"         , "hsbc-small-cap-equity-fund-direct-growth-,schemeid-16325");
//        put("Principal"    , "principal-small-cap-fund-direct-growth-,schemeid-39797");
//        put("Quant"        , "quant-small-cap-fund-direct-plan-growth-,schemeid-17366");
//        put("Union"        , "union-small-cap-fund-direct-growth-,schemeid-26860");

    }};

    public static Map<String, String> ECONOMIC_TIMES_SCHEME_ID_MAP_MULTI = new LinkedHashMap<String, String>() {{
        put("HDFC"         , "hdfc-focused-30-fund-direct-plan-growth-,schemeid-16021");
        put("L&T"          , "l%26t-focused-equity-fund-direct-growth-,schemeid-38083");
    }};

    public static final Map<String, String> INVESTING_SCHEME_ID_MAP = new LinkedHashMap<String, String>(){{

        put("Nifty Small Cap 100"        , "#pair_17948 > td.pid-17948-last");
        put("Nifty Smallcap 250"         , "#pair_1141645 > td.pid-1141645-last");
        put("BSE SmallCap"               , "#pair_39937 > td.pid-39937-last");
        put("BSE 250 Smallcap"           , "#pair_1066766 > td.pid-1066766-last");
        put("BSE 400 MidSmallCap"        , "#pair_1066767 > td.pid-1066767-last");
        put("Nifty Midcap 50"            , "#pair_17947 > td.pid-17947-last");
        put("NIFTY Midcap 100"           , "#pair_17946 > td.pid-17946-last");
        put("BSE 150 Midcap"             , "#pair_1066764 > td.pid-1066764-last");
        put("Nifty 50"                   , "#pair_17940 > td.pid-17940-last");
        put("Nifty 500"                  , "#pair_17945 > td.pid-17945-last");
        put("BSE Sensex"                 , "#pair_39929 > td.pid-39929-last");
        put("BSE Sensex 50"              , "#pair_1066776 > td.pid-1066776-last");
        put("BSE Sensex Next 50"         , "#pair_1066777 > td.pid-1066777-last");
        put("BSE India Manufacturing"    , "#pair_1066771 > td.pid-1066771-last");
        put("India VIX"                  , "#pair_17942 > td.pid-17942-last");
    }};


    public static void main(String[] args) {
        //System.out.println(String.format(url_economictimes, "one","two","three"));

//        System.out.println(MF_IND_QUERY_ECONOMICTIMES.nav.getQuery());
//        System.out.println(MF_IND_QUERY_ECONOMICTIMES.nav.getMFProperty());
//        System.out.println(MF_IND_QUERY_ECONOMICTIMES.nav.toString());
//
//        System.out.println(MF_IND_QUERY_ECONOMICTIMES.fiftyTwoWeek.getQuery());
//        System.out.println(MF_IND_QUERY_ECONOMICTIMES.fiftyTwoWeek.getMFProperty());


        //Get Enum object by string https://www.java67.com/2012/08/string-to-enum-in-java-conversion.html
//        String str = "nav";
//        Enum propQueryEnum =  Enum.valueOf(MF_IND_QUERY_ECONOMICTIMES.class, str);
//        System.out.println(propQueryEnum);
//        System.out.println(((MF_IND_QUERY_ECONOMICTIMES) propQueryEnum).getMFProperty());
//        System.out.println(((MF_IND_QUERY_ECONOMICTIMES) propQueryEnum).getQuery());


        getStr("#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(1) > div.w50.flt,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(1) > div.w25.flt.txtCenter,#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(2) > div.w50.flt\\n\" +\n" +
                "        \",#marketcap > div.subSec.mright40.top5Sec > div.data > div:nth-child(2) > div.w25.flt.txtCenter");


    }


    private static void getStr(String str) {

        String[] strnew = str.split(",");
        System.out.println(strnew.length);
    }


}
