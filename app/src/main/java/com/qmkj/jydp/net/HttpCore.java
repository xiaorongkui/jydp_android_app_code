package com.qmkj.jydp.net;


import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.util.LogUtil;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求配置
 */
public class HttpCore {
    private static final String TAG = HttpCore.class.getSimpleName();
    private static HttpCore httpCore;
    private static ApiService apiService;

    private HttpCore() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(new LogInterceptor())
                .addNetworkInterceptor(new SessionInterceptor())
                .addNetworkInterceptor(new SleepInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 日志打印拦截器
     */
    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtil.i(TAG, "request:" + request.toString());
            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            LogUtil.i(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtil.i(TAG, "response body:" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
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

    public ApiService getApiService() {
        return apiService;
    }
}
