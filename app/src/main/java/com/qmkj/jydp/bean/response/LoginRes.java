package com.qmkj.jydp.bean.response;

import java.io.Serializable;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginRes extends BaseRes implements Serializable {

    /**
     * user : {"sessionId":null,"userId":557,"userAccount":"syl1001","outTime":1526628292232,"isPwd":1,"isDealer":1}
     * token : a5e21b92-9ac3-477f-a164-70773c05b95f65485888408b434eaeb4761194e25106
     */

    private UserBean user;
    private String token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean implements Serializable {
        /**
         * sessionId : null
         * userId : 557
         * userAccount : syl1001
         * outTime : 1526628292232
         * isPwd : 1
         * isDealer : 1
         */

        private Object sessionId;
        private int userId;
        private String userAccount;
        private long outTime;
        private int isPwd;
        private int isDealer;

        public Object getSessionId() {
            return sessionId;
        }

        public void setSessionId(Object sessionId) {
            this.sessionId = sessionId;
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

        public long getOutTime() {
            return outTime;
        }

        public void setOutTime(long outTime) {
            this.outTime = outTime;
        }

        public int getIsPwd() {
            return isPwd;
        }

        public void setIsPwd(int isPwd) {
            this.isPwd = isPwd;
        }

        public int getIsDealer() {
            return isDealer;
        }

        public void setIsDealer(int isDealer) {
            this.isDealer = isDealer;
        }
    }
}
