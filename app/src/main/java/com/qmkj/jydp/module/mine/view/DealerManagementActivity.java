package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.DeleteDealerReq;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.DealerManagementRes;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.module.mine.presenter.DealerManagementRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:经销商管理
 */
public class DealerManagementActivity extends BaseMvpActivity<MinePresenter> {
    private static final int ACTIVITY_REQUEST_CODE = 100;  //打开发起广告界面请求码
    private static final int REQUEST_GET_DATA = 1; //请求数据
    private static final int REQUEST_DELETE_INFO = 2;//删除
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.refreshLayout)
    XRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.publish_adversite_btn) //发起广告
            Button publishAdversiteBtn;

    private DealerManagementRecyAdapter adapter;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;
    int mPage;
    private int delete_position = -1;
    private CommonDialog commonDialog;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        refreshLayout.callRefresh();
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.dealer_managment));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_dealer_management;
    }

    @Override
    protected void initView() {
        adapter = new DealerManagementRecyAdapter(mContext);
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
        adapter.setOnLoadMoreListener(() -> {
            mIsLoadMore = true;
            getDataFromNet();
        }, recyclerView);
        publishAdversiteBtn.setOnClickListener(v -> ActivityManager.startActivityForResult(
                DealerManagementActivity.this, PublishAdvertisementActivity.class, ACTIVITY_REQUEST_CODE));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showDeleteDialog(position);
            }
        });

    }

    /**
     * 获取网络数据
     */
    private void getDataFromNet() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(mPage);
        presenter.getDealerManagementInfo(req, REQUEST_GET_DATA, false);
    }

    /**
     * 显示删除dialog
     */
    private void showDeleteDialog(final int position) {
        commonDialog = new CommonDialog(this);
        commonDialog.setContentText("是否确认删除");
        commonDialog.setOnPositiveButtonClickListener((dialog, view) -> {
            DeleteDealerReq req = new DeleteDealerReq();
            req.setOtcPendingOrderNo(adapter.getData().get(position).getOtcPendingOrderNo() + "");
            presenter.deleteDealerManagementInfo(req, REQUEST_DELETE_INFO, true);
            delete_position = position;
        });
        commonDialog.show();
    }


    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        if (tag == REQUEST_GET_DATA) {
            DealerManagementRes res = (DealerManagementRes) response;
            if (refreshLayout != null && refreshLayout.isRefreshing()) {
                refreshLayout.refreshComplete();
            }
            if (mIsLoadMore) {
                adapter.addData(res.getOtcTransactionPendOrderList());
            } else {
                adapter.update(res.getOtcTransactionPendOrderList());
            }
            if (mPage < res.getTotalPageNumber() - 1) {
                adapter.loadMoreComplete();
                mPage++;
            } else {
                adapter.loadMoreEnd();
            }
        } else if (tag == REQUEST_DELETE_INFO) {
            if (delete_position >= 0) {
                adapter.remove(delete_position);
                adapter.notifyDataSetChanged();
                commonDialog.dismiss();
            }
        }

    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.refreshComplete();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (commonDialog != null && commonDialog.isShowing()) {
            commonDialog.dismiss();
            commonDialog = null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == PublishAdvertisementActivity.ACTIVITY_RESULT_CODE) {
            refreshLayout.callRefresh();
        }
    }
}
