package com.qmkj.jydp.module.login.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.response.AppUpdateRes;
import com.qmkj.jydp.module.login.modle.LoginContract;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;
import com.qmkj.jydp.util.StringUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * author：rongkui.xiao --2018/3/29
 * email：dovexiaoen@163.com
 * description:启动页界面
 */
public class SplashActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.UpdateView {

    private static final int CHECK_APP_TAG = 1;
    private boolean permissionFinish = false;
    private boolean timeFinish;
    private Disposable subscribe;
    private static final int SPLASH_TOTAL_COUNTDOWN_TIME = 3;
    private boolean isUpdate = false;
    private CommonDialog alterNormalDialog;
    private CommonDialog alterFocusDialog;
    private ProgressBar update_prg_bar_normal;

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
                    isUpdate = false;
                    goMianActivity();
                    return;
                }
//                goMianActivity();
                try {
                    calculateUpdate(appUpdateRes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        isUpdate = false;
    }

    private void calculateUpdate(AppUpdateRes appUpdateRes) throws Exception {
        String forceStatus = appUpdateRes.getForceStatus();//1强制升级，2，普通升级
        String newestVersion = appUpdateRes.getNewestVersion();//新版本
        String oldVersion = CommonUtil.getAppVersionName(mContext);
        LogUtil.i("newVersion=" + newestVersion + ";oldVersion=" + oldVersion);
        if (compareVersion(newestVersion, oldVersion)) {//需要升级
            switch (forceStatus) {
                case "1"://普通升级
                    normalUpdate(appUpdateRes);
                    break;
                case "2"://强制升级
                    focuseUpdate(appUpdateRes);
                    break;
                default:
                    normalUpdate(appUpdateRes);
                    break;
            }
        } else {//不需要升级
            isUpdate = false;
            goMianActivity();
        }
    }

    /**
     * 版本号比较
     *
     * @param version1 新版本号
     * @param version2 旧版本号
     * @return true代表version1大于version2，需要升级;false代表不需要升级
     */
    public boolean compareVersion(String version1, String version2) throws Exception {
        if (version1.equalsIgnoreCase(version2)) {
            return false;
        }
        if (!TextUtils.isEmpty(version1) && (version1.startsWith("V") || version1
                .startsWith("v"))) {
            version1 = version1.substring(1, version1.length());
        }
        if (!TextUtils.isEmpty(version2) && (version2.startsWith("V") || version2
                .startsWith("v"))) {
            version2 = version2.substring(1, version2.length());
        }

        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer
                .parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return true;
                }
            }
            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return false;
                }
            }
            return false;
        } else {
            return diff > 0;
        }
    }

    private void normalUpdate(AppUpdateRes appUpdateRes) {
        isUpdate = true;
        alterNormalDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_update_app);
        alterNormalDialog.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        alterNormalDialog.setCanceledOnTouchOutside(false);
        alterNormalDialog.setCancelable(false);
        alterNormalDialog.setTitle(CommonUtil.getString(R.string.upgrade_notice) + appUpdateRes.getNewestVersion());
        alterNormalDialog.setMessage(appUpdateRes.getUpdateExplain());
        Button update_immediately_bt = alterNormalDialog.getView(R.id.update_immediately_bt, Button.class);
        Button update_later_bt = alterNormalDialog.getView(R.id.update_later_bt, Button.class);
        TextView update_loading_tv = alterNormalDialog.getView(R.id.update_loading_tv, TextView.class);
        LinearLayout update_bt_ll = alterNormalDialog.getView(R.id.update_bt_ll, LinearLayout.class);
        update_prg_bar_normal = alterNormalDialog.getView(R.id.update_prg_bar, ProgressBar.class);

        update_immediately_bt.setOnClickListener(v -> {
            if (permissionFinish) {
                update_prg_bar_normal.setVisibility(View.VISIBLE);
                update_bt_ll.setVisibility(View.GONE);
                update_loading_tv.setVisibility(View.VISIBLE);
                presenter.downLoadApk(mContext, appUpdateRes.getAppUrl());
            } else {
                checkPermission();
            }

        });
        update_later_bt.setOnClickListener(v -> {
            alterNormalDialog.dismiss();
            isUpdate = false;
            goMianActivity();
        });

        alterNormalDialog.show();
    }

    private void focuseUpdate(AppUpdateRes appUpdateRes) {
        isUpdate = true;
        alterFocusDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_update_app);
        alterFocusDialog.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        alterFocusDialog.setCanceledOnTouchOutside(false);
        alterFocusDialog.setCancelable(false);
        alterFocusDialog.setTitle(CommonUtil.getString(R.string.upgrade_notice) + appUpdateRes.getNewestVersion());
        alterFocusDialog.setMessage(appUpdateRes.getUpdateExplain());
        Button update_immediately_bt = alterNormalDialog.getView(R.id.update_immediately_bt, Button.class);
        Button update_later_bt = alterNormalDialog.getView(R.id.update_later_bt, Button.class);
        TextView update_loading_tv = alterNormalDialog.getView(R.id.update_loading_tv, TextView.class);
        LinearLayout update_bt_ll = alterNormalDialog.getView(R.id.update_bt_ll, LinearLayout.class);
        update_prg_bar_normal = alterNormalDialog.getView(R.id.update_prg_bar, ProgressBar.class);
        update_later_bt.setVisibility(View.GONE);

        update_immediately_bt.setOnClickListener(v -> {
            update_prg_bar_normal.setVisibility(View.VISIBLE);
            update_bt_ll.setVisibility(View.GONE);
            update_loading_tv.setVisibility(View.VISIBLE);
            presenter.downLoadApk(mContext, appUpdateRes.getAppUrl());
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
        if (alterNormalDialog != null && alterNormalDialog.isShowing()) alterNormalDialog.dismiss();
        if (alterFocusDialog != null && alterFocusDialog.isShowing()) alterFocusDialog.dismiss();
    }

    private void setTimeCountDown(final int splashTotalCountdownTime) {
        subscribe = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers
                .mainThread()).observeOn(AndroidSchedulers.mainThread()).map(increaseTime -> splashTotalCountdownTime
                - increaseTime.intValue()).take(splashTotalCountdownTime + 1).subscribe(integer -> {
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
        if (timeFinish && permissionFinish && !isUpdate) {
            String token = CommonUtil.getToken();
            if (StringUtil.isNull(token)) {
                CommonUtil.gotoActivity(mContext, LoginActivity.class);
            } else {
                CommonUtil.gotoActivity(mContext, MainActivity.class);
            }
        }
    }

    @Override
    public void update(int progrss) {
        LogUtil.i("下载进度=progrss" + progrss);
        if (update_prg_bar_normal != null) {
            update_prg_bar_normal.setProgress(progrss);
        }
    }

    @Override
    public void downLoadFinish(File file) {
        presenter.installApk(mContext, file);
        if (update_prg_bar_normal != null) {
            update_prg_bar_normal.setVisibility(View.GONE);
        }
    }

    @Override
    public void downLoadFailed() {
        toast("apk下载失败");
        if (update_prg_bar_normal != null) {
            update_prg_bar_normal.setVisibility(View.GONE);
        }
    }
}
