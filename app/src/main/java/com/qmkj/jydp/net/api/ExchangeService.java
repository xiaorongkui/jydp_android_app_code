package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.BuyExchangeReq;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.request.ExchangeDealRecodeReq;
import com.qmkj.jydp.bean.request.ExchangePwdReq;
import com.qmkj.jydp.bean.request.KlineReq;
import com.qmkj.jydp.bean.request.SellExchangeReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.BuyExchangeRes;
import com.qmkj.jydp.bean.response.CancleOrderReq;
import com.qmkj.jydp.bean.response.DealRecodeRes;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.bean.response.ExchangeCurrencyRes;
import com.qmkj.jydp.bean.response.KlineRes;
import com.qmkj.jydp.bean.response.SellExchangeRes;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * The interface Exchange service.
 */
public interface ExchangeService {
    /**
     * 交易中心币种获取
     *
     * @return ExchangeCurrencyRes exchange currency
     */
    @GET("wap/tradeCenter/currencyInfo")
    Observable<BaseRes<ExchangeCurrencyRes>> getExchangeCurrency();

    /**
     * 交易中心数据
     *
     * @param req the req
     * @return ExchangeCenterRes exchange center data
     */
    @POST("wap/tradeCenter/getWapTradeCenterInfo")
    Observable<BaseRes<ExchangeCenterRes>> getExchangeCenterData(@Body ExchangeCenterReq req);

    /**
     * 交易中心获取挂单记录
     *
     * @param req the req
     * @return ExchangeCenterRes exchange pend order
     */
    @POST("wap/tradeCenter/pend")
    Observable<BaseRes<ExchangeCenterRes>> getExchangePendOrder(@Body ExchangeCenterReq req);

    /**
     * 交易中心委托记录数据
     *
     * @param req the req
     * @return the entrust recode data
     */
    @POST("wap/tradeCenter/entrust")
    Observable<BaseRes<ExchangeCenterRes>> getEntrustRecodeData(@Body ExchangeCenterReq req);

    /**
     * 交易中心获取交易相关价格（基准信息）
     *
     * @param req the req
     * @return the exchange deal price
     */
    @POST("wap/tradeCenter/gainDealPrice")
    Observable<BaseRes<ExchangeCenterRes>> getExchangeDealPrice(@Body ExchangeCenterReq req);

    /**
     * 交易中心购买接口
     *
     * @param req the req
     * @return the observable
     */
    @POST("wap/tradeCenter/buy")
    Observable<BaseRes<BuyExchangeRes>> buyXtExchange(@Body BuyExchangeReq req);

    /**
     * 交易中心卖出接口
     *
     * @param req the req
     * @return the observable
     */
    @POST("wap/tradeCenter/sell")
    Observable<BaseRes<SellExchangeRes>> sellXtExchange(@Body SellExchangeReq req);

    /**
     * 交易中心记住密码接口
     *
     * @param req the req
     * @return the observable
     */
    @POST("wap/tradeCenter/rememberPwd")
    Observable<BaseRes<Object>> rememberExchangePwd(@Body ExchangePwdReq req);

    /**
     * 交易中心获取K线图数据
     *
     * @param req the req
     * @return the kline data
     */
    @POST("wap/tradeCenter/gainGraphData")
    Observable<BaseRes<KlineRes>> getKlineData(@Body KlineReq req);

    /**
     * 交易中心取消挂单
     *
     * @param req the req
     * @return the observable
     */
    @POST("wap/wapTransactionPendOrderController/revoke")
    Observable<BaseRes<Object>> cancleOrder(@Body CancleOrderReq req);

    /**
     * 交易中心取消挂单
     *
     * @param req the req
     * @return the exchange deal recode
     */
    @POST("wap/tradeCenter/deal")
    Observable<BaseRes<DealRecodeRes>> getExchangeDealRecode(@Body ExchangeDealRecodeReq req);
}
