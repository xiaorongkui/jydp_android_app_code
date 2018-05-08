package com.qmkj.jydp.net;

/**
 * 成功回调处理
 *
 * @param <T> the type parameter
 */
public interface HttpCallBack<T> {
    /**
     * 成功后回调方法
     *
     * @param t   the t
     * @param tag the tag
     */
    void onNext(T t, int tag);


    /**
     * 失败或者错误方法
     *
     * @param e the e
     */
    void onError(Throwable e, int tag);

    /**
     * On cancel.
     */
    void onCancel(int tag);
}
