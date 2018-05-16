package com.qmkj.jydp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.di.component.DaggerFragmentComponent;
import com.qmkj.jydp.di.component.FragmentComponent;
import com.qmkj.jydp.di.module.FragmentModule;

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
    }

    @Override
    public void onSuccess(Object response, int tag) {

    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {

    }

}
