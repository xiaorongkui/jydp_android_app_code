package com.qmkj.jydp;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qmkj.jydp.manager.Constants;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Yun on 2018/3/13 0013.
 */
public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initBugly();
        initLog();
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
    public static App getInstance() {
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
}
