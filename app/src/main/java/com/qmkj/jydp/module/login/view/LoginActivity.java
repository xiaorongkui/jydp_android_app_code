package com.qmkj.jydp.module.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.DoubleString;
import com.qmkj.jydp.bean.event.ExchangePwdEvent;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.common.SystemMessageConfig;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.DataManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.manager.SystemManager;
import com.qmkj.jydp.module.home.view.WebActivity;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.EditHItemView;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:登录页面
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> {

    private static final int splashTotalCountdownTime = 60;
    private static final int LOGIN_SATRT_TAG = 1;
    private static final int REGISTER_CODE_TAG = 2;
    private static final int REGISTER_TAG = 3;
    @BindView(R.id.login_login_title_tv)
    TextView loginLoginTitleTv;
    @BindView(R.id.login_login_title_line)
    View loginLoginTitleLine;
    @BindView(R.id.login_register_title_tv)
    TextView loginRegisterTitleTv;
    @BindView(R.id.login_register_title_line)
    View loginRegisterTitleLine;
    @BindView(R.id.login_account_eiv)
    EditHItemView loginAccountEiv;
    @BindView(R.id.login_password_eiv)
    EditHItemView loginPasswordEiv;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.login_forget_pwd_tv)
    TextView loginForgetPwdTv;
    @BindView(R.id.login_ll)
    LinearLayout loginLl;

    @BindView(R.id.register_account_eiv)
    EditHItemView registerAccountEiv;
    @BindView(R.id.register_phone_erea_tv)
    TextView registerPhoneEreaTv;
    @BindView(R.id.register_phone_erea_iv)
    ImageView registerPhoneEreaIv;
    @BindView(R.id.register_phone_eiv)
    EditHItemView registerPhoneEiv;
    @BindView(R.id.register_verification_code_eiv)
    EditHItemView registerVerificationCodeEiv;
    @BindView(R.id.register_password_one_eiv)
    EditHItemView registerPasswordOneEiv;
    @BindView(R.id.register_password_two_eiv)
    EditHItemView registerPasswordTwoEiv;
    @BindView(R.id.register_exchange_password_one_eiv)
    EditHItemView registerExchangePasswordOneEiv;
    @BindView(R.id.register_exchange_password_two_eiv)
    EditHItemView registerExchangePasswordTwoEiv;
    @BindView(R.id.register_bt)
    Button registerBt;
    @BindView(R.id.register_agreement_notice_tv)
    TextView registerAgreementNoticeTv;
    @BindView(R.id.register_agreement_name_tv)
    TextView registerAgreementNameTv;
    @BindView(R.id.register_agreement_rl)
    RelativeLayout registerAgreementRl;
    @BindView(R.id.register_ll)
    LinearLayout registerLl;
    @BindView(R.id.login_title_ll)
    LinearLayout loginTitleLl;
    @BindView(R.id.register_title_ll)
    LinearLayout registerTitleLl;
    @BindView(R.id.login_logo_view)
    View login_logo_view;
    private Disposable disposable;//倒计时的disposable
    private TextView codeTimeDownTv;
    private String account;
    private boolean isCertifyCode = false;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView() {

        setShowStatusView(0);
        codeTimeDownTv = registerVerificationCodeEiv.getView(R.id.edit_right_tv);
        codeTimeDownTv.setText(ResourcesManager.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> {
            SystemManager.hideInputWindow(mContext);
            getVerificationCode();
        });

        RxView.clicks(loginBt).throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(o -> loginSatart());//防重复点击

        RxView.clicks(registerBt).throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(o -> startRegister());
    }

    private void setShowStatusView(int index) {
        SystemManager.hideInputWindow(mContext);
        switch (index) {
            case 0:
                loginAccountEiv.getEditTextView().setText(DataManager.getUserAccount());
                loginLoginTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_bule_1));
                loginRegisterTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_gray_2));
                loginLoginTitleLine.setVisibility(View.VISIBLE);
                loginRegisterTitleLine.setVisibility(View.INVISIBLE);
                loginLl.setVisibility(View.VISIBLE);
                registerLl.setVisibility(View.GONE);
                break;
            case 1:
                loginLoginTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_gray_2));
                loginRegisterTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_bule_1));
                loginLoginTitleLine.setVisibility(View.INVISIBLE);
                loginRegisterTitleLine.setVisibility(View.VISIBLE);
                loginLl.setVisibility(View.GONE);
                registerLl.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.login_title_ll, R.id.register_title_ll, R.id.login_bt, R.id.login_forget_pwd_tv, R.id
            .register_bt, R.id.register_phone_erea_tv, R.id.register_phone_erea_iv, R.id.register_agreement_name_tv,
            R.id.login_logo_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_logo_view:
                ActivityManager.gotoActivity(mContext, MainActivity.class);
                ActivityManager.getInstance().removeCurrent();
                break;
            case R.id.login_title_ll:
                setShowStatusView(0);
                break;
            case R.id.register_title_ll:
                setShowStatusView(1);
                break;
            case R.id.login_bt://登录
