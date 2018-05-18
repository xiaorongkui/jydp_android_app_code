package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:修改手机号
 */

public class ModifyPhoneActivity extends BaseMvpActivity<LoginPresenter> {
    private static final int GET_CODE_TAG_1 = 1;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.modify_phone_erea_tv)
    TextView modify_phone_erea_tv;

    @BindView(R.id.modify_phone_erea_et)
    EditText modify_phone_erea_et;

    @BindView(R.id.modify_phone_old_num_civ)
    ClickItemView modify_phone_old_num_civ;
    @BindView(R.id.modify_phone_submit_bt)
    Button modify_phone_submit_bt;
    @BindView(R.id.modify_phone_verification_code_civ)
    EditVItemView modify_phone_verification_code_civ;
    private TextView codeTimeDownTv;
    @BindView(R.id.modify_phone_verification_code_eiv)
    EditVItemView modify_phone_verification_code_eiv;
    private TextView codeTimeDownTv1;
    private Object verificationCode2;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        if(CommonUtil.getLoginInfo()!=null&&CommonUtil.getLoginInfo().getUser()!=null){
            modify_phone_old_num_civ.setRightText(CommonUtil.getLoginInfo().getUser().getPhoneAreaCode()+CommonUtil.getLoginInfo().getUser().getUserPhone()+"");
        }
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.modify_phone_num));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_modify_phone_num;
    }

    @Override
    protected void initView() {
        codeTimeDownTv = modify_phone_verification_code_civ.getView(R.id.edit_right_tv);
        codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> {
            CommonUtil.hideInputWindow(mContext);
            getVerificationCode();
        });

        codeTimeDownTv1 = modify_phone_verification_code_eiv.getView(R.id.edit_right_tv);
        codeTimeDownTv1.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv1.setOnClickListener(v -> {
            CommonUtil.hideInputWindow(mContext);
            getVerificationCode2();
        });


    }


    @OnClick(R.id.modify_phone_submit_bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modify_phone_submit_bt:
                changePhone();
                break;
        }
    }

    private void changePhone() {

    }

    private void getVerificationCode() {
        String phone = CommonUtil.getLoginInfo().getUser().getUserPhone();
        String phoneAreaCode = CommonUtil.getLoginInfo().getUser().getPhoneAreaCode();
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode+phone);
        presenter.getRegisterCode(phoneCodeReq, GET_CODE_TAG_1);
    }

    public void getVerificationCode2() {
        if (TextUtils.isEmpty(modify_phone_erea_et.getText())) {
            toast("登陆密码不能为空");
            return;
        }
        String phone =modify_phone_erea_et.getText().toString();
        String phoneAreaCode =modify_phone_erea_tv.getText().toString();
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode+phone);
        presenter.getRegisterCode(phoneCodeReq, GET_CODE_TAG_1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
