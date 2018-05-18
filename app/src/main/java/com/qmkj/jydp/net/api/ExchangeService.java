package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.bean.response.ExchangeCurrencyRes;
import com.qmkj.jydp.common.AppNetConfig;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface ExchangeService {
    /**
     * 交易中心币种获取
     *
     * @return the home auto roll product
     */
    @GET(AppNetConfig.urlPath + "wap/tradeCenter/currencyInfo")
    Observable<BaseRes<ExchangeCurrencyRes>> getExchangeCurrency();

    /**
     * 交易中心数据
     *
     * @return the home auto roll product
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/getWapTradeCenterInfo")
    Observable<BaseRes<ExchangeCenterRes>> getExchangeCenterData(@Body ExchangeCenterReq req);
}
