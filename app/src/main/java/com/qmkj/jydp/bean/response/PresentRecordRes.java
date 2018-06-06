package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/6/6
 * @author Yi Shan Xiang
 * 文件名称： 币种提现记录
 * email: 380948730@qq.com
 */

public class PresentRecordRes extends BaseRes implements Serializable{

    /**
     * coinOutRecordList : [{"coinRecordNo":"401804262994216","sylRecordNo":"1","currencyId":217,"userId":317,"userAccount":"syl005","walletAccount":"aaaaa","currencyName":"TTT","currencyNumber":"3.99","handleStatus":2,"handleTime":1524721496000,"outStatus":2,"sendStatus":4,"finishTime":"1","remark":"TTT: 币种提现","addTime":1524721496000},{"coinRecordNo":"401804267276550","sylRecordNo":"1","currencyId":217,"userId":317,"userAccount":"syl005","walletAccount":"aaaaa","currencyName":"TTT","currencyNumber":"1.01","handleStatus":2,"handleTime":1524721405000,"outStatus":2,"sendStatus":4,"finishTime":"11","remark":"TTT: 币种提现","addTime":1524721405000}]
     * pageNumber : 0
     * totalNumber : 2
     * totalPageNumber : 1
     */

    private int pageNumber; //页码
    private int totalNumber; //总数量
    private int totalPageNumber; //总页数
    private List<CoinOutRecordListBean> coinOutRecordList;//币种提现记录

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

        private String coinRecordNo; //币种转出记录号
        private String sylRecordNo; //电子钱包操作记录号
        private int currencyId;   //币种Id
        private int userId;  //用户Id
        private String userAccount;  //用户帐号
        private String walletAccount; //电子钱包帐号
        private String currencyName; //币种名称
        private String currencyNumber; //币种数量
        private int handleStatus; //审核状态 1：待审核，2：审核通过，3：审核拒绝 4:已撤回
        private long handleTime; //审核时间
        private int outStatus; //推送状态，1：未推送，2：已推送
        private int sendStatus; //转出状态，1：未转出，2：转出中，3：转出成功，4：转出失败
        private String finishTime; //完成时间
        private String remark; //备注
        private long addTime; //添加时间

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
