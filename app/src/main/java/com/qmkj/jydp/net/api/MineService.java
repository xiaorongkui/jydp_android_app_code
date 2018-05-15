package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface MineService {
    /**
     * 获取我的信息
     *
     * @return the home auto roll product
     */
    @POST(AppNetConfig.urlPath + "userApp/login/my.htm")
    Observable<BaseRes<Object>> getMineInfo();
}
