package com.qmkj.jydp.module.login.presenter;

import android.app.Activity;

import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.request.RegisterCodeReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.net.api.LoginService;

import javax.inject.Inject;

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

        sendHttpRequest(loginService.getRegisterCode(req), tag);
    }

    public void startRegister(RegisterReq req, int tag) {

        sendHttpRequest(loginService.startRegister(req), tag);
    }
}
