package com.qmkj.jydp.net;

/**
 * 成功回调处理
 */
public interface HttpCallBack<T> {
    /**
     * 成功后回调方法
     *
     * @param t
     */
    void onNext(T t);


    /**
     * 失败或者错误方法
     *
     * @param e
     */
    void onError(Throwable e);

    void onCancel();
}
