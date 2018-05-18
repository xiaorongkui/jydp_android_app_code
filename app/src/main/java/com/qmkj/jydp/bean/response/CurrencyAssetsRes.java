package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/17
 * @author Yi Shan Xiang
 * 文件名称： CurrencyAssetsRes
 * email: 380948730@qq.com
 */

public class CurrencyAssetsRes extends BaseRes implements Serializable{

    private List<UserCurrencyAssetsBean> userCurrencyAssets;

    public List<UserCurrencyAssetsBean> getUserCurrencyAssets() {
        return userCurrencyAssets;
    }

    public void setUserCurrencyAssets(List<UserCurrencyAssetsBean> userCurrencyAssets) {
        this.userCurrencyAssets = userCurrencyAssets;
    }

    public static class UserCurrencyAssetsBean {
        /**
         * currencyId : 224
         * currencyName : 比特币
         * currencyNumber : 1.11111263E8
         * currencyNumberLock : 1.0
         * totalCurrencyAssets : 1.11111264E8
         */

        private int currencyId;
        private String currencyName;
        private double currencyNumber;
        private double currencyNumberLock;
        private double totalCurrencyAssets;

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

        public double getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(double currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public double getCurrencyNumberLock() {
            return currencyNumberLock;
        }

        public void setCurrencyNumberLock(double currencyNumberLock) {
            this.currencyNumberLock = currencyNumberLock;
        }

        public double getTotalCurrencyAssets() {
            return totalCurrencyAssets;
        }

        public void setTotalCurrencyAssets(double totalCurrencyAssets) {
            this.totalCurrencyAssets = totalCurrencyAssets;
        }
    }
}
