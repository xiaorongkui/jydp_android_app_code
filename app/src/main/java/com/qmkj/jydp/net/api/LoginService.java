package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.RegisterCodeReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.response.RegisterRes;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求接口,入参统一使用map集合,
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param req the maps
     * @return the home auto roll product
     */
    @POST(AppNetConfig.urlPath + "wap/userLogin/login/{userAccount}/{password}")
    Observable<BaseRes<LoginRes>> startLogin(@Body LoginReq req);

    /**
     * 注册获取验证码
     */
    @POST(AppNetConfig.urlPath + "sendCode/sendPhoneCode")
    Observable<BaseRes<BaseRes>> getRegisterCode(@Body RequestBody req);

    /**
     * 注册
     */
    @POST(AppNetConfig.urlPath + "wap/userRegister/register")
    Observable<BaseRes<RegisterRes>> startRegister(@Body RequestBody req);
}
