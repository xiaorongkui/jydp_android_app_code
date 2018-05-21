package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/21
 * @author Yi Shan Xiang
 * 文件名称： AccountRecordRes
 * email: 380948730@qq.com
 */
public class AccountRecordRes extends BaseRes implements Serializable {


    /**
     * dealRecordList : [{"actualPrice":1,"actualPriceForWap":"1","addTime":1524711448000,"currencyId":216,"currencyName":"ceui0","currencyNumber":1,"currencyTotalPrice":1,"fee":0,"feeForWap":"0","feeNumber":0,"orderNo":"311804260000018385","paymentType":2,"pendTime":1524707802000,"pendingOrderNo":"301804263315737265","remark":"挂单成交","transactionPrice":1,"userAccount":"syl005","userId":317}]
     * totalPageNumber : 1
     */

    private int totalPageNumber;
    private List<DealRecordListBean> dealRecordList;

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public List<DealRecordListBean> getDealRecordList() {
        return dealRecordList;
    }

    public void setDealRecordList(List<DealRecordListBean> dealRecordList) {
        this.dealRecordList = dealRecordList;
    }

    public static class DealRecordListBean {
        /**
         * actualPrice : 1
         * actualPriceForWap : 1
         * addTime : 1524711448000
         * currencyId : 216
         * currencyName : ceui0
         * currencyNumber : 1
         * currencyTotalPrice : 1
         * fee : 0
         * feeForWap : 0
         * feeNumber : 0
         * orderNo : 311804260000018385
         * paymentType : 2
         * pendTime : 1524707802000
         * pendingOrderNo : 301804263315737265
         * remark : 挂单成交
         * transactionPrice : 1
         * userAccount : syl005
         * userId : 317
         */

        private int actualPrice;
        private String actualPriceForWap;
        private long addTime;
        private int currencyId;
        private String currencyName;
        private int currencyNumber;
        private int currencyTotalPrice;
        private int fee;
        private String feeForWap;
        private int feeNumber;
        private String orderNo;
        private int paymentType;
        private long pendTime;
        private String pendingOrderNo;
        private String remark;
        private int transactionPrice;
        private String userAccount;
        private int userId;

        public int getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(int actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getActualPriceForWap() {
            return actualPriceForWap;
        }

        public void setActualPriceForWap(String actualPriceForWap) {
            this.actualPriceForWap = actualPriceForWap;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public int getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(int currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public int getCurrencyTotalPrice() {
            return currencyTotalPrice;
        }

        public void setCurrencyTotalPrice(int currencyTotalPrice) {
            this.currencyTotalPrice = currencyTotalPrice;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public String getFeeForWap() {
            return feeForWap;
        }

        public void setFeeForWap(String feeForWap) {
            this.feeForWap = feeForWap;
        }

        public int getFeeNumber() {
            return feeNumber;
        }

        public void setFeeNumber(int feeNumber) {
            this.feeNumber = feeNumber;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public long getPendTime() {
            return pendTime;
        }

        public void setPendTime(long pendTime) {
            this.pendTime = pendTime;
        }

        public String getPendingOrderNo() {
            return pendingOrderNo;
        }

        public void setPendingOrderNo(String pendingOrderNo) {
            this.pendingOrderNo = pendingOrderNo;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getTransactionPrice() {
            return transactionPrice;
        }

        public void setTransactionPrice(int transactionPrice) {
            this.transactionPrice = transactionPrice;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
