package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemNoticeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.system_notice_rl)
    XRefreshLayout mRefreshLayout;
    @BindView(R.id.system_notice_rv)
    RecyclerView systemNoticeRv;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    private SystemNoticeRecyAdapter systemNoticeAdapter;
    private int mPage;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.system_notice));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_system_notice;
    }

    @Override
    protected void initView() {
        initRecycleView();
        initListener();
    }

    @Override
    protected void initData() {
        mRefreshLayout.callRefresh();
    }

    private void initRecycleView() {
        systemNoticeAdapter = new SystemNoticeRecyAdapter(mContext);
        systemNoticeRv.setLayoutManager(new LinearLayoutManager(mContext));
        systemNoticeRv.setAdapter(systemNoticeAdapter);
        systemNoticeAdapter.setEmptyView(R.layout.empty, systemNoticeRv);
    }

    private void initListener() {
        mRefreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsLoadMore = false;
                mPage = 0;
                getDateFromNet();
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return mIsCanRefresh;
            }
        });

        systemNoticeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        systemNoticeAdapter.setOnLoadMoreListener(() -> {
            mIsLoadMore = true;
            getDateFromNet();
        }, systemNoticeRv);
    }


    /**
     * 从网络获取数据
     */
    private void getDateFromNet() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(mPage);
        presenter.getSystemNoticeInfo(req, 1, false);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        SystemNoticeRes systemNoticeRes = (SystemNoticeRes) response;
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.refreshComplete();
        }
        if (mIsLoadMore) {
            systemNoticeAdapter.addData(systemNoticeRes.getSystemNoticeList());
        } else {
            systemNoticeAdapter.update(systemNoticeRes.getSystemNoticeList());
        }
        if (mPage < systemNoticeRes.getTotalPageNumber() - 1) {
            systemNoticeAdapter.loadMoreComplete();
            mPage++;
        } else {
            systemNoticeAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.refreshComplete();
        }
    }
}
