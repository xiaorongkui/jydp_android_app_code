package com.qmkj.jydp.util;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;


/**
 * Toast统一管理类
 *
 * @author Yun
 */
public class ToastUtil {
    private static Toast toast;

    public static void showError(Throwable t) {
        if (t instanceof TimeoutException || t instanceof SocketTimeoutException) {
            ToastUtil.showShort("网络超时，请重试");
        } else if (t instanceof ConnectException || t instanceof UnknownHostException) {
            ToastUtil.showShort("网络连接错误，请检查网络");
        } else {
            ToastUtil.showShort("网络异常，请重试");
        }
    }

    public static void showShort(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(JYDPExchangeApp.getInstance(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(message);
        }
        toast.show();
    }

    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }

    /**
     * 在任何地方都可以弹的Toast,保证运行在ui线程中
     */
    public static void toast(final Context context, final CharSequence s) {
        new Thread(() -> {
            Looper.prepare();
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }).start();
    }

    public static void toast(final Activity activity, final CharSequence s) {
        activity.runOnUiThread(() -> Toast.makeText(activity, s, Toast.LENGTH_SHORT).show());
    }

    /**
     * 自定义toast
     */
    public static void showDefinedToast(Context context, CharSequence s) {
        View view = View.inflate(context, R.layout.toast_define, null);
        TextView text = view.findViewById(R.id.toast_tv);
        text.setText(s);
        Toast mToast = new Toast(context);
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }

    /**
     * 自定义toast时间
     */
    public static void showToastTime(final Toast toast, final int duration) {
        final long SHORT = 2000;
        final long LONG = 3500;
        final long ONE_SECOND = 1000;
        final long d = duration <= SHORT ? SHORT : duration > LONG ? duration : LONG;
        new CountDownTimer(Math.max(d, duration), ONE_SECOND) {

            @Override
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            @Override
            public void onFinish() {
                toast.cancel();
            }
        }.start();
    }
}
