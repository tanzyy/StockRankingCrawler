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
 * 1. Exception handling
 * 2. Save to CSV file
 * 3.
 */
public class ZacksDataV2 {

    private static final int    DATA_SIZE_IN_MB = 100;
    private static final String ZACKS_SEPARATOR = "-";
    private static final String SEPARATOR_DATE_RANK = " - ";
    private static final String SEPARATOR_SYMBOL_RANK = ":";
    private static final String SEPARATOR_FILE_DATA = " , ";
    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_COMMA = ",";
    private static final String ZACKS_MAIN_URL  = "https://www.zacks.com/stock/quote/";
    private static final String SYMBOLS         = "tgt,cvs,wba";
    private static final String OUT_FILE_LOC    = "/Users/i852841/Downloads/";
    private static final String OUT_FILE_NAME    = "ZacksRanks.csv";
    private static final String COPY_FILE_NAME    = "ZacksRanks_Back.csv";
    private static final String ZACKS_RANK_UNAVAILABLE    = "0";

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

            //result = symbol + SEPARATOR_SYMBOL_RANK + SEPARATOR_SPACE + getRankDate() + SEPARATOR_DATE_RANK + getRankStr(filterData(rank.text()));
            result = symbol + SEPARATOR_SYMBOL_RANK + getRankStr(filterData(rank.text()));
            System.out.println(result);

        } catch(Exception e) {
            System.out.println(String.format("Error occurred for [%s] with error %s ", symbol,  e));
            result = symbol + SEPARATOR_SYMBOL_RANK + ZACKS_RANK_UNAVAILABLE;
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
    public void writeToFile(List<String> allFetchedData) {

        BufferedWriter bw = null;
        BufferedReader br = null;
        FileWriter fw     = null;
        FileReader fr     = null;
        File file         = null;

        boolean firstRowExist = true;
        List<String> fileDataList = new ArrayList<>();

        try {

            file = new File(OUT_FILE_LOC + OUT_FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                firstRowExist = false;
            } else {
                File backupFile =  new File(OUT_FILE_LOC + COPY_FILE_NAME);
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
            String prefixComma = "";
            if(fileDataList.size() > 0) {
                prefixComma = getNumberOfCommas(fileDataList.get(0));
                System.out.println(String.format("The number of commas [ %s ]", prefixComma));
            }

            // This will handle if file does not exist
            if(prefixComma.length() == 0)
                prefixComma = SEPARATOR_COMMA;

            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            if(firstRowExist) {
                System.out.println("First row data is: " + fileDataList.get(0));
                fw.write(fileDataList.get(0) + "\n");
            }


            boolean isMatched = false;
            for(String fetchedData : allFetchedData) {

                isMatched = false;
                String[] inData = fetchedData.split(SEPARATOR_SYMBOL_RANK);
                String symbolFromFetchedData   = inData[0];
                String dateRankFromFetchedData = inData[1];

                for(String fileData : fileDataList) {

                    String symbolFromFileData = fileData.split(SEPARATOR_COMMA)[0];

                    if(symbolFromFileData.equalsIgnoreCase(symbolFromFetchedData)) {

                        String finalData = fileData + SEPARATOR_COMMA + dateRankFromFetchedData;
                        bw.write(finalData);
                        bw.write("\n");
                        isMatched = true;
                        break;
                    }
                }

                if(!isMatched) {
                    //ToDo: Same length as of maximum file data
                    bw.write( symbolFromFetchedData.toUpperCase()  + prefixComma  + dateRankFromFetchedData);
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
                        originalData.add(firstRow + SEPARATOR_COMMA + getRankDate());
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


    public static void main(String[] args) {

        ZacksDataV2 zacksData             = new ZacksDataV2();
        List<String> currentRankingData = new ArrayList<>();
        zacksData.init();

        for(String symbol : SYMBOLS.split(SEPARATOR_COMMA)) {
            currentRankingData.add(zacksData.getData(symbol));
        }

        zacksData.writeToFile(currentRankingData);
        zacksData.updateFirstRow(new File(OUT_FILE_LOC + OUT_FILE_NAME), false);


//        currentRankingData.add("symc:Sell");
//        currentRankingData.add("CSV:Sell");
//        currentRankingData.add("TSLA:Buy");
//        currentRankingData.add("AMD:1");
//        zacksData.writeToFile(currentRankingData);


//        zacksData.updateFirstRow(new File(OUT_FILE_LOC + "UpdateFileDemo.txt"), false);

//        System.out.println(zacksData.getData("ftr"));

//        System.out.println(zacksData.getNumberOfCommas("SYMC: May_10 - 3 , May_11 - 3 , May_11 - 3 , May_11 - 3"));
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















