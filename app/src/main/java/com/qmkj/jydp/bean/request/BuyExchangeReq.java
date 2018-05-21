package com.qmkj.jydp.bean.request;

/**
 * author：rongkui.xiao --2018/5/21
 * email：dovexiaoen@163.com
 * description:
 */

public class BuyExchangeReq extends BaseReq {
    public String currencyId;
    public String buyNum;
    public String buyPrice;
    public String buyPwd;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyPwd() {
        return buyPwd;
    }

    public void setBuyPwd(String buyPwd) {
        this.buyPwd = buyPwd;
    }
}
