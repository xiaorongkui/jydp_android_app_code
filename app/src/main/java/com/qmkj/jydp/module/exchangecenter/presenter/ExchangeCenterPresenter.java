package com.qmkj.jydp.module.exchangecenter.presenter;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
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

    public void getEntrustRecodeData(ExchangeCenterReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(exchangeService.getEntrustRecodeData(req), tag, isShowProgress);
    }
}
