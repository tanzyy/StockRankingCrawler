package Experimemts;

/**
 * Created by i852841 on 6/2/18.
 */
public class General {

    public static void main(String[] args) {

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = path + "config.properties";
//        Properties appProps = new Properties();
//        try {
//            appProps.load(new FileInputStream(appConfigPath));
//        } catch (IOException e) {
//            LOG.error(String.format("Error occurred when loading config file with exception %s ", e));
//            return;
//        }

//        String str = "Rank [Hold]  Price [$36.72] NAV [33.25] Expense Ratio [0.39%] Risk [High]";
//
//        System.out.println(str.substring(str.indexOf("[") + 1, str.indexOf("]")));

//        String str = "https://www.zacks.com/funds/etf/%s/profile?q=%s";
//
//        System.out.println(String.format(str, "SLYG", "SLYG"));
//
//        ZacksRatings zacksDataInExcel = new ZacksRatings();
//        zacksDataInExcel.getETFData("FYC");
    }

}
