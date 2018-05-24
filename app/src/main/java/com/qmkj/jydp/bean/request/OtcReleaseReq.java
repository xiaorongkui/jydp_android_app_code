package com.qmkj.jydp.bean.request;

/**
 * 创建日期：2018/5/23
 * @author Yi Shan Xiang
 * 文件名称： OtcReleaseReq
 * email: 380948730@qq.com
 */
public class OtcReleaseReq extends BaseReq{
    private String alipayAccount;//	支付宝账号
    private String bankAccount;//	收款账号
    private String bankBranch;//	收款支行
    private String bankName;//	收款银行
    private String currencyId;//	币种Id
    private String maxNumber;//	最大金额
    private String minNumber;//	最小金额
    private String orderType;//	挂单类型 1：出售，2：回购
    private String paymentName;//	收款人姓名
    private String paymentPhone;//	收款人手机号
    private String pendingRatio;//	挂单比例
    private String wechatAccount;//	微信账号

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public String getPendingRatio() {
        return pendingRatio;
    }

    public void setPendingRatio(String pendingRatio) {
        this.pendingRatio = pendingRatio;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }
}
