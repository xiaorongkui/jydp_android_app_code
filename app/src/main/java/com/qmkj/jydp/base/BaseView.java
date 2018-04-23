package com.qmkj.jydp.base;

public interface BaseView<T> {

    /**
     * @param errorMsg 网络请求失败的返回结果
     * @param code     网络请求失败的返回码
     * @param tag      网络请求标记，作为返回
     */
    void onError(String errorMsg, String code, int tag);

    /**
     * @param response 网络请求返回结果
     * @param tag      网络请求标记，作为返回
     */
    void onSuccess(T response, int tag);

}
