package com.qmkj.jydp.bean.response;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 用户提币币种管理信息
 */
public class UserCoinWithdrawInfo {
    private String phoneAreaCode;  //手机号区号
    private String phoneNumber;  //绑定手机号
    private String phoneNumberEn;  //绑定手机号-隐藏部分
    private List<CoinWithdrawInfo> userCoinConfigList;  //用户币种管理

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberEn() {
        return phoneNumberEn;
    }

    public void setPhoneNumberEn(String phoneNumberEn) {
        this.phoneNumberEn = phoneNumberEn;
    }

    public List<CoinWithdrawInfo> getUserCoinConfigList() {
        return userCoinConfigList;
    }

    public void setUserCoinConfigList(List<CoinWithdrawInfo> userCoinConfigList) {
        this.userCoinConfigList = userCoinConfigList;
    }

    /**
     * 用户币种管理
     */
    public class CoinWithdrawInfo {
        private int currencyId;  //币种Id
        private String currencyName;  //币种名称
        private double currencyNumber;  //货币数量
        private double freeCurrencyNumber;  //免审数量
        private double minCurrencyNumber;  //最低数量
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

        public double getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(double currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public double getFreeCurrencyNumber() {
            return freeCurrencyNumber;
        }

        public void setFreeCurrencyNumber(double freeCurrencyNumber) {
            this.freeCurrencyNumber = freeCurrencyNumber;
        }

        public double getMinCurrencyNumber() {
            return minCurrencyNumber;
        }

        public void setMinCurrencyNumber(double minCurrencyNumber) {
            this.minCurrencyNumber = minCurrencyNumber;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
