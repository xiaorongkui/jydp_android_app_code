package com.qmkj.jydp.bean.request;

import java.io.Serializable;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class CertifyNameReq extends BaseReq {
    public byte[] backImg;
    public byte[] frontImg;
    public String userAccount;
    public String userCertNo;
    public String userCertTypeStr;
    public String userName;

    public byte[] getBackImg() {
        return backImg;
    }

    public void setBackImg(byte[] backImg) {
        this.backImg = backImg;
    }

    public byte[] getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(byte[] frontImg) {
        this.frontImg = frontImg;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserCertNo() {
        return userCertNo;
    }

    public void setUserCertNo(String userCertNo) {
        this.userCertNo = userCertNo;
    }

    public String getUserCertTypeStr() {
        return userCertTypeStr;
    }

    public void setUserCertTypeStr(String userCertTypeStr) {
        this.userCertTypeStr = userCertTypeStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
