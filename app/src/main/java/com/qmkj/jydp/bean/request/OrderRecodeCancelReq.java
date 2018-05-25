package com.qmkj.jydp.bean.request;

/**
 * Created by yishanxiang on 2018/5/25.
 */

public class OrderRecodeCancelReq extends BaseReq{
    private String pendingOrderNo;

    public String getPendingOrderNo() {
        return pendingOrderNo;
    }

    public void setPendingOrderNo(String pendingOrderNo) {
        this.pendingOrderNo = pendingOrderNo;
    }
}
