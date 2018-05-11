package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.OutSideExchangeRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:场外交易记录
 */

public class OutSideExchangeRecodeActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.mine_outside_exchange_recode_rv)
    RecyclerView mineOutsideExchangeRecodeRv;
    private ArrayList<Object> mData;
    private OutSideExchangeRecodeRecyAdapter outSideExchangeRecodeRecyAdapter;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.outside_exchange_recode));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_outside_exchange_recode;
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
        outSideExchangeRecodeRecyAdapter = new OutSideExchangeRecodeRecyAdapter(mContext, mData, R.layout
                .mine_outside_exchange_recode_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineOutsideExchangeRecodeRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        outSideExchangeRecodeRecyAdapter.setEmptyView(mEmptyView);
        mineOutsideExchangeRecodeRv.setAdapter(outSideExchangeRecodeRecyAdapter);

        outSideExchangeRecodeRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.outside_exchange_recode_see_detail_tv:
                case R.id.outside_exchange_recode_comfirm_receivables_tv:
                    CommonUtil.gotoActivity(mContext, OutSideExchangeOrderDetailActivity.class);
                    break;
            }
        });
    }
}
