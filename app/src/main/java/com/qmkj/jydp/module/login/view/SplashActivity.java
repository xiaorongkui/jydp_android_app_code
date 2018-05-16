package com.qmkj.jydp.module.login.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * author：rongkui.xiao --2018/3/29
 * email：dovexiaoen@163.com
 * description:启动页界面
 */

public class SplashActivity extends BaseMvpActivity {

    private boolean permissionFinish = false;
    private boolean timeFinish;
    private Disposable subscribe;
    private static final int SPLASH_TOTAL_COUNTDOWN_TIME = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Window window = getWindow();
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {
        checkPermission();
        setTimeCountDown(SPLASH_TOTAL_COUNTDOWN_TIME);
    }

    private void checkPermission() {
        RxPermissionUtils.getInstance(mContext).setPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission
                        .READ_PHONE_STATE).setOnPermissionCallBack(new RxPermissionUtils
                .OnPermissionListener() {

            @Override
            public void onPermissionGranted(String name) {
                LogUtil.i("onPermissionGranted name=" + name);
            }

            @Override
            protected void onAllPermissionFinish() {
                permissionFinish = true;
                goMianActivity();
            }
        }).start();
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxPermissionUtils.getInstance(mContext).destory();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
            subscribe = null;
        }
    }

    private void setTimeCountDown(final int splashTotalCountdownTime) {
        subscribe = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers
                .mainThread()).observeOn(AndroidSchedulers.mainThread()).map(increaseTime -> splashTotalCountdownTime
                - increaseTime.intValue()).take(splashTotalCountdownTime + 1).subscribe(integer -> {
            LogUtil.i("integer=" + integer);
//                logoTime_tv.setText(getSpannableStringBuilder(integer));
            if (integer == 0) {
                timeFinish = true;
                goMianActivity();
            }
        });
    }

    private void goMianActivity() {
        if (timeFinish && permissionFinish) {
            CommonUtil.gotoActivity(mContext, LoginActivity.class);
            AppManager.getInstance().removeCurrent();
        }
    }
}
