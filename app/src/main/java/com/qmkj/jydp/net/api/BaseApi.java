package com.qmkj.jydp.net.api;

import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.net.HttpOnNextListener;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求数据统一封装类
 */
public abstract class BaseApi<T> implements Function<BaseResponse<T>, T> {
    //rx生命周期管理
    private SoftReference<RxAppCompatActivity> rxAppCompatActivity;
    private SoftReference<RxFragment> rxAppCompatFragment;
    /*回调*/
    private HttpOnNextListener listener;
    /*是否能取消加载框*/
    private boolean cancel;
    /*是否显示加载框*/
    private boolean showProgressDialog;
    /*是否需要缓存处理*/
    private boolean cache = false;
    /*基础url*/
    private String baseUrl = AppNetConfig.BASE_URL;
    private Converter.Factory gsonConverterFactory;

    public BaseApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        setListener(listener);

        setRxAppCompatActivity(rxAppCompatActivity);
        setshowProgressDialog(true);
        setCancel(true);
        setGsonConverterFactory(GsonConverterFactory.create());
    }

    public BaseApi(HttpOnNextListener listener, RxFragment rxFragment) {
        setListener(listener);

        setRxAppCompatFragment(rxFragment);
        setshowProgressDialog(true);
        setCancel(true);
        setGsonConverterFactory(GsonConverterFactory.create());
    }

    public void setRxAppCompatFragment(RxFragment rxAppCompatFragment) {
        this.rxAppCompatFragment = new SoftReference(rxAppCompatFragment);
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
    public abstract Observable getObservable(Retrofit retrofit);

    public Converter.Factory getGsonConverterFactory() {
        return gsonConverterFactory;
    }

    public void setGsonConverterFactory(Converter.Factory gsonConverterFactory) {
        this.gsonConverterFactory = gsonConverterFactory;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public void setRxAppCompatActivity(RxAppCompatActivity rxAppCompatActivity) {
        this.rxAppCompatActivity = new SoftReference(rxAppCompatActivity);
    }


    public boolean isshowProgressDialog() {
        return showProgressDialog;
    }

    public void setshowProgressDialog(boolean showProgressDialog) {
        this.showProgressDialog = showProgressDialog;
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
    public T apply(BaseResponse<T> resultEntry) throws Exception {

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
