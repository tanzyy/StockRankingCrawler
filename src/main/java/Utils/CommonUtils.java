package Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by i852841 on 5/19/18.
 */
public class CommonUtils {

    /**
     * Get the number of commas so that we can that many commas for non existing record so all the ranks captured at same date will be on same column
     * @param inStr
     * @return
     */
    public static String getNumberOfCommas(String inStr) {

        String[] splits = inStr.split(Constants.SEPARATOR_COMMA);
        StringBuilder commas = new StringBuilder();

        for(String split : splits)
            commas.append(Constants.SEPARATOR_COMMA);

        return commas.toString();
    }

    public static String getMonthDay() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Constants.sdf.format(timestamp);
    }

    public static String getCurrentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Constants.tsdf.format(timestamp);
    }

    public static String getYear() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Constants.ysdf.format(timestamp);
    }

    public static String getCurrentDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Constants.dsdf.format(timestamp);
    }

    public static String calculateDateFrom(String dateString, String dateFormat, int days) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        try {
            cal.setTime(s.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, days);
        return s.format(cal.getTime());
    }

    /**
     * @param dateStr
     * @param range
     * @return 1 if within range, -1 out of range, 0 if exception
     */
    public static int isDateInRange(String dateStr, int range) {

        int status = 0;

        try {

            Date inDateObj       = Constants.dsdf.parse(dateStr);
            String curDateStr    = CommonUtils.getCurrentDate();
            String rangeDateStr  = CommonUtils.calculateDateFrom(curDateStr, "M/dd/yyyy", -range);
            Date rangeDateObj    = Constants.dsdf.parse(rangeDateStr);

            if(inDateObj.compareTo(rangeDateObj) >= 0) {
                //System.out.println(String.format("Input DateStr [%s] is within range from [%s] to [%s]", dateStr, rangeDateStr, curDateStr));
                status = 1;
            } else {
                System.out.println(String.format("Input DateStr [%s] is out of range from [%s] to [%s]", dateStr, rangeDateStr, curDateStr));
                status = -1;
            }

        } catch (ParseException e) {
            System.err.println("isDateInRange threw error : " + e.getMessage());
            status = 0;
        }

        return status;
    }
}
