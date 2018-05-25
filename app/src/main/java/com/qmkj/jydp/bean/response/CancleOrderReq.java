package com.qmkj.jydp.bean.response;

import com.qmkj.jydp.bean.request.BaseReq;

/**
 * author：rongkui.xiao --2018/5/25
 * email：dovexiaoen@163.com
 * description:
 */

public class CancleOrderReq extends BaseReq {
    public String pendingOrderNo;

    public String getPendingOrderNo() {
        return pendingOrderNo;
    }

    public void setPendingOrderNo(String pendingOrderNo) {
        this.pendingOrderNo = pendingOrderNo;
    }
}
