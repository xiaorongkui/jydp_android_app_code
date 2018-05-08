package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
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

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.modify_phone_num));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_modify_payment_password;
    }

    @Override
    protected void initView() {

    }

}
