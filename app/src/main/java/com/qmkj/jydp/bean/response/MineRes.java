package com.qmkj.jydp.bean.response;

import java.io.Serializable;

/**
 * 创建日期：2018/5/16
 * @author Yi Shan Xiang
 * 文件名称： MineRes
 * email: 380948730@qq.com
 */
public class MineRes extends BaseRes implements Serializable {
    /**
     * userInfo : {"userId":313,"userAccount":"syl001","userBalance":979.99,"userBalanceLock":24.2,"totalUserBalance":1004.19}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userId : 313
         * userAccount : syl001
         * userBalance : 979.99
         * userBalanceLock : 24.2
         * totalUserBalance : 1004.19
         */

        private int userId;
        private String userAccount;
        private double userBalance;
        private double userBalanceLock;
        private double totalUserBalance;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public double getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(double userBalance) {
            this.userBalance = userBalance;
        }

        public double getUserBalanceLock() {
            return userBalanceLock;
        }

        public void setUserBalanceLock(double userBalanceLock) {
            this.userBalanceLock = userBalanceLock;
        }

        public double getTotalUserBalance() {
            return totalUserBalance;
        }

        public void setTotalUserBalance(double totalUserBalance) {
            this.totalUserBalance = totalUserBalance;
        }
    }
}
