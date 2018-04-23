package com.qmkj.jydp.module.login.view;

import android.Manifest;
import android.view.View;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;

/**
 * author：rongkui.xiao --2018/3/29
 * email：dovexiaoen@163.com
 * description:启动页界面
 */

public class SplashActivity extends BaseMvpActivity {
    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {
        findViewById(R.id.splash_tv).setOnClickListener(v -> checkPermission());
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(mContext, LoginActivity.class);
            }
        });
//        checkPermission();
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
            public void onPermissionDenied(String name) {
                LogUtil.i("onPermissionDenied name=" + name);
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
    }
}
