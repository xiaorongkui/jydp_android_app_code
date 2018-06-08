package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/6/4
 *
 * @author Yi Shan Xiang
 *         文件名称： OtcCoinConfigR
 *         email: 380948730@qq.com
 */

public class OtcCoinConfigRes extends BaseRes implements Serializable {

    /**
     * dealerCode : 1
     * userOtcCoinConfigList : [{"currencyId":"1","currencyName":"1","currencyNumber":"1","freeCurrencyNumber":"1",
     * "minCurrencyNumber":"1","userId":"1"}]
     */

    private int dealerCode;
    private List<UserOtcCoinConfigListBean> userOtcCoinConfigList;

    public int getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(int dealerCode) {
        this.dealerCode = dealerCode;
    }

    public List<UserOtcCoinConfigListBean> getUserOtcCoinConfigList() {
        return userOtcCoinConfigList;
    }

    public void setUserOtcCoinConfigList(List<UserOtcCoinConfigListBean> userOtcCoinConfigList) {
        this.userOtcCoinConfigList = userOtcCoinConfigList;
    }

    public static class UserOtcCoinConfigListBean {
        /**
         * currencyId : 1
         * currencyName : 1
         * currencyNumber : 1
         * freeCurrencyNumber : 1
         * minCurrencyNumber : 1
         * userId : 1
         */

        private String currencyId;
        private String currencyName;
        private String currencyNumber;
        private String freeCurrencyNumber;
        private String minCurrencyNumber;
        private String userId;

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
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

        public String getFreeCurrencyNumber() {
            return freeCurrencyNumber;
        }

        public void setFreeCurrencyNumber(String freeCurrencyNumber) {
            this.freeCurrencyNumber = freeCurrencyNumber;
        }

        public String getMinCurrencyNumber() {
            return minCurrencyNumber;
        }

        public void setMinCurrencyNumber(String minCurrencyNumber) {
            this.minCurrencyNumber = minCurrencyNumber;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
