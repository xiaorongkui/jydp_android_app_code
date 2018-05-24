package com.qmkj.jydp.module.mine.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.OrderRecodeRecyAdapter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:挂单委托记录
 */

public class OrderRecodeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.refreshLayout)
    XRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private OrderRecodeRecyAdapter adapter;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;
    int mPage;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.exchange_entrust_recod));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_order_recode;
    }

    @Override
    protected void initView() {
        adapter = new OrderRecodeRecyAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        View mEmptyView = LayoutInflater.from(mContext).inflate(R.layout.empty, null);
        adapter.setEmptyView(mEmptyView);

        refreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsLoadMore = false;
                mPage = 0;
                getDataFromNet();
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return mIsCanRefresh;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mIsCanRefresh = topRowVerticalPosition >= 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        adapter.setOnLoadMoreListener(() -> {
            mIsLoadMore = true;
            getDataFromNet();
        }, recyclerView);
    }

    @Override
    protected void initData() {
        refreshLayout.callRefresh();
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(mPage);
        presenter.getTradeCenterInfo(req, 1, false);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        OrderRecodeRes recodeRes = (OrderRecodeRes) response;
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.refreshComplete();
        }
        if (mIsLoadMore) {
            adapter.addData(recodeRes.getTransactionPendOrderRecordList());
        } else {
            adapter.update(recodeRes.getTransactionPendOrderRecordList());
        }
        if (mPage < recodeRes.getTotalPageNumber() - 1) {
            adapter.loadMoreComplete();
            mPage++;
        } else {
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.refreshComplete();
        }
    }
}
