package com.qmkj.jydp.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;

/**
 * author：rongkui.xiao --2018/3/28
 * email：dovexiaoen@163.com
 * description:android 6.0运行时权限申请工具类
 */

public class RxPermissionUtils {
    private static RxPermissionUtils instance;
    private RxPermissions rxPermissions;
    private String[] permissions;
    private Disposable disposable;
    private OnPermissionListener onPermissionListener;

    private RxPermissionUtils(Activity mContext) {
        rxPermissions = new RxPermissions(mContext);
    }

    public static RxPermissionUtils getInstance(Activity context) {
        if (instance == null) {
            synchronized (RxPermissionUtils.class) {
                if (instance == null) {
                    instance = new RxPermissionUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * Sets on permission call back.
     *
     * @param onPermissionListener the on permission listener
     */
    public RxPermissionUtils setOnPermissionCallBack(OnPermissionListener onPermissionListener) {
        this.onPermissionListener = onPermissionListener;
        return this;
    }

    public RxPermissionUtils setPermission(String... permissions) {
        this.permissions = permissions;
        return this;
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void start() {
        disposable = rxPermissions.requestEach(permissions).subscribe(permission -> {
            if (permission.granted) {
                if (onPermissionListener != null) {
                    onPermissionListener.onPermissionGranted(permission.name);
                }
            } else if (permission.shouldShowRequestPermissionRationale) {
                if (onPermissionListener != null) {
                    onPermissionListener.onPermissionDenied(permission.name);
                }
            } else {
                if (onPermissionListener != null) {
                    onPermissionListener.onPermissionDeniedAndNeverAsk(permission.name);
                }
            }
        }, throwable -> LogUtil.i("权限申请失败" + permissions.toString()), () -> LogUtil.i("权限申请完成"));
    }

    public void destory() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        instance = null;
    }


    public abstract static class OnPermissionListener {

        //权限通过
        public abstract void onPermissionGranted(String name);

        //权限拒绝
        public abstract void onPermissionDenied(String name);

        //权限拒绝 并且不再询问,这里实现弹窗提示
        protected void onPermissionDeniedAndNeverAsk(String name) {
            LogUtil.i("onPermissionDeniedAndNeverAsk name=" + name);
        }
    }
}
