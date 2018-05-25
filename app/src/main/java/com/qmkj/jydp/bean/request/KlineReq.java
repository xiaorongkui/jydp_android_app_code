package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/22
 * email：dovexiaoen@163.com
 * description:
 */

public class KlineReq extends BaseReq {

    /**
     * currencyId : 200
     * node : 5分钟 5m、15分钟 15m、30分钟 30m、1小时 1h、4小时 4h、1天 1d 、1周 1w
     */

    private String currencyId;
    private String node;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
