package com.qmkj.jydp.util;

import android.app.Activity;
import android.content.Context;

import com.qmkj.jydp.R;
import com.qmkj.jydp.ui.widget.NetProgressDialog;

import java.lang.ref.WeakReference;

/**
 * author：rongkui.xiao --2018/4/2
 * email：dovexiaoen@163.com
 * description:网络请求的对话框显示控制工具
 */

public class ProgressDialogUtil {

    private static WeakReference<Context> mThreadActivityRef;  //弱引用
    private static WeakReference<NetProgressDialog> waitDialog;   //弱引用

    public static NetProgressDialog initProgressDialog(Context mContext, boolean cancel) {
        if (waitDialog != null && waitDialog.get() != null && waitDialog.get().isShowing()) {
            waitDialog.get().dismiss();
        }

        if (mContext == null || !(mContext instanceof Activity) || ((Activity) mContext).isFinishing()) {
            return null;
        }
        mThreadActivityRef = new WeakReference<>(mContext);
        NetProgressDialog loadingDialog = NetProgressDialog.createLoadingDialog(mThreadActivityRef.get(), "加载中...");
        loadingDialog.setAlertDialogSize((int) CommonUtil.getDimen(R.dimen.x100), (int) CommonUtil
                .getDimen(R.dimen.y100));

        waitDialog = new WeakReference<>(loadingDialog);
        loadingDialog.setCancelable(cancel);
        return loadingDialog;
    }

    public static void showWaitDialog() {
        if (waitDialog != null && waitDialog.get() != null && !waitDialog.get().isShowing()) {
            waitDialog.get().show();
        }
    }

    /**
     * 取消等待dialog
     */
    public static void stopWaitDialog() {
        if (waitDialog != null && waitDialog.get() != null && waitDialog.get().isShowing()) {
            waitDialog.get().dismiss();
        }
    }
}
