package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/18
 * email：dovexiaoen@163.com
 * description:交易中心币种选择页面数据返回
 */

public class ExchangeCurrencyRes extends BaseRes {

    private List<TransactionUserDealListBean> transactionUserDealList;//当前交易币种类表

    public List<TransactionUserDealListBean> getTransactionUserDealList() {
        return transactionUserDealList;
    }

    public void setTransactionUserDealList(List<TransactionUserDealListBean> transactionUserDealList) {
        this.transactionUserDealList = transactionUserDealList;
    }

    public static class TransactionUserDealListBean {
        /**
         * currencyId : 240
         * currencyName : 星云币
         * currencyShortName : NAS
         * latestPrice : 0
         * buyOnePrice : 0
         * sellOnePrice : 0
         * volume : 0
         * change : 0
         * yesterdayLastPrice : 0
         * currencyImg : /upload/transactionCyrrency/20180516/2018051610144388283346372.rtf
         * currencyImgUrl : http://test.oksheng.com
         * .cn/fileservice/upload/transactionCyrrency/20180516/2018051610144388283346372.rtf
         */

        private int currencyId;
        private String currencyName;//币种名称
        private String currencyShortName;//币种简称
        private double latestPrice;//币种当前价
        private double buyOnePrice;
        private double sellOnePrice;
        private double volume;//成交量
        private double change;//日涨跌
        private double yesterdayLastPrice;
        private String currencyImg;
        private String currencyImgUrl;

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

        public String getCurrencyShortName() {
            return currencyShortName;
        }

        public void setCurrencyShortName(String currencyShortName) {
            this.currencyShortName = currencyShortName;
        }

        public double getLatestPrice() {
            return latestPrice;
        }

        public void setLatestPrice(double latestPrice) {
            this.latestPrice = latestPrice;
        }

        public double getBuyOnePrice() {
            return buyOnePrice;
        }

        public void setBuyOnePrice(double buyOnePrice) {
            this.buyOnePrice = buyOnePrice;
        }

        public double getSellOnePrice() {
            return sellOnePrice;
        }

        public void setSellOnePrice(double sellOnePrice) {
            this.sellOnePrice = sellOnePrice;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }

        public double getYesterdayLastPrice() {
            return yesterdayLastPrice;
        }

        public void setYesterdayLastPrice(double yesterdayLastPrice) {
            this.yesterdayLastPrice = yesterdayLastPrice;
        }

        public String getCurrencyImg() {
            return currencyImg;
        }

        public void setCurrencyImg(String currencyImg) {
            this.currencyImg = currencyImg;
        }

        public String getCurrencyImgUrl() {
            return currencyImgUrl;
        }

        public void setCurrencyImgUrl(String currencyImgUrl) {
            this.currencyImgUrl = currencyImgUrl;
        }
    }
}
