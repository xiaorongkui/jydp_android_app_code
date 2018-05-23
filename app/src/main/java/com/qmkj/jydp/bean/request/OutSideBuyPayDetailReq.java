package com.qmkj.jydp.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideBuyPayDetailReq extends BaseReq implements Parcelable {
    public String buyNum;
    public String otcPendingOrderNo;
    public String paymentType;
    public String pageNumber;
    public String userId;
    public String payMentMoney;

    public OutSideBuyPayDetailReq() {
    }

    protected OutSideBuyPayDetailReq(Parcel in) {
        buyNum = in.readString();
        otcPendingOrderNo = in.readString();
        paymentType = in.readString();
        pageNumber = in.readString();
        userId = in.readString();
        payMentMoney = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buyNum);
        dest.writeString(otcPendingOrderNo);
        dest.writeString(paymentType);
        dest.writeString(pageNumber);
        dest.writeString(userId);
        dest.writeString(payMentMoney);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutSideBuyPayDetailReq> CREATOR = new Creator<OutSideBuyPayDetailReq>() {
        @Override
        public OutSideBuyPayDetailReq createFromParcel(Parcel in) {
            return new OutSideBuyPayDetailReq(in);
        }

        @Override
        public OutSideBuyPayDetailReq[] newArray(int size) {
            return new OutSideBuyPayDetailReq[size];
        }
    };

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getOtcPendingOrderNo() {
        return otcPendingOrderNo;
    }

    public void setOtcPendingOrderNo(String otcPendingOrderNo) {
        this.otcPendingOrderNo = otcPendingOrderNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayMentMoney() {
        return payMentMoney;
    }

    public void setPayMentMoney(String payMentMoney) {
        this.payMentMoney = payMentMoney;
    }
}
