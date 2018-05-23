package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.request.HelpCenterReq;
import com.qmkj.jydp.bean.request.OtcReleaseReq;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.request.SendAdsReq;
import com.qmkj.jydp.bean.request.SendContactServiceReq;
import com.qmkj.jydp.bean.response.AccountRecordRes;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.bean.response.DealerManagementRes;
import com.qmkj.jydp.bean.response.HelpCenterRes;
import com.qmkj.jydp.bean.response.MineRes;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.bean.response.OtcDealRecordRes;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.bean.response.SystemHotRes;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.common.AppNetConfig;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface MineService {
    //获取我的信息
    @GET(AppNetConfig.urlPath + "wap/userInfo/show")
    Observable<BaseRes<MineRes>> getMineInfo();

    //获取用户币种信息
    @POST(AppNetConfig.urlPath + "wap/userLogin/loginOut")
    Observable<BaseRes<Object>> loginOut();

    //获取用户币种信息
    @GET(AppNetConfig.urlPath + "wap/userInfo/currencyAssets")
    Observable<BaseRes<CurrencyAssetsRes>> getCurrencyAssetsInfo();

    //获取经销商管理信息
    @GET(AppNetConfig.urlPath + "wap/dealerManagment/show")
    Observable<BaseRes<DealerManagementRes>> getDealerManagmentInfo();

    //获取挂单记录信息
    @POST(AppNetConfig.urlPath + "wap/wapTransactionPendOrderController/getTransactionPendOrderList")
    Observable<BaseRes<OrderRecodeRes>> getTradeCenterInfo(@Body PageNumberReq req);

    //获取币种提出记录信息
    @POST(AppNetConfig.urlPath + "wap/presentRecord/showMorePresent")
    Observable<BaseRes<PresentRecordRes>> getPresentRecordInfo(@Body PageNumberReq req);

    //获取场外交易成交记录(经销商)
    @POST(AppNetConfig.urlPath + "wap/dealerOtcRecord/showMore")
    Observable<BaseRes<OtcDealRecordRes>> getDealOtcRecordInfo(@Body PageNumberReq req);

    //获取场外交易成交记录
    @POST(AppNetConfig.urlPath + "wap/userOtcDealRecord/showMore")
    Observable<BaseRes<OtcDealRecordRes>> getOtcDealRecordInfo(@Body PageNumberReq req);

    //获取系统公告
    @POST(AppNetConfig.urlPath + "wap/wapSystemNotice/showMoreNotice")
    Observable<BaseRes<SystemNoticeRes>> getSystemNoticeInfo(@Body PageNumberReq req);

    //获取热门话题
    @POST(AppNetConfig.urlPath + "wap/wapSystemHot/showMoreHot")
    Observable<BaseRes<SystemHotRes>> getSystemHotInfo(@Body PageNumberReq req);

    //获取客服信息
    @POST(AppNetConfig.urlPath + "wap/wapCustomerService/getServiceInfo")
    Observable<BaseRes<CustomerServiceRes>> getCustomerServiceInfo(@Body PageNumberReq req);

    //获取用户成交记录
    @POST(AppNetConfig.urlPath + "wap/wapDealRecord/getAccountRecord")
    Observable<BaseRes<AccountRecordRes>> getAccountRecordInfo(@Body PageNumberReq req);

    //获取帮助中心
    @POST(AppNetConfig.urlPath + "wap/wapHelpCenter/show")
    Observable<BaseRes<HelpCenterRes>> getHelpCenterInfo(@Body HelpCenterReq req);

    //提交意见反馈
    @POST(AppNetConfig.urlPath + "wap/wapCustomerService/feedback")
    Observable<BaseRes<Object>> sendCustomerServiceInfo(@Body SendContactServiceReq req);

    //经销售发布广告
    @POST(AppNetConfig.urlPath + "wap/dealerManagment/initiateAds")
    Observable<BaseRes<Object>> sendInitiateAdsInfo(@Body SendAdsReq req);

    //场外交易挂单
    @POST(AppNetConfig.urlPath + "wap/dealerManagment/otcRelease")
    Observable<BaseRes<Object>> sendOtcReleaseInfo(@Body OtcReleaseReq req);
}
