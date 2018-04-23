package com.qmkj.jydp.di.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qmkj.jydp.BuildConfig;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.net.api.BaseNetFunction;
import com.qmkj.jydp.net.api.ExchangeService;
import com.qmkj.jydp.net.api.HomeService;
import com.qmkj.jydp.net.api.LoginService;
import com.qmkj.jydp.net.api.MineService;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class HttpModule {

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    BaseNetFunction provideBaseNetFunction() {
        return new BaseNetFunction();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.LOG_DEBUG) {
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
            builder.addInterceptor(loggingInterceptor);
        }
        //设置Http缓存
        Cache cache = new Cache(new File(CommonUtil.getCacheDir(), "httpCache"), 1024 * 1024 * 10);
        return builder.addInterceptor(new SessionInterceptor())
                .addNetworkInterceptor(new SleepInterceptor())
                .cache(cache)
                .connectTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .readTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .writeTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, AppNetConfig.BASE_URL);
    }

    @Provides
    @Singleton
    LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @Provides
    @Singleton
    HomeService provideHomeService(Retrofit retrofit) {
        return retrofit.create(HomeService.class);
    }

    @Provides
    @Singleton
    ExchangeService provideExchangeService(Retrofit retrofit) {
        return retrofit.create(ExchangeService.class);
    }

    @Provides
    @Singleton
    MineService provideMineService(Retrofit retrofit) {
        return retrofit.create(MineService.class);
    }


    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return builder.baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
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
