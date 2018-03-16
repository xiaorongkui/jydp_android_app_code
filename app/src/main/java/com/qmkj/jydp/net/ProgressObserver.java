package com.qmkj.jydp.net;

import android.app.Activity;
import android.content.Context;

import com.qmkj.jydp.R;
import com.qmkj.jydp.net.api.BaseApi;
import com.qmkj.jydp.ui.widget.NetProgressDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


import java.lang.ref.SoftReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressObserver<T> implements Observer<T> {
    /*是否弹框*/
    private boolean showPorgress = true;
    /* 软引用回调接口*/
    private HttpOnNextListener mSubscriberOnNextListener;
    /*软引用反正内存泄露*/
    private SoftReference<RxAppCompatActivity> mActivity;
    /*加载框可自己定义*/
    private NetProgressDialog pd;
    /*请求数据*/
    private Disposable disposable;


    /**
     * 构造
     *
     * @param api
     */
    public ProgressObserver(BaseApi api) {
        this.mSubscriberOnNextListener = api.getListener();
        this.mActivity = new SoftReference(api.getRxAppCompatActivity());
        setShowPorgress(api.isshowProgressDialog());
        if (api.isshowProgressDialog()) {
            initProgressDialog(api.isCancel());
        }
    }


    /**
     * 初始化加载框
     */
    private void initProgressDialog(boolean cancel) {
        Activity context = mActivity.get();
        if (pd == null && context != null) {
            pd = NetProgressDialog.createLoadingDialog(context, "加载中...");
            pd.setAlertDialogSize((int) CommonUtil.getDimen(R.dimen.x100), (int) CommonUtil
                    .getDimen(R.dimen.y100));
            pd.setCancelable(cancel);
            if (cancel) {
                pd.setOnCancelListener(dialogInterface -> {
                    if (mSubscriberOnNextListener != null) {
                        mSubscriberOnNextListener.onCancel();
                    }
                    onCancelProgress();
                });
            }
        }
    }


    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowPorgress()) return;
        Context context = mActivity.get();
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            pd.show();
        }
    }


    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress()) return;
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }


    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        LogUtil.i("订阅完成。");
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        LogUtil.i("订阅出错。异常情况=" + e.toString() + ":" + e.getMessage());
        errorDo(e);
    }

    /*错误统一处理*/
    private void errorDo(Throwable e) {
        Context context = mActivity.get();
        if (context == null) return;

    }


    @Override
    public void onSubscribe(Disposable d) {
        LogUtil.i("订阅开始");
        showProgressDialog();
        this.disposable = d;
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建observer时的泛型类型
     */
    @Override
    public void onNext(T t) {
        LogUtil.i("1027", "成功网络请求返回");
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
        dismissProgressDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!disposable.isDisposed()) {
            disposable.isDisposed();
        }
    }


    public boolean isShowPorgress() {
        return showPorgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }


}