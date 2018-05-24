package com.qmkj.jydp.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideSellDetailReq extends BaseReq implements Parcelable {
    public String alipayPaymentAccount;
    public String bankBranch;
    public String bankCardPaymentAccount;
    public String bankName;
    public String otcPendingOrderNo;
    public String paymentName;
    public String paymentPhone;
    public String paymentType;
    public String sellNum;
    public String wechatPaymentAccount;

    public OutSideSellDetailReq() {
    }

    protected OutSideSellDetailReq(Parcel in) {
        alipayPaymentAccount = in.readString();
        bankBranch = in.readString();
        bankCardPaymentAccount = in.readString();
        bankName = in.readString();
        otcPendingOrderNo = in.readString();
        paymentName = in.readString();
        paymentPhone = in.readString();
        paymentType = in.readString();
        sellNum = in.readString();
        wechatPaymentAccount = in.readString();
    }

    public static final Creator<OutSideSellDetailReq> CREATOR = new Creator<OutSideSellDetailReq>() {
        @Override
        public OutSideSellDetailReq createFromParcel(Parcel in) {
            return new OutSideSellDetailReq(in);
        }

        @Override
        public OutSideSellDetailReq[] newArray(int size) {
            return new OutSideSellDetailReq[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alipayPaymentAccount);
        dest.writeString(bankBranch);
        dest.writeString(bankCardPaymentAccount);
        dest.writeString(bankName);
        dest.writeString(otcPendingOrderNo);
        dest.writeString(paymentName);
        dest.writeString(paymentPhone);
        dest.writeString(paymentType);
        dest.writeString(sellNum);
        dest.writeString(wechatPaymentAccount);
    }

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

    public String getSellNumStr() {
        return sellNum;
    }

    public void setSellNumStr(String sellNumStr) {
        this.sellNum = sellNumStr;
    }

    public String getWechatPaymentAccount() {
        return wechatPaymentAccount;
    }

    public void setWechatPaymentAccount(String wechatPaymentAccount) {
        this.wechatPaymentAccount = wechatPaymentAccount;
    }
}
