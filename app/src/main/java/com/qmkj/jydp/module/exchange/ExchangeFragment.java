package com.qmkj.jydp.module.exchange;

import android.view.View;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BasePresenter;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.module.home.presenter.HomePresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeFragment extends MvpBaseFragment {
    HomePresenter homePresenter;


    @Override
    protected void initView() {
    }

    @Override
    protected void findViewId(View rootView) {

    }

    @Override
    protected void initData() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);


    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
    }
}
