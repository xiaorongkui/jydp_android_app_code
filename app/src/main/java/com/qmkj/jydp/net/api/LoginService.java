package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.CertificetionInfoReq;
import com.qmkj.jydp.bean.request.CertifyNameReq;
import com.qmkj.jydp.bean.request.ForgetPwdReq;
import com.qmkj.jydp.bean.request.ReCertificetionReq;
import com.qmkj.jydp.bean.request.RegisterCodeReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.CertificetionInfoRes;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.response.ReCertificetionRes;
import com.qmkj.jydp.bean.response.RegisterRes;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @POST(AppNetConfig.urlPath + "wap/userLogin/login")
    Observable<BaseRes<LoginRes>> startLogin(@Body LoginReq req);

    /**
     * 注册获取验证码
     */
    @FormUrlEncoded
    @POST(AppNetConfig.urlPath + "sendCode/sendPhoneCode")
    Observable<BaseRes<Object>> getRegisterCode(@Field("phoneNumber") String code);

    /**
     * 注册
     */
    @POST(AppNetConfig.urlPath + "wap/userRegister/register")
    Observable<BaseRes<RegisterRes>> startRegister(@Body RegisterReq req);

    /**
     * 实名认证
     */
    @Multipart
    @POST(AppNetConfig.urlPath + "wap/identificationController/add")
    Observable<BaseRes<Object>> submitCertify(@Part("data") RequestBody req, @Part("frontImg\"; filename = " +
            "\"frontImg.jpg") RequestBody frontFile, @Part("backImg\"; filename = \"backImg.jpg") RequestBody backFile);

    /**
     * 忘记密码
     */
    @POST(AppNetConfig.urlPath + "wap/forgetPassword/forgetPassword")
    Observable<BaseRes<Object>> submitForgetPwd(@Body ForgetPwdReq req);

    /**
     * 重新认证
     */
    @POST(AppNetConfig.urlPath + "wap/identificationController/showAdd")
    Observable<BaseRes<ReCertificetionRes>> getReCertificationStaus(@Body ReCertificetionReq req);

    /**
     * 认证信息
     */
    @POST(AppNetConfig.urlPath + "wap/identificationController/show")
    Observable<BaseRes<CertificetionInfoRes>> getCertifyStatus(@Body CertificetionInfoReq req);
}
