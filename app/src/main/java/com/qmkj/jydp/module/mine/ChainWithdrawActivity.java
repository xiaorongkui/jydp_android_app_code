package com.qmkj.jydp.module.mine;

import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

public class ChainWithdrawActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.chain_withdraw));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chain_withdraw;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
