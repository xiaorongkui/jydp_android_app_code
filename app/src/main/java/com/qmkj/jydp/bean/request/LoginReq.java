package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginReq extends BaseReq {
    public String password;
    public String userAccount;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
