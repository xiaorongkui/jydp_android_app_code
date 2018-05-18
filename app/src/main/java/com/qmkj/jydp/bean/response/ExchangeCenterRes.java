package com.qmkj.jydp.bean.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * author：rongkui.xiao --2018/5/18
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeCenterRes extends BaseRes {

    /**
     * userDealCapitalMessage : {"userBalance":0,"userBalanceLock":0,"currencyNumber":0,"currencyNumberLock":0,
     * "currencyNumberSum":0}
     * standardParameter : {"nowPrice":0,"buyOne":0,"sellOne":0,"todayMax":0,"todayMin":0,"todayRange":0,
     * "yesterdayPrice":10,"dayTurnove":0,"dayTransaction":0}
     * transactionPendOrderSellList : []
     * transactionCurrency : {"currencyId":230,"currencyShortName":"qaa","currencyName":"ceui",
     * "currencyImg":"/upload/transactionCyrrency/20180508/2018050811053828079037916.jpg","buyFee":0,"sellFee":0,
     * "guidancePrice":10,"paymentType":2,"upStatus":4,"rankNumber":10,"backerAccount":"test001",
     * "ipAddress":"60.176.183.10","upTime":1525748782000,"addTime":1525748739000,"currencyImgUrl":"http://test
     * .oksheng.com.cn/fileservice/upload/transactionCyrrency/20180508/2018050811053828079037916.jpg",
     * "upTimeStr":"2018-05-08 11:06:22","reCode":0}
     * transactionPendOrderBuyList : []
     * webAppPath : /jydp
     * currencyId : 230
     * dealList : []
     * transactionPendOrderList : null
     */

    private UserDealCapitalMessageBean userDealCapitalMessage;
    private StandardParameterBean standardParameter;
    private TransactionCurrencyBean transactionCurrency;
    private String webAppPath;
    private String currencyId;
    private Object transactionPendOrderList;
    private List<?> transactionPendOrderSellList;
    private List<?> transactionPendOrderBuyList;
    private List<?> dealList;

    public UserDealCapitalMessageBean getUserDealCapitalMessage() {
        return userDealCapitalMessage;
    }

    public void setUserDealCapitalMessage(UserDealCapitalMessageBean userDealCapitalMessage) {
        this.userDealCapitalMessage = userDealCapitalMessage;
    }

    public StandardParameterBean getStandardParameter() {
        return standardParameter;
    }

    public void setStandardParameter(StandardParameterBean standardParameter) {
        this.standardParameter = standardParameter;
    }

    public TransactionCurrencyBean getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(TransactionCurrencyBean transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Object getTransactionPendOrderList() {
        return transactionPendOrderList;
    }

    public void setTransactionPendOrderList(Object transactionPendOrderList) {
        this.transactionPendOrderList = transactionPendOrderList;
    }

    public List<?> getTransactionPendOrderSellList() {
        return transactionPendOrderSellList;
    }

    public void setTransactionPendOrderSellList(List<?> transactionPendOrderSellList) {
        this.transactionPendOrderSellList = transactionPendOrderSellList;
    }

    public List<?> getTransactionPendOrderBuyList() {
        return transactionPendOrderBuyList;
    }

    public void setTransactionPendOrderBuyList(List<?> transactionPendOrderBuyList) {
        this.transactionPendOrderBuyList = transactionPendOrderBuyList;
    }

    public List<?> getDealList() {
        return dealList;
    }

    public void setDealList(List<?> dealList) {
        this.dealList = dealList;
    }

    public static class UserDealCapitalMessageBean {
        /**
         * userBalance : 0
         * userBalanceLock : 0
         * currencyNumber : 0
         * currencyNumberLock : 0
         * currencyNumberSum : 0
         */

        private double userBalance;
        private double userBalanceLock;
        private double currencyNumber;
        private double currencyNumberLock;
        private double currencyNumberSum;

        public double getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(double userBalance) {
            this.userBalance = userBalance;
        }

        public double getUserBalanceLock() {
            return userBalanceLock;
        }

        public void setUserBalanceLock(double userBalanceLock) {
            this.userBalanceLock = userBalanceLock;
        }

        public double getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(double currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public double getCurrencyNumberLock() {
            return currencyNumberLock;
        }

        public void setCurrencyNumberLock(double currencyNumberLock) {
            this.currencyNumberLock = currencyNumberLock;
        }

        public double getCurrencyNumberSum() {
            return currencyNumberSum;
        }

        public void setCurrencyNumberSum(double currencyNumberSum) {
            this.currencyNumberSum = currencyNumberSum;
        }
    }

    public static class StandardParameterBean {
        /**
         * nowPrice : 0
         * buyOne : 0
         * sellOne : 0
         * todayMax : 0
         * todayMin : 0
         * todayRange : 0
         * yesterdayPrice : 10
         * dayTurnove : 0
         * dayTransaction : 0
         */

        private String nowPrice;
        private String buyOne;
        private String sellOne;
        private String todayMax;
        private String todayMin;
        private String todayRange;
        private String yesterdayPrice;
        private String dayTurnove;
        private String dayTransaction;

        public String getNowPrice() {
            return nowPrice;
        }

        public void setNowPrice(String nowPrice) {
            this.nowPrice = nowPrice;
        }

        public String getBuyOne() {
            return buyOne;
        }

        public void setBuyOne(String buyOne) {
            this.buyOne = buyOne;
        }

        public String getSellOne() {
            return sellOne;
        }

        public void setSellOne(String sellOne) {
            this.sellOne = sellOne;
        }

        public String getTodayMax() {
            return todayMax;
        }

        public void setTodayMax(String todayMax) {
            this.todayMax = todayMax;
        }

        public String getTodayMin() {
            return todayMin;
        }

        public void setTodayMin(String todayMin) {
            this.todayMin = todayMin;
        }

        public String getTodayRange() {
            return todayRange;
        }

        public void setTodayRange(String todayRange) {
            this.todayRange = todayRange;
        }

        public String getYesterdayPrice() {
            return yesterdayPrice;
        }

        public void setYesterdayPrice(String yesterdayPrice) {
            this.yesterdayPrice = yesterdayPrice;
        }

        public String getDayTurnove() {
            return dayTurnove;
        }

        public void setDayTurnove(String dayTurnove) {
            this.dayTurnove = dayTurnove;
        }

        public String getDayTransaction() {
            return dayTransaction;
        }

        public void setDayTransaction(String dayTransaction) {
            this.dayTransaction = dayTransaction;
        }
    }

    public static class TransactionCurrencyBean {
        /**
         * currencyId : 230
         * currencyShortName : qaa
         * currencyName : ceui
         * currencyImg : /upload/transactionCyrrency/20180508/2018050811053828079037916.jpg
         * buyFee : 0
         * sellFee : 0
         * guidancePrice : 10
         * paymentType : 2
         * upStatus : 4
         * rankNumber : 10
         * backerAccount : test001
         * ipAddress : 60.176.183.10
         * upTime : 1525748782000
         * addTime : 1525748739000
         * currencyImgUrl : http://test.oksheng.com
         * .cn/fileservice/upload/transactionCyrrency/20180508/2018050811053828079037916.jpg
         * upTimeStr : 2018-05-08 11:06:22
         * reCode : 0
         */

        private int currencyId;
        private String currencyShortName;
        private String currencyName;
        private String currencyImg;
        private double buyFee;
        private double sellFee;
        private double guidancePrice;
        private double paymentType;
        private double upStatus;
        private double rankNumber;
        private String backerAccount;
        private String ipAddress;
        private long upTime;
        private long addTime;
        private String currencyImgUrl;
        private String upTimeStr;
        private double reCode;

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public String getCurrencyShortName() {
            return currencyShortName;
        }

        public void setCurrencyShortName(String currencyShortName) {
            this.currencyShortName = currencyShortName;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getCurrencyImg() {
            return currencyImg;
        }

        public void setCurrencyImg(String currencyImg) {
            this.currencyImg = currencyImg;
        }

        public double getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(double buyFee) {
            this.buyFee = buyFee;
        }

        public double getSellFee() {
            return sellFee;
        }

        public void setSellFee(double sellFee) {
            this.sellFee = sellFee;
        }

        public double getGuidancePrice() {
            return guidancePrice;
        }

        public void setGuidancePrice(double guidancePrice) {
            this.guidancePrice = guidancePrice;
        }

        public double getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(double paymentType) {
            this.paymentType = paymentType;
        }

        public double getUpStatus() {
            return upStatus;
        }

        public void setUpStatus(double upStatus) {
            this.upStatus = upStatus;
        }

        public double getRankNumber() {
            return rankNumber;
        }

        public void setRankNumber(double rankNumber) {
            this.rankNumber = rankNumber;
        }

        public String getBackerAccount() {
            return backerAccount;
        }

        public void setBackerAccount(String backerAccount) {
            this.backerAccount = backerAccount;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public long getUpTime() {
            return upTime;
        }

        public void setUpTime(long upTime) {
            this.upTime = upTime;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getCurrencyImgUrl() {
            return currencyImgUrl;
        }

        public void setCurrencyImgUrl(String currencyImgUrl) {
            this.currencyImgUrl = currencyImgUrl;
        }

        public String getUpTimeStr() {
            return upTimeStr;
        }

        public void setUpTimeStr(String upTimeStr) {
            this.upTimeStr = upTimeStr;
        }

        public double getReCode() {
            return reCode;
        }

        public void setReCode(double reCode) {
            this.reCode = reCode;
        }
    }
}
