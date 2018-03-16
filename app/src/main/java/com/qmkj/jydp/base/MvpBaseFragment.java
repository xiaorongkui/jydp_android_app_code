package com.qmkj.jydp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmkj.jydp.ui.activity.ViewInterface;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseFragment implements ViewInterface {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSuccess(Object response, int tag) {

    }

    @Override
    public void onError(String errorMsg, String code, int tag) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
