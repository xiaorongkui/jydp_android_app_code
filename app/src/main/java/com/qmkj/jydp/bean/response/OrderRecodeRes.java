package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;


/**
 * 创建日期：2018/5/18
 * @author Yi Shan Xiang
 * 文件名称： OrderRecodeRes 
 * email: 380948730@qq.com
 */

public class OrderRecodeRes extends BaseRes implements Serializable{


    /**
     * totalPageNumber : 2
     * transactionPendOrderRecordList : [{"addTime":1525336486000,"buyFee":0,"currencyId":202,"currencyName":"量子链","dealNumber":2038,"endTime":1525396002000,"feeRemark":1,"paymentType":1,"pendingNumber":2038,"pendingOrderNo":"301805039939696925","pendingPrice":5,"pendingStatus":3,"remainNum":0,"remark":"","restBalanceLock":"0","totalPrice":"10190","userAccount":"syl002","userId":314}]
     */

    private int totalPageNumber;
    private List<TransactionPendOrderRecordListBean> transactionPendOrderRecordList;

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public List<TransactionPendOrderRecordListBean> getTransactionPendOrderRecordList() {
        return transactionPendOrderRecordList;
    }

    public void setTransactionPendOrderRecordList(List<TransactionPendOrderRecordListBean> transactionPendOrderRecordList) {
        this.transactionPendOrderRecordList = transactionPendOrderRecordList;
    }

    public static class TransactionPendOrderRecordListBean {
        /**
         * addTime : 1525336486000
         * buyFee : 0
         * currencyId : 202
         * currencyName : 量子链
         * dealNumber : 2038
         * endTime : 1525396002000
         * feeRemark : 1
         * paymentType : 1
         * pendingNumber : 2038
         * pendingOrderNo : 301805039939696925
         * pendingPrice : 5
         * pendingStatus : 3
         * remainNum : 0
         * remark :
         * restBalanceLock : 0
         * totalPrice : 10190
         * userAccount : syl002
         * userId : 314
         */

        private long addTime;
        private int buyFee;
        private int currencyId;
        private String currencyName;
        private int dealNumber;
        private long endTime;
        private int feeRemark;
        private int paymentType;
        private int pendingNumber;
        private String pendingOrderNo;
        private int pendingPrice;
        private int pendingStatus;
        private int remainNum;
        private String remark;
        private String restBalanceLock;
        private String totalPrice;
        private String userAccount;
        private int userId;

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(int buyFee) {
            this.buyFee = buyFee;
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

        public int getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(int dealNumber) {
            this.dealNumber = dealNumber;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getFeeRemark() {
            return feeRemark;
        }

        public void setFeeRemark(int feeRemark) {
            this.feeRemark = feeRemark;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(int pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public String getPendingOrderNo() {
            return pendingOrderNo;
        }

        public void setPendingOrderNo(String pendingOrderNo) {
            this.pendingOrderNo = pendingOrderNo;
        }

        public int getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(int pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public int getPendingStatus() {
            return pendingStatus;
        }

        public void setPendingStatus(int pendingStatus) {
            this.pendingStatus = pendingStatus;
        }

        public int getRemainNum() {
            return remainNum;
        }

        public void setRemainNum(int remainNum) {
            this.remainNum = remainNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRestBalanceLock() {
            return restBalanceLock;
        }

        public void setRestBalanceLock(String restBalanceLock) {
            this.restBalanceLock = restBalanceLock;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
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