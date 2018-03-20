package com.qmkj.jydp.module.mine.view;

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
 * description:我的页面功能
 */

public class MineFragment extends MvpBaseFragment {


    @Override
    protected void initView() {
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
    protected void injectPresenter() {

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