package com.qmkj.jydp.bean.request;

import java.io.Serializable;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class ReCertificetionReq extends BaseReq implements Serializable {
    public String userAccount;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
