package com.qmkj.jydp.module.login.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/4/25
 * email：dovexiaoen@163.com
 * description:
 */

public class ForgetLoginPwdActivity extends BaseMvpActivity<LoginPresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.login_forget_pwd_account_eiv)
    EditVItemView loginForgetPwdAccountEiv;
    @BindView(R.id.forget_pwd_phone_erea_tv)
    TextView forgetPwdPhoneEreaTv;
    @BindView(R.id.forget_pwd_phone_erea_iv)
    ImageView forgetPwdPhoneEreaIv;
    @BindView(R.id.forget_pwd_phone_erea_et)
    EditText forgetPwdPhoneEreaEt;
    @BindView(R.id.login_forget_pwd_vertification_code_eiv)
    EditVItemView loginForgetPwdVertificationCodeEiv;
    @BindView(R.id.login_forget_pwd_newpwd_eiv)
    EditVItemView loginForgetPwdNewpwdEiv;
    @BindView(R.id.login_forget_pwd_newpwd_again_eiv)
    EditVItemView loginForgetPwdNewpwdAgainEiv;
    @BindView(R.id.forget_pwd_commit_bt)
    Button forgetPwdCommitBt;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.find_back_login_pwd));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_forget_pwd;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
