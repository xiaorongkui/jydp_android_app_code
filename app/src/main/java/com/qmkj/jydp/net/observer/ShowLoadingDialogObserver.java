package com.qmkj.jydp.net.observer;

import android.content.Context;

import com.qmkj.jydp.ui.dialog.LoadingDialog;


/**
 * Created by Yun on 2018/2/12 0012.
 * 网络请求观察者（请求时会弹出请求弹窗Dialog）
 */
public abstract class ShowLoadingDialogObserver<T> extends BaseShowLoadingObserver<T> {
    private LoadingDialog loadingDialog;

    protected ShowLoadingDialogObserver(Context context) {
        super(context);
    }

    /**
     * 显示loadingDialog
     */
    public void showLoading() {
        if (getContext() == null) {
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.show(getContext(), "请求中···");
        } else if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 隐藏loadingDialog
     */
    public void hideLoading() {
        if (getContext() == null) {
            return;
        }
        if (loadingDialog == null) {
            return;
        }
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
