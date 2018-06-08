package com.qmkj.jydp.bean.response;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/17
 *
 * @author Yi Shan Xiang
 *         文件名称： 链资产(用户币种信息)
 *         email: 380948730@qq.com
 */

public class CurrencyAssetsRes extends BaseRes implements Serializable {

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

        private int currencyId; //币种id
        private String currencyName; //货币名称
        private String currencyNumber;//货币数量
        private String currencyNumberLock;//冻结数量
        private String totalCurrencyAssets; //币种总资产
        //业务字段
        @Expose
        private boolean isSelected;

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

        public String getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(String currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public String getCurrencyNumberLock() {
            return currencyNumberLock;
        }

        public void setCurrencyNumberLock(String currencyNumberLock) {
            this.currencyNumberLock = currencyNumberLock;
        }

        public String getTotalCurrencyAssets() {
            return totalCurrencyAssets;
        }

        public void setTotalCurrencyAssets(String totalCurrencyAssets) {
            this.totalCurrencyAssets = totalCurrencyAssets;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @Override
        public String toString() {
            return "UserCurrencyAssetsBean{" +
                    "currencyId=" + currencyId +
                    ", currencyName='" + currencyName + '\'' +
                    ", currencyNumber=" + currencyNumber +
                    ", currencyNumberLock=" + currencyNumberLock +
                    ", totalCurrencyAssets=" + totalCurrencyAssets +
                    ", isSelected=" + isSelected +
                    '}';
        }
    }
}
