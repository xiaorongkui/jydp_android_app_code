package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:个人信息界面
 */

public class PersonInfoActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.person_info_login_out_bt)
    Button personInfoLoginOutBt;
    @BindView(R.id.person_info_modify_payment_password_civ)
    ClickItemView personInfoModifyPaymentPasswordCiv;
    @BindView(R.id.person_info_modify_password_civ)
    ClickItemView personInfoModifyPasswordCiv;
    @BindView(R.id.person_info_modify_phone_num_civ)
    ClickItemView personInfoModifyPhoneNumCiv;

    @Override
    protected void injectPresenter() {

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
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x3))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_10))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        personInfoLoginOutBt.setBackground(shapeSelector.create());

        personInfoModifyPaymentPasswordCiv.setOnClickListener(this);
        personInfoModifyPasswordCiv.setOnClickListener(this);
        personInfoModifyPhoneNumCiv.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_info_modify_payment_password_civ://修改支付密码
                break;
            case R.id.person_info_modify_password_civ://修改登录密码
                CommonUtil.gotoActivity(mContext, ModifyLoginPwdActivity.class);
                break;
            case R.id.person_info_modify_phone_num_civ://修改手机号
                CommonUtil.gotoActivity(mContext, ModifyPhoneActivity.class);
                break;
        }
    }
}
