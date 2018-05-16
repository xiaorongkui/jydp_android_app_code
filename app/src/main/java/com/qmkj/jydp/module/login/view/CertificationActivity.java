package com.qmkj.jydp.module.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:实名认证界面
 */

public class CertificationActivity extends BaseMvpActivity<LoginPresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.certify_container_fl)
    FrameLayout certifyContainerFl;
    private FragmentTransaction ft;
    private CertifyNameFragment certifyNameFragment;
    private CertifyNameStatusFragment certifyCheckFragment;
    public static final int CERTIFY_STATUS_NO_SUBMIT = 1;//未提交
    public static final int CERTIFY_STATUS_CHECK = 2;//审核中
    public static final int CERTIFY_STATUS_NO_PASS = 3;//拒绝通过
    public int status;

    public int getStatus() {
        return status;
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.getvertify_name));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_certification_name;
    }

    @Override
    protected void initView() {
        status = getIntent().getIntExtra(Constants.INTENT_PARAMETER_1, 0);
        switch (status) {
            case CERTIFY_STATUS_NO_SUBMIT:
                setSelect(0);
                break;
            case CERTIFY_STATUS_CHECK:
                setSelect(1);
                break;
            case CERTIFY_STATUS_NO_PASS:
                setSelect(1);
                break;
        }
    }

    public void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragments();

        switch (i) {
            case 0:
                if (certifyNameFragment == null) {
                    certifyNameFragment = new CertifyNameFragment();
                    ft.add(R.id.certify_container_fl, certifyNameFragment);
                }
                ft.show(certifyNameFragment);
                break;
            case 1:
                if (certifyCheckFragment == null) {
                    certifyCheckFragment = new CertifyNameStatusFragment();
                    ft.add(R.id.certify_container_fl, certifyCheckFragment);
                }
                ft.show(certifyCheckFragment);
                break;
        }
        ft.commit();
    }

    private void hideFragments() {
        if (certifyNameFragment != null) {
            ft.hide(certifyNameFragment);
        }
        if (certifyCheckFragment != null) {
            ft.hide(certifyCheckFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (certifyNameFragment != null) {
//            certifyNameFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
