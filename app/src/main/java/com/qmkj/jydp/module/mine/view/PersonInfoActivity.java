package com.qmkj.jydp.module.mine.view;

import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.module.mine.dialog.TipDialog;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:个人信息界面
 */

public class PersonInfoActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.person_info_login_out_bt)
    TextView personInfoLoginOutBt;
    @BindView(R.id.person_info_modify_payment_password_civ)
    ClickItemView personInfoModifyPaymentPasswordCiv;
    @BindView(R.id.person_info_modify_password_civ)
    ClickItemView personInfoModifyPasswordCiv;
    @BindView(R.id.person_info_modify_phone_num_civ)
    ClickItemView personInfoModifyPhoneNumCiv;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.person_info));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_person_info;
    }

    @Override
    protected void initView() {
        personInfoLoginOutBt.setOnClickListener(this);
        personInfoModifyPaymentPasswordCiv.setOnClickListener(this);
        personInfoModifyPasswordCiv.setOnClickListener(this);
        personInfoModifyPhoneNumCiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_info_login_out_bt://退出登陆
                TipDialog tipDialog = new TipDialog(this);
                tipDialog.setContentText("是否确认退出");
                tipDialog.setOnPositiveButtonClickListener((dialog, view) -> {
                    presenter.loginOut(1, true);
                    tipDialog.dismiss();
                });
                tipDialog.show();
                break;
            case R.id.person_info_modify_payment_password_civ://修改支付密码
                CommonUtil.gotoActivity(mContext, ModifyPaymentActivity.class);
                break;
            case R.id.person_info_modify_password_civ://修改登录密码
                CommonUtil.gotoActivity(mContext, ModifyLoginPwdActivity.class);
                break;
            case R.id.person_info_modify_phone_num_civ://修改手机号
                CommonUtil.gotoActivity(mContext, ModifyPhoneActivity.class);
                break;
        }
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        //退出登陆 清空token
        CommonUtil.setToken("");
        AppManager.getInstance().clear();
        CommonUtil.gotoActivity(mContext, LoginActivity.class);
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        //退出登陆 清空token
        CommonUtil.setToken("");
        AppManager.getInstance().clear();
        CommonUtil.gotoActivity(mContext, LoginActivity.class);
    }
}
