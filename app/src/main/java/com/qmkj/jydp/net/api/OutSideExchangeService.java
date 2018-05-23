package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.DistributorPayMethodReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.DistributorPayMethodRes;
import com.qmkj.jydp.bean.response.OutSideBuyPayDetailRes;
import com.qmkj.jydp.bean.response.OutSideExchangeRes;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface OutSideExchangeService {
    /**
     * 场外交易列表接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/showMore")
    Observable<BaseRes<OutSideExchangeRes>> getOutsideExchangeData(@Body OutSideExchangeReq req);

    /**
     * 场外交易购买接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/buy")
    Observable<BaseRes<Object>> payOutsideExchange(@Body OutSideBuyPayReq req);

    /**
     * 场外交易购买详情确认接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/userBuyDetail")
    Observable<BaseRes<OutSideBuyPayDetailRes>> payOutsideDetailConfirm(@Body OutSideBuyPayDetailReq req);

    /**
     * 场外交易获取经销商的付款方式
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/getPayType")
    Observable<BaseRes<DistributorPayMethodRes>> getDistributorPayMethod(@Body DistributorPayMethodReq req);
}
