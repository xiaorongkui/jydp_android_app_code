package com.qmkj.jydp.module.exchange.view;

import android.os.Bundle;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.common.Constants;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:m
 * 卖出界面
 */

public class ExchangeSoldFragment extends MvpBaseFragment {

    public static ExchangeSoldFragment newInstance(int index) {
        Bundle args = new Bundle();
        ExchangeSoldFragment fragment = new ExchangeSoldFragment();
        args.putInt(Constants.INTENT_PARAMETER_1, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.exchange_fragment_exchange_buy;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }
}