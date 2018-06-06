package com.qmkj.jydp.bean.response;

import java.io.Serializable;

/**
 * 创建日期：2018/5/16
 * @author Yi Shan Xiang
 * 文件名称： 个人中心
 * email: 380948730@qq.com
 */
public class MineRes extends BaseRes implements Serializable {
    /**
     * userInfo : {"userId":313,"userAccount":"syl001","userBalance":979.99,"userBalanceLock":24.2,"totalUserBalance":1004.19}
     */

    private UserInfoBean userInfo; //用户信息
    private int isDealer;//1:不是经销商；2：经销商

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getIsDealer() {
        return isDealer;
    }

    public void setIsDealer(int isDealer) {
        this.isDealer = isDealer;
    }

    public static class UserInfoBean {
        /**
         * userId : 313
         * userAccount : syl001
         * userBalance : 979.99
         * userBalanceLock : 24.2
         * totalUserBalance : 1004.19
         */

        private int userId; //用户id
        private String userAccount; //用户账号
        private String userBalance; //用户可用余额
        private String userBalanceLock; //用户账户总余额
        private String totalUserBalance; //用户账户总余额

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

        public String getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(String userBalance) {
            this.userBalance = userBalance;
        }

        public String getUserBalanceLock() {
            return userBalanceLock;
        }

        public void setUserBalanceLock(String userBalanceLock) {
            this.userBalanceLock = userBalanceLock;
        }

        public String getTotalUserBalance() {
            return totalUserBalance;
        }

        public void setTotalUserBalance(String totalUserBalance) {
            this.totalUserBalance = totalUserBalance;
        }
    }
}
