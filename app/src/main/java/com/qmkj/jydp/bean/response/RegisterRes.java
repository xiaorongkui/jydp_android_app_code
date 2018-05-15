package com.qmkj.jydp.bean.response;

import com.qmkj.jydp.bean.request.BaseReq;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class RegisterRes extends BaseRes {
    public String userAccount;
    public String userId;

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
}
