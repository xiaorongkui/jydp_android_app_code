package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideSellReq extends BaseReq {
    public String alipayPaymentAccount;
    public String bankBranch;
    public String bankCardPaymentAccount;
    public String bankName;
    public String imageUrl;
    public String otcPendingOrderNo;
    public String paymentName;
    public String paymentPhone;
    public String paymentType;
    public String sellNum;
    public String wechatPaymentAccount;

    public String getAlipayPaymentAccount() {
        return alipayPaymentAccount;
    }

    public void setAlipayPaymentAccount(String alipayPaymentAccount) {
        this.alipayPaymentAccount = alipayPaymentAccount;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankCardPaymentAccount() {
        return bankCardPaymentAccount;
    }

    public void setBankCardPaymentAccount(String bankCardPaymentAccount) {
        this.bankCardPaymentAccount = bankCardPaymentAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOtcPendingOrderNo() {
        return otcPendingOrderNo;
    }

    public void setOtcPendingOrderNo(String otcPendingOrderNo) {
        this.otcPendingOrderNo = otcPendingOrderNo;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentPhone() {
        return paymentPhone;
    }

    public void setPaymentPhone(String paymentPhone) {
        this.paymentPhone = paymentPhone;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSellNum() {
        return sellNum;
    }

    public void setSellNum(String sellNum) {
        this.sellNum = sellNum;
    }

    public String getWechatPaymentAccount() {
        return wechatPaymentAccount;
    }

    public void setWechatPaymentAccount(String wechatPaymentAccount) {
        this.wechatPaymentAccount = wechatPaymentAccount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
