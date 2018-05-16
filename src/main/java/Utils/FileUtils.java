package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileUtils {

    public static final SimpleDateFormat sdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    public static String getCurrentTimeInString() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }

    public static Date getDateObj(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        return (Date)formatter.parse(str);
    }

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
}
