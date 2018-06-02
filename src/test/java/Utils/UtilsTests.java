package Utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by i852841 on 5/26/18.
 */
public class UtilsTests {

    @Test
    public void testIsDateInRange() {
        System.out.println(CommonUtils.isDateInRange("5/24/2018", 2));
    }

    @Test
    public void testGetCurrentDate() {
        System.out.println(CommonUtils.getCurrentDate());
    }

    @Test
    public void testDate() throws ParseException {

        String curDateStr = CommonUtils.getCurrentDate();
        Date currDateObj  = Constants.dsdf.parse(curDateStr);

        String dateStr1  = CommonUtils.calculateDateFrom(curDateStr, "M/dd/yyyy", -2);
        Date dateStr1Obj = Constants.dsdf.parse(dateStr1);

        if(dateStr1Obj.compareTo(currDateObj) > 0)
            System.out.println(dateStr1 + " , is greater than " + curDateStr);
        else
            System.out.println(dateStr1 + " , is lower than " + curDateStr);


    }

}

