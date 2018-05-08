package com.qmkj.jydp.util;

import android.app.Activity;
import android.content.Context;

import com.qmkj.jydp.R;
import com.qmkj.jydp.ui.widget.LoadingDialog;

import java.lang.ref.WeakReference;

/**
 * author：rongkui.xiao --2018/4/2
 * email：dovexiaoen@163.com
 * description:网络请求的对话框显示控制工具
 */

public class ProgressDialogUtil {

    private static WeakReference<Context> contextWeakReference;  //弱引用
    private static WeakReference<LoadingDialog> waitDialog;   //弱引用

    public static LoadingDialog initProgressDialog(Context mContext, boolean cancel) {
        if (waitDialog != null && waitDialog.get() != null && waitDialog.get().isShowing()) {
            waitDialog.get().dismiss();
        }

        if (mContext == null || !(mContext instanceof Activity) || ((Activity) mContext).isFinishing()) {
            return null;
        }
        contextWeakReference = new WeakReference<>(mContext);

        LoadingDialog loadingDialog = new LoadingDialog(contextWeakReference.get(), R.style.loading_dialog);
        loadingDialog.setAlertDialogSize((int) CommonUtil.getDimen(R.dimen.x100), (int) CommonUtil
                .getDimen(R.dimen.y100));
        loadingDialog.setMessage(mContext.getString(R.string.loading));
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
