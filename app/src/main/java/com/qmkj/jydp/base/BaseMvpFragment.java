package com.qmkj.jydp.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.di.component.DaggerFragmentComponent;
import com.qmkj.jydp.di.component.FragmentComponent;
import com.qmkj.jydp.di.module.FragmentModule;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:mvp模式下的fragment
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    @Inject
    public T presenter;
    private CommonDialog loginCommonDialog;

    protected abstract void injectPresenter();

    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(JYDPExchangeApp.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        injectPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
        if (loginCommonDialog != null && loginCommonDialog.isShowing()) loginCommonDialog.dismiss();
    }

    @Override
    public void onSuccess(Object response, int tag) {

    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        LogUtil.i("接口请求失败" + ";errorMsg=" + errorMsg + ";errorcode=" + code + ";tag=" + tag);

        if (TextUtils.isEmpty(code)) {
            toast(errorMsg);
            return;
        }
        switch (code) {
            case NetResponseCode.HMC_NO_LOGIN:
                if (loginCommonDialog == null) {
                    loginCommonDialog = new com.qmkj.jydp.ui.widget.dialog.CommonDialog(mContext);
                    loginCommonDialog.setContentText("登录失效，请登录");
                    loginCommonDialog.setOnPositiveButtonClickListener((Dialog dialog, View view) -> {
                        CommonUtil.gotoActivity(mContext, LoginActivity.class);
                        loginCommonDialog.dismiss();
                    });
                    loginCommonDialog.show();
                } else {
                    if (!loginCommonDialog.isShowing()) loginCommonDialog.show();
                }
                break;
            default:
                toast(errorMsg);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (loginCommonDialog != null && loginCommonDialog.isShowing()) loginCommonDialog.dismiss();
    }
}
