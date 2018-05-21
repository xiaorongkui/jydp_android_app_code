package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/21
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangePwdReq extends BaseReq {
    public String payPasswordStatus;
    public String rememberPwd;

    public String getPayPasswordStatus() {
        return payPasswordStatus;
    }

    public void setPayPasswordStatus(String payPasswordStatus) {
        this.payPasswordStatus = payPasswordStatus;
    }

    public String getRememberPwd() {
        return rememberPwd;
    }

    public void setRememberPwd(String rememberPwd) {
        this.rememberPwd = rememberPwd;
    }
}
