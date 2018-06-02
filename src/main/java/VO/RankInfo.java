package VO;

/**
 * Created by i852841 on 5/19/18.
 */
public class RankInfo {

    private String symbol;
    private String rank;

    //Last closed price
    private String price;

    private String researchFirm;
    private String speculatedPrice;

    //Zacks: Downgrades or Upgrades
    //MS: Set Price Target, Raises Target, Reiterates
    //CS: Downgrades, Set Price Target, Lowers Target, Raises Target, Reiterates, Upgrades
    //Needham: Raises Target, Initiates, Lowers Target, Set Price Target, Downgrades, Reiterates
    private String action;

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

    public String getResearchFirm() {
        return researchFirm;
    }

    public void setResearchFirm(String researchFirm) {
        this.researchFirm = researchFirm;
    }

    public String getSpeculatedPrice() {
        return speculatedPrice;
    }

    public void setSpeculatedPrice(String speculatedPrice) {
        this.speculatedPrice = speculatedPrice;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

        if(researchFirm !=  null && researchFirm.length() != 0) {
            sbr.append(" ResearchFirm [");
            sbr.append(researchFirm);
            sbr.append("] Speculated Price [");
            sbr.append(speculatedPrice);
            sbr.append("] Action [");
            sbr.append(action);
            sbr.append("]");
        }

        return sbr.toString();
    }
}