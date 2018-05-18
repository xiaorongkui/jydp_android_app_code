package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/18
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeEntrustRecodeRes extends BaseRes {

    private List<TransactionPendOrderListBean> transactionPendOrderList;

    public List<TransactionPendOrderListBean> getTransactionPendOrderList() {
        return transactionPendOrderList;
    }

    public void setTransactionPendOrderList(List<TransactionPendOrderListBean> transactionPendOrderList) {
        this.transactionPendOrderList = transactionPendOrderList;
    }

    public static class TransactionPendOrderListBean {
        /**
         * currencyName : 测试内容6152
         * endTime : 测试内容y7fd
         * feeRemark : 测试内容w731
         * remark : 测试内容1n78
         * addTime : 1525224865000
         * buyFee : 0
         * countPrice : 1
         * currencyId : 0
         * dealNumber : 0
         * paymentType : 1
         * pendingNumber : 1
         * pendingOrderNo : 301805028339574138
         * pendingPrice : 1
         * pendingStatus : 0
         * restBalanceLock : 0
         */

        private String currencyName;
        private String endTime;
        private String feeRemark;
        private String remark;
        private long addTime;
        private String buyFee;
        private String countPrice;
        private String currencyId;
        private String dealNumber;
        private int paymentType;
        private String pendingNumber;
        private String pendingOrderNo;
        private String pendingPrice;
        private int pendingStatus;
        private String restBalanceLock;

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFeeRemark() {
            return feeRemark;
        }

        public void setFeeRemark(String feeRemark) {
            this.feeRemark = feeRemark;
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

        public String getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(String buyFee) {
            this.buyFee = buyFee;
        }

        public String getCountPrice() {
            return countPrice;
        }

        public void setCountPrice(String countPrice) {
            this.countPrice = countPrice;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public String getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(String dealNumber) {
            this.dealNumber = dealNumber;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public String getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(String pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public String getPendingOrderNo() {
            return pendingOrderNo;
        }

        public void setPendingOrderNo(String pendingOrderNo) {
            this.pendingOrderNo = pendingOrderNo;
        }

        public String getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(String pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public int getPendingStatus() {
            return pendingStatus;
        }

        public void setPendingStatus(int pendingStatus) {
            this.pendingStatus = pendingStatus;
        }

        public String getRestBalanceLock() {
            return restBalanceLock;
        }

        public void setRestBalanceLock(String restBalanceLock) {
            this.restBalanceLock = restBalanceLock;
        }
    }
}
