package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.CertificetionInfoReq;
import com.qmkj.jydp.bean.request.ChangePassWordReq;
import com.qmkj.jydp.bean.request.ChangePhoneReq;
import com.qmkj.jydp.bean.request.ForgetPwdReq;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.bean.request.ReCertificetionReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.bean.response.AppUpdateRes;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.CertificetionInfoRes;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.bean.response.ReCertificetionRes;
import com.qmkj.jydp.bean.response.RegisterRes;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @POST("wap/userLogin/login")
    Observable<BaseRes<LoginRes>> startLogin(@Body LoginReq req);

    /**
     * 注册获取验证码
     */
    @POST("wap/sendCode/sendPhoneCode")
    Observable<BaseRes<Object>> getRegisterCode(@Body PhoneCodeReq req);

    /**
     * 注册
     */
    @POST("wap/userRegister/register")
    Observable<BaseRes<RegisterRes>> startRegister(@Body RegisterReq req);

    /**
     * 实名认证
     */
    @Multipart
    @POST("wap/identificationController/add")
    Observable<BaseRes<Object>> submitCertify(@Part("data") RequestBody req, @Part("frontImg\"; filename = " +
            "\"frontImg.jpg") RequestBody frontFile, @Part("backImg\"; filename = \"backImg.jpg") RequestBody backFile);

    /**
     * 忘记密码
     */
    @POST("wap/forgetPassword/forgetPassword")
    Observable<BaseRes<Object>> submitForgetPwd(@Body ForgetPwdReq req);

    /**
     * 重新认证
     */
    @POST("wap/identificationController/showAdd")
    Observable<BaseRes<ReCertificetionRes>> getReCertificationStaus(@Body ReCertificetionReq req);

    /**
     * 认证信息
     */
    @POST("wap/identificationController/show")
    Observable<BaseRes<CertificetionInfoRes>> getCertifyStatus(@Body CertificetionInfoReq req);

    /**
     * 修改密码
     */
    @POST("wap/userInfo/password/modify")
    Observable<BaseRes<Object>> changePassWord(@Body ChangePassWordReq req);

    /**
     * 通过支付密码修改支付密码
     */
    @POST("wap/userInfo/payPassword/modifyByPwd")
    Observable<BaseRes<Object>> changePassWordByPwd(@Body ChangePassWordReq req);

    /**
     * 通过验证码修改支付密码
     */
    @POST("wap/userInfo/payPassword/modifyByPhone")
    Observable<BaseRes<Object>> changePassWordByPhone(@Body ChangePassWordReq req);

    /**
     * 通过验证码修改支付密码
     */
    @POST("wap/userInfo/phone/modify")
    Observable<BaseRes<Object>> changePhone(@Body ChangePhoneReq req);

    /**
     * 检查app更新
     *
     * @return the home auto roll product
     */
    @GET("app/version/updateVersion")
    Observable<BaseRes<AppUpdateRes>> checkAppUpdate();

}
