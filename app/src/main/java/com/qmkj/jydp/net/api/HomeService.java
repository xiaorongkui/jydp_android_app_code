package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.HomeDataRes;

import io.reactivex.Observable;
import retrofit2.http.GET;

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
    @GET("wap/homePage/show")
    Observable<BaseRes<HomeDataRes>> getCurrentPrice();
}
