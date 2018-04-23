package com.qmkj.jydp.module.login.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
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
    @BindView(R.id.title_right_tv)
    TextView title_right_tv;
    @BindView(R.id.certify_container_fl)
    FrameLayout certifyContainerFl;
    private FragmentTransaction ft;
    private CertifyNameFragment certifyNameFragment;
    private CertifyCheckFragment certifyCheckFragment;

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
        title_right_tv.setText(CommonUtil.getString(R.string.modify));
        title_right_tv.setTextColor(CommonUtil.getColor(R.color.colorGreen_3));
        title_right_tv.setOnClickListener(v -> setSelect(0));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_certification_name;
    }

    @Override
    protected void initView() {
        setSelect(0);
    }

    private void setSelect(int i) {
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
                title_right_tv.setVisibility(View.INVISIBLE);
                break;
            case 1:
                if (certifyCheckFragment == null) {
                    certifyCheckFragment = new CertifyCheckFragment();
                    ft.add(R.id.certify_container_fl, certifyCheckFragment);
                }
                ft.show(certifyCheckFragment);
                title_right_tv.setVisibility(View.VISIBLE);
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
}
