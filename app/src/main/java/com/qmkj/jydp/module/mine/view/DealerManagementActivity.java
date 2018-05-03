package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.CurrencyAssetsRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.DealerManagmentRecyAdapter;
import com.qmkj.jydp.ui.widget.SpaceItemDecoration;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:经销商管理
 */

public class DealerManagementActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.dealer_managment_rv)
    RecyclerView dealerManagmentRv;
    private ArrayList<Object> mData;
    private DealerManagmentRecyAdapter dealerManagmentRecyAdapter;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.dealer_managment));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_dealer_managment;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        dealerManagmentRecyAdapter = new DealerManagmentRecyAdapter(mContext, mData, R.layout
                .mine_dealer_managment_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dealerManagmentRv.setLayoutManager(layoutManager);

        dealerManagmentRv.addItemDecoration(new SpaceItemDecoration((int) CommonUtil.getDimen(R.dimen
                .y10), (int) CommonUtil.getDimen(R.dimen.y10)));
        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        dealerManagmentRecyAdapter.setEmptyView(mEmptyView);
        dealerManagmentRv.setAdapter(dealerManagmentRecyAdapter);
    }
}
