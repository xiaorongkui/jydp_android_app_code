package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.bean.LoginBean;
import com.qmkj.jydp.bean.LoginRequest;
import com.qmkj.jydp.common.AppNetConfig;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 网络请求接口,入参统一使用map集合,
 */
public interface LoginService {
    /**
     * 用户登录
     * @param req the maps
     * @return the home auto roll product
     */
    @POST(AppNetConfig.urlPath + "userApp/login/login")
    Observable<BaseResponse<LoginBean>> startLogin(@Body LoginRequest req);
}
