package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.module.home.view.WebActivity;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:系统公告
 */

public class SystemNoticeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.refreshLayout)
    XRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SystemNoticeRecyAdapter adapter;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;
    int mPage;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.system_notice));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_system_notice;
    }

    @Override
    protected void initView() {
        adapter = new SystemNoticeRecyAdapter(mContext);
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
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0)
                                .getTop();
                mIsCanRefresh = topRowVerticalPosition >= 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Intent intent = WebActivity.getActivityIntent(mContext, "公告详情", AppNetConfig.SYSTEM_NOTICE_URL + adapter
                    .getData().get(position).getId());
            ActivityManager.gotoActivity(mContext, intent);
        });
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
        presenter.getSystemNoticeInfo(req, 1, false);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        SystemNoticeRes systemNoticeRes = (SystemNoticeRes) response;
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.refreshComplete();
        }
        if (mIsLoadMore) {
            adapter.addData(systemNoticeRes.getSystemNoticeList());
        } else {
            adapter.update(systemNoticeRes.getSystemNoticeList());
        }
        if (mPage < systemNoticeRes.getTotalPageNumber() - 1) {
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
