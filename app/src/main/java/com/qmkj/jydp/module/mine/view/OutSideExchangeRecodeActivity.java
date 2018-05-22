package com.qmkj.jydp.module.mine.view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.OtcDealRecordRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.OutSideExchangeRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:场外交易记录
 */

public class OutSideExchangeRecodeActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private ArrayList<OtcDealRecordRes.OtcTransactionUserDealListBean> mData;
    private OutSideExchangeRecodeRecyAdapter outSideExchangeRecodeRecyAdapter;
    private int type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        type =getIntent().getIntExtra(MineRecodeActivity.RECODE_TYPE,1);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        switch (type){
            case MineRecodeActivity.RECODE_TYPE_NORMAL:
                presenter.getOtcDealRecordInfo(req,1,true);
                break;
            case MineRecodeActivity.RECODE_TYPE_AGENCY:
                presenter.getDealOtcRecordInfo(req,1,true);
                break;

        }

    }


    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return outSideExchangeRecodeRecyAdapter;
    }


    private void initRecycleView() {
        mData = new ArrayList<>();
        outSideExchangeRecodeRecyAdapter = new OutSideExchangeRecodeRecyAdapter(mContext, mData, R.layout
                .mine_outside_exchange_recode_item);


        outSideExchangeRecodeRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.outside_exchange_recode_see_detail_tv:
                case R.id.outside_exchange_recode_comfirm_receivables_tv:
                    CommonUtil.gotoActivity(mContext, OutSideExchangeOrderDetailActivity.class);
                    break;
            }
        });
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        OtcDealRecordRes recordRes = (OtcDealRecordRes)response;
        if(recordRes.getOtcTransactionUserDealList()!=null){
            outSideExchangeRecodeRecyAdapter.addData(recordRes.getOtcTransactionUserDealList());
            outSideExchangeRecodeRecyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public String getTittle() {
        switch (type){
            case MineRecodeActivity.RECODE_TYPE_NORMAL:
                return CommonUtil.getString(R.string.outside_exchange_recode);
            case MineRecodeActivity.RECODE_TYPE_AGENCY:
                return CommonUtil.getString(R.string.outside_exchange_recode_agcy);
        }
        return CommonUtil.getString(R.string.outside_exchange_recode);
    }
}
