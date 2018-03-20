package com.qmkj.jydp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmkj.jydp.ui.activity.ViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseFragment implements ViewInterface {
    protected List<? extends BasePresenter> p = new ArrayList<>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        injectPresenter();
        for (BasePresenter presenter : p) {
            if (presenter != null) presenter.attachView(this);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
