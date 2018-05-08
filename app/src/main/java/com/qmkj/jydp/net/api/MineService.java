package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.common.AppNetConfig;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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
    Observable<BaseResponse<Object>> getMineInfo();
}
