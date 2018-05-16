package com.qmkj.jydp.module.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.bean.request.CertifyNameReq;
import com.qmkj.jydp.bean.request.ForgetPwdReq;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.net.api.LoginService;
import com.qmkj.jydp.util.LogUtil;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class LoginPresenter extends BaseRxPresenter<BaseView> {
    @Inject
    LoginService loginService;

    @Inject
    public LoginPresenter(Context context) {
        super(context);
    }


    public void loginStart(LoginReq req, int tag, boolean isShowProgress) {

        sendHttpRequest(loginService.startLogin(req), tag);
    }

    public void getRegisterCode(String num, int tag) {
        LogUtil.i("s=" + num);
        sendHttpRequest(loginService.getRegisterCode(num), tag);
    }

    public void startRegister(RegisterReq req, int tag) {
        sendHttpRequest(loginService.startRegister(req), tag);
    }

    public void submitCertify(CertifyNameReq req, byte[] backBytes, byte[] frontBytes, int tag) {
        RequestBody data = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(req));
        RequestBody frontRequestBody = MultipartBody.create(MediaType.parse("image/jpg"), backBytes);
        RequestBody backRequestBody = MultipartBody.create(MediaType.parse("image/jpg"), frontBytes);
//        MultipartBody.Part frontRequestBodyPart = MultipartBody.Part.createFormData("frontImg", "front.jpg",
// frontRequestBody);
//        MultipartBody.Part backRequestBodyPart = MultipartBody.Part.createFormData("backImg", "back.jpg",
// backRequestBody);
//        RequestBody frontRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), backBytes);
//        RequestBody backRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), frontBytes);
        sendHttpRequest(loginService.submitCertify(data, frontRequestBody, backRequestBody), tag);
    }

    public void submitForgetPwd(ForgetPwdReq req, int tag, boolean isShowProgress) {
        sendHttpRequest(loginService.submitForgetPwd(req), tag);
    }

}
