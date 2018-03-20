package com.qmkj.jydp.net.observer;

import android.app.Activity;
import android.content.Context;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.R;
import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.exception.HandlerException;
import com.qmkj.jydp.net.HttpOnNextListener;
import com.qmkj.jydp.net.api.BaseApi;
import com.qmkj.jydp.ui.widget.NetProgressDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;

/**
 * Created by Yun on 2018/2/19 0019.
 * 网络请求观察者(刷新样式由子类决定)
 */
public class BaseShowLoadingObserver<T> extends BaseObserver<T> {

    private final SoftReference<Context> mActivity;
    private HttpOnNextListener httpOnNextListener = null;
    private NetProgressDialog pd;
    private boolean showPorgress = true;

    /**
     * 显示loading
     */
    protected void showLoading() {
        if (!isShowPorgress()) return;
        Context context = mActivity.get();
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            pd.show();
        }
    }

    ;

    /**
     * 隐藏loading
     */
    protected void hideLoading() {
        if (!isShowPorgress()) return;
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        hideLoading();
    }

    private void initProgressDialog(boolean cancel) {
        Context context = mActivity.get();
        if (pd == null && context != null) {
            pd = NetProgressDialog.createLoadingDialog(context, "加载中...");
            pd.setAlertDialogSize((int) CommonUtil.getDimen(R.dimen.x100), (int) CommonUtil
                    .getDimen(R.dimen.y100));
            pd.setCancelable(cancel);
            if (cancel) {
                pd.setOnCancelListener(dialogInterface -> {
                    if (httpOnNextListener != null) {
                        httpOnNextListener.onCancel();
                    }
                    onCancelProgress();
                });
            }
        }

    }

    public BaseShowLoadingObserver(BaseApi api) {
        this.httpOnNextListener = api.getListener();
        this.mActivity = new SoftReference(api.getRxAppCompatActivity());
        setShowPorgress(api.isshowProgressDialog());
        if (api.isshowProgressDialog()) {
            initProgressDialog(api.isCancel());
        }
    }

    //自定义方法调用,
    public BaseShowLoadingObserver(Context context, boolean isShowProgress) {
        setShowPorgress(isShowProgress);
        this.mActivity = new SoftReference(context);
        if (isShowPorgress()) {
            initProgressDialog(true);
        }
    }

    @Override
    protected void onRequestStart() {
        super.onRequestStart();
        if (!CommonUtil.isNetworkAvailable(JYDPExchangeApp.getContext())) {
            onRequestError(HandlerException.handleException(new
                    HandlerException.ResponeThrowable("网络连接失败，请检查网络后重试", NetResponseCode
                    .HMC_NETWORK_ERROR)));

        } else {
            showLoading();
        }
    }

    @Override
    protected void onRequestSuccess(T t) {
        LogUtil.i("1027", "成功网络请求返回");
        if (httpOnNextListener != null) {
            httpOnNextListener.onNext(t);
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
        if (httpOnNextListener != null) {
            httpOnNextListener.onError(HandlerException.handleException(e));
        }
    }

    @Override
    protected void onRequestComplete() {
        super.onRequestComplete();
        LogUtil.i("订阅完成。");
        hideLoading();
    }

    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }

    public boolean isShowPorgress() {
        return showPorgress;
    }
}
