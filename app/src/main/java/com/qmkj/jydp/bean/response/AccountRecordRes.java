package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/21
 *
 * @author Yi Shan Xiang
 *         文件名称：用户成交记录
 *         email: 380948730@qq.com
 */
public class AccountRecordRes extends BaseRes implements Serializable {


    /**
     * dealRecordList : [{"actualPrice":1,"actualPriceForWap":"1","addTime":1524711448000,"currencyId":216,
     * "currencyName":"ceui0","currencyNumber":1,"currencyTotalPrice":1,"fee":0,"feeForWap":"0","feeNumber":0,
     * "orderNo":"311804260000018385","paymentType":2,"pendTime":1524707802000,"pendingOrderNo":"301804263315737265",
     * "remark":"挂单成交","transactionPrice":1,"userAccount":"syl005","userId":317}]
     * totalPageNumber : 1
     */

    private int totalPageNumber; //总页数
    private List<DealRecordListBean> dealRecordList;//用户成交记录

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

        private String actualPrice; //实际价
        private String actualPriceForWap; //wap端实际总价
        private long addTime; //添加时间
        private String currencyId; //币种Id
        private String currencyName; //货币名称
        private String currencyNumber; //成交数量
        private String currencyTotalPrice; //成交总价
        private String fee; //手续费
        private String feeForWap; //wap端手续费
        private String feeNumber;//成交费率
        private String orderNo; //记录号
        private int paymentType; //收支类型,1：买入，2：卖出，3：撤销
        private long pendTime; //挂单时间
        private String pendingOrderNo; //挂单记录号
        private String remark; //备注
        private String transactionPrice; //成交单价
        private String userAccount; //用户账号
        private String userId; //用户Id


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


        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getFeeForWap() {
            return feeForWap;
        }

        public void setFeeForWap(String feeForWap) {
            this.feeForWap = feeForWap;
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

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public String getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(String currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public String getCurrencyTotalPrice() {
            return currencyTotalPrice;
        }

        public void setCurrencyTotalPrice(String currencyTotalPrice) {
            this.currencyTotalPrice = currencyTotalPrice;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getFeeNumber() {
            return feeNumber;
        }

        public void setFeeNumber(String feeNumber) {
            this.feeNumber = feeNumber;
        }

        public String getTransactionPrice() {
            return transactionPrice;
        }

        public void setTransactionPrice(String transactionPrice) {
            this.transactionPrice = transactionPrice;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
