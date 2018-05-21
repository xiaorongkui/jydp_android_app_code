package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/21
 * email：dovexiaoen@163.com
 * description:
 */

public class SellExchangeReq extends BaseReq {
    public String currencyId;
    public String sellNum;
    public String sellPrice;
    public String sellPwd;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getSellNum() {
        return sellNum;
    }

    public void setSellNum(String sellNum) {
        this.sellNum = sellNum;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellPwd() {
        return sellPwd;
    }

    public void setSellPwd(String sellPwd) {
        this.sellPwd = sellPwd;
    }
}
