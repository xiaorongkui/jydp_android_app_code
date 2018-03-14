package com.qmkj.jydp.net.observer;

import android.content.Context;

import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.base.BaseActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求观察者（activity关闭时同时取消网络订阅）
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    protected WeakReference<Context> reference;

    protected Context getContext() {
        return reference.get();
    }

    /**
     * 网络请求开始
     */
    protected void onRequestStart() {

    }

    /**
     * 网络请求成功 业务处理成功code = 1
     */
    protected abstract void onRequestSuccess(BaseResponse<T> response);

    /**
     * 网络请求成功 业务处理失败code != 1
     */
    protected abstract void onRequestError(BaseResponse<T> response);

    /**
     * 网络请求失败
     */
    protected void onRequestNetError(Throwable e) {

    }

    /**
     * 网络请求完成
     */
    protected void onRequestComplete() {

    }

    public BaseObserver(Context context) {
        reference = new WeakReference<>(context);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (reference.get() != null) {
            Context context = reference.get();
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).disposableList.add(d);
            }
        }
        onRequestStart();
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (response.getCode() == 1) {
            onRequestSuccess(response);
        } else if (response.getCode() == 4) {
            //todo session过期、异常处理
        } else {
            onRequestError(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onRequestNetError(e);
        onComplete();
    }

    @Override
    public void onComplete() {
        onRequestComplete();
    }
}
