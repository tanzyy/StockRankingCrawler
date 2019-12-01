package Financial;

import Utils.CommonUtils;
import Utils.Constants;
import VO.DataAttributes;
import VO.DataSelection;
import VO.MFInfo;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class IndMFProcessor2 {

    private static final int    DATA_SIZE_IN_MB  = 100;
    private static final Logger LOG              = Logger.getLogger(IndMFProcessor2.class);
    private static final String MULTI_DASH       = " ## ";

    private String rankDate;
    private String rankYear;

    public void init() {

        setRankDate(CommonUtils.getMonthDay());
        setRankYear(CommonUtils.getYear());
    }

    public MFInfo getMFData(DataSelection dataSelection) {

        Document document;
        MFInfo mfInfo = new MFInfo();
        mfInfo.setSymbol(dataSelection.getSymbol());

        LOG.info("Calling getMFDataFromEconomicTimes for :: " + dataSelection.getTargetURL());

        try {
            Connection connection = Jsoup.connect(dataSelection.getTargetURL()).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000);
            connection.userAgent("Mozilla/5.0");
            document = connection.get();

            for(String prop : dataSelection.getMfPropertiesToExtract()) {

                Enum   propQueryEnum =  Enum.valueOf(Constants.MF_IND_QUERY_ECONOMICTIMES.class, prop);
                String query         = ((Constants.MF_IND_QUERY_ECONOMICTIMES) propQueryEnum).getQuery();
                String property      = ((Constants.MF_IND_QUERY_ECONOMICTIMES) propQueryEnum).getMFProperty();

                String data = "";

                String[] queries = query.split(Constants.COMMA);
                if(queries.length == 1) {
                    data = document.select(query).text();
                } else {
                    //In case there are multiple queries

                    StringBuilder dataBuilder = new StringBuilder();
                    int lastQueryCount = queries.length;
                    for(String individualQuery : queries) {

                        dataBuilder.append(document.select(individualQuery).text());

                        //Check for last query, if yes avoid adding separator
                        if(lastQueryCount > 1) {
                            dataBuilder.append(MULTI_DASH);
                            lastQueryCount--;
                        }
                    }

                    data = dataBuilder.toString();
                }


                System.out.println(String.format("For Symbol [%s], The value for [%s] is [%s] with query [%s]", dataSelection.getSymbol(), property, data, query));

                mfInfo.setMFInfo(property, data);
            }

        } catch (IOException e) {
            LOG.error(String.format("Error occurred for [%s] with error ", dataSelection.getSymbol()), e);
        }

        return mfInfo;
    }


    public List<MFInfo> getSinglePageMultiQueryMFData(DataSelection dataSelection, DataAttributes dataAttributes) {

        Document document;

        List<MFInfo> mfInfoList = new ArrayList<>();

        try {
            Connection connection = Jsoup.connect(dataSelection.getTargetURL()).maxBodySize(1024 * 1024 * DATA_SIZE_IN_MB).timeout(100*1000);
            connection.userAgent("Mozilla/5.0");
            document = connection.get();

            for(Map.Entry<String, String> symbolSchemeEntry : dataAttributes.getSymbolSchemeMap().entrySet()) {

                MFInfo mfInfo = new MFInfo();
                String query    = symbolSchemeEntry.getValue();
                String symbol   = symbolSchemeEntry.getKey();

                String data = document.select(query).text();

                System.out.println(String.format("For Symbol [%s], The value is [%s] with query [%s]", symbol, data, query));

                mfInfo.setSymbol(symbol);

                Enum   propQueryEnum =  Enum.valueOf(Constants.MF_IND_QUERY_INVESTING.class, dataSelection.getMfPropertiesToExtract().get(0));
                String property      = ((Constants.MF_IND_QUERY_INVESTING) propQueryEnum).getMFProperty();

                mfInfo.setMFInfo(property, data);
                mfInfoList.add(mfInfo);
            }

        } catch (IOException e) {
            LOG.error(String.format("Error occurred for [%s] with error ", dataSelection.getSymbol()), e);
        }

        return mfInfoList;
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
