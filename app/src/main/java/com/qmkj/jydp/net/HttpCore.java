package com.qmkj.jydp.net;


import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.util.LogUtil;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
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
import rx.Observable;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求配置
 */
public class HttpCore {
    public static final int connectionTime = 20;//连接时间
    private static final String TAG = HttpCore.class.getSimpleName();
    private static volatile HttpCore httpCore;
    private static OkHttpClient.Builder mOkHttpClientBuilder;

    static {
        initOkHttpClient();
    }

    private static ApiService apiService;

    // 初始化执行一次
    private static void initOkHttpClient() {
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtil.i
                ("OkHttp====Message:" + message));
        //设置Http缓存
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        //设置Http缓存
        Cache cache = new Cache(new File("httplog", "HttpCache"), 1024 * 1024 * 10);
        mOkHttpClientBuilder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .readTimeout(connectionTime, TimeUnit.SECONDS)
                .writeTimeout(connectionTime, TimeUnit.SECONDS)
                .connectTimeout(connectionTime, TimeUnit.SECONDS);

        apiService = createApi(ApiService.class);
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
    }

    private static <T> T createApi(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppNetConfig.BASE_URL)
                .client(mOkHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void sendHttpRequest(BaseApi basePar) {

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(basePar
                .getGsonConverterFactory()).addCallAdapterFactory(RxJava2CallAdapterFactory.create
                ()).client(mOkHttpClientBuilder.build()).baseUrl(basePar.getBaseUrl()).build();
        /*rx处理*/
        ProgressSubscriber subscriber = new ProgressSubscriber(basePar);

        Observable observable = basePar.getObservable(retrofit)
                .compose(basePar.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.DESTROY));
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()).map(basePar);
        /*数据回调*/
        observable.subscribe(subscriber);
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

    public ApiService getApiService() {
        return apiService;
    }


}
