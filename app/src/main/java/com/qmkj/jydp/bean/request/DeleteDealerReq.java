package com.qmkj.jydp.bean.request;

/**
 * Created by yishanxiang on 2018/5/25.
 */

public class DeleteDealerReq extends BaseReq{
    private String otcPendingOrderNo;

    public String getOtcPendingOrderNo() {
        return otcPendingOrderNo;
    }

    public void setOtcPendingOrderNo(String otcPendingOrderNo) {
        this.otcPendingOrderNo = otcPendingOrderNo;
    }
}
