package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.BaseReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.HomeDataRes;
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
public interface HomeService {
    /**
     * 首页接口
     *
     * @return the home auto roll product
     */
    @GET(AppNetConfig.urlPath + "wap/homePage/show")
    Observable<BaseRes<HomeDataRes>> getCurrentPrice();
}
