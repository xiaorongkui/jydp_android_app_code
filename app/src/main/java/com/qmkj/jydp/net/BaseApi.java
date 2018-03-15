package com.qmkj.jydp.net;

import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.NetResponseCode;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * 请求数据统一封装类
 * Created by WZG on 2016/7/16.
 */
public abstract class BaseApi<T> implements Func1<BaseResponse<T>, T> {
    //rx生命周期管理
    private SoftReference<RxAppCompatActivity> rxAppCompatActivity;
    /*回调*/
    private HttpOnNextListener listener;
    /*是否能取消加载框*/
    private boolean cancel;
    /*是否显示加载框*/
    private boolean showProgress;
    /*是否需要缓存处理*/
    private boolean cache = false;
    /*基础url*/
    private String baseUrl = AppNetConfig.BASE_URL;
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置*/
    private String mothed;
    /*超时时间-默认6秒*/
    private int connectionTime = 20;
    /*有网情况下的本地缓存时间默认60秒*/
    private int cookieNetWorkTime = 60;
    /*无网络的情况下本地缓存时间默认1天*/
    private int cookieNoNetWorkTime = 24 * 60 * 60;
    //    private Converter.Factory gsonConverterFactory = ResponseConverterFactory.create();
    private Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    public BaseApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        setListener(listener);

        setRxAppCompatActivity(rxAppCompatActivity);
        setShowProgress(true);
//        setCache(true);
        setCancel(true);
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable<BaseResponse<T>> getObservable(Retrofit retrofit);

    public Converter.Factory getGsonConverterFactory() {
        return gsonConverterFactory;
    }

    public void setGsonConverterFactory(Converter.Factory gsonConverterFactory) {
        this.gsonConverterFactory = gsonConverterFactory;
    }

    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }

    public String getMothed() {
        return mothed;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public void setMothed(String mothed) {
        this.mothed = mothed;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl + mothed;
    }

    public void setRxAppCompatActivity(RxAppCompatActivity rxAppCompatActivity) {
        this.rxAppCompatActivity = new SoftReference(rxAppCompatActivity);
    }


    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public HttpOnNextListener getListener() {
        return listener;
    }

    public void setListener(HttpOnNextListener listener) {
        this.listener = listener;
    }

    /*
     * 获取当前rx生命周期
     * @return
     */
    public RxAppCompatActivity getRxAppCompatActivity() {
        return rxAppCompatActivity.get();
    }

    @Override
    public T call(BaseResponse<T> resultEntry) {
        String responseCode = resultEntry.getCode();
        String responseMessage = resultEntry.getMessage();
        T data = resultEntry.getData();
        switch (responseCode) {
            case NetResponseCode.HMC_SUCCESS:
                //数据成功
                return data;
            //其他情况自己处理
            default:
                throw new RuntimeException();
        }
    }
}
