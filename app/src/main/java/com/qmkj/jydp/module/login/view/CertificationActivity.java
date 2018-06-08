package com.qmkj.jydp.module.login.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.CertificetionInfoReq;
import com.qmkj.jydp.bean.response.CertificetionInfoRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.DataManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:实名认证界面
 */

public class CertificationActivity extends BaseMvpActivity<LoginPresenter> {
    private static final int CERTIFY_STATUS_TAG = 1;
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
    private String account;
    private CertificetionInfoRes infoRes;

    public CertificetionInfoRes getInfoRes() {
        return infoRes;
    }

    public String getAccount() {
        return account;
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {

    }

    public void getCertifyStatus() {
        CertificetionInfoReq infoReq = new CertificetionInfoReq();
        infoReq.setUserAccount(account);
        presenter.getCertifyStatus(infoReq, CERTIFY_STATUS_TAG, true);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.getvertify_name));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_certification_name;
    }

    @Override
    protected void initView() {
        status = getIntent().getIntExtra(Constants.INTENT_PARAMETER_1, 0);
        account = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        switch (status) {
            case CERTIFY_STATUS_NO_SUBMIT:
                setSelect(0);
                break;
            case CERTIFY_STATUS_CHECK:
            case CERTIFY_STATUS_NO_PASS:
                getCertifyStatus();
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
    }

    @Override
    public void onSuccess(Object response, int tag) {
        switch (tag) {
            case CERTIFY_STATUS_TAG:
                try {
                    infoRes = (CertificetionInfoRes) response;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (infoRes == null) {
                    toast("认证信息获取为空");
                    return;
                }
                setSelect(1);
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        showNetErrorView(certifyContainerFl, true);
    }

    @Override
    protected void tryData(int id) {
        super.tryData(id);
        switch (status) {
            case CERTIFY_STATUS_CHECK:
            case CERTIFY_STATUS_NO_PASS:
                getCertifyStatus();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataManager.clearLoginData();
    }
}
