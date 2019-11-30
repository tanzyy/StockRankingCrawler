package VO;

import Utils.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAttributes {

    private boolean  isNumeric = false;
    private boolean  isMF = true;
    private boolean  highlightChange = false;

    //Comma separated fields to be extracted
    private String       dataExtraction;
    private List<String> dataExtractionList;
    private List<String> targetDataSheetIndexList;

    //For inputs
    private String baseURL;
    private String targetDataURI;
    private Map<String, String> symbolSchemeMap = new HashMap<>();

    public DataAttributes () {

    }

    public DataAttributes(String dataExtraction) {
        this.dataExtraction = dataExtraction;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public void setNumeric(boolean numeric) {
        isNumeric = numeric;
    }

    public boolean isMF() {
        return isMF;
    }

    public void setMF(boolean MF) {
        isMF = MF;
    }

    public boolean isHighlightChange() {
        return highlightChange;
    }

    public void setHighlightChange(boolean highlightChange) {
        this.highlightChange = highlightChange;
    }

    public String getDataExtraction() {
        return dataExtraction;
    }

    public void setDataExtraction(String dataExtraction) {

        this.dataExtractionList = CommonUtils.getListByCommaSeparatedString(dataExtraction);
    }

    public List<String> getDataExtractionList() {
        return dataExtractionList;
    }

    public void setTargetDataSheetIndexList(String dataExtractionList) {

        this.targetDataSheetIndexList = CommonUtils.getListByCommaSeparatedString(dataExtractionList);
    }

    public List<String> getTargetDataSheetIndexList() {
        return targetDataSheetIndexList;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getTargetDataURI() {
        return targetDataURI;
    }

    public void setTargetDataURI(String targetDataURI) {
        this.targetDataURI = targetDataURI;
    }

    public Map<String, String> getSymbolSchemeMap() {
        return symbolSchemeMap;
    }

    public void setSymbolSchemeMap(Map<String, String> symbolSchemeMap) {
        this.symbolSchemeMap = symbolSchemeMap;
    }
}
