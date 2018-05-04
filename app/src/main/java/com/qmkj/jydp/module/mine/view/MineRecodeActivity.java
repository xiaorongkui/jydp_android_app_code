package com.qmkj.jydp.module.mine.view;

import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:
 */

public class MineRecodeActivity extends BaseMvpActivity {
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
        titleHeaderTv.setText(CommonUtil.getString(R.string.mine_recode));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_mine_recode;
    }

    @Override
    protected void initView() {

    }
}
