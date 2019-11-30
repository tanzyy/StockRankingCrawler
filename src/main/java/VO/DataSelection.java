package VO;

import Utils.Constants;
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
    private List<String> queries;

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

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL() {

        Map<String, String> urlData = new HashMap();

        int count = 0;
        for(String urlAttribute : urlAttributes) {
            count++;
            urlData.put("arg" + count, urlAttribute);

        }

        this.targetURL = StrSubstitutor.replace(baseUrl, urlData);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
