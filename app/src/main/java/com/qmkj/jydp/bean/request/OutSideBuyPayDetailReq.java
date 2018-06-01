package com.qmkj.jydp.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideBuyPayDetailReq extends BaseReq {
    public String buyNum;
    public String otcPendingOrderNo;
    public String paymentType;
    public String pageNumber;
    public String userId;
    public String payMentMoney;

    public OutSideBuyPayDetailReq() {
    }


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
