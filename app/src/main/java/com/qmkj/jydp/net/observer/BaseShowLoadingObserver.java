package com.qmkj.jydp.net.observer;

import android.content.Context;

import com.qmkj.jydp.net.BaseResponse;
import com.qmkj.jydp.util.ToastUtil;

/**
 * Created by Yun on 2018/2/19 0019.
 * 网络请求观察者(刷新样式由子类决定)
 */
public abstract class BaseShowLoadingObserver<T> extends BaseObserver<T> {

    /**
     * 显示loading
     */
    protected abstract void showLoading();

    /**
     * 隐藏loading
     */
    protected abstract void hideLoading();

    protected BaseShowLoadingObserver(Context context) {
        super(context);
    }

    @Override
    protected void onRequestStart() {
        super.onRequestStart();
        showLoading();
    }

    @Override
    protected void onRequestSuccess(BaseResponse<T> response) {
        hideLoading();
    }

    @Override
    protected void onRequestError(BaseResponse<T> response) {
        hideLoading();
        ToastUtil.showShort(response.getMessage());
    }

    @Override
    protected void onRequestNetError(Throwable e) {
        super.onRequestNetError(e);
        hideLoading();
        ToastUtil.showError(e);
    }
}
