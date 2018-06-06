package com.qmkj.jydp.bean.response;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求BaseResponse
 * code 1请求成功 4session过期
 */
public class BaseRes<T> {
    private String code;//1成功
    private String message;//成功的返回信息
    private T data;//具体数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseRes{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
