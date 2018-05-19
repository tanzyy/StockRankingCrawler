package VO;

/**
 * Created by i852841 on 5/19/18.
 */
public class RankInfo {

    private String symbol;
    private String rank;
    private String price;

    public RankInfo(String symbol, String rank, String price) {

        setSymbol(symbol);
        setRank(rank);
        setPrice(price);
    }

    public RankInfo() {

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {

        this.symbol = symbol.toUpperCase();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Mainly for test case purpose which will avoid recreating rankInfo object.
     * @param symbol
     * @param rank
     * @param price
     */
    public void resetRankInfo(String symbol, String rank, String price) {

        if(symbol != "")
            setSymbol(symbol);

        if(rank != "")
            setRank(rank);

        if(price != "")
            setPrice(price);
    }

    @Override
    public String toString() {

        StringBuilder sbr = new StringBuilder();
        sbr.append("RankInfo Values are: Symbol [");
        sbr.append(symbol);
        sbr.append("]  Rank [");
        sbr.append(rank);
        sbr.append("]  Price [");
        sbr.append(price);
        sbr.append("]");

        return sbr.toString();
    }
}
