package com.qmkj.jydp.net.api;

import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.common.SystemMessageConfig;
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
                LogUtil.i(data == null ? "null" : "请求成功");
                if (data == null) {
                    throw new HandlerException.ResponeThrowable(responseMessage, NetResponseCode.HMC_SUCCESS_NULL);
                }
                return data;
            //其他情况自己处理
            case SystemMessageConfig.NOIDENTIFICATION_CODE + ""://未审核实名
                throw new HandlerException.ResponeThrowable(responseMessage, SystemMessageConfig
                        .NOIDENTIFICATION_CODE + "", data);
            case SystemMessageConfig.NOADOPT_CODE + ""://审核实名未通过
                throw new HandlerException.ResponeThrowable(responseMessage, SystemMessageConfig
                        .NOADOPT_CODE + "", data);
            case SystemMessageConfig.REFUE_CODE + ""://审核实名拒绝
                throw new HandlerException.ResponeThrowable(responseMessage, SystemMessageConfig
                        .REFUE_CODE + "", data);
            default:
                throw new HandlerException.ResponeThrowable(responseMessage, responseCode);
        }
    }
}
