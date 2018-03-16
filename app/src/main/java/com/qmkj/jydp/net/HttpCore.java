package com.qmkj.jydp.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.net.api.HomeService;
import com.qmkj.jydp.net.api.BaseApi;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求配置
 */
public class HttpCore {
    private static volatile HttpCore httpCore;

    private static Retrofit retrofit;

    private void initOkHttpClient() {
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {

            try {
                String s = URLDecoder.decode(message, "utf-8");
                LogUtil.i("OkHttp====OK:" + s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                LogUtil.i("OkHttp====UnsupportedEncodingException:" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置Http缓存
        Cache cache = new Cache(new File(CommonUtil.getCacheDir(), "httpcache"), 1024 * 1024 * 10);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new SessionInterceptor())
                .addNetworkInterceptor(new SleepInterceptor())
                .retryOnConnectionFailure(true)
                .readTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .writeTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .connectTimeout(Constants.connectionTime, TimeUnit.SECONDS).build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(AppNetConfig.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static HttpCore getInstance() {
        if (httpCore == null) {
            synchronized (HttpCore.class) {
                if (httpCore == null) {
                    httpCore = new HttpCore();
                }
            }
        }
        return httpCore;
    }

    private HttpCore() {
        initOkHttpClient();
    }

    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    @SuppressWarnings("unchecked")
    public void sendHttpRequest(BaseApi basePar) {
        ProgressObserver progressObserver = new ProgressObserver(basePar);

        Observable observable = basePar.getObservable(retrofit)
                .compose(basePar.getRxAppCompatActivity().bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(basePar);
        /*数据回调*/
        observable.subscribe(progressObserver);
    }

    /**
     * Session拦截器
     */
    private class SessionInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            //todo session待处理
            //String sessionId = JYDPExchangeApp.getInstance().getSessionId();
            String sessionId = "todo";
            Request request = oldRequest.newBuilder().addHeader("sessionId", sessionId).build();
            return chain.proceed(request);
        }
    }

    /**
     * 平滑请求时间拦截器
     */
    private class SleepInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long start = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            if (System.currentTimeMillis() - start < 1000) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }


}
