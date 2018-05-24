package com.qmkj.jydp.module.login.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.response.AppUpdateRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;
import com.qmkj.jydp.util.StringUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * author：rongkui.xiao --2018/3/29
 * email：dovexiaoen@163.com
 * description:启动页界面
 */
public class SplashActivity extends BaseMvpActivity<LoginPresenter> {

    private static final int CHECK_APP_TAG = 1;
    private boolean permissionFinish = false;
    private boolean timeFinish;
    private Disposable subscribe;
    private static final int SPLASH_TOTAL_COUNTDOWN_TIME = 3;
    private boolean isFocuseUpdate = false;
    private CommonDialog alterNormalDialog;
    private boolean isNormalUpdate;
    private CommonDialog alterFocusDialog;

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
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        checkPermission();
        setTimeCountDown(SPLASH_TOTAL_COUNTDOWN_TIME);
        checkAppUpdate(false);
    }

    private void checkAppUpdate(boolean b) {
        presenter.checkAppUpdate(CHECK_APP_TAG, b);
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
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case CHECK_APP_TAG:
                AppUpdateRes appUpdateRes = (AppUpdateRes) response;
                if (appUpdateRes == null) {
                    isFocuseUpdate = false;
                    goMianActivity();
                    return;
                }
                calculateUpdate(appUpdateRes);
                break;
        }
    }

    private void calculateUpdate(AppUpdateRes appUpdateRes) {
        String forceStatus = appUpdateRes.getForceStatus();//1强制升级，2，普通升级
        String newestVersion = appUpdateRes.getNewestVersion();//新版本
        String oldVersion = CommonUtil.getAppVersionName(mContext);
        LogUtil.i("newVersion=" + newestVersion + ";oldVersion=" + oldVersion);
        if (!TextUtils.isEmpty(newestVersion) && (newestVersion.startsWith("V") || newestVersion
                .startsWith("v"))) {
            newestVersion = newestVersion.substring(1, newestVersion.length());
        }
        if (!TextUtils.isEmpty(oldVersion) && (oldVersion.startsWith("V") || oldVersion
                .startsWith("v"))) {
            oldVersion = oldVersion.substring(1, oldVersion.length());
        }
        if (compareVersion(newestVersion, oldVersion)) {//需要升级
            switch (forceStatus) {
                case "1":
                    normalUpdate(appUpdateRes.getAppUrl(), appUpdateRes.getUpdateExplain());
                    break;
                case "2"://强制升级
                    focuseUpdate(appUpdateRes.getAppUrl(), appUpdateRes.getUpdateExplain());
                    break;
            }
        }
    }

    /**
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return true代表version1大于version2, 需要升级
     */
    public boolean compareVersion(String version1, String version2) {
        return TextUtils.isEmpty(version1) || !version1.equalsIgnoreCase(version2);
    }

    private void normalUpdate(final String url, String updateDesc) {
        isNormalUpdate = true;
        alterNormalDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_update_app);
        alterNormalDialog.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x290));
        alterNormalDialog.setCanceledOnTouchOutside(false);
        alterNormalDialog.setOneOrTwoBtn(false);
//        alterNormalDialog.setTitle(CommonUtil.getString(R.string.upgrade_notice));
        alterNormalDialog.setMessage(updateDesc);
        alterNormalDialog.setTwoCancelBtn("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterNormalDialog.dismiss();
            }
        });
        alterNormalDialog.setTwoConfirmBtn("升级", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alterNormalDialog.dismiss();
            }
        });
        alterNormalDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                isNormalUpdate = false;
                goMianActivity();
            }
        });
        alterNormalDialog.show();
    }

    private void focuseUpdate(final String url, String updateDesc) {
        isFocuseUpdate = true;
        alterFocusDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_update_app);
        alterFocusDialog.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x290));
        alterFocusDialog.setOneOrTwoBtn(true);
//        focuse_update_prg_bar = alterFocusDialog.getView(R.id.focuse_update_prg_bar, ProgressBar.class);
//        only_confirm_btn = alterFocusDialog.getView(R.id.only_confirm_btn, TextView.class);
        alterFocusDialog.setCanceledOnTouchOutside(false);
        alterFocusDialog.setCancelable(false);
//        alterFocusDialog.setTitle(CommonUtil.getString(R.string.upgrade_notice));
        alterFocusDialog.setMessage(updateDesc);

        alterFocusDialog.setOneConfirmBtn("升级", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        alterFocusDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxPermissionUtils.destory();
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

    /**
     * 跳转主页面
     * 根据token判断是否需要用户登录
     */
    private void goMianActivity() {
        if (timeFinish && permissionFinish && !isFocuseUpdate) {
            String token = CommonUtil.getToken();
//            if (StringUtil.isNull(token)) {
//                CommonUtil.gotoActivity(mContext, LoginActivity.class);
//            } else {
//                CommonUtil.gotoActivity(mContext, MainActivity.class);
//            }
            CommonUtil.gotoActivity(mContext, LoginActivity.class);
            AppManager.getInstance().removeCurrent();
        }
    }
}
