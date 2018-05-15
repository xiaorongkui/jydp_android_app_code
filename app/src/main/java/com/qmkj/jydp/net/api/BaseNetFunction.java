package com.qmkj.jydp.net.api;

import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.net.exception.HandlerException;
import com.qmkj.jydp.util.LogUtil;

import io.reactivex.functions.Function;

/**
 * 请求返回结果的过滤
 */
public class BaseNetFunction<T> implements Function<BaseRes<T>, T> {
    public BaseNetFunction() {
    }

    @Override
    public T apply(BaseRes<T> resultEntry) throws Exception {

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
