package Financial;

import Utils.CommonUtils;
import VO.RankInfo;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class IndMFProcessorExperiment {

    private static final int    DATA_SIZE_IN_MB  = 100;
    private static final Logger LOG              = Logger.getLogger(IndMFProcessorExperiment.class);

    private String rankDate;
    private String rankYear;

    public void init() {

        setRankDate(CommonUtils.getMonthDay());
        setRankYear(CommonUtils.getYear());
    }

    /**
     * This is from economictimes. To get Index, ER, PE, PB, Manager.
     * Sample--
     *  Category #### Equity : Small Cap
     *  Benchmark #### NIFTY Smallcap 100 Total Retur
     *  Asset Under Management #### ₹1,199.83 Cr
     *  Expense Ratio #### 0.63%
     *  Fund Manager #### Anupam Tiwari
     *  Axis Small Cap Fund Direct-Growth #### 24.50
     *  Equity Small Cap #### 16.97
     *  Axis Small Cap Fund Direct-Growth #### 4.01
     *  Equity Small Cap #### 2.5
     * @param symbol
     * @param url
     * @return
     */
    public RankInfo getOtherMFDataFromEconomicTimes(String symbol, String url) {

        Document document;
        RankInfo rankInfo = new RankInfo();
        rankInfo.setSymbol(symbol);

        Elements elem = null;

        LOG.info(url);

        try {
            document = Jsoup.connect(url).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();

            //Elements stt = document.select("#overview > ul > li:nth-child(10) > label");
            //Elements stt = document.select("#overview > ul > li:nth-child(11) > label");
            System.out.println(document.select("#funddetails > ul > li.nav-status > div > label").text());
//            System.out.println(stt.size());
//            for (Element el : stt) {
//                System.out.println(el.text());
//            }


        } catch (IOException e) {
            LOG.error(String.format("Error occurred for [%s] with error ", symbol), e);
        }

        System.out.println(rankInfo);
        return rankInfo;
    }

    /**
     * This is from economictimes.
     * @param symbol
     * @param url
     * @return
     */
    public RankInfo getMFDataFromEconomicTimes(String symbol, String url) {

        Document document;
        RankInfo rankInfo = new RankInfo();
        rankInfo.setSymbol(symbol);

        LOG.info(url);

        try {
            document = Jsoup.connect(url).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            String nav = document.getElementsByClass("navValue").text();
            rankInfo.setNav(nav);
            rankInfo = CommonUtils.populateETFData(rankInfo, null, null, null, nav, null);
        } catch (IOException e) {
            LOG.error(String.format("Error occurred for [%s] with error ", symbol), e);
        }

        return rankInfo;
    }

    public RankInfo getTestMFData(String symbol, String url) {

        Document document;
        RankInfo rankInfo = new RankInfo();
        rankInfo.setSymbol(symbol);

        Elements elem = null;

        LOG.info(url);

        try {
            document = Jsoup.connect(url).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000).get();
            elem = document.getElementsByClass("factsheetListing");

            Elements elems = elem.select("li");
            System.out.println(elems.get(1).children().get(1).text());

        } catch (IOException e) {
            LOG.error(String.format("Error occurred for [%s] with error ", symbol), e);
        }

        return rankInfo;
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public String getRankYear() {
        return rankYear;
    }

    public void setRankYear(String rankYear) {
        this.rankYear = rankYear;
    }
}
