package com.qmkj.jydp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qmkj.jydp.ui.activity.ViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseActivity implements ViewInterface {
    protected List<? extends BasePresenter> p = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectPresenter();
        for (BasePresenter presenter : p) {
            if (presenter != null) presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (BasePresenter presenter : p) {
            if (presenter != null) presenter.detachView(this);
        }
        p.clear();
        p = null;
    }

    protected abstract void injectPresenter();

    @Override
    public void onSuccess(Object response, int tag) {

    }

    @Override
    public void onError(String errorMsg, String code, int tag) {

    }

}
