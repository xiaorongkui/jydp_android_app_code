package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class PresentRecordRes extends BaseRes implements Serializable{

    /**
     * coinOutRecordList : [{"coinRecordNo":"401804262994216","sylRecordNo":"1","currencyId":217,"userId":317,"userAccount":"syl005","walletAccount":"aaaaa","currencyName":"TTT","currencyNumber":"3.99","handleStatus":2,"handleTime":1524721496000,"outStatus":2,"sendStatus":4,"finishTime":"1","remark":"TTT: 币种提现","addTime":1524721496000},{"coinRecordNo":"401804267276550","sylRecordNo":"1","currencyId":217,"userId":317,"userAccount":"syl005","walletAccount":"aaaaa","currencyName":"TTT","currencyNumber":"1.01","handleStatus":2,"handleTime":1524721405000,"outStatus":2,"sendStatus":4,"finishTime":"11","remark":"TTT: 币种提现","addTime":1524721405000}]
     * pageNumber : 0
     * totalNumber : 2
     * totalPageNumber : 1
     */

    private int pageNumber;
    private int totalNumber;
    private int totalPageNumber;
    private List<CoinOutRecordListBean> coinOutRecordList;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public List<CoinOutRecordListBean> getCoinOutRecordList() {
        return coinOutRecordList;
    }

    public void setCoinOutRecordList(List<CoinOutRecordListBean> coinOutRecordList) {
        this.coinOutRecordList = coinOutRecordList;
    }

    public static class CoinOutRecordListBean {
        /**
         * coinRecordNo : 401804262994216
         * sylRecordNo : 1
         * currencyId : 217
         * userId : 317
         * userAccount : syl005
         * walletAccount : aaaaa
         * currencyName : TTT
         * currencyNumber : 3.99
         * handleStatus : 2
         * handleTime : 1524721496000
         * outStatus : 2
         * sendStatus : 4
         * finishTime : 1
         * remark : TTT: 币种提现
         * addTime : 1524721496000
         */

        private String coinRecordNo;
        private String sylRecordNo;
        private int currencyId;
        private int userId;
        private String userAccount;
        private String walletAccount;
        private String currencyName;
        private String currencyNumber;
        private int handleStatus;
        private long handleTime;
        private int outStatus;
        private int sendStatus;
        private String finishTime;
        private String remark;
        private long addTime;

        public String getCoinRecordNo() {
            return coinRecordNo;
        }

        public void setCoinRecordNo(String coinRecordNo) {
            this.coinRecordNo = coinRecordNo;
        }

        public String getSylRecordNo() {
            return sylRecordNo;
        }

        public void setSylRecordNo(String sylRecordNo) {
            this.sylRecordNo = sylRecordNo;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getWalletAccount() {
            return walletAccount;
        }

        public void setWalletAccount(String walletAccount) {
            this.walletAccount = walletAccount;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(String currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public int getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(int handleStatus) {
            this.handleStatus = handleStatus;
        }

        public long getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(long handleTime) {
            this.handleTime = handleTime;
        }

        public int getOutStatus() {
            return outStatus;
        }

        public void setOutStatus(int outStatus) {
            this.outStatus = outStatus;
        }

        public int getSendStatus() {
            return sendStatus;
        }

        public void setSendStatus(int sendStatus) {
            this.sendStatus = sendStatus;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
