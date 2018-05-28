package com.qmkj.jydp.module.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.DoubleString;
import com.qmkj.jydp.bean.request.LoginReq;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.bean.request.RegisterReq;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.common.SystemMessageConfig;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.module.mine.view.HelpCenterDetailsActivity;
import com.qmkj.jydp.ui.widget.EditHItemView;
import com.qmkj.jydp.util.CheckTextUtil;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

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
    private Disposable disposable;//倒计时的disposable
    private TextView codeTimeDownTv;
    private String account;


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
        codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> {
            CommonUtil.hideInputWindow(mContext);
            getVerificationCode();
        });
    }

    private void setShowStatusView(int index) {
        CommonUtil.hideInputWindow(mContext);
        switch (index) {
            case 0:
                loginAccountEiv.getEditTextView().setText(CommonUtil.getUserAccount());
                loginLoginTitleTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                loginRegisterTitleTv.setTextColor(CommonUtil.getColor(R.color.color_gray_2));
                loginLoginTitleLine.setVisibility(View.VISIBLE);
                loginRegisterTitleLine.setVisibility(View.INVISIBLE);
                loginLl.setVisibility(View.VISIBLE);
                registerLl.setVisibility(View.GONE);
                break;
            case 1:
                loginLoginTitleTv.setTextColor(CommonUtil.getColor(R.color.color_gray_2));
                loginRegisterTitleTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                loginLoginTitleLine.setVisibility(View.INVISIBLE);
                loginRegisterTitleLine.setVisibility(View.VISIBLE);
                loginLl.setVisibility(View.GONE);
                registerLl.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.login_title_ll, R.id.register_title_ll, R.id.login_bt, R.id.login_forget_pwd_tv, R.id
            .register_bt, R.id.register_phone_erea_tv, R.id.register_phone_erea_iv, R.id.register_agreement_name_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_title_ll:
                setShowStatusView(0);
                break;
            case R.id.register_title_ll:
                setShowStatusView(1);
                break;
            case R.id.login_bt://登录
                loginSatart();
                break;
            case R.id.register_bt://注册，注册成功后到实名认证界面
                startRegister();
                break;
            case R.id.register_phone_erea_tv://选择区号
            case R.id.register_phone_erea_iv://选择区号
                CommonUtil.startActivityForResult(mContext, AreaCodeSecActivity.class, 1);
                break;
            case R.id.login_forget_pwd_tv://选择区号
                CommonUtil.gotoActivity(mContext, ForgetLoginPwdActivity.class);
                break;
            case R.id.register_agreement_name_tv://注册协议
                Intent intent = new Intent(this, HelpCenterDetailsActivity.class);
                intent.putExtra(HelpCenterDetailsActivity.ID_KEY, "101010");
                intent.putExtra(HelpCenterDetailsActivity.ACTIVITY_TITLE_KEY, "注册协议");
                CommonUtil.gotoActivity(mContext, intent);
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
        if (!CheckTextUtil.checkPassword(registerAccount)) {
            toast("注册账号必须是字母、数字，6～16个字符");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            toast("手机号不能为空");
            return;
        }

        if (TextUtils.isEmpty(code) || code.length() != 6) {
            toast("验证码必须为六位");
            return;
        }
        if (!CheckTextUtil.checkPassword(loginPwdOne)) {
            toast("登录密码必须是字母、数字，6～16个字符");
            return;
        }
        if (TextUtils.isEmpty(loginPwdOne) || !loginPwdOne.equals(loginPwdTwo)) {
            toast("两次登录密码输入不同");
            return;
        }
        if (!CheckTextUtil.checkPassword(exchangePwdOne)) {
            toast("登录密码必须是字母、数字，6～16个字符");
            return;
        }
        if (TextUtils.isEmpty(exchangePwdOne) || !exchangePwdOne.equals(exchangePwdTwo)) {
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
        if (!CheckTextUtil.checkPassword(account)) {
            toast("账号必须是字母、数字，6～16个字符");
            return;
        }
        if (!CheckTextUtil.checkPassword(password)) {
            toast("密码必须是字母、数字，6～16个字符");
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
        if (TextUtils.isEmpty(phone)) {
            toast("手机号不能为空");
            return;
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
                DoubleString parcelableExtra = (DoubleString) data.getParcelableExtra(Constants.INTENT_PARAMETER_1);
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
            CommonUtil.setStatusBarInvisible(this, false);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case LOGIN_SATRT_TAG:
                toast("登陆成功");
                LogUtil.i(response.toString());
                LoginRes loginBean = (LoginRes) response;
                LoginRes.UserBean user = loginBean.getUser();
                CommonUtil.setLoginInfo(loginBean);
                if (user != null) CommonUtil.setUserAccount(loginAccountEiv.getEditTextString());
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                AppManager.getInstance().removeCurrent();
                break;
            case REGISTER_CODE_TAG:
                toast("验证码获取成功");
                codeTimeDown();
                break;
            case REGISTER_TAG:
                toast("注册成功");
                setShowStatusView(0);
                CommonUtil.setUserAccount(registerAccountEiv.getEditTextString());
                Intent intent1 = new Intent(mContext, CertificationActivity.class);
                intent1.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_NO_SUBMIT);
                CommonUtil.gotoActivity(mContext, intent1);
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        switch (tag) {
            case LOGIN_SATRT_TAG:
                try {
                    LoginRes loginBean = (LoginRes) response;
                    if (loginBean != null) CommonUtil.setLoginInfo(loginBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                switch (code) {
                    case SystemMessageConfig.NOIDENTIFICATION_CODE + ""://未审核
                        CommonUtil.setUserAccount(account);
                        Intent intent1 = new Intent(mContext, CertificationActivity.class);
                        intent1.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_NO_SUBMIT);
                        intent1.putExtra(Constants.INTENT_PARAMETER_2, account);
                        CommonUtil.gotoActivity(mContext, intent1);
                        break;
                    case SystemMessageConfig.NOADOPT_CODE + ""://审核中
                        CommonUtil.setUserAccount(account);
                        Intent intent2 = new Intent(mContext, CertificationActivity.class);
                        intent2.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_CHECK);
                        intent2.putExtra(Constants.INTENT_PARAMETER_2, account);
                        CommonUtil.gotoActivity(mContext, intent2);
                        break;
                    case SystemMessageConfig.REFUE_CODE + ""://拒绝
                        CommonUtil.setUserAccount(account);
                        Intent intent3 = new Intent(mContext, CertificationActivity.class);
                        intent3.putExtra(Constants.INTENT_PARAMETER_1, CertificationActivity.CERTIFY_STATUS_NO_PASS);
                        intent3.putExtra(Constants.INTENT_PARAMETER_2, account);
                        CommonUtil.gotoActivity(mContext, intent3);
                        break;
                }

                break;
            case REGISTER_TAG:
                CommonUtil.gotoActivity(mContext, CertificationActivity.class);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CommonUtil.gotoActivity(mContext, MainActivity.class);
    }
}
