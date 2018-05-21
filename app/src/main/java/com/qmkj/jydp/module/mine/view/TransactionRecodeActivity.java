package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.AccountRecordRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.TransactionRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:成交记录
 */

public class TransactionRecodeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.mine_transaction_recode_rv)
    RecyclerView mineTransactionRecodeRv;
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
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.transaction_recode));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_transaction_recode;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        transactionRecodeRecyAdapter = new TransactionRecodeRecyAdapter(mContext, mData, R.layout
                .mine_transaction_reocde_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineTransactionRecodeRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        transactionRecodeRecyAdapter.setEmptyView(mEmptyView);
        mineTransactionRecodeRv.setAdapter(transactionRecodeRecyAdapter);
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
}
