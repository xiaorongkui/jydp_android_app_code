package com.qmkj.jydp.bean.response;

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
     * standardParameter : {"nowPrice":99999999,"buyOne":0.01,"sellOne":999999999,"todayMax":0,"todayMin":0,
     * "todayRange":0,"yesterdayPrice":99999999,"dayTurnove":0,"dayTransaction":0}
     * transactionPendOrderSellList : [{"pendingPrice":999999999,"pendingNumber":1.0E-4,"dealNumber":0,
     * "restNumber":1.0E-4,"sumPrice":99999.9999}]
     * transactionCurrency : {"currencyId":237,"currencyShortName":"tttttttttt","currencyName":"TTTTTTTTTT",
     * "currencyImg":"/upload/transactionCyrrency/20180515/2018051516084234974882834.jpg","buyFee":0,"sellFee":0,
     * "guidancePrice":11.11,"paymentType":1,"upStatus":2,"rankNumber":5,"backerAccount":"qianmo001",
     * "ipAddress":"183.134.151.251","upTime":1526293161000,"addTime":1526293161000,"currencyImgUrl":"http://test
     * .oksheng.com.cn/fileservice/upload/transactionCyrrency/20180515/2018051516084234974882834.jpg",
     * "upTimeStr":"2018-05-14 18:19:21","reCode":0}
     * transactionPendOrderBuyList : [{"pendingPrice":0.01,"pendingNumber":1.0E-4,"dealNumber":0,
     * "restNumber":1.0E-4,"sumPrice":1.0E-6}]
     * webAppPath : /jydp
     * currencyId : 237
     * dealList : [{"id":0,"orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":99999999,
     * "currencyNumber":1.0E-4,"currencyTotalPrice":9999.9999,"addTime":1526366507000},{"id":0,"orderNo":null,
     * "paymentType":1,"currencyId":0,"transactionPrice":0.01,"currencyNumber":1.0E-4,
     * "currencyTotalPrice":1.0E-6,"addTime":1526366412000},{"id":0,"orderNo":null,"paymentType":2,
     * "currencyId":0,"transactionPrice":1,"currencyNumber":4,"currencyTotalPrice":4,"addTime":1526366369000},
     * {"id":0,"orderNo":null,"paymentType":1,"currencyId":0,"transactionPrice":2,"currencyNumber":2,
     * "currencyTotalPrice":4,"addTime":1526366285000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":1,"currencyNumber":1,"currencyTotalPrice":1,"addTime":1526347708000},{"id":0,
     * "orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":7,"currencyNumber":1,
     * "currencyTotalPrice":7,"addTime":1526347549000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":16,"currencyNumber":36,"currencyTotalPrice":576,"addTime":1526347366000},{"id":0,
     * "orderNo":null,"paymentType":1,"currencyId":0,"transactionPrice":1,"currencyNumber":1,
     * "currencyTotalPrice":1,"addTime":1526347155000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":15,"currencyNumber":10,"currencyTotalPrice":150,"addTime":1526299826000},{"id":0,
     * "orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":0.99,"currencyNumber":1,
     * "currencyTotalPrice":0.99,"addTime":1526299742000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":15,"currencyNumber":5,"currencyTotalPrice":75,"addTime":1526299646000},{"id":0,
     * "orderNo":null,"paymentType":1,"currencyId":0,"transactionPrice":13,"currencyNumber":13,
     * "currencyTotalPrice":169,"addTime":1526299646000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":12,"currencyNumber":12,"currencyTotalPrice":144,"addTime":1526299646000},{"id":0,
     * "orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":1,"currencyNumber":1,
     * "currencyTotalPrice":1,"addTime":1526299408000},{"id":0,"orderNo":null,"paymentType":2,"currencyId":0,
     * "transactionPrice":2,"currencyNumber":2,"currencyTotalPrice":4,"addTime":1526299408000},{"id":0,
     * "orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":3,"currencyNumber":3,
     * "currencyTotalPrice":9,"addTime":1526299408000},{"id":0,"orderNo":null,"paymentType":2,"currencyId":0,
     * "transactionPrice":4,"currencyNumber":4,"currencyTotalPrice":16,"addTime":1526299408000},{"id":0,
     * "orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":5,"currencyNumber":3,
     * "currencyTotalPrice":15,"addTime":1526299408000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":11.01,"currencyNumber":1,"currencyTotalPrice":11.01,"addTime":1526299356000},{"id":0,
     * "orderNo":null,"paymentType":1,"currencyId":0,"transactionPrice":11,"currencyNumber":9,
     * "currencyTotalPrice":99,"addTime":1526299356000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":5.01,"currencyNumber":1,"currencyTotalPrice":5.01,"addTime":1526299211000},{"id":0,
     * "orderNo":null,"paymentType":2,"currencyId":0,"transactionPrice":5,"currencyNumber":2,
     * "currencyTotalPrice":10,"addTime":1526299175000},{"id":0,"orderNo":null,"paymentType":1,"currencyId":0,
     * "transactionPrice":11,"currencyNumber":1,"currencyTotalPrice":11,"addTime":1526296932000},{"id":0,
     * "orderNo":null,"paymentType":1,"currencyId":0,"transactionPrice":11,"currencyNumber":1,
     * "currencyTotalPrice":11,"addTime":1526296623000}]
     * transactionPendOrderList : null
     */

    private UserDealCapitalMessageBean userDealCapitalMessage;
    private StandardParameterBean standardParameter;
    private TransactionCurrencyBean transactionCurrency;
    private String webAppPath;
    private String currencyId;
    private Object transactionPendOrderList;
    private List<TransactionPendOrderSellListBean> transactionPendOrderSellList;
    private List<TransactionPendOrderBuyListBean> transactionPendOrderBuyList;
    private List<DealListBean> dealList;

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

    public List<TransactionPendOrderSellListBean> getTransactionPendOrderSellList() {
        return transactionPendOrderSellList;
    }

    public void setTransactionPendOrderSellList(List<TransactionPendOrderSellListBean>
                                                        transactionPendOrderSellList) {
        this.transactionPendOrderSellList = transactionPendOrderSellList;
    }

    public List<TransactionPendOrderBuyListBean> getTransactionPendOrderBuyList() {
        return transactionPendOrderBuyList;
    }

    public void setTransactionPendOrderBuyList(List<TransactionPendOrderBuyListBean> transactionPendOrderBuyList) {
        this.transactionPendOrderBuyList = transactionPendOrderBuyList;
    }

    public List<DealListBean> getDealList() {
        return dealList;
    }

    public void setDealList(List<DealListBean> dealList) {
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

        private int userBalance;
        private int userBalanceLock;
        private int currencyNumber;
        private int currencyNumberLock;
        private int currencyNumberSum;

        public int getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(int userBalance) {
            this.userBalance = userBalance;
        }

        public int getUserBalanceLock() {
            return userBalanceLock;
        }

        public void setUserBalanceLock(int userBalanceLock) {
            this.userBalanceLock = userBalanceLock;
        }

        public int getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(int currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public int getCurrencyNumberLock() {
            return currencyNumberLock;
        }

        public void setCurrencyNumberLock(int currencyNumberLock) {
            this.currencyNumberLock = currencyNumberLock;
        }

        public int getCurrencyNumberSum() {
            return currencyNumberSum;
        }

        public void setCurrencyNumberSum(int currencyNumberSum) {
            this.currencyNumberSum = currencyNumberSum;
        }
    }

    public static class StandardParameterBean {
        /**
         * nowPrice : 99999999
         * buyOne : 0.01
         * sellOne : 999999999
         * todayMax : 0
         * todayMin : 0
         * todayRange : 0
         * yesterdayPrice : 99999999
         * dayTurnove : 0
         * dayTransaction : 0
         */

        private int nowPrice;
        private double buyOne;
        private int sellOne;
        private int todayMax;
        private int todayMin;
        private int todayRange;
        private int yesterdayPrice;
        private int dayTurnove;
        private int dayTransaction;

        public int getNowPrice() {
            return nowPrice;
        }

        public void setNowPrice(int nowPrice) {
            this.nowPrice = nowPrice;
        }

        public double getBuyOne() {
            return buyOne;
        }

        public void setBuyOne(double buyOne) {
            this.buyOne = buyOne;
        }

        public int getSellOne() {
            return sellOne;
        }

        public void setSellOne(int sellOne) {
            this.sellOne = sellOne;
        }

        public int getTodayMax() {
            return todayMax;
        }

        public void setTodayMax(int todayMax) {
            this.todayMax = todayMax;
        }

        public int getTodayMin() {
            return todayMin;
        }

        public void setTodayMin(int todayMin) {
            this.todayMin = todayMin;
        }

        public int getTodayRange() {
            return todayRange;
        }

        public void setTodayRange(int todayRange) {
            this.todayRange = todayRange;
        }

        public int getYesterdayPrice() {
            return yesterdayPrice;
        }

        public void setYesterdayPrice(int yesterdayPrice) {
            this.yesterdayPrice = yesterdayPrice;
        }

        public int getDayTurnove() {
            return dayTurnove;
        }

        public void setDayTurnove(int dayTurnove) {
            this.dayTurnove = dayTurnove;
        }

        public int getDayTransaction() {
            return dayTransaction;
        }

        public void setDayTransaction(int dayTransaction) {
            this.dayTransaction = dayTransaction;
        }
    }

    public static class TransactionCurrencyBean {
        /**
         * currencyId : 237
         * currencyShortName : tttttttttt
         * currencyName : TTTTTTTTTT
         * currencyImg : /upload/transactionCyrrency/20180515/2018051516084234974882834.jpg
         * buyFee : 0
         * sellFee : 0
         * guidancePrice : 11.11
         * paymentType : 1
         * upStatus : 2
         * rankNumber : 5
         * backerAccount : qianmo001
         * ipAddress : 183.134.151.251
         * upTime : 1526293161000
         * addTime : 1526293161000
         * currencyImgUrl : http://test.oksheng.com
         * .cn/fileservice/upload/transactionCyrrency/20180515/2018051516084234974882834.jpg
         * upTimeStr : 2018-05-14 18:19:21
         * reCode : 0
         */

        private int currencyId;
        private String currencyShortName;
        private String currencyName;
        private String currencyImg;
        private int buyFee;
        private int sellFee;
        private double guidancePrice;
        private int paymentType;
        private int upStatus;
        private int rankNumber;
        private String backerAccount;
        private String ipAddress;
        private long upTime;
        private long addTime;
        private String currencyImgUrl;
        private String upTimeStr;
        private int reCode;

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

        public int getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(int buyFee) {
            this.buyFee = buyFee;
        }

        public int getSellFee() {
            return sellFee;
        }

        public void setSellFee(int sellFee) {
            this.sellFee = sellFee;
        }

        public double getGuidancePrice() {
            return guidancePrice;
        }

        public void setGuidancePrice(double guidancePrice) {
            this.guidancePrice = guidancePrice;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getUpStatus() {
            return upStatus;
        }

        public void setUpStatus(int upStatus) {
            this.upStatus = upStatus;
        }

        public int getRankNumber() {
            return rankNumber;
        }

        public void setRankNumber(int rankNumber) {
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

        public int getReCode() {
            return reCode;
        }

        public void setReCode(int reCode) {
            this.reCode = reCode;
        }
    }

    public static class TransactionPendOrderSellListBean {
        /**
         * pendingPrice : 999999999
         * pendingNumber : 1.0E-4
         * dealNumber : 0
         * restNumber : 1.0E-4
         * sumPrice : 99999.9999
         */

        private int pendingPrice;
        private double pendingNumber;
        private int dealNumber;
        private double restNumber;
        private double sumPrice;

        public int getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(int pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public double getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(double pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public int getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(int dealNumber) {
            this.dealNumber = dealNumber;
        }

        public double getRestNumber() {
            return restNumber;
        }

        public void setRestNumber(double restNumber) {
            this.restNumber = restNumber;
        }

        public double getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(double sumPrice) {
            this.sumPrice = sumPrice;
        }
    }

    public static class TransactionPendOrderBuyListBean {
        /**
         * pendingPrice : 0.01
         * pendingNumber : 1.0E-4
         * dealNumber : 0
         * restNumber : 1.0E-4
         * sumPrice : 1.0E-6
         */

        private double pendingPrice;
        private double pendingNumber;
        private int dealNumber;
        private double restNumber;
        private double sumPrice;

        public double getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(double pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public double getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(double pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public int getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(int dealNumber) {
            this.dealNumber = dealNumber;
        }

        public double getRestNumber() {
            return restNumber;
        }

        public void setRestNumber(double restNumber) {
            this.restNumber = restNumber;
        }

        public double getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(double sumPrice) {
            this.sumPrice = sumPrice;
        }
    }

    public static class DealListBean {
        /**
         * id : 0
         * orderNo : null
         * paymentType : 2
         * currencyId : 0
         * transactionPrice : 99999999
         * currencyNumber : 1.0E-4
         * currencyTotalPrice : 9999.9999
         * addTime : 1526366507000
         */

        private int id;
        private Object orderNo;
        private int paymentType;
        private int currencyId;
        private int transactionPrice;
        private double currencyNumber;
        private double currencyTotalPrice;
        private long addTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Object orderNo) {
            this.orderNo = orderNo;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public int getTransactionPrice() {
            return transactionPrice;
        }

        public void setTransactionPrice(int transactionPrice) {
            this.transactionPrice = transactionPrice;
        }

        public double getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(double currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public double getCurrencyTotalPrice() {
            return currencyTotalPrice;
        }

        public void setCurrencyTotalPrice(double currencyTotalPrice) {
            this.currencyTotalPrice = currencyTotalPrice;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
