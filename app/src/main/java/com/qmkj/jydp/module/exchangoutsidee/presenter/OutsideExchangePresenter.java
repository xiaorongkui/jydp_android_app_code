package com.qmkj.jydp.module.exchangoutsidee.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.bean.request.DistributorPayMethodReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.bean.request.OutSideSellDetailReq;
import com.qmkj.jydp.bean.request.OutSideSellReq;
import com.qmkj.jydp.net.api.OutSideExchangeService;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    public void sellOutsideExchange(OutSideSellReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(outSideExchangeService.sellOutsideExchange(req), tag, isShowProgress);
    }

    public void sellOutsideDetailConfirm(OutSideSellDetailReq req, byte[] aliPayImgBytes, byte[] weiChartImgBytes,
                                         int tag, boolean isShowProgress) {
        RequestBody data = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(req));
        RequestBody aliPayImageBody = null;
        RequestBody weiChartImageBody = null;
        if (aliPayImgBytes != null) {
            aliPayImageBody = MultipartBody.create(MediaType.parse("image/jpg"), aliPayImgBytes);
        }
        if (weiChartImgBytes != null) {
            weiChartImageBody = MultipartBody.create(MediaType.parse("image/jpg"), weiChartImgBytes);
        }
        sendHttpRequest(outSideExchangeService.sellOutsideDetailConfirm(data, aliPayImageBody, weiChartImageBody),
                tag, isShowProgress);
    }
}
