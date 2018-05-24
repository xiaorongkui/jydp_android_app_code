package com.qmkj.jydp.module.mine.presenter;


import android.content.Context;

import com.google.gson.Gson;
import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.bean.request.HelpCenterReq;
import com.qmkj.jydp.bean.request.OtcReleaseReq;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.request.SendAdsReq;
import com.qmkj.jydp.bean.request.SendContactServiceReq;
import com.qmkj.jydp.net.api.MineService;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author：rongkui.xiao --2018/4/8
 * email：dovexiaoen@163.com
 * description:
 */


public class MinePresenter extends BaseRxPresenter {
    @Inject
    MineService mineService;

    @Inject
    public MinePresenter(Context context) {
        super(context);
    }

    public void getMineInfo(int tag, boolean isShowProgress) {

        sendHttpRequest(mineService.getMineInfo(), tag, isShowProgress);
    }

    public void loginOut(int tag, boolean isShowProgress){
        sendHttpRequest(mineService.loginOut(), tag);
    }

    public void getCurrencyAssetsInfo(int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getCurrencyAssetsInfo(), tag, isShowProgress);
    }

    public void getDealerManagementInfo(PageNumberReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(mineService.getDealerManagmentInfo(req), tag, isShowProgress);
    }

    public void getTradeCenterInfo(PageNumberReq id, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getTradeCenterInfo(id), tag, isShowProgress);
    }

    public void getPresentRecordInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getPresentRecordInfo(req), tag, isShowProgress);
    }

    public void getDealOtcRecordInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getDealOtcRecordInfo(req), tag);
    }

    public void getOtcDealRecordInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getOtcDealRecordInfo(req), tag);
    }

    public void getSystemNoticeInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getSystemNoticeInfo(req), tag, isShowProgress);
    }

    public void getSystemHotInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getSystemHotInfo(req), tag, isShowProgress);
    }

    public void getCustomerServiceInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getCustomerServiceInfo(req), tag, isShowProgress);
    }

    public void getAccountRecordInfo(PageNumberReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getAccountRecordInfo(req), tag, isShowProgress);
    }

    public void getHelpCenterInfo(HelpCenterReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getHelpCenterInfo(req), tag);
    }

    public void sendCustomerServiceInfo(SendContactServiceReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.sendCustomerServiceInfo(req), tag);
    }

    public void sendInitiateAdsInfo(SendAdsReq req, int tag, boolean isShowProgress){
        sendHttpRequest(mineService.sendInitiateAdsInfo(req), tag);
    }

    public void sendOtcReleaseInfo(OtcReleaseReq req,byte[] ali,byte[] weixin, int tag, boolean isShowProgress){
        RequestBody data = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(req));
        RequestBody frontRequestBody =null;
        RequestBody backRequestBody = null;
        if(ali!=null){
            frontRequestBody = MultipartBody.create(MediaType.parse("image/jpg"), ali);

        }
        if(weixin!=null){

            backRequestBody = MultipartBody.create(MediaType.parse("image/jpg"), weixin);
        }
        sendHttpRequest(mineService.sendOtcReleaseInfo(data, frontRequestBody, backRequestBody), tag);
    }

}
