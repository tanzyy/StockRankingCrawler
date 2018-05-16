package Financial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by i852841 on 5/12/18.
 */


/**
 * Todo
 * 1. Write Unit Tests
 */
public class ZacksData {

    private static final int    DATA_SIZE_IN_MB        = 100;
    private static final String ZACKS_SEPARATOR        = "-";
    private static final String SEPARATOR_SYMBOL_RANK  = ":";
    private static final String SEPARATOR_COMMA        = ",";
    private static final String SEPARATOR_UNDERSCORE   = "_";
    private static final String EXTENSION_CSV          = ".csv";
    private static final String ZACKS_MAIN_URL         = "https://www.zacks.com/stock/quote/";
    private static final String ZACKS_RANK_UNAVAILABLE = "0";

    private static final String SYMBOLS_TANZY_WATCH      = "amzn,goog,googl,nflx,aapl,nvda,shop,tsla,adbe,anet,ba,mu,ter,wmt,tgt,edit,exas,t,tmus,cmcsa,ebay";
    private static final String OUT_FILE_TANZY_WATCH     = "TanzyWatch.csv";

    private static final String SYMBOLS_TANZY_INVESTED   = "sq,roku,irbt,wb,hibb,ibn,cost,amd,intc,f,gm,abb,rrc,kmi,teva,alb,sap,wm,bac,botz,jd,mpw,msft,pfe,rds.a,wfc";
    private static final String OUT_FILE_TANZY_INVESTED  = "TanzyInvested.csv";

    private static final String SYMBOLS_TANZY_TO_INVEST  = "m,etsy,ntnx,curo,brkb,qqq";
    private static final String OUT_FILE_TANZY_TO_INVEST = "TanzyToInvest.csv";

    private static final String SYMBOLS_TANZY_PENNY      = "gern,rad,jagx,aprn,opgn,zsan,cris,nete,sgyp,agen,avxl,ohrp,snes,insy,chk,ftr,mobl,nvax,nihd";
    private static final String OUT_FILE_TANZY_PENNY     = "TanzyPenny.csv";

    private static final String SYMBOLS_TANZY_GROWTH     = "abcd,gluu,llnw,crox,tbbk,spar,usat,cdxs,tndm";
    private static final String OUT_FILE_TANZY_GROWTH    = "TanzyGrowth.csv";

    private static final String OUT_FILE_LOC     = "/Users/i852841/Desktop/Personal/Finance_Learn_Reports/ZacksRank/";
    private static final String BACK_FILE_LOC    = "/Users/i852841/Desktop/Personal/Finance_Learn_Reports/ZacksRank/Backup/";

    public static final SimpleDateFormat sdf  = new SimpleDateFormat("MMM_dd");

    private String rankDate;

    private void init() {
        getCurrentTimeInString();
    }

    private String getData(String symbol) {

        Document document = null;
        String result     = "";

        try {
            String targetURL = ZACKS_MAIN_URL + symbol.toUpperCase() + "?q=" + symbol;
            System.out.println(targetURL);

            document = Jsoup.connect(targetURL).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            Element rank = document.select("div.zr_rankbox").first().select("p.rank_view").first();

            result = symbol + SEPARATOR_SYMBOL_RANK + getRankStr(filterData(rank.text()));
            System.out.println(result);

        } catch(Exception e) {
            System.out.println(String.format("Error occurred for [%s] with error %s ", symbol,  e));
            result = symbol + SEPARATOR_SYMBOL_RANK + "NA";
            System.out.println(result);
        }

        return result;
    }

    /**
     * Input Data Format: Symbol: date - Rank (abcd:3)
     * OutFile content format-->  , date , date
     *                      symbol, rank , rank
     *                      symbol, rank , rank
     * @param allFetchedData
     */
    public void writeToFile(List<String> allFetchedData, String outFileName, String backupFileName) {

        BufferedWriter bw = null;
        BufferedReader br = null;
        FileWriter fw     = null;
        FileReader fr     = null;
        File file         = null;

        boolean firstRowExist = true;
        List<String> fileDataList = new ArrayList<>();

        try {

            file = new File(OUT_FILE_LOC + outFileName);
            if (!file.exists()) {
                file.createNewFile();
                firstRowExist = false;
            } else {
                File backupFile =  new File(BACK_FILE_LOC + backupFileName);
                if(backupFile.exists())
                    backupFile.delete();

                Files.copy(file.toPath(), backupFile.toPath());
            }

            fr = new FileReader(file.getAbsoluteFile());
            br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null) {
                System.out.println("File data [ " + line + "]");
                fileDataList.add(line);
            }

            //Get the number of commas so that we can that many commas for non existing record so all the ranks captured at same date will be on same column.
            String postfixComma = "";
            if(fileDataList.size() > 0) {
                postfixComma = getNumberOfCommas(fileDataList.get(0));
                System.out.println(String.format("The number of commas [ %s ]", postfixComma));
            }

            // This will handle if file does not exist
            if(postfixComma.length() == 0)
                postfixComma = SEPARATOR_COMMA;

            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            if(firstRowExist) {
                System.out.println("First row data is: " + fileDataList.get(0));
                fw.write(fileDataList.get(0) + "\n");
            }


            boolean isMatched = false;
            for(String fetchedData : allFetchedData) {

                isMatched                      = false;
                String[] inData                = fetchedData.split(SEPARATOR_SYMBOL_RANK);
                String symbolFromFetchedData   = inData[0];
                String dateRankFromFetchedData = inData[1];

                for(String fileData : fileDataList) {

                    String symbolFromFileData = fileData.split(SEPARATOR_COMMA)[0];
                    String restFromFileData   = fileData.substring(fileData.indexOf(",") , fileData.length());

                    if(symbolFromFileData.equalsIgnoreCase(symbolFromFetchedData)) {

                        //String finalData = fileData + SEPARATOR_COMMA + dateRankFromFetchedData;
                        String finalData = symbolFromFileData + SEPARATOR_COMMA + dateRankFromFetchedData + restFromFileData ;
                        bw.write(finalData);
                        bw.write("\n");
                        isMatched = true;
                        break;
                    }
                }

                if(!isMatched) {
                    //ToDo: Same length as of maximum file data
                    bw.write( symbolFromFetchedData.toUpperCase() + SEPARATOR_COMMA + dateRankFromFetchedData + postfixComma);
                    bw.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file " + file.getAbsolutePath() + e.toString());
        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                System.out.println("Error occurred when closing bufferedwriter/filewriter " + ex.toString());
            }
        }
    }

