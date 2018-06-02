package Experimemts;

import Financial.ZacksDataInExcel;

/**
 * Created by i852841 on 6/2/18.
 */
public class General {

    public static void main(String[] args) {


        String str = "Rank [Hold]  Price [$36.72] NAV [33.25] Expense Ratio [0.39%] Risk [High]";

        System.out.println(str.substring(str.indexOf("[") + 1, str.indexOf("]")));

//        String str = "https://www.zacks.com/funds/etf/%s/profile?q=%s";
//
//        System.out.println(String.format(str, "SLYG", "SLYG"));
//
//        ZacksDataInExcel zacksDataInExcel = new ZacksDataInExcel();
//        zacksDataInExcel.getETFData("FYC");
    }

}
