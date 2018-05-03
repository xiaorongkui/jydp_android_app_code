package com.qmkj.jydp.module.exchangecenter.view;

import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author
 * @date 2018/4/24
 * @desc 交易中心的币种选择界面
 */

public class ExchangeCurrencySelectFrament extends BaseMvpFragment<ExchangeCenterPresenter> {
    @BindView(R.id.exchange_center_title_ll)
    LinearLayout title_ll;
    @BindView(R.id.exchange_center_xrl)
    XRefreshLayout refresh;
    @BindView(R.id.exchange_center_rv)
    RecyclerView recyclerView;
    ArrayList<Object> mData;
    ExchangeCenterAdapter exchangeCenterAdapter;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {
        initStatusBar();
        initRecyclerView();
    }

    private void initStatusBar() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            title_ll.addView(statusView, 0, lp);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutmanager);
        mData = new ArrayList<>();
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        exchangeCenterAdapter = new ExchangeCenterAdapter(R.layout.home_exchange_price_item, mData);
        View mEmptyView = View.inflate(getContext(), R.layout.empty, null);
        exchangeCenterAdapter.setEmptyView(mEmptyView);
        recyclerView.setAdapter(exchangeCenterAdapter);

        exchangeCenterAdapter.setOnItemClickListener((adapter, view, position) -> {
            try {
                ((MainActivity) getActivity()).showExchangeFrament("盛源链");//去交易中心核心页面
            } catch (Exception e) {
                e.printStackTrace();
                toast(getString(R.string.cerrecy_select_failed));
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.exchange_fragment_currency_select;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
    }
}
