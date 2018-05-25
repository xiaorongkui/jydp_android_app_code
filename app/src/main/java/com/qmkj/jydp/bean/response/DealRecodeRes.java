package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/25
 * email：dovexiaoen@163.com
 * description:
 */

public class DealRecodeRes extends BaseRes {


    private List<DealListBean> dealList;

    public List<DealListBean> getDealList() {
        return dealList;
    }

    public void setDealList(List<DealListBean> dealList) {
        this.dealList = dealList;
    }

    public static class DealListBean {
        /**
         * id : 0
         * orderNo : null
         * paymentType : 1
         * currencyId : 0
         * transactionPrice : 1.0
         * currencyNumber : 1.0
         * currencyTotalPrice : 1.0
         * addTime : 1526889660000
         */

        private int id;
        private Object orderNo;
        private int paymentType;
        private int currencyId;
        private double transactionPrice;
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

        public double getTransactionPrice() {
            return transactionPrice;
        }

        public void setTransactionPrice(double transactionPrice) {
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
