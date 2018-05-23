package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.AccountRecordRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.TransactionRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:成交记录
 */

public class TransactionRecodeActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private ArrayList<AccountRecordRes.DealRecordListBean> mData;
    private TransactionRecodeRecyAdapter transactionRecodeRecyAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getAccountRecordInfo(req,1,true);
    }
    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return transactionRecodeRecyAdapter;
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        transactionRecodeRecyAdapter = new TransactionRecodeRecyAdapter(mContext, mData, R.layout
                .mine_transaction_reocde_item);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        AccountRecordRes recordRes = (AccountRecordRes)response;
        if(recordRes.getDealRecordList()!=null){
            transactionRecodeRecyAdapter.addData(recordRes.getDealRecordList());
            transactionRecodeRecyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public List getData() {
        return null;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.transaction_recode);
    }
}
