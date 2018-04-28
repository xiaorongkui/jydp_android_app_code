package com.qmkj.jydp.module.login.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:实名认证界面
 */

public class CertificationActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.certify_container_fl)
    FrameLayout certifyContainerFl;
    private FragmentTransaction ft;
    private CertifyNameFragment certifyNameFragment;
    private CertifyNameStatusFragment certifyCheckFragment;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {
        titleHeaderTv.setOnClickListener(v -> setSelect(1));
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
        setSelect(1);
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
