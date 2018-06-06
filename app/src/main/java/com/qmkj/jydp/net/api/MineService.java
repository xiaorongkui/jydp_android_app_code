package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.AccountRecordReq;
import com.qmkj.jydp.bean.request.CoinRecordCancelReq;
import com.qmkj.jydp.bean.request.DeleteDealerReq;
import com.qmkj.jydp.bean.request.HelpCenterReq;
import com.qmkj.jydp.bean.request.OrderRecodeCancelReq;
import com.qmkj.jydp.bean.request.OtcReleaseReq;
import com.qmkj.jydp.bean.request.OutSideDetailReq;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.request.SendAdsReq;
import com.qmkj.jydp.bean.request.SendContactServiceReq;
import com.qmkj.jydp.bean.request.UserWithdrawReq;
import com.qmkj.jydp.bean.response.AccountRecordRes;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.bean.response.DealerManagementRes;
import com.qmkj.jydp.bean.response.HelpCenterRes;
import com.qmkj.jydp.bean.response.MineRes;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.bean.response.OtcCoinConfigRes;
import com.qmkj.jydp.bean.response.OtcDealRecordDetailsRes;
import com.qmkj.jydp.bean.response.OtcDealRecordRes;
import com.qmkj.jydp.bean.response.PresentRechargeRes;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.bean.response.SystemHotRes;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.bean.response.UserCoinWithdrawInfo;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用json,
 */
public interface MineService {
    //获取我的信息
    @GET(AppNetConfig.urlPath + "wap/userInfo/show")
    Observable<BaseRes<MineRes>> getMineInfo();

    //获取用户币种信息
    @POST(AppNetConfig.urlPath + "wap/userLogin/loginOut")
    Observable<BaseRes<Object>> loginOut();

    //获取用户币种信息(所有币种)
    @GET(AppNetConfig.urlPath + "wap/userInfo/currencyAssets")
    Observable<BaseRes<CurrencyAssetsRes>> getCurrencyAssetsInfo();

    //获取用户提币币种管理信息
    @GET(AppNetConfig.urlPath + "wap/userCoinWithdrawal/show")
    Observable<BaseRes<UserCoinWithdrawInfo>> getUserCoinWithdrawalInfo();

    //经销商发起广告币种信息
    @GET(AppNetConfig.urlPath + "wap/dealerManagment/openInitiateAds")
    Observable<BaseRes<OtcCoinConfigRes>> getDealerManagmentCoinInfo();

    //用户提币
    @POST(AppNetConfig.urlPath + "wap/userCoinWithdrawal/mentionCoin")
    Observable<BaseRes<UserCoinWithdrawInfo>> userWithdraw(@Body UserWithdrawReq req);

    //获取经销商管理信息
    @POST(AppNetConfig.urlPath + "wap/dealerManagment/showMore")
    Observable<BaseRes<DealerManagementRes>> getDealerManagmentInfo(@Body PageNumberReq req);

    //删除经销商管理信息
    @POST(AppNetConfig.urlPath + "wap/dealerManagment/deleteOtcTransactionPendOrder")
    Observable<BaseRes<Object>> deleteDealerManagementInfo(@Body DeleteDealerReq req);

    //获取挂单记录信息
    @POST(AppNetConfig.urlPath + "wap/wapTransactionPendOrderController/getTransactionPendOrderList")
    Observable<BaseRes<OrderRecodeRes>> getTradeCenterInfo(@Body PageNumberReq req);

    //撤销挂单
    @POST(AppNetConfig.urlPath + "wap/wapTransactionPendOrderController/revoke")
    Observable<BaseRes<Object>> cancelTradeCenter(@Body OrderRecodeCancelReq req);

    //获取币种提出记录信息
    @POST(AppNetConfig.urlPath + "wap/presentRecord/showMorePresent")
    Observable<BaseRes<PresentRecordRes>> getPresentRecordInfo(@Body PageNumberReq req);

    //获取币种充值记录信息
    @POST(AppNetConfig.urlPath + "wap/wapRechargeCoinRecord/getRechargeCoinRecordList")
    Observable<BaseRes<PresentRechargeRes>> getPresentRechargeCoinInfo(@Body PageNumberReq req);

    //撤回币种提现
    @POST(AppNetConfig.urlPath + "wap/presentRecord/withdrawCoinOutRecord")
    Observable<BaseRes<Object>> cancelPresentRecord(@Body CoinRecordCancelReq req);

    //获取场外交易成交记录(经销商)
    @POST(AppNetConfig.urlPath + "wap/dealerOtcRecord/showMore")
    Observable<BaseRes<OtcDealRecordRes>> getDealOtcRecordInfo(@Body PageNumberReq req);

    //获取场外交易成交记录
    @POST(AppNetConfig.urlPath + "wap/userOtcDealRecord/showMore")
    Observable<BaseRes<OtcDealRecordRes>> getOtcDealRecordInfo(@Body PageNumberReq req);

    //获取场外交易成交记录详情
    @POST(AppNetConfig.urlPath + "wap/userOtcDealRecord/userOtcDetail")
    Observable<BaseRes<OtcDealRecordDetailsRes>> getUserSideOrderDetaid(@Body OutSideDetailReq req);

    //获取场外交易成交记录详情(经销商)
    @POST(AppNetConfig.urlPath + "wap/dealerOtcRecord/sellerOtcDetail")
    Observable<BaseRes<OtcDealRecordDetailsRes>> getOutSideOrderDetaid(@Body OutSideDetailReq req);

    //确认收币
    @POST(AppNetConfig.urlPath + "wap/dealerOtcRecord/confirmTakeCoin")
    Observable<BaseRes<Object>> getOutSideOrderTakeCoin(@Body OutSideDetailReq req);

    //普通用户确认收货
    @POST(AppNetConfig.urlPath + "wap/userOtcDealRecord/userConfirm")
    Observable<BaseRes<Object>> getOutSideOrderTakeUser(@Body OutSideDetailReq req);

    //确认收钱
    @POST(AppNetConfig.urlPath + "wap/dealerOtcRecord/confirmTakeMoney")
    Observable<BaseRes<Object>> getOutSideOrderTakeMoney(@Body OutSideDetailReq req);

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
    Observable<BaseRes<AccountRecordRes>> getAccountRecordInfo(@Body AccountRecordReq req);

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
    @Multipart
    @POST(AppNetConfig.urlPath + "wap/dealerManagment/otcRelease")
    Observable<BaseRes<Object>> sendOtcReleaseInfo(@Part("data") RequestBody req, @Part("alipayImageUrl\"; filename = " +
            "\"aliPay.jpg") RequestBody frontFile, @Part("wechatImageUrl\"; filename = \"weixinPay.jpg") RequestBody backFile);

}
