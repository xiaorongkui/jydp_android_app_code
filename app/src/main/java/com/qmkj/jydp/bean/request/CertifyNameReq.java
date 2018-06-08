package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class CertifyNameReq extends BaseReq {
    public String userAccount;
    public String userCertNo;
    public String userCertTypeStr;
    public String userName;

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
