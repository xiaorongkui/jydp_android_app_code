package com.qmkj.jydp.module.login.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.request.RegisterCodeReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.net.api.LoginService;
import com.qmkj.jydp.util.LogUtil;

import javax.inject.Inject;

import okhttp3.MediaType;
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
    public LoginPresenter(Activity activity) {
        super(activity);
    }

    public void loginStart(LoginReq req, int tag, boolean isShowProgress) {

        sendHttpRequest(loginService.startLogin(req), tag);
    }

    public void getRegisterCode(RegisterCodeReq req, int tag) {
        Gson gson = new Gson();
        String s = gson.toJson(req);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), s);
        sendHttpRequest(loginService.getRegisterCode(requestBody), tag);
    }

    public void startRegister(RegisterReq req, int tag) {
        Gson gson = new Gson();
        String s = gson.toJson(req);
        LogUtil.i("s=" + s);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), s);
        sendHttpRequest(loginService.startRegister(requestBody), tag);
    }
}
