package com.qmkj.jydp.net.api;


import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.bean.response.BaseRes;
import com.qmkj.jydp.bean.response.OutSideExchangeRes;
import com.qmkj.jydp.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface OutSideExchangeService {
    /**
     * 场外交易列表接口
     *
     * @return
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/showMore")
    Observable<BaseRes<OutSideExchangeRes>> getOutsideExchangeData(@Body OutSideExchangeReq req);
}
