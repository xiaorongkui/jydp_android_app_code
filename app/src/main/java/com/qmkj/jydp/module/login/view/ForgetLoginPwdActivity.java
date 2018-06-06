package com.qmkj.jydp.module.login.view;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.DoubleString;
import com.qmkj.jydp.bean.request.ForgetPwdReq;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/4/25
 * email：dovexiaoen@163.com
 * description:
 */

public class ForgetLoginPwdActivity extends BaseMvpActivity<LoginPresenter> {
    private static final int FORGET_CODE_TAG = 1;
    private static final int FORGET_PWD_TAG = 2;
    private static final int splashTotalCountdownTime = 60;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.forget_pwd_phone_erea_layout)
    RelativeLayout forget_pwd_phone_erea_layout;
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
    private Disposable disposable;
    private TextView codeTimeDownTv;
    private boolean isCertifyCode;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
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
        forgetPwdCommitBt.setOnClickListener(this);
        forget_pwd_phone_erea_layout.setOnClickListener(this);
        codeTimeDownTv = loginForgetPwdVertificationCodeEiv.getView(R.id.edit_right_tv);
        codeTimeDownTv.setOnClickListener(this);
        codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_pwd_commit_bt:
                submitForgetPwd();
                break;
            case R.id.edit_right_tv:
                getVerificationCode();
                break;
            case R.id.forget_pwd_phone_erea_layout:
                CommonUtil.startActivityForResult(mContext, AreaCodeSecActivity.class, 1);
                break;
        }
    }

    private void submitForgetPwd() {
        String accunt = loginForgetPwdAccountEiv.getEditTextString();
        String phone = forgetPwdPhoneEreaEt.getText().toString().trim();
        String area = forgetPwdPhoneEreaTv.getText().toString().trim();
        String code = loginForgetPwdVertificationCodeEiv.getEditTextString();
        String newPwd = loginForgetPwdNewpwdEiv.getEditTextString();
        String newPwdAgain = loginForgetPwdNewpwdAgainEiv.getEditTextString();
        if (TextUtils.isEmpty(accunt)) {
            toast("请输入账号");
            return;
        }
        if (accunt.length() < 6) {
            toast("账号不能小于六位");
            return;
        }

        if (TextUtils.isEmpty(area)) {
            toast("请选择区号");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            toast("请先输入手机号");
            return;
        }


        if (area.startsWith("+86")) {
            if (phone.length() != 11) {
                toast("手机号必须为11位");
                return;
            }

        } else {
            if (phone.length() < 6) {
                toast("手机号必须大于6位");
                return;
            }
        }

        if (!isCertifyCode) {
            toast("请先获取验证码");
            return;
        }

        if (TextUtils.isEmpty(code)) {
            toast("请输入验证码");
            return;
        }
        if (code.length() != 6) {
            toast("验证码必须为六位");
            return;
        }

        if (TextUtils.isEmpty(newPwd)) {
            toast("请输入新密码");
            return;
        }
        if (newPwd.length() < 6) {
            toast("新密码不能小于六位");
            return;
        }

        if (TextUtils.isEmpty(newPwdAgain)) {
            toast("请再次输入新密码");
            return;
        }
        if (newPwdAgain.length() < 6) {
            toast("再次输入新密码不能小于六位");
            return;
        }

        if (!newPwd.equals(newPwdAgain)) {
            toast("两次登录密码输入不同");
            return;
        }
        ForgetPwdReq forgetPwdReq = new ForgetPwdReq();
        forgetPwdReq.setPassword(newPwd);
        forgetPwdReq.setPhoneAreaCode(area);
        forgetPwdReq.setPhoneNumber(phone);
        forgetPwdReq.setUserAccount(accunt);
        forgetPwdReq.setValidateCode(code);
        presenter.submitForgetPwd(forgetPwdReq, FORGET_PWD_TAG, true);
    }

    private void getVerificationCode() {
        String phone = forgetPwdPhoneEreaEt.getText().toString().trim();
        String areaCode = forgetPwdPhoneEreaTv.getText().toString().trim();
        if (TextUtils.isEmpty(areaCode)) {
            toast("请选择区号");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            toast("请先输入手机号");
            return;
        }


        if (areaCode.startsWith("+86")) {
            if (phone.length() != 11) {
                toast("手机号必须为11位");
                return;
            }

        } else {
            if (phone.length() < 6) {
                toast("手机号必须大于6位");
                return;
            }
        }
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(areaCode + phone);
        presenter.getRegisterCode(phoneCodeReq, FORGET_CODE_TAG);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case FORGET_CODE_TAG:
                toast("验证码获取成功");
                isCertifyCode = true;
                codeTimeDown();
                break;
            case FORGET_PWD_TAG:
                toast("密码修改成功");
                CommonUtil.gotoActivity(mContext, LoginActivity.class);
                AppManager.getInstance().removeCurrent();
                break;
        }
    }

    private void codeTimeDown() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).map(aLong -> splashTotalCountdownTime - aLong.intValue()).take
                (splashTotalCountdownTime + 1).subscribe(integer -> {
            if (integer == 0) {
                codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
                codeTimeDownTv.setEnabled(true);
                codeTimeDownTv.setAlpha(1);
            } else {
                codeTimeDownTv.setText(String.format(CommonUtil.getString(R.string.get_rigister_getvertify_code),
                        integer));
                codeTimeDownTv.setEnabled(false);
                codeTimeDownTv.setAlpha(0.5f);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
                DoubleString parcelableExtra = data.getParcelableExtra(Constants.INTENT_PARAMETER_1);
                forgetPwdPhoneEreaTv.setText(parcelableExtra.str1);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.i("区号选择失败");
            }
        }
    }

}
