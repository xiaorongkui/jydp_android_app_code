package com.qmkj.jydp.module.exchangoutsidee.view;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.request.DistributorPayMethodReq;
import com.qmkj.jydp.bean.request.OutSideExchangeReq;
import com.qmkj.jydp.bean.response.DistributorPayMethodRes;
import com.qmkj.jydp.bean.response.OutSideExchangeRes;
import com.qmkj.jydp.common.Constants;
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
    private static final int DISTRIBUTOR_PAYMETHOD_TAG = 2;
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
    private OutSideExchangeRes.OtcTransactionPendOrderListBean orderListBean;

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
                    orderListBean = mData.get(position);
                    switch (orderListBean.getOrderType()) {
                        case 1://1：回购（对于经销商来说是出售）
                            getDistributorPayMethod();
                            break;
                        case 2://2：出售(对于经销商来说的购买)
                            String orderNo = orderListBean.getOtcPendingOrderNo();
                            String pendingRatio = orderListBean.getPendingRatio() + "";
                            String dealerName = orderListBean.getDealerName();
                            Intent sellIntent = new Intent(mContext, OutSideSoldActivity.class);
                            if (TextUtils.isEmpty(orderNo) || TextUtils.isEmpty(pendingRatio)) {
                                toast("挂单记录号或者比例为空");
                                return;
                            }
                            sellIntent.putExtra(Constants.INTENT_PARAMETER_1, orderNo);
                            sellIntent.putExtra(Constants.INTENT_PARAMETER_2, pendingRatio);
                            sellIntent.putExtra(Constants.INTENT_PARAMETER_3, dealerName);
                            CommonUtil.gotoActivity(mContext, sellIntent);
                            break;
                    }

            }
        });
    }

    private void getDistributorPayMethod() {
        if (orderListBean == null) {
            toast("订单信息为空请刷新重试");
            return;
        }
        DistributorPayMethodReq distributorPayMethodReq = new DistributorPayMethodReq();
        distributorPayMethodReq.setUserId(orderListBean.getUserId() + "");
        distributorPayMethodReq.setOtcPendingOrderNo(orderListBean.getOtcPendingOrderNo());
        presenter.getDistributorPayMethod(distributorPayMethodReq, DISTRIBUTOR_PAYMETHOD_TAG, true);
    }

    @Override
    protected void initData() {
        refresh.callRefresh();
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
            case DISTRIBUTOR_PAYMETHOD_TAG:
                DistributorPayMethodRes payMethodRes = (DistributorPayMethodRes) response;
                if (payMethodRes == null) return;
                String orderNo = orderListBean.getOtcPendingOrderNo();
                String pendingRatio = orderListBean.getPendingRatio() + "";
                String userId = orderListBean.getUserId() + "";
                Intent buyIntent = new Intent(mContext, OutSideBuyActivity.class);
                if (TextUtils.isEmpty(orderNo) || TextUtils.isEmpty(pendingRatio)) {
                    toast("挂单记录号或者比例为空");
                    return;
                }
                buyIntent.putExtra(Constants.INTENT_PARAMETER_1, orderNo);
                buyIntent.putExtra(Constants.INTENT_PARAMETER_2, pendingRatio);
                buyIntent.putExtra(Constants.INTENT_PARAMETER_3, payMethodRes);
                buyIntent.putExtra(Constants.INTENT_PARAMETER_4, userId);
                CommonUtil.gotoActivity(mContext, buyIntent);
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
