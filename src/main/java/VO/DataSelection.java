package VO;

import Utils.Constants;
import Utils.QueryMap;
import org.apache.commons.text.StrSubstitutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSelection {

    private String symbol;
    private String baseUrl;
    private List<String> urlAttributes;
    private String targetURL;
    private List<String> mfPropertiesToExtract;
    private boolean isSinglePageMultiQuery;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> getUrlAttributes() {
        return urlAttributes;
    }

    public void setUrlAttributes(List<String> urlAttributes) {
        this.urlAttributes = urlAttributes;
    }

    public List<String> getMfPropertiesToExtract() {
        return mfPropertiesToExtract;
    }

    public void setMfPropertiesToExtract(List<String> mfPropertiesToExtract) {
        this.mfPropertiesToExtract = mfPropertiesToExtract;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL() {

        if(urlAttributes == null) {
            this.targetURL = baseUrl;

        } else {
            Map<String, String> urlData = new HashMap();

            int count = 0;
            for(String urlAttribute : urlAttributes) {
                count++;
                urlData.put("arg" + count, urlAttribute);

            }

            this.targetURL = StrSubstitutor.replace(baseUrl, urlData);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isSinglePageMultiQuery() {
        return isSinglePageMultiQuery;
    }

    public void setSinglePageMultiQuery(boolean singlePageMultiQuery) {
        isSinglePageMultiQuery = singlePageMultiQuery;
    }

    @Override
    public String toString() {
        return "DataSelection{" +
                "symbol='" + symbol + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", urlAttributes=" + urlAttributes +
                ", targetURL='" + targetURL + '\'' +
                ", queries=" + mfPropertiesToExtract +
                '}';
    }

    public static void main(String[] args) {

        DataSelection ds = new DataSelection();
        ds.setBaseUrl(Constants.url_economictimes);

        System.out.println(ds.getBaseUrl());

        String urlargs = "one, two, three";
        ds.setUrlAttributes(Arrays.asList(urlargs.split("\\s*,\\s*")));

        System.out.println(ds.getUrlAttributes());

        ds.setTargetURL();

        System.out.println(ds.getTargetURL());
    }


}
