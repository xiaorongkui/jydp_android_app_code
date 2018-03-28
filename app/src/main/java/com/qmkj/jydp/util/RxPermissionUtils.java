package com.qmkj.jydp.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * author：rongkui.xiao --2018/3/28
 * email：dovexiaoen@163.com
 * description:android 6.0运行时权限申请工具类
 */

public class RxPermissionUtils {
    private static RxPermissionUtils instance;
    private final RxPermissions rxPermissions;
    private Disposable disposable;

    private RxPermissionUtils(Activity mContext) {
        rxPermissions = new RxPermissions(mContext);
    }

    public static RxPermissionUtils getInstance(Activity mContext) {
        if (instance == null) {
            synchronized (IOUtlis.class) {
                if (instance == null) instance = new RxPermissionUtils(mContext);
            }
        }
        return instance;
    }

    /**
     * 同时请求多个权限，分别获取授权结果
     *
     * @param onPermissionListener the on permission listener
     * @param permissions          the permissions
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void getPermission(OnPermissionListener onPermissionListener, String... permissions) {
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
    }


    public abstract class OnPermissionListener {
        //权限通过
        abstract void onPermissionGranted(String name);

        //权限拒绝
        abstract void onPermissionDenied(String name);

        //权限拒绝 并且不再询问,这里实现弹窗提示
        protected void onPermissionDeniedAndNeverAsk(String name) {
        }
    }
}
