package com.qmkj.jydp.bean.response;

/**
 * author：rongkui.xiao --2018/5/21
 * email：dovexiaoen@163.com
 * description:
 */

public class BuyExchangeRes extends BaseRes {
    public String userIsPwd;
    public String payPasswordStatus;//支付密码的

    public String getUserIsPwd() {
        return userIsPwd;
    }

    public void setUserIsPwd(String userIsPwd) {
        this.userIsPwd = userIsPwd;
    }

    public String getPayPasswordStatus() {
        return payPasswordStatus;
    }

    public void setPayPasswordStatus(String payPasswordStatus) {
        this.payPasswordStatus = payPasswordStatus;
    }
}
