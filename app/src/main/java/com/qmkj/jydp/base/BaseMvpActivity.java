package com.qmkj.jydp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.di.component.ActivityComponent;
import com.qmkj.jydp.di.component.DaggerActivityComponent;
import com.qmkj.jydp.di.module.ActivityModule;
import com.qmkj.jydp.util.LogUtil;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:基础的BaseMvpActivity
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    /**
     * 对象要给子类用，所以要在其实例化的地方进行注解
     */
    @Inject
    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        presenter = null;
    }

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(JYDPExchangeApp.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void injectPresenter();

    @Override
    public void onSuccess(Object response, int tag) {
        LogUtil.i(response + "");
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        LogUtil.i("接口请求失败" + ";errorMsg=" + errorMsg + ";errorcode=" + code + ";tag=" + tag);
        toast(errorMsg);
    }

}
