package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Bundle;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/7
 * email：dovexiaoen@163.com
 * description:确认出售
 */

public class OutSideSoldDetailActivity extends BaseMvpActivity {
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
        titleHeaderTv.setText(CommonUtil.getString(R.string.confirm_sold));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.outside_activity_sold_detail;
    }

    @Override
    protected void initView() {

    }

}
