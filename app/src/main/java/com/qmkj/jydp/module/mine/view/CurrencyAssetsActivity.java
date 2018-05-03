package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.CurrencyAssetsRecyAdapter;
import com.qmkj.jydp.ui.widget.SpaceItemDecoration;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:币种资产页面
 */

public class CurrencyAssetsActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.currency_assets_rv)
    RecyclerView currencyAssetsRv;
    private ArrayList<String> mData;
    private CurrencyAssetsRecyAdapter assetsRecyAdapter;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.currency_assets));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_currency_assets;
    }

    @Override
    protected void initView() {
        initRecycleView();
        initRecycleFooter();
    }

    private void initRecycleFooter() {
        View inflate = View.inflate(mContext, R.layout.more_view, null);
        assetsRecyAdapter.addFooterView(inflate);
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        assetsRecyAdapter = new CurrencyAssetsRecyAdapter(mContext, mData, R.layout.mine_currency_assets_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        currencyAssetsRv.setLayoutManager(layoutManager);

        currencyAssetsRv.addItemDecoration(new SpaceItemDecoration((int) CommonUtil.getDimen(R.dimen
                .y10), (int) CommonUtil.getDimen(R.dimen.y10)));
        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        assetsRecyAdapter.setEmptyView(mEmptyView);
        currencyAssetsRv.setAdapter(assetsRecyAdapter);
    }
}
