package com.qmkj.jydp.module.mine.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.module.mine.presenter.CurrencyWithDrawRecodeRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;


/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:币种提现记录
 */
public class CurrencyWithDrawRecodeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.refreshLayout)
    XRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CurrencyWithDrawRecodeRecyAdapter adapter;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;
    int mPage;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
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
        adapter = new CurrencyWithDrawRecodeRecyAdapter(mContext);
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

    private void getDataFromNet() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(mPage);
        presenter.getPresentRecordInfo(req, 1, false);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        PresentRecordRes recordRes = (PresentRecordRes)response;
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.refreshComplete();
        }
        if (mIsLoadMore) {
            adapter.addData(recordRes.getCoinOutRecordList());
        } else {
            adapter.update(recordRes.getCoinOutRecordList());
        }
        if (mPage < recordRes.getTotalPageNumber() - 1) {
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
