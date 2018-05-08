package com.qmkj.jydp.module.login.presenter;

import android.app.Activity;

import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.bean.LoginRequest;
import com.qmkj.jydp.net.api.LoginService;

import java.util.Map;

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

    public void loginStart(LoginRequest maps, int tag, boolean isShowProgress) {

        sendHttpRequest(loginService.startLogin(maps), tag);
    }
}
