package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.BuyExchangeReq;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.request.ExchangePwdReq;
import com.qmkj.jydp.bean.request.SellExchangeReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.BuyExchangeRes;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.bean.response.ExchangeCurrencyRes;
import com.qmkj.jydp.bean.response.ExchangeEntrustRecodeRes;
import com.qmkj.jydp.bean.response.SellExchangeRes;
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
     * @return ExchangeCurrencyRes
     */
    @GET(AppNetConfig.urlPath + "wap/tradeCenter/currencyInfo")
    Observable<BaseRes<ExchangeCurrencyRes>> getExchangeCurrency();

    /**
     * 交易中心数据
     *
     * @return ExchangeCenterRes
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/getWapTradeCenterInfo")
    Observable<BaseRes<ExchangeCenterRes>> getExchangeCenterData(@Body ExchangeCenterReq req);

    /**
     * 交易中心委托记录数据
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/entrust")
    Observable<BaseRes<ExchangeEntrustRecodeRes>> getEntrustRecodeData(@Body ExchangeCenterReq req);

    /**
     * 交易中心购买接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/buy")
    Observable<BaseRes<BuyExchangeRes>> buyXtExchange(@Body BuyExchangeReq req);

    /**
     * 交易中心卖出接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/sell")
    Observable<BaseRes<SellExchangeRes>> sellXtExchange(@Body SellExchangeReq req);

    /**
     * 交易中心记住密码接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/rememberPwd")
    Observable<BaseRes<BaseRes>> rememberExchangePwd(@Body ExchangePwdReq req);
}
