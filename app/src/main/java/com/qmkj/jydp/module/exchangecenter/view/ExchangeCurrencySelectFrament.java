package com.qmkj.jydp.module.exchangecenter.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.response.ExchangeCurrencyRes;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author
 * @date 2018/4/24
 * @desc 交易中心的币种选择界面
 */

public class ExchangeCurrencySelectFrament extends BaseMvpFragment<ExchangeCenterPresenter> {
    private static final int EXCHANGE_CURRENCY_TAG = 1;
    @BindView(R.id.exchange_center_title_ll)
    LinearLayout title_ll;
    @BindView(R.id.exchange_center_xrl)
    XRefreshLayout refresh;
    @BindView(R.id.exchange_center_rv)
    RecyclerView recyclerView;
    boolean isCanRefresh = true;
    ArrayList<ExchangeCurrencyRes.TransactionUserDealListBean> mData = new ArrayList<>();
    ExchangeCenterAdapter exchangeCenterAdapter;

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        initStatusBar();
        initRecyclerView();
        initRefreshView();
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
        exchangeCenterAdapter = new ExchangeCenterAdapter(R.layout.home_exchange_price_item, mData);
        View mEmptyView = View.inflate(getContext(), R.layout.empty, null);
        exchangeCenterAdapter.setEmptyView(mEmptyView);
        recyclerView.setAdapter(exchangeCenterAdapter);

        exchangeCenterAdapter.setOnItemClickListener((adapter, view, position) -> {

            try {
                ExchangeCurrencyRes.TransactionUserDealListBean cerrencyData = mData.get(position);
                ((MainActivity) getActivity()).showExchangeFrament(cerrencyData.getCurrencyName(), cerrencyData
                        .getCurrencyId() + "");//去交易中心核心页面
            } catch (Exception e) {
                e.printStackTrace();
                toast(getString(R.string.cerrecy_select_failed));
            }
        });
    }

    private void initRefreshView() {
        refresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getExchangeCurrencyData(false);
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return isCanRefresh;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                isCanRefresh = topRowVerticalPosition >= 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    protected void initData() {
        getExchangeCurrencyData(true);
    }

    private void getExchangeCurrencyData(boolean isShowProgress) {
        presenter.getExchangeCurrency(EXCHANGE_CURRENCY_TAG, isShowProgress);
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
        switch (tag) {
            case EXCHANGE_CURRENCY_TAG:
                if (refresh.isRefreshing()) refresh.refreshComplete();
                ExchangeCurrencyRes currencyRes = (ExchangeCurrencyRes) response;
                if (currencyRes == null) {
                    LogUtil.i("交易中心获取币种信息为空");
                    return;
                }
                List<ExchangeCurrencyRes.TransactionUserDealListBean> transactionUserDealList = currencyRes
                        .getTransactionUserDealList();
                if (transactionUserDealList != null) {
                    mData.clear();
                    mData.addAll(transactionUserDealList);
                    exchangeCenterAdapter.notifyDataSetChanged();
                }

                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        switch (tag) {
            case EXCHANGE_CURRENCY_TAG:
                if (refresh.isRefreshing()) refresh.refreshComplete();
                showNetErrorView(recyclerView, true);
                break;
        }
    }
}
