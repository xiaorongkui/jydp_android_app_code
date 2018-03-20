package com.qmkj.jydp.net.observer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求观察者（activity关闭时同时取消网络订阅）
 */
public abstract class BaseObserver<T> implements Observer<T> {

    protected List<Disposable> disposableList = new ArrayList<>();
    /**
     * 网络请求开始
     */
    protected void onRequestStart() {

    }

    /**
     * 网络请求成功 业务处理成功code = 1
     */
    protected abstract void onRequestSuccess(T response);

    /**
     * 网络请求成功 业务处理失败code != 1
     */
    protected abstract void onRequestError(Throwable response);


    /**
     * 网络请求完成
     */
    protected void onRequestComplete() {

    }

    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposableList.add(d);
        onRequestStart();
    }

    @Override
    public void onNext(T t) {
        onRequestSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onRequestError(e);
    }

    @Override
    public void onComplete() {
        onRequestComplete();
    }
}
