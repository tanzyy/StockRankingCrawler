package VO;

import Utils.Constants;

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
    private int ratingVal;
    private String dateReported;


    //Zacks: Downgrades or Upgrades
    //MS: Set Price Target, Raises Target, Reiterates
    //CS: Downgrades, Set Price Target, Lowers Target, Raises Target, Reiterates, Upgrades
    //Needham: Raises Target, Initiates, Lowers Target, Set Price Target, Downgrades, Reiterates
    private String action;

    //For ETF: NAV, Expense Ratio, Risk
    private String nav;
    private String expenseRatio;
    private String risk;


    public enum RatingState {

        RED(-1),
        GREEN(1),
        GRAY(0);

        public int getState() {
            return state;
        }

        private final int state;

        RatingState(int s) {
            state = s;
        }
    }

    public int getRatingVal() {
        return ratingVal;
    }

    public void setRatingVal(String ratingAction) {

        if(Constants.RATING_DOWNGRADES.equalsIgnoreCase(ratingAction) || Constants.RATING_LOWERS_TARGET.equalsIgnoreCase(ratingAction))
            this.ratingVal = RatingState.RED.getState();

        else if(Constants.RATING_RAISES_TARGET.equalsIgnoreCase(ratingAction)
                || Constants.RATING_SET_PRICE_TARGET.equalsIgnoreCase(ratingAction)
                || Constants.RATING_UPGRADES.equalsIgnoreCase(ratingAction)
                || Constants.RATING_BOOST_PRICE_TARGET.equalsIgnoreCase(ratingAction))
            this.ratingVal = RatingState.GREEN.getState();
        else
            this.ratingVal = RatingState.GRAY.getState();
    }

    public RankInfo(String symbol, String rank, String price) {

        setSymbol(symbol);
        setRank(rank);
        setPrice(price);
    }

    public RankInfo(String researchFirm) {

        setResearchFirm(researchFirm);
        setRank(Constants.UNAVAILABLE);
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

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(String expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
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
            sbr.append("] Date Reported [");
            sbr.append(dateReported);
            sbr.append("]");
        }

        if(nav !=  null && nav.length() != 0) {
            sbr.append(" NAV [");
            sbr.append(nav);
            sbr.append("] Expense Ratio [");
            sbr.append(expenseRatio);
            sbr.append("] Risk [");
            sbr.append(risk);
            sbr.append("]");
        }

        return sbr.toString();
    }

    public String getETFInfo() {

        StringBuilder sbr = new StringBuilder();
        sbr.append("Rank [");
        sbr.append(rank);
        sbr.append("]  Price [");
        sbr.append(price);
        sbr.append("]");

        if(nav !=  null && nav.length() != 0) {
            sbr.append(" NAV [");
            sbr.append(nav);
            sbr.append("] Expense Ratio [");
            sbr.append(expenseRatio);
            sbr.append("] Risk [");
            sbr.append(risk);
            sbr.append("]");
        }

        return sbr.toString();
    }
}
