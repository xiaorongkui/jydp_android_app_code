package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.bean.response.DealerManagementRes;
import com.qmkj.jydp.bean.response.MineRes;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.common.AppNetConfig;

import java.util.Map;

import io.reactivex.Observable;
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
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/pend")
    Observable<BaseRes<OrderRecodeRes>> getTradeCenterInfo(@Field("currencyId") String id);
}
