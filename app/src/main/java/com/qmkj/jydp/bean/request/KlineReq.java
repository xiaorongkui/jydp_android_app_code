package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/22
 * email：dovexiaoen@163.com
 * description:
 */

public class KlineReq extends BaseReq {

    /**
     * currencyId : 200
     * node : 1523589300000
     */

    private String currencyId;
    private long node;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public long getNode() {
        return node;
    }

    public void setNode(long node) {
        this.node = node;
    }
}
