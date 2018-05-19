package Utils;

import java.sql.Timestamp;

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
        //setRankDate(sdf.format(timestamp));
        return Constants.sdf.format(timestamp);
    }

    public static String getCurrentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Constants.tsdf.format(timestamp);
    }

}
