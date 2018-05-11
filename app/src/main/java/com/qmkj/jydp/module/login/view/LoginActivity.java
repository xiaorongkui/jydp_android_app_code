package com.qmkj.jydp.module.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.qmkj.jydp.bean.LoginBean;
import com.qmkj.jydp.bean.LoginRequest;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.EditItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.MD5Util;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.single.SingleDoOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:登录页面
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> {

    private static final int splashTotalCountdownTime = 60;
    private static final int LOGIN_SATRT_TAG = 1;
    @BindView(R.id.login_login_title_tv)
    TextView loginLoginTitleTv;
    @BindView(R.id.login_login_title_line)
    View loginLoginTitleLine;
    @BindView(R.id.login_register_title_tv)
    TextView loginRegisterTitleTv;
    @BindView(R.id.login_register_title_line)
    View loginRegisterTitleLine;
    @BindView(R.id.login_account_eiv)
    EditItemView loginAccountEiv;
    @BindView(R.id.login_password_eiv)
    EditItemView loginPasswordEiv;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.login_forget_pwd_tv)
    TextView loginForgetPwdTv;
    @BindView(R.id.login_ll)
    LinearLayout loginLl;
    @BindView(R.id.register_account_eiv)
    EditItemView registerAccountEiv;
    @BindView(R.id.register_phone_erea_tv)
    TextView registerPhoneEreaTv;
    @BindView(R.id.register_phone_erea_iv)
    ImageView registerPhoneEreaIv;
    @BindView(R.id.register_phone_erea_et)
    EditText registerPhoneEreaEt;
    @BindView(R.id.register_verification_code_eiv)
    EditItemView registerVerificationCodeEiv;
    @BindView(R.id.register_password_one_eiv)
    EditItemView registerPasswordOneEiv;
    @BindView(R.id.register_password_two_eiv)
    EditItemView registerPasswordTwoEiv;
    @BindView(R.id.register_exchange_password_one_eiv)
    EditItemView registerExchangePasswordOneEiv;
    @BindView(R.id.register_exchange_password_two_eiv)
    EditItemView registerExchangePasswordTwoEiv;
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
        loginAccountEiv.getEditTextView().setCursorVisible(false);
        loginAccountEiv.getEditTextView().setOnTouchListener((v, event) -> {
            loginAccountEiv.getEditTextView().setCursorVisible(true);
            return false;
        });
        registerAccountEiv.getEditTextView().setCursorVisible(false);
        registerAccountEiv.getEditTextView().setOnTouchListener((v, event) -> {
            registerAccountEiv.getEditTextView().setCursorVisible(true);
            return false;
        });

        codeTimeDownTv = (TextView) registerVerificationCodeEiv.getView(R.id.edit_right_tv);
        codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> codeTimeDown());

    }

    private void setShowStatusView(int index) {
        CommonUtil.hideInputWindow(mContext);
        switch (index) {
            case 0:
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
            .register_bt, R.id.register_phone_erea_tv, R.id.register_phone_erea_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_title_ll:
                setShowStatusView(0);
                break;
            case R.id.register_title_ll:
                setShowStatusView(1);
                break;
            case R.id.login_bt://登录
                CommonUtil.gotoActivity(mContext, MainActivity.class);
//                loginSatart();
                break;
            case R.id.register_bt://注册，注册成功后到实名认证界面
                CommonUtil.gotoActivity(mContext, CertificationActivity.class);
                break;
            case R.id.register_phone_erea_tv://选择区号
            case R.id.register_phone_erea_iv://选择区号
                CommonUtil.startActivityForResult(mContext, AreaCodeSecActivity.class, 1);
                break;
            case R.id.login_forget_pwd_tv://选择区号
                CommonUtil.gotoActivity(mContext, ForgetLoginPwdActivity.class);
                break;
        }
    }

    private void loginSatart() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserAccount("13276717926");
        loginRequest.setPassword(MD5Util.toMd5("123456"));
        presenter.loginStart(loginRequest, LOGIN_SATRT_TAG, true);
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
                LoginBean loginBean = (LoginBean) response;
                CommonUtil.setLoginInfo(loginBean);
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                AppManager.getInstance().removeCurrent();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
        switch (tag) {
            case LOGIN_SATRT_TAG:
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                AppManager.getInstance().removeCurrent();
                break;
        }
    }
}