    /**
     * To update the heading with date only
     * @param targetFile
     * @param isFirstRowExist
     */
    private void updateFirstRow(File targetFile, boolean isFirstRowExist) {

        FileReader fr     = null;
        BufferedReader br = null;

        FileWriter fw     = null;
        BufferedWriter bw = null;

        List<String> originalData = new ArrayList<>();
        String firstRow = "";

        if(!isFirstRowExist)
            originalData.add(SEPARATOR_COMMA + getRankDate());

        try {
            fr = new FileReader(targetFile.getAbsoluteFile());
            br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null) {

                if(firstRow.length() == 0) {
                    firstRow = line;
                    if(isFirstRowExist)
                        originalData.add(SEPARATOR_COMMA + getRankDate() + firstRow);
                    else
                        originalData.add(firstRow);
                } else {
                    originalData.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                System.out.println("Error occurred when closing reader stream  " + ex.toString());
            }
        }

        try {
            fw = new FileWriter(targetFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            for(String data : originalData)
                bw.write(data + "\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                System.out.println("Error occurred when closing writer stream " + ex.toString());
            }
        }

    }

    private void processData(String symbols, String outFile, String backupFileName) {

        List<String> currentRankingData = new ArrayList<>();
        for(String symbol : symbols.split(SEPARATOR_COMMA)) {
            currentRankingData.add(getData(symbol));
        }

        String backupFile = backupFileName + SEPARATOR_UNDERSCORE +getRankDate() + EXTENSION_CSV;
        writeToFile(currentRankingData, outFile, backupFile);
        updateFirstRow(new File(OUT_FILE_LOC + outFile), true);
    }

    public static void main(String[] args) {

        ZacksData zacksData             = new ZacksData();
        zacksData.init();

//        String execute = "TANZY_WATCH";
//        String execute = "TANZY_INVESTED";
        String execute = "TANZY_TO_INVEST";
//        String execute = "TANZY_PENNY";
//        String execute = "TANZY_GROWTH";

        switch(execute) {

            case "TANZY_WATCH"     : zacksData.processData(SYMBOLS_TANZY_WATCH, OUT_FILE_TANZY_WATCH, "TanzyWatch");         break;
            case "TANZY_INVESTED"  : zacksData.processData(SYMBOLS_TANZY_INVESTED, OUT_FILE_TANZY_INVESTED, "TanzyInvested");   break;
            case "TANZY_TO_INVEST" : zacksData.processData(SYMBOLS_TANZY_TO_INVEST, OUT_FILE_TANZY_TO_INVEST, "TanzyToInvest"); break;
            case "TANZY_PENNY"     : zacksData.processData(SYMBOLS_TANZY_PENNY, OUT_FILE_TANZY_PENNY, "TanzyPenny");         break;
            case "TANZY_GROWTH"    : zacksData.processData(SYMBOLS_TANZY_GROWTH, OUT_FILE_TANZY_GROWTH, "TanzyGrowth");       break;
        }
    }

    /**
     *
     * @param toFilter
     * @return
     */
    private String filterData(String toFilter) {
        //3-Hold     3
        return (toFilter.split(ZACKS_SEPARATOR))[0];
    }

    public void getCurrentTimeInString() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        setRankDate(sdf.format(timestamp));
    }

    private static void makeCopy(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    /**
     * Get the number of commas so that we can that many commas for non existing record so all the ranks captured at same date will be on same column
     * @param inStr
     * @return
     */
    private String getNumberOfCommas(String inStr) {

        String[] splits = inStr.split(SEPARATOR_COMMA);
        StringBuilder commas = new StringBuilder();

        for(String split : splits)
            commas.append(SEPARATOR_COMMA);

        return commas.toString();
    }

    private String getRankStr(String rankNumber) {

        switch(rankNumber) {

            case "1" : return "Strong Buy";
            case "2" : return "Buy";
            case "3" : return "Hold";
            case "4" : return "Sell";
            case "5" : return "Strong Sell";
            default  : return ZACKS_RANK_UNAVAILABLE;
        }
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }
}















