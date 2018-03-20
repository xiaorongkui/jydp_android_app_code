package com.qmkj.jydp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.tencent.bugly.crashreport.CrashReport;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yun on 2018/3/13 0013.
 */
public class JYDPExchangeApp extends Application {
    private static JYDPExchangeApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initBugly();
        initLog();
        registerActivityLifecycle();
    }

    private void registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }

    /**
     * 初始化Bugly(APP异常捕获)
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID, BuildConfig.DEBUG);
    }

    /**
     * 初始化Log
     */
    private void initLog() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 获取Application Context
     */
    public static JYDPExchangeApp getInstance() {
        return context;
    }

    /**
     * 设置app字体不随系统改变
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 退出应用
     */
    public static void exit() {
        AppManager.getInstance().removeCurrent();
        AppManager.getInstance().clear();
        System.exit(0);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Observable.create(e -> Glide.get(context).clearDiskCache()).subscribeOn(Schedulers.io()).subscribeOn
                (AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Glide.get(context).clearMemory();
            }
        });
    }
}
