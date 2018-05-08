package com.qmkj.jydp.net.api;

import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.net.exception.HandlerException;
import com.qmkj.jydp.net.HttpCallBack;
import com.qmkj.jydp.util.LogUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.ref.SoftReference;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求返回结果的过滤
 */
public class BaseNetFunction<T> implements Function<BaseResponse<T>, T> {
    public BaseNetFunction() {
    }

    @Override
    public T apply(BaseResponse<T> resultEntry) throws Exception {

        String responseCode = resultEntry.getCode();
        String responseMessage = resultEntry.getMessage();
        T data = resultEntry.getData();
        LogUtil.i(resultEntry.toString());
        switch (responseCode) {
            case NetResponseCode.HMC_SUCCESS:
                //数据成功
                return data;
            //其他情况自己处理
            default:
                throw new HandlerException.ResponeThrowable(responseMessage, responseCode);
        }
    }
}
