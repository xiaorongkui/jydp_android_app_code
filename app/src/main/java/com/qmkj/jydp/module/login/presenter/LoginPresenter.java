package com.qmkj.jydp.module.login.presenter;

import android.app.Activity;

import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.base.BaseRxPresenter;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class LoginPresenter extends BaseRxPresenter<BaseView> {

    @Inject
    public LoginPresenter(Activity activity) {
        super(activity);
    }

}
