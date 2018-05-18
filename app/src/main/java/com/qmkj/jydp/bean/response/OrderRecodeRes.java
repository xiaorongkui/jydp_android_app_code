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


    private List<TransactionPendOrderBuyListBean> transactionPendOrderBuyList;
    private List<TransactionPendOrderSellListBean> transactionPendOrderSellList;

    public List<TransactionPendOrderBuyListBean> getTransactionPendOrderBuyList() {
        return transactionPendOrderBuyList;
    }

    public void setTransactionPendOrderBuyList(List<TransactionPendOrderBuyListBean> transactionPendOrderBuyList) {
        this.transactionPendOrderBuyList = transactionPendOrderBuyList;
    }

    public List<TransactionPendOrderSellListBean> getTransactionPendOrderSellList() {
        return transactionPendOrderSellList;
    }

    public void setTransactionPendOrderSellList(List<TransactionPendOrderSellListBean> transactionPendOrderSellList) {
        this.transactionPendOrderSellList = transactionPendOrderSellList;
    }

    public static class TransactionPendOrderBuyListBean {
        /**
         * dealNumber : 0
         * pendingNumber : 2
         * pendingPrice : 1
         * restNumber : 2
         * sumPrice : 2
         */

        private int dealNumber;
        private int pendingNumber;
        private int pendingPrice;
        private int restNumber;
        private int sumPrice;

        public int getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(int dealNumber) {
            this.dealNumber = dealNumber;
        }

        public int getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(int pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public int getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(int pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public int getRestNumber() {
            return restNumber;
        }

        public void setRestNumber(int restNumber) {
            this.restNumber = restNumber;
        }

        public int getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(int sumPrice) {
            this.sumPrice = sumPrice;
        }
    }

    public static class TransactionPendOrderSellListBean {
        /**
         * dealNumber : 0
         * pendingNumber : 0.01
         * pendingPrice : 100
         * restNumber : 0.01
         * sumPrice : 1
         */

        private int dealNumber;
        private double pendingNumber;
        private int pendingPrice;
        private double restNumber;
        private int sumPrice;

        public int getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(int dealNumber) {
            this.dealNumber = dealNumber;
        }

        public double getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(double pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public int getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(int pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public double getRestNumber() {
            return restNumber;
        }

        public void setRestNumber(double restNumber) {
            this.restNumber = restNumber;
        }

        public int getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(int sumPrice) {
            this.sumPrice = sumPrice;
        }
    }
}
