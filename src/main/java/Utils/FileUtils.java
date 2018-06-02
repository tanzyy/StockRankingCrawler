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
import java.util.Date;
import java.util.List;

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

    public static void main(String[] args) {
        copyFile(
                "/Users/i852841/OneDriveSAPSE/Code_Personal_On_Git/StockRankingCrawler/target/test-classes/ZacksTest_FileExistWithChange.xlsx",
                "/Users/i852841/Downloads");
    }
}
