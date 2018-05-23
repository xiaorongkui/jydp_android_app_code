package com.qmkj.jydp.module.exchangoutsidee.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.bean.request.DistributorPayMethodReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.net.api.OutSideExchangeService;

import javax.inject.Inject;

/**
 * @author wujiangming
 * @date 2018/4/23
 * @desc
 */

public class OutsideExchangePresenter extends BaseRxPresenter<BaseView> {
    @Inject
    OutSideExchangeService outSideExchangeService;

    @Inject
    public OutsideExchangePresenter(Context context) {
        super(context);
    }


    public void getOutsideExchangeData(OutSideExchangeReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(outSideExchangeService.getOutsideExchangeData(req), tag, isShowProgress);
    }

    public void payOutsideExchange(OutSideBuyPayReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(outSideExchangeService.payOutsideExchange(req), tag, isShowProgress);
    }

    public void getDistributorPayMethod(DistributorPayMethodReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(outSideExchangeService.getDistributorPayMethod(req), tag, isShowProgress);
    }

    public void payOutsideDetailConfirm(OutSideBuyPayDetailReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(outSideExchangeService.payOutsideDetailConfirm(req), tag, isShowProgress);
    }
}
