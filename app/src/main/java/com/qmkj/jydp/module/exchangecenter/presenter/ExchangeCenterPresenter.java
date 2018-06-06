package com.qmkj.jydp.module.exchangecenter.presenter;


import android.content.Context;

import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.bean.request.BuyExchangeReq;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.request.ExchangeDealRecodeReq;
import com.qmkj.jydp.bean.request.ExchangePwdReq;
import com.qmkj.jydp.bean.request.KlineReq;
import com.qmkj.jydp.bean.request.SellExchangeReq;
import com.qmkj.jydp.bean.response.CancleOrderReq;
import com.qmkj.jydp.net.api.ExchangeService;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/4/8
 * email：dovexiaoen@163.com
 * description:
 */


public class ExchangeCenterPresenter extends BaseRxPresenter {
    @Inject
    ExchangeService exchangeService;

    @Inject
    public ExchangeCenterPresenter(Context context) {
        super(context);
    }


    public void getExchangeCurrency(int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getExchangeCurrency(), tag, isShowProgress);
    }

    public void getExchangeCenterData(ExchangeCenterReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getExchangeCenterData(req), tag, isShowProgress);
    }

    public void getExchangePendOrder(ExchangeCenterReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getExchangePendOrder(req), tag, isShowProgress);
    }

    public void getEntrustRecodeData(ExchangeCenterReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getEntrustRecodeData(req), tag, isShowProgress);
    }

    public void getExchangeDealPrice(ExchangeCenterReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getExchangeDealPrice(req), tag, isShowProgress);
    }

    public void buyXtExchange(BuyExchangeReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.buyXtExchange(req), tag, isShowProgress);
    }

    public void sellXtExchange(SellExchangeReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.sellXtExchange(req), tag, isShowProgress);
    }

    public void rememberExchangePwd(ExchangePwdReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.rememberExchangePwd(req), tag, isShowProgress);
    }

    public void getKlineData(KlineReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getKlineData(req), tag, isShowProgress);
    }

    public void cancleOrder(CancleOrderReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.cancleOrder(req), tag, isShowProgress);
    }

    public void getExchangeDealRecode(ExchangeDealRecodeReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getExchangeDealRecode(req), tag, isShowProgress);
    }
}
