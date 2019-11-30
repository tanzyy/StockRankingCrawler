package VO;

import Utils.Constants;

import java.lang.reflect.Field;
import java.util.List;

public class MFInfo {

    private String symbol;

    private String nav;
    private String fiftyTwoWeek;

    private String benchmark;
    private String expenseRatio;
    private String aum;

    private String manager;
    private String peRatio;
    private String pbRatio;

    private String monthReturnOne;
    private String monthReturnThree;
    private String monthReturnSix;
    private String returns;


    private String holdingSectorTop5;
    private String holdingTotalStocks;
    private String holdingTotalSector;
    private String holdingPercentStockTop5;
    private String holdingPercentStockTop10;
    private String holdingPercentSectorTop3;
    private String holdingPercentSectorTop5;
    private String holdingMarketCap;

    public enum MF_PROPERTY {
        nav,
        fiftyTwoWeek,
        benchmark,
        expenseRatio,
        aum,
        manager,
        peRatio,
        pbRatio,
        returns,
        monthReturnOne,
        monthReturnThree,
        monthReturnSix,
        holdingSectorTop5,
        holdingTotalStocks,
        holdingTotalSector,
        holdingPercentStockTop5,
        holdingPercentStockTop10,
        holdingPercentSectorTop3,
        holdingPercentSectorTop5,
        holdingMarketCap
    };

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getFiftyTwoWeek() {
        return fiftyTwoWeek;
    }

    public void setFiftyTwoWeek(String fiftyTwoWeek) {
        this.fiftyTwoWeek = fiftyTwoWeek;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(String expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public String getAum() {
        return aum;
    }

    public void setAum(String aum) {
        this.aum = aum;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(String peRatio) {
        this.peRatio = peRatio;
    }

    public String getPbRatio() {
        return pbRatio;
    }

    public void setPbRatio(String pbRatio) {
        this.pbRatio = pbRatio;
    }

    public String getMonthReturnOne() {
        return monthReturnOne;
    }

    public void setMonthReturnOne(String monthReturnOne) {
        this.monthReturnOne = monthReturnOne;
    }

    public String getMonthReturnThree() {
        return monthReturnThree;
    }

    public void setMonthReturnThree(String monthReturnThree) {
        this.monthReturnThree = monthReturnThree;
    }

    public String getMonthReturnSix() {
        return monthReturnSix;
    }

    public void setMonthReturnSix(String monthReturnSix) {
        this.monthReturnSix = monthReturnSix;
    }

    public String getHoldingSectorTop5() {
        return holdingSectorTop5;
    }

    public void setHoldingSectorTop5(String holdingSectorTop5) {
        this.holdingSectorTop5 = holdingSectorTop5;
    }

    public String getHoldingTotalSector() {
        return holdingTotalSector;
    }

    public void setHoldingTotalSector(String holdingTotalSector) {
        this.holdingTotalSector = holdingTotalSector;
    }

    public String getHoldingMarketCap() {
        return holdingMarketCap;
    }

    public void setHoldingMarketCap(String holdingMarketCap) {
        this.holdingMarketCap = holdingMarketCap;
    }

    public String getHoldingTotalStocks() {
        return holdingTotalStocks;
    }

    public void setHoldingTotalStocks(String holdingTotalStocks) {
        this.holdingTotalStocks = holdingTotalStocks;
    }

    public String getHoldingPercentStockTop5() {
        return holdingPercentStockTop5;
    }

    public void setHoldingPercentStockTop5(String holdingPercentStockTop5) {
        this.holdingPercentStockTop5 = holdingPercentStockTop5;
    }

    public String getHoldingPercentStockTop10() {
        return holdingPercentStockTop10;
    }

    public void setHoldingPercentStockTop10(String holdingPercentStockTop10) {
        this.holdingPercentStockTop10 = holdingPercentStockTop10;
    }

    public String getHoldingPercentSectorTop3() {
        return holdingPercentSectorTop3;
    }

    public void setHoldingPercentSectorTop3(String holdingPercentSectorTop3) {
        this.holdingPercentSectorTop3 = holdingPercentSectorTop3;
    }

    public String getHoldingPercentSectorTop5() {
        return holdingPercentSectorTop5;
    }

    public void setHoldingPercentSectorTop5(String holdingPercentSectorTop5) {
        this.holdingPercentSectorTop5 = holdingPercentSectorTop5;
    }

    public String getHoldingMarketCapExposure() {
        return holdingMarketCap;
    }

    public void setHoldingMarketCapExposure(String holdingMarketCapExposure) {
        this.holdingMarketCap = holdingMarketCapExposure;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }

    //TODO Print only non null values
    @Override
    public String toString() {
        return "MFInfo{" +
                "symbol='" + symbol + '\'' +
                ", nav=" + nav +
                ", fiftyTwoWeek='" + fiftyTwoWeek + '\'' +
                ", benchmark='" + benchmark + '\'' +
                ", expenseRatio='" + expenseRatio + '\'' +
                ", aum='" + aum + '\'' +
                ", manager='" + manager + '\'' +
                ", peRatio='" + peRatio + '\'' +
                ", pbRatio='" + pbRatio + '\'' +
                ", monthReturnOne='" + monthReturnOne + '\'' +
                ", monthReturnThree='" + monthReturnThree + '\'' +
                ", monthReturnSix='" + monthReturnSix + '\'' +
                ", holdingSectorTopFive='" + holdingSectorTop5 + '\'' +
                ", holdingTotalStocks='" + holdingTotalStocks + '\'' +
                ", holdingPercentStockTop5='" + holdingPercentStockTop5 + '\'' +
                ", holdingPercentStockTop10='" + holdingPercentStockTop10 + '\'' +
                ", holdingPercentSectorTop3='" + holdingPercentSectorTop3 + '\'' +
                ", holdingPercentSectorTop5='" + holdingPercentSectorTop5 + '\'' +
                ", holdingMarketCapExposure='" + holdingMarketCap + '\'' +
                '}';
    }

    public Object getMFInfo(String key) {

        Object result = "";

        if(key == null || key.isEmpty()) {
            //Error
            return result;
        }

        try {

            Field field = this.getClass().getDeclaredField(key);
            result = field.get(this);

        } catch (NoSuchFieldException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        }

        return result;
    }

    public Object setMFInfo(String key, Object val) {

        Object result = "";

        if(key == null || key.isEmpty()) {
            //Error
            return result;
        }

        try {

            Field field = this.getClass().getDeclaredField(key);
            field.set(this, val);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }
}
