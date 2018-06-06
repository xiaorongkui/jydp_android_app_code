package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.DistributorPayMethodReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.bean.request.OutSideSellReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.DistributorPayMethodRes;
import com.qmkj.jydp.bean.response.OutSideBuyPayDetailRes;
import com.qmkj.jydp.bean.response.OutSideExchangeRes;
import com.qmkj.jydp.bean.response.OutSideSellDetailRes;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface OutSideExchangeService {
    /**
     * 场外交易列表接口
     *
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/showMore")
    Observable<BaseRes<OutSideExchangeRes>> getOutsideExchangeData(@Body OutSideExchangeReq req);

    /**
     * 场外交易购买接口
     *
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/buy")
    Observable<BaseRes<Object>> payOutsideExchange(@Body OutSideBuyPayReq req);

    /**
     * 场外交易购买详情确认接口
     *
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/userBuyDetail")
    Observable<BaseRes<OutSideBuyPayDetailRes>> payOutsideDetailConfirm(@Body OutSideBuyPayDetailReq req);

    /**
     * 场外交易获取经销商的付款方式
     *
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/getPayType")
    Observable<BaseRes<DistributorPayMethodRes>> getDistributorPayMethod(@Body DistributorPayMethodReq req);

    /**
     * 场外交易出售详情接口
     *
     */
    @Multipart
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/userSellDetail")
    Observable<BaseRes<OutSideSellDetailRes>> sellOutsideDetailConfirm(@Part("data") RequestBody req, @Part
            ("alipayPaymentImage\"; " +
            "filename = " + "\"alipayPaymentImage.jpg") RequestBody alipayImage, @Part("wechatPaymentImage\"; " +
            "filename = " + "\"wechatPaymentImage.jpg") RequestBody weichatImage);

    /**
     * 场外交易出售
     *
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/sell")
    Observable<BaseRes<Object>> sellOutsideExchange(@Body OutSideSellReq req);
}
