package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/25
 * email：dovexiaoen@163.com
 * description:
 */

public class KlineRes extends BaseRes {


    private List<TransactionGraphListBean> transactionGraphList;

    public List<TransactionGraphListBean> getTransactionGraphList() {
        return transactionGraphList;
    }

    public void setTransactionGraphList(List<TransactionGraphListBean> transactionGraphList) {
        this.transactionGraphList = transactionGraphList;
    }

    public static class TransactionGraphListBean {
        /**
         * openPrice : 10.7
         * closPrice : 22.0
         * maxPrice : 30.0
         * minPrice : 10.5
         * countPrice : 24.0
         * dealDate : 1525824000000
         */

        private double openPrice;
        private double closPrice;
        private double maxPrice;
        private double minPrice;
        private double countPrice;
        private long dealDate;

        public double getOpenPrice() {
            return openPrice;
        }

        public void setOpenPrice(double openPrice) {
            this.openPrice = openPrice;
        }

        public double getClosPrice() {
            return closPrice;
        }

        public void setClosPrice(double closPrice) {
            this.closPrice = closPrice;
        }

        public double getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(double maxPrice) {
            this.maxPrice = maxPrice;
        }

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
            this.minPrice = minPrice;
        }

        public double getCountPrice() {
            return countPrice;
        }

        public void setCountPrice(double countPrice) {
            this.countPrice = countPrice;
        }

        public long getDealDate() {
            return dealDate;
        }

        public void setDealDate(long dealDate) {
            this.dealDate = dealDate;
        }
    }
}
