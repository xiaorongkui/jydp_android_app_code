package com.qmkj.jydp.bean.response;

import java.io.Serializable;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginRes extends BaseRes implements Serializable {


    /**
     * token : 350c9fc4-805f-4ffe-8cdf-35873072597c428cb8cd0aa74c51bb1fd513cc91fbf3
     * user : {"isPwd":1,"userId":314,"userAccount":"syl002","isDealer":2,"outTime":1524885787407}
     */

    private String token;
    private String userAccount;//此参数只有在未认证时才有效
    private String userId;//此参数只有在未认证时才有效
    private UserBean user;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable {
        /**
         * isPwd : 1
         * userId : 314
         * userAccount : syl002
         * isDealer : 2
         * outTime : 1524885787407
         */

        private int isPwd;
        private int userId;
        private String userAccount;
        private int isDealer;
        private long outTime;

        public int getIsPwd() {
            return isPwd;
        }

        public void setIsPwd(int isPwd) {
            this.isPwd = isPwd;
        }

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

        public int getIsDealer() {
            return isDealer;
        }

        public void setIsDealer(int isDealer) {
            this.isDealer = isDealer;
        }

        public long getOutTime() {
            return outTime;
        }

        public void setOutTime(long outTime) {
            this.outTime = outTime;
        }
    }
}
