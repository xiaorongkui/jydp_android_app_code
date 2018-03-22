package com.qmkj.jydp.module.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseActivity;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:登录页面
 */

public class LoginActivity extends MvpBaseActivity {
    @BindView(R.id.login_title_tv)
    TextView loginTitleTv;
    @BindView(R.id.register_title_tv)
    TextView registerTitleTv;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.login_ll)
    LinearLayout loginLl;
    @BindView(R.id.code_time_down_tv)
    TextView codeTimeDownTv;
    @BindView(R.id.register_bt)
    Button registerBt;
    @BindView(R.id.register_ll)
    LinearLayout registerLl;
    @BindView(R.id.register_agreement_rl)
    RelativeLayout registerAgreementRl;
    private static final int splashTotalCountdownTime = 60;
    private Disposable disposable;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setShowStatusView(0);
    }

    private void setShowStatusView(int index) {
        switch (index) {
            case 0:
                loginTitleTv.setTextColor(CommonUtil.getColor(R.color.textRed_2));
                registerTitleTv.setTextColor(CommonUtil.getColor(R.color.colorBlack_5));
                loginLl.setVisibility(View.VISIBLE);
                registerLl.setVisibility(View.GONE);
                registerAgreementRl.setVisibility(View.GONE);
                break;
            case 1:
                loginTitleTv.setTextColor(CommonUtil.getColor(R.color.colorBlack_5));
                registerTitleTv.setTextColor(CommonUtil.getColor(R.color.textRed_2));
                loginLl.setVisibility(View.GONE);
                registerLl.setVisibility(View.VISIBLE);
                registerAgreementRl.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_title_tv, R.id.register_title_tv, R.id.code_time_down_tv, R.id.login_bt, R.id.register_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_title_tv:
                setShowStatusView(0);
                break;
            case R.id.register_title_tv:
                setShowStatusView(1);
                break;
            case R.id.code_time_down_tv:
                codeTimeDown();
                break;
            case R.id.login_bt://登录
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                break;
            case R.id.register_bt://注册，注册成功后到实名认证界面
                CommonUtil.gotoActivity(mContext, CertificationActivity.class);
                break;
        }
    }

    private void codeTimeDown() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).map(aLong -> splashTotalCountdownTime - aLong.intValue()).take
                (splashTotalCountdownTime + 1).subscribe(aLong -> {
            codeTimeDownTv.setText(String.format(CommonUtil.getString(R.string.getvertify_code), aLong));
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
}