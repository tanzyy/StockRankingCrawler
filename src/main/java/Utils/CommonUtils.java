package Utils;

import VO.RankInfo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

            if(inDateObj.compareTo(rangeDateObj) > 0) {
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

    public static RankInfo populateETFData(RankInfo rankInfo, String price, String rank, String risk, String nav, String exp) {

        if(price != null && price.length() != 0)
            rankInfo.setPrice(price.substring(0, price.indexOf(Constants.SEPARATOR_WHITE_SPACE)));
        else
            rankInfo.setPrice(Constants.ZERO_VALUE);

        if(rank != null && rank.length() != 0)
            rankInfo.setRank(rank);
        else
            rankInfo.setRank(Constants.UNAVAILABLE);

        if(risk != null && risk.length() != 0)
            rankInfo.setRisk(risk);
        else
            rankInfo.setRisk(Constants.UNAVAILABLE);

        if(nav != null && nav.length() != 0)
            rankInfo.setNav(nav);
        else
            rankInfo.setNav(Constants.UNAVAILABLE);

        if(exp != null && exp.length() != 0)
            rankInfo.setExpenseRatio(exp);
        else
            rankInfo.setExpenseRatio(Constants.UNAVAILABLE);

        return rankInfo;
    }

    public static List<String> getListByCommaSeparatedString(String commaSepString) {
        return Arrays.asList(commaSepString.split("\\s*,\\s*"));
    }


    public static void main(String[] args) {

        String alpha = "A";
        System.out.println(getListByCommaSeparatedString(alpha));

        //System.out.println(CommonUtils.isDateInRange("5/9/2019", 7));
    }
}
