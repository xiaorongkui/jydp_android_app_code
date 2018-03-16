package com.qmkj.jydp.module.login.presenter;

import com.qmkj.jydp.base.BasePresenter;
import com.qmkj.jydp.module.login.modle.LoginImpl;
import com.qmkj.jydp.net.HttpOnNextListener;
import com.qmkj.jydp.ui.activity.ViewInterface;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Map;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class LoginPresenter extends BasePresenter<ViewInterface> {
    private LoginImpl loginImpl;

    public LoginPresenter(RxAppCompatActivity activity) {
        loginImpl = new LoginImpl(activity);
    }

    public void getCurrentPrice(final Map<String, Object> maps, int tag, boolean isShowProgress) {
        loginImpl.getCurrentPrice(maps, new HttpOnNextListener() {
            @Override
            public void onNext(Object o) {
                mView.onSuccess(o, tag);
            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getMessage(), "", tag);
            }

            @Override
            public void onCancel() {

            }
        }, isShowProgress);
    }

}
