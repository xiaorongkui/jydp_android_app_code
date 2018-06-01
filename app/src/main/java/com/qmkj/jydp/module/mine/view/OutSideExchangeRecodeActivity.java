package com.qmkj.jydp.module.mine.view;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.OutSideDetailReq;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.OtcDealRecordRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.OutSideExchangeRecodeRecyAdapter;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.ui.widget.dialog.base.BaseDialog;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:场外交易记录
 */

public class OutSideExchangeRecodeActivity extends BaseMvpActivity<MinePresenter> {
    private static final int SEND_REQUEST = 2;
    private static final int GET_DATA = 1;
    private static final int NEXT_ACTIVITY_CODE= 100;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.dealer_management_refresh)
    XRefreshLayout refreshLayout;
    @BindView(R.id.system_hot_rv)
    RecyclerView recyclerView;

    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;
    int mPage;

    private ArrayList<OtcDealRecordRes.OtcTransactionUserDealListBean> mData;
    private OutSideExchangeRecodeRecyAdapter outSideExchangeRecodeRecyAdapter;
    private int type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        type =getIntent().getIntExtra(MineRecodeActivity.RECODE_TYPE,1);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initTitle() {
        switch (type){
            case MineRecodeActivity.RECODE_TYPE_NORMAL:
                titleHeaderTv.setText(CommonUtil.getString(R.string.outside_exchange_recode));
                break;
            case MineRecodeActivity.RECODE_TYPE_AGENCY:
                titleHeaderTv.setText(CommonUtil.getString(R.string.outside_exchange_recode_agcy));
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_base_hot_topic;
    }

    @Override
    protected void initView() {
        initRecycleView();
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
        switch (type){
            case MineRecodeActivity.RECODE_TYPE_NORMAL:
                presenter.getOtcDealRecordInfo(req,GET_DATA,false);
                break;
            case MineRecodeActivity.RECODE_TYPE_AGENCY:
                presenter.getDealOtcRecordInfo(req,GET_DATA,false);
                break;

        }
    }


    private void initRecycleView() {
        mData = new ArrayList<>();
        outSideExchangeRecodeRecyAdapter = new OutSideExchangeRecodeRecyAdapter(mContext,type);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(outSideExchangeRecodeRecyAdapter);
        View mEmptyView = LayoutInflater.from(mContext).inflate(R.layout.empty, null);
        outSideExchangeRecodeRecyAdapter.setEmptyView(mEmptyView);


        outSideExchangeRecodeRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mContext, OutSideExchangeOrderDetailActivity.class);
            intent.putExtra("NUMBER",outSideExchangeRecodeRecyAdapter.getItem(position).getOtcOrderNo());
            intent.putExtra(MineRecodeActivity.RECODE_TYPE,type);
            switch (view.getId()) {
                case R.id.outside_exchange_recode_see_detail_tv: //查看详情
                    CommonUtil.startActivityForResult(mContext,intent,NEXT_ACTIVITY_CODE);

                    break;
                case R.id.outside_exchange_recode_comfirm_receivables_tv: //确认收款
                    CommonDialog commonDialog = new CommonDialog(this);
                    commonDialog.setTitleText("确认收款");
                    commonDialog.setContentText("确认已收到货款？");
                    commonDialog.setOnPositiveButtonClickListener(new BaseDialog.OnPositiveButtonClickListener() {
                        @Override
                        public void onClick(Dialog dialog, View view) {
                            OutSideDetailReq req =new OutSideDetailReq();
                            req.setOtcOrderNo(outSideExchangeRecodeRecyAdapter.getItem(position).getOtcOrderNo());
                            if(CommonUtil.getLoginInfo()!=null&&CommonUtil.getLoginInfo().getUser().getIsDealer()==2){   //=2 为经销商
                                presenter.getOutSideOrderTakeMoney(req,SEND_REQUEST,true);
                            }else {
                                presenter.getOutSideOrderTakeUser(req,SEND_REQUEST,true);
                            }
                            commonDialog.dismiss();
                        }
                    });
                    commonDialog.show();
                    break;
            }
        });


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

        outSideExchangeRecodeRecyAdapter.setOnLoadMoreListener(() -> {
            mIsLoadMore = true;
            getDataFromNet();
        }, recyclerView);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag){
            case SEND_REQUEST:
                refreshLayout.callRefresh();
                break;
            case GET_DATA:
                OtcDealRecordRes recordRes = (OtcDealRecordRes)response;
                if(recordRes.getOtcTransactionUserDealList()!=null){

                    outSideExchangeRecodeRecyAdapter.notifyDataSetChanged();
                }

                if (refreshLayout != null && refreshLayout.isRefreshing()) {
                    refreshLayout.refreshComplete();
                }
                if (mIsLoadMore) {
                    outSideExchangeRecodeRecyAdapter.addData(recordRes.getOtcTransactionUserDealList());
                } else {
                    outSideExchangeRecodeRecyAdapter.update(recordRes.getOtcTransactionUserDealList());
                }
                if (mPage < recordRes.getTotalPageNumber() - 1) {
                    outSideExchangeRecodeRecyAdapter.loadMoreComplete();
                    mPage++;
                } else {
                    outSideExchangeRecodeRecyAdapter.loadMoreEnd();
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEXT_ACTIVITY_CODE&&requestCode==200){
            refreshLayout.callRefresh();
        }
    }
}
