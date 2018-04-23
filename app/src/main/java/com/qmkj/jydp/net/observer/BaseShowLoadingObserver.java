package com.qmkj.jydp.net.observer;

import android.content.Context;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.R;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.net.exception.HandlerException;
import com.qmkj.jydp.net.HttpCallBack;
import com.qmkj.jydp.net.api.BaseNetFunction;
import com.qmkj.jydp.ui.widget.NetProgressDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ProgressDialogUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Yun on 2018/2/19 0019.
 * 网络请求观察者(刷新样式由子类决定)
 */
public class BaseShowLoadingObserver<T> extends BaseObserver<T> {

    private final WeakReference<Context> mActivity;
    private HttpCallBack httpCallBack = null;
    private boolean showProgressDialog;
    private boolean isCancel;

    /**
     * 显示loading
     */
    protected void showLoading() {
        ProgressDialogUtil.showWaitDialog();
    }


    /**
     * 隐藏loading
     */
    protected void hideLoading() {
        ProgressDialogUtil.stopWaitDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isDisposed())
            this.dispose();
        hideLoading();
        if (httpCallBack != null) httpCallBack.onCancel();
    }

    private void initProgressDialog(boolean cancel) {
        Context context = mActivity.get();
        NetProgressDialog netProgressDialog = ProgressDialogUtil.initProgressDialog(context, cancel);
        if (cancel && netProgressDialog != null) netProgressDialog.setOnCancelListener(dialog -> onCancelProgress());
    }

    public BaseShowLoadingObserver(Context context, HttpCallBack httpCallBack, boolean isShowProgress, boolean
            isCancel) {
        this.httpCallBack = httpCallBack;
        this.mActivity = new WeakReference<>(context);
        this.showProgressDialog = isShowProgress;
        this.isCancel = isCancel;
    }

    @Override
    protected void onRequestStart() {
        super.onRequestStart();
        if (showProgressDialog) {
            initProgressDialog(isCancel);
        }
        if (!CommonUtil.isNetworkAvailable(JYDPExchangeApp.getContext())) {
            onRequestError(HandlerException.handleException(new
                    HandlerException.ResponeThrowable(CommonUtil.getString(R.string.net_connect_error), NetResponseCode
                    .HMC_NETWORK_ERROR)));

        } else {
            showLoading();
        }
    }

    @Override
    protected void onRequestSuccess(T t) {
        LogUtil.i("成功网络请求返回");
        if (httpCallBack != null) {
            httpCallBack.onNext(t);
        }
        hideLoading();
    }

    @Override
    protected void onRequestError(Throwable e) {
        hideLoading();
        LogUtil.i("订阅出错。异常情况=" + e.toString() + ":" + e.getMessage());
        errorDo(e);
    }

    private void errorDo(Throwable e) {
        Context context = mActivity.get();
        if (context == null) return;
        if (httpCallBack != null) {
            httpCallBack.onError(HandlerException.handleException(e));
        }
    }

    @Override
    protected void onRequestComplete() {
        super.onRequestComplete();
        hideLoading();
    }
}
