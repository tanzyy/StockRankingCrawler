package Financial;

import Utils.CommonUtils;
import VO.RankInfo;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class IndMFProcessor {

    private static final int    DATA_SIZE_IN_MB  = 100;
    private static final Logger LOG              = Logger.getLogger(IndMFProcessor.class);

    private String rankDate;
    private String rankYear;

    public void init() {

        setRankDate(CommonUtils.getMonthDay());
        setRankYear(CommonUtils.getYear());
    }

    public RankInfo getMFData(String symbol, String url) {

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
