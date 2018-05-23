package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/22
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideBuyPayReq extends BaseReq {
    public String buyNum;
    public String otcPendingOrderNo;
    public String paymentType;

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
}
