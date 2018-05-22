package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.bean.response.OutSideExchangeRes;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangeAdapter;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wujiangming
 * @date 2018/4/23
 * @desc
 */

public class OutsideExchangeFragment extends BaseMvpFragment<OutsideExchangePresenter> {
    private static final int OUTSIDE_LIST_TAG = 1;
    @BindView(R.id.title_ll)
    LinearLayout title_ll;
    @BindView(R.id.refresh)
    XRefreshLayout refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    OutsideExchangeAdapter mOutsideExchangeAdapter;
    ArrayList<OutSideExchangeRes.OtcTransactionPendOrderListBean> mData = new ArrayList<>();
    int currentPageNumber = 0;
    private boolean isRefresh = true;

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        initStatusBar();
        initRecyclerView();
        initClick();
        initRefresh();
    }

    private void initRefresh() {
        refresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOutSideExchangeData(false);
                isRefresh = true;
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return isTop();
            }
        });
        mOutsideExchangeAdapter.setOnLoadMoreListener(() -> {
            isRefresh = false;
            getOutSideExchangeData(false);
        }, recyclerView);
    }

    private boolean isTop() {
        int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView
                .getChildAt(0).getTop();
        return topRowVerticalPosition >= 0;
    }

    private void initStatusBar() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            title_ll.addView(statusView, 0, lp);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(mContext);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutmanager);
        mOutsideExchangeAdapter = new OutsideExchangeAdapter(mContext, R.layout.exchange_outside_item, mData);
        recyclerView.setAdapter(mOutsideExchangeAdapter);
    }

    public void initClick() {
        mOutsideExchangeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            LogUtil.i("view=" + view.getId() + "position=" + position);
            switch (view.getId()) {
                case R.id.exchange_outside_go_exchange_tv:
                    toast("去交易");
                    switch (mData.get(position).getOrderType()) {
                        case 1://1：出售
                            CommonUtil.gotoActivity(mContext, OutSideSoldActivity.class);
                            break;
                        case 2://2：回购
                            CommonUtil.gotoActivity(mContext, OutSideBuyActivity.class);
                            break;
                    }

                    break;
                default:
                    break;
            }
        });
    }

    @Override
    protected void initData() {
        getOutSideExchangeData(true);
    }

    private void getOutSideExchangeData(boolean b) {
        OutSideExchangeReq outSideExchangeReq = new OutSideExchangeReq();
        outSideExchangeReq.setPageNumber(currentPageNumber + "");
        presenter.getOutsideExchangeData(outSideExchangeReq, OUTSIDE_LIST_TAG, b);
    }

    @Override
    public int getLayoutId() {
        return R.layout.outside_exchange_fragment;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case OUTSIDE_LIST_TAG:
                OutSideExchangeRes outSideExchangeRes = (OutSideExchangeRes) response;
                if (outSideExchangeRes == null || outSideExchangeRes.getOtcTransactionPendOrderList() == null ||
                        outSideExchangeRes.getOtcTransactionPendOrderList().size() == 0) return;

                List<OutSideExchangeRes.OtcTransactionPendOrderListBean> otcTransactionPendOrderList =
                        outSideExchangeRes.getOtcTransactionPendOrderList();
                if (isRefresh) {
                    mData.clear();
                    refresh.refreshComplete();
                }
                mData.addAll(otcTransactionPendOrderList);
                mOutsideExchangeAdapter.notifyDataSetChanged();
                calculatePage(outSideExchangeRes);
                break;
        }
    }

    private void calculatePage(OutSideExchangeRes outSideExchangeRes) {
        int totalPageNumber = outSideExchangeRes.getTotalPageNumber();
        int pageNumber = outSideExchangeRes.getPageNumber();
        if ((pageNumber + 1) < totalPageNumber) {
            mOutsideExchangeAdapter.loadMoreComplete();
            currentPageNumber++;
        } else {
            //没有更多数据
            mOutsideExchangeAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        if (refresh.isRefreshing()) refresh.refreshComplete();
        if (mOutsideExchangeAdapter.isLoading()) mOutsideExchangeAdapter.loadMoreComplete();
    }
}
