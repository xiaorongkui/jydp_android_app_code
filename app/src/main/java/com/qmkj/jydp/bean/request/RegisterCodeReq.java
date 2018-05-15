package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class RegisterCodeReq extends BaseReq {
    public String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
