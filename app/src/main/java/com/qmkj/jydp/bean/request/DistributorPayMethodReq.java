package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class DistributorPayMethodReq extends BaseReq {
    public String otcPendingOrderNo;
    public String userId;

    public String getOtcPendingOrderNo() {
        return otcPendingOrderNo;
    }

    public void setOtcPendingOrderNo(String otcPendingOrderNo) {
        this.otcPendingOrderNo = otcPendingOrderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
