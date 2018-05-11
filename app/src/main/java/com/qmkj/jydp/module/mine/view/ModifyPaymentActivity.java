package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:修改支付密码
 */

public class ModifyPaymentActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.modify_pwd_old_pwd_title_tv)
    TextView modifyPwdOldPwdTitleTv;
    @BindView(R.id.modify_pwd_old_pwd_title_line)
    View modifyPwdOldPwdTitleLine;
    @BindView(R.id.modify_pwd_old_pwd_title_ll)
    LinearLayout modifyPwdOldPwdTitleLl;
    @BindView(R.id.modify_pwd_phone_title_tv)
    TextView modifyPwdPhoneTitleTv;
    @BindView(R.id.modify_pwd_phone_title_line)
    View modifyPwdPhoneTitleLine;
    @BindView(R.id.modify_pwd_phone_title_ll)
    LinearLayout modifyPwdPhoneTitleLl;
    @BindView(R.id.modify_original_pwd_ll)
    LinearLayout modifyOriginalPwdLl;
    @BindView(R.id.modify_phone_pwd_ll)
    LinearLayout modifyPhonePwdLl;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.modify_payment_pwd));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_modify_payment_password;
    }

    @Override
    protected void initView() {
        modifyPwdOldPwdTitleLl.setOnClickListener(this);
        modifyPwdPhoneTitleLl.setOnClickListener(this);
        setSelect(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_pwd_old_pwd_title_ll:
                setSelect(0);
                break;
            case R.id.modify_pwd_phone_title_ll:
                setSelect(1);
                break;
        }
    }

    private void setSelect(int index) {
        switch (index) {
            case 0:
                modifyPwdOldPwdTitleTv.setTextColor(CommonUtil.getColor(R.color.color_bule_5));
                modifyPwdPhoneTitleTv.setTextColor(CommonUtil.getColor(R.color.color_black_10));
                modifyPwdPhoneTitleLine.setVisibility(View.VISIBLE);
                modifyPwdOldPwdTitleLine.setVisibility(View.INVISIBLE);
                modifyOriginalPwdLl.setVisibility(View.VISIBLE);
                modifyPhonePwdLl.setVisibility(View.GONE);
                break;
            case 1:
                modifyPwdOldPwdTitleTv.setTextColor(CommonUtil.getColor(R.color.color_black_10));
                modifyPwdPhoneTitleTv.setTextColor(CommonUtil.getColor(R.color.color_bule_5));
                modifyPwdPhoneTitleLine.setVisibility(View.INVISIBLE);
                modifyPwdOldPwdTitleLine.setVisibility(View.VISIBLE);
                modifyOriginalPwdLl.setVisibility(View.GONE);
                modifyPhonePwdLl.setVisibility(View.VISIBLE);
                break;
        }
    }
}
