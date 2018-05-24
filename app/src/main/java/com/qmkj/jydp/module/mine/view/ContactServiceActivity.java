package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.request.SendContactServiceReq;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.module.mine.presenter.ContactServiceRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.dialog.SubmitQuestionDialog;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.StringUtil;

import butterknife.BindView;


/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:联系客服
 */
public class ContactServiceActivity extends BaseMvpActivity<MinePresenter> {
    private static final int CONTACTS_GET_MSG = 1;
    private static final int CONTACTS_SEND_MSG = 2;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.refreshLayout)
    XRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.submit_question_btn)
    Button submitQuestionBtn;

    private ContactServiceRecyAdapter adapter;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;
    int mPage;

    private SubmitQuestionDialog submitQuestionDialog;

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.connect_service));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_contact_service;
    }

    @Override
    protected void initView() {
        adapter = new ContactServiceRecyAdapter(mContext);
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
        submitQuestionBtn.setOnClickListener(v -> {
            showConformDialog();
        });
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
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
        presenter.getCustomerServiceInfo(req, 1, false);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case CONTACTS_GET_MSG:
                CustomerServiceRes customerServiceRes = (CustomerServiceRes) response;
                if (refreshLayout != null && refreshLayout.isRefreshing()) {
                    refreshLayout.refreshComplete();
                }
                if (mIsLoadMore) {
                    adapter.addData(customerServiceRes.getUserFeedbackList());
                } else {
                    adapter.update(customerServiceRes.getUserFeedbackList());
                }
                if (mPage < customerServiceRes.getTotalPageNumber() - 1) {
                    adapter.loadMoreComplete();
                    mPage++;
                } else {
                    adapter.loadMoreEnd();
                }
                break;
            case CONTACTS_SEND_MSG:
                toast("提交成功");
                refreshLayout.callRefresh();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.refreshComplete();
        }
    }


    private void showConformDialog() {
        submitQuestionDialog = new SubmitQuestionDialog(mContext);
        submitQuestionDialog.setOnSubmitQuestionListener((questionTitle, questionContent) -> {
            if (StringUtil.isNull(questionTitle)) {
                toast("标题不能为空");
            }
            if (StringUtil.isNull(questionContent)) {
                toast("内容不能为空");
            }
            SendContactServiceReq req = new SendContactServiceReq();
            req.setFeedbackTitle(questionTitle);
            req.setFeedbackContent(questionContent);
            presenter.sendCustomerServiceInfo(req, CONTACTS_SEND_MSG, true);
            submitQuestionDialog.dismiss();
        });
        submitQuestionDialog.show();
    }
}
