package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    public static void writeStrToFile(String outFilePath, String content) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outFilePath));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void writeToFile(List<String> datas, String fileName) {

        BufferedWriter bw = null;
        FileWriter fw = null;
        File file = null;

        try {

            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            for(String data : datas) {
                bw.write(data);
                bw.write("\n");
            }

        } catch (IOException e) {
            System.out.println("Error occurred when writing to file " + file.getAbsolutePath() + e.toString());
        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                System.out.println("Error occurred when closing bufferedwriter/filewriter " + ex.toString());
            }
        }
    }

    public static boolean copyFile(String sourceFileWithLocation, String dir) {

        boolean isSuccess = true;

        Path sourceFile = Paths.get(sourceFileWithLocation);
        Path targetDir  = Paths.get(dir);
        Path targetFile = targetDir.resolve(sourceFile.getFileName());

        File file = new File(targetFile.toString());
        if(file.exists())
            file.delete();

        try {
            Files.copy(sourceFile, targetFile);
        } catch (IOException e) {
            isSuccess = false;
            e.printStackTrace();
        }

        return isSuccess;
    }

    public static void clearBackupDir(String rootPath, String dirToDelete) {

        List<String> results = new ArrayList<>();
        getAllInstanceofSpecificDir(rootPath, dirToDelete, results);
        results.forEach(System.out::println);

        for (String result : results) {
            Stream.of(new File(result).listFiles()).forEach(file -> file.delete());
        }
    }

    /**
     * This method will return absolute path of all occurrences of matched dir name @name under @rootPath
     *
     * @param rootPath under which target directories to look for.
     * @param name name of directory to look for.
     * @param results list holder to hold the absolute path for all occurrences of name directory
     * @return results
     */
    public static List<String> getAllInstanceofSpecificDir(String rootPath, String name, List<String> results) {

        File[] files = new File(rootPath).listFiles();

        if(files != null) {

            for (File f : files) {

                if(f.isDirectory()) {

                    if(name.equals(f.getName()))
                        results.add(f.getAbsolutePath());
                    else
                        getAllInstanceofSpecificDir(f.getAbsolutePath(), name, results);

                } else
                    continue;
            }
        }

        return results;
    }

    public static void sortList(String commaSeparatedList) {

        String[] strs = commaSeparatedList.split(",");
        List<String> out = new ArrayList<>();
        for(int i = strs.length-1; i >= 0; i--) {
            out.add(strs[i]);
        }
        System.out.println(out);
        System.out.println(commaSeparatedList.toLowerCase());
    }

    public static void main(String[] args) throws IOException {

//        copyFile(
//                "/Users/i852841/OneDriveSAPSE/Code_Personal_On_Git/StockRankingCrawler/target/test-classes/ZacksTest_FileExistWithChange.xlsx",
//                "/Users/i852841/Downloads");

//        sortList("^VIX,SPY,QQQ,XLK,XLF,XLRE,XLE,XLC,XLV,XLP,XLY,XLI,XLB,XLU,SPDV,MJ,SCHD,VT,SOXX,SOXL,IHI,KBE,EFA,VOO,VTI,IVV,IBB,EEM,SLY,MDY,MDYG,SLYG");
    }
}
