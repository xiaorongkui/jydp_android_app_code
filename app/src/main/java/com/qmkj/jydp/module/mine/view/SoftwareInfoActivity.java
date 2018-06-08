package com.qmkj.jydp.module.mine.view;

import android.Manifest;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.response.AppUpdateRes;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.manager.SystemManager;
import com.qmkj.jydp.module.login.modle.LoginContract;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;
import com.qmkj.jydp.util.SelectorFactory;

import java.io.File;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:软件信息
 */

public class SoftwareInfoActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.UpdateView {
    private static final int CHECK_APP_TAG = 1;

    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @BindView(R.id.software_info_version_num_tv)
    TextView softwareInfoVersionNumTv;

    @BindView(R.id.software_info_check_update_tv)
    TextView software_info_check_update_tv;
    private CommonDialog alterNormalDialog;
    private ProgressBar update_prg_bar_normal;
    private CommonDialog alterFocusDialog;
    private boolean permissionFinish;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        checkPermission();
        presenter.checkAppUpdate(CHECK_APP_TAG, false);
    }

    private void checkPermission() {
        RxPermissionUtils.getInstance(mContext).setPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).setOnPermissionCallBack(new RxPermissionUtils
                .OnPermissionListener() {

            @Override
            public void onPermissionGranted(String name) {
                LogUtil.i("onPermissionGranted name=" + name);
            }

            @Override
            protected void onAllPermissionFinish() {
                permissionFinish = true;
            }
        }).start();
    }


    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.software_info));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_software_info;
    }

    @Override
    protected void initView() {
        final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x11))
                .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_gray_2))
                .setStrokeWidth((int) ResourcesManager.getDimen(R.dimen.x1))
                .setDefaultBgColor(ResourcesManager.getColor(R.color.color_white_1));

        softwareInfoVersionNumTv.setBackground(shapeSelector.create());
        softwareInfoVersionNumTv.setText(SystemManager.getAppVersionName(mContext));


        software_info_check_update_tv.setOnClickListener(view -> {
            checkPermission();
            presenter.checkAppUpdate(CHECK_APP_TAG, false);
        });
    }


    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case CHECK_APP_TAG:
                AppUpdateRes appUpdateRes = (AppUpdateRes) response;
                if (appUpdateRes == null) {
                    software_info_check_update_tv.setText(R.string.no_version_update_notice);
                    return;
                }
                if (alterNormalDialog != null && alterNormalDialog.isShowing() || alterFocusDialog != null &&
                        !alterFocusDialog.isShowing()) {
                    return;
                }
                try {
                    calculateUpdate(appUpdateRes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void calculateUpdate(AppUpdateRes appUpdateRes) throws Exception {
        String forceStatus = appUpdateRes.getForceStatus();//1强制升级，2，普通升级
        String newestVersion = appUpdateRes.getNewestVersion();//新版本
        String oldVersion = SystemManager.getAppVersionName(mContext);
        LogUtil.i("newVersion=" + newestVersion + ";oldVersion=" + oldVersion);
        if (compareVersion(newestVersion, oldVersion)) {//需要升级
            switch (forceStatus) {
                case "1"://普通升级
                    normalUpdate(appUpdateRes);
                    break;
                case "2"://强制升级
                    focuseUpdate(appUpdateRes);
                    break;
            }
        } else {
            software_info_check_update_tv.setText(R.string.no_version_update_notice);
        }
    }

    /**
     * 版本号比较
     *
     * @param version1 新版本号
     * @param version2 旧版本号
     * @return true代表需要升级，false代表不需要升级
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


    /**
     * 选择升级
     *
     * @param appUpdateRes
     */
    private void normalUpdate(AppUpdateRes appUpdateRes) {
        alterNormalDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_update_app);
        alterNormalDialog.setAlertDialogWidth((int) ResourcesManager.getDimen(R.dimen.x330));
        alterNormalDialog.setCanceledOnTouchOutside(false);
        alterNormalDialog.setCancelable(false);
        alterNormalDialog.setTitle(ResourcesManager.getString(R.string.upgrade_notice) + appUpdateRes
                .getNewestVersion());
        alterNormalDialog.setMessage(appUpdateRes.getUpdateExplain());
        Button update_immediately_bt = alterNormalDialog.getView(R.id.update_immediately_bt, Button.class);
        Button update_later_bt = alterNormalDialog.getView(R.id.update_later_bt, Button.class);
        TextView update_loading_tv = alterNormalDialog.getView(R.id.update_loading_tv, TextView.class);
        LinearLayout update_bt_ll = alterNormalDialog.getView(R.id.update_bt_ll, LinearLayout.class);
        update_prg_bar_normal = alterNormalDialog.getView(R.id.update_prg_bar, ProgressBar.class);

        update_immediately_bt.setOnClickListener(v -> {
            update_prg_bar_normal.setVisibility(View.VISIBLE);
            update_bt_ll.setVisibility(View.GONE);
            update_loading_tv.setVisibility(View.VISIBLE);
            presenter.downLoadApk(mContext, appUpdateRes.getAppUrl());
        });
        update_later_bt.setOnClickListener(v -> {
            alterNormalDialog.dismiss();
        });

        alterNormalDialog.show();
    }


    private void focuseUpdate(AppUpdateRes appUpdateRes) {
        alterFocusDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_update_app);
        alterFocusDialog.setAlertDialogWidth((int) ResourcesManager.getDimen(R.dimen.x330));
        alterFocusDialog.setCanceledOnTouchOutside(false);
        alterFocusDialog.setCancelable(false);
        alterFocusDialog.setTitle(ResourcesManager.getString(R.string.upgrade_notice) + appUpdateRes.getNewestVersion
                ());
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
        if (alterNormalDialog != null && alterNormalDialog.isShowing()) {
            alterNormalDialog.dismiss();
        }
        if (alterFocusDialog != null && alterFocusDialog.isShowing()) {
            alterFocusDialog.dismiss();
        }
    }

    @Override
    public void downLoadFailed() {
        toast("apk下载失败");
        if (update_prg_bar_normal != null) {
            update_prg_bar_normal.setVisibility(View.GONE);
        }
        if (alterNormalDialog != null && alterNormalDialog.isShowing()) {
            alterNormalDialog.dismiss();
        }
        if (alterFocusDialog != null && alterFocusDialog.isShowing()) {
            alterFocusDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxPermissionUtils.destory();
    }
}
