package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.CurrencyWithDrawRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:币种提现记录
 */

public class CurrencyWithDrawRecodeActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.mine_currency_withdraw_recode_rv)
    RecyclerView mineCurrencyWithdrawRecodeRv;
    private ArrayList<Object> mData;
    private CurrencyWithDrawRecodeRecyAdapter currencyWithDrawRecodeRecyAdapter;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.currency_withdraw_recode));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_currency_withdraw_recode;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        currencyWithDrawRecodeRecyAdapter = new CurrencyWithDrawRecodeRecyAdapter(mContext, mData, R.layout
                .mine_currency_withdraw_reocde_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineCurrencyWithdrawRecodeRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        currencyWithDrawRecodeRecyAdapter.setEmptyView(mEmptyView);
        mineCurrencyWithdrawRecodeRv.setAdapter(currencyWithDrawRecodeRecyAdapter);
    }
}