//                loginSatart();
                break;
            case R.id.register_bt://注册，注册成功后到实名认证界面
//                startRegister();
                break;
            case R.id.register_phone_erea_tv://选择区号
            case R.id.register_phone_erea_iv://选择区号
                ActivityManager.startActivityForResult(mContext, AreaCodeSecActivity.class, 1);
                break;
            case R.id.login_forget_pwd_tv://选择区号
                ActivityManager.gotoActivity(mContext, ForgetLoginPwdActivity.class);
                break;
            case R.id.register_agreement_name_tv://注册协议
                Intent intent = WebActivity.getActivityIntent(mContext, "注册协议", AppNetConfig.HELP_CENTER_URL +
                        "101010");
                ActivityManager.gotoActivity(mContext, intent);
                break;
        }
    }

    private void startRegister() {
        String registerAccount = registerAccountEiv.getEditTextString();
        String phone = registerPhoneEiv.getEditTextString();
        String code = registerVerificationCodeEiv.getEditTextString();
        String loginPwdOne = registerPasswordOneEiv.getEditTextString();
        String loginPwdTwo = registerPasswordTwoEiv.getEditTextString();
        String exchangePwdOne = registerExchangePasswordOneEiv.getEditTextString();
        String exchangePwdTwo = registerExchangePasswordTwoEiv.getEditTextString();
        String areaCode = registerPhoneEreaTv.getText().toString().trim();

        if (TextUtils.isEmpty(registerAccount)) {
            toast("请输入注册账号");
            return;
        }
        if (registerAccount.length() < 6) {
            toast("注册账号不能小于六位");
            return;
        }

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

        if (TextUtils.isEmpty(loginPwdOne)) {
            toast("请输入登录密码");
            return;
        }
        if (loginPwdOne.length() < 6) {
            toast("登录密码不能小于六位");
            return;
        }

        if (TextUtils.isEmpty(loginPwdTwo)) {
            toast("请输入重复登录密码");
            return;
        }
        if (loginPwdTwo.length() < 6) {
            toast("重复登录密码不能小于六位");
            return;
        }

        if (!loginPwdOne.equals(loginPwdTwo)) {
            toast("两次登录密码输入不同");
            return;
        }

        if (TextUtils.isEmpty(exchangePwdOne)) {
            toast("请输入交易密码");
            return;
        }
        if (exchangePwdOne.length() < 6) {
            toast("交易密码不能小于六位");
            return;
        }
        if (loginPwdOne.equals(exchangePwdOne)) {
            toast("登录密码和交易密码不能相同");
            return;
        }

        if (TextUtils.isEmpty(exchangePwdTwo)) {
            toast("请输入重复交易密码");
            return;
        }
        if (exchangePwdTwo.length() < 6) {
            toast("重复交易密码不能小于六位");
            return;
        }

        if (!exchangePwdOne.equals(exchangePwdTwo)) {
            toast("两次交易密码输入不同");
            return;
        }

        RegisterReq registerReq = new RegisterReq();
        registerReq.setUserAccount(registerAccount);
        registerReq.setPhoneAreaCode(areaCode);
        registerReq.setPhoneNumber(phone);
        registerReq.setValidateCode(code);
        registerReq.setPassword(loginPwdOne);
        registerReq.setPayPassword(exchangePwdOne);
        presenter.startRegister(registerReq, REGISTER_TAG);
    }

    private void loginSatart() {
        account = loginAccountEiv.getEditTextString();
        String password = loginPasswordEiv.getEditTextString();
        if (TextUtils.isEmpty(account)) {
            toast("请输入登录账号");
            return;
        }
        if (account.length() < 6) {
            toast("登录账号不能小于六位");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            toast("请输入登录密码");
            return;
        }
        if (password.length() < 6) {
            toast("登录密码不能小于六位");
            return;
        }

        LoginReq loginRequest = new LoginReq();
        loginRequest.setUserAccount(account);//13276717926
        loginRequest.setPassword(password);//123456
        presenter.loginStart(loginRequest, LOGIN_SATRT_TAG, true);
    }

    private void getVerificationCode() {
        String phone = registerPhoneEiv.getEditTextString();
        String areaCode = registerPhoneEreaTv.getText().toString();

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
        presenter.getRegisterCode(phoneCodeReq, REGISTER_CODE_TAG);
    }

    private void codeTimeDown() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).map(aLong -> splashTotalCountdownTime - aLong.intValue()).take
                (splashTotalCountdownTime + 1).subscribe(integer -> {
            if (integer == 0) {
                codeTimeDownTv.setText(ResourcesManager.getString(R.string.get_rigister_getvertify_code_1));
                codeTimeDownTv.setEnabled(true);
                codeTimeDownTv.setAlpha(1);
            } else {
                codeTimeDownTv.setText(String.format(ResourcesManager.getString(R.string.get_rigister_getvertify_code),
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
                registerPhoneEreaTv.setText(parcelableExtra.str1);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.i("区号选择失败");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(R.style.mainActivityTheme);
            SystemManager.setStatusBarInvisible(this, false);
        } else {
            setTheme(R.style.BaseAppTheme);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case LOGIN_SATRT_TAG:
                toast("登录成功");
                LogUtil.i(response.toString());
                RxBus.getDefault().post(new ExchangePwdEvent());//去更新密码状态
                LoginRes loginBean = (LoginRes) response;
                LoginRes.UserBean user = loginBean.getUser();
                DataManager.setLoginInfo(loginBean);
                if (user != null) DataManager.setUserAccount(loginAccountEiv.getEditTextString());
                ActivityManager.getInstance().removeActivity(MainActivity.class);
                ActivityManager.gotoActivity(mContext, MainActivity.class);
                ActivityManager.getInstance().removeCurrent();
                break;
            case REGISTER_CODE_TAG:
                toast("验证码获取成功");
                isCertifyCode = true;
                codeTimeDown();
                break;
            case REGISTER_TAG:
                toast("注册成功");
                setShowStatusView(0);
                DataManager.setUserAccount(registerAccountEiv.getEditTextString());
                Intent intent1 = new Intent(mContext, CertificationActivity.class);
                intent1.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_NO_SUBMIT);
                intent1.putExtra(Constants.INTENT_PARAMETER_2, registerAccountEiv.getEditTextString());
                DataManager.setUserAccount(account);
                ActivityManager.gotoActivity(mContext, intent1);
                clearRegisterData();
                break;
        }
    }

    private void clearRegisterData() {
        registerAccountEiv.getEditTextView().setText("");
        registerPhoneEiv.getEditTextView().setText("");
        registerVerificationCodeEiv.getEditTextView().setText("");
        registerPasswordOneEiv.getEditTextView().setText("");
        registerPasswordTwoEiv.getEditTextView().setText("");
        registerExchangePasswordOneEiv.getEditTextView().setText("");
        registerExchangePasswordTwoEiv.getEditTextView().setText("");
        registerPhoneEreaTv.setText("");
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        switch (tag) {
            case LOGIN_SATRT_TAG:
                try {
                    LoginRes loginBean = (LoginRes) response;
                    if (loginBean != null) DataManager.setLoginInfo(loginBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                switch (code) {
                    case SystemMessageConfig.NOIDENTIFICATION_CODE + ""://未审核

                        DataManager.setUserAccount(account);
                        Intent intent1 = new Intent(mContext, CertificationActivity.class);
                        intent1.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_NO_SUBMIT);
                        intent1.putExtra(Constants.INTENT_PARAMETER_2, account);
                        ActivityManager.gotoActivity(mContext, intent1);
                        break;
                    case SystemMessageConfig.NOADOPT_CODE + ""://审核中

                        DataManager.setUserAccount(account);
                        Intent intent2 = new Intent(mContext, CertificationActivity.class);
                        intent2.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_CHECK);
                        intent2.putExtra(Constants.INTENT_PARAMETER_2, account);
                        ActivityManager.gotoActivity(mContext, intent2);
                        break;
                    case SystemMessageConfig.REFUE_CODE + ""://拒绝

                        DataManager.setUserAccount(account);
                        Intent intent3 = new Intent(mContext, CertificationActivity.class);
                        intent3.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_NO_PASS);
                        intent3.putExtra(Constants.INTENT_PARAMETER_2, account);
                        ActivityManager.gotoActivity(mContext, intent3);
                        break;
                }

                break;
            case REGISTER_TAG:
                break;
        }
    }
}
