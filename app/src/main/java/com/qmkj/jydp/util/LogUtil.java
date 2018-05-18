package com.qmkj.jydp.util;

import com.orhanobut.logger.Logger;
import com.qmkj.jydp.BuildConfig;

/**
 * Created by Yun on 2015/10/27.
 * 自定义log，show控制是否打印log
 */
public class LogUtil {
    private static final boolean show = BuildConfig.LOG_DEBUG;

    private static final String TAG = "xiao";

    public static void v(String tag, String msg) {
        if (show) Logger.v(tag + ": %s", msg);
    }

    public static void i(String tag, String msg) {
        if (show) Logger.i(tag + ": %s", msg);
    }

    public static void d(String tag, String msg) {
        if (show) Logger.d(tag + ": %s", msg);
    }

    public static void w(String tag, String msg) {
        if (show) Logger.w(tag + ": %s", msg);
    }

    public static void e(String tag, String msg) {
        if (show) Logger.e(tag + ": %s", msg);
    }
    public static void e(String msg) {
        if (show) Logger.e(TAG + ": %s", msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }
}
