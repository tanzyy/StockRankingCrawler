package Utils;

import java.text.SimpleDateFormat;

/**
 * Created by i852841 on 5/19/18.
 */
public class Constants {

    public static final String TEMP_PREFIX             = "TEMP_";
    public static final String SEPARATOR_RANK_PRICE    = " -- ";
    public static final String SEPARATOR_OPEN_BRACE    = "(";
    public static final String SEPARATOR_CLOSE_BRACE   = ")";
    public static final String SEPARATOR_WHITE_SPACE   = " ";
    public static final String SEPARATOR_COMMA         = ",";
    public static final String SEPARATOR_UNDERSCORE    = "_";
    public static final String EXTENSION_CSV           = ".csv";
    public static final String ZERO_VALUE              = "0";

    public static final SimpleDateFormat sdf   = new SimpleDateFormat("MMM_dd");
    public static final SimpleDateFormat tsdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    public static final SimpleDateFormat ysdf  = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat dsdf  = new SimpleDateFormat("M/dd/yyyy");
}