package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.OrderRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:挂单委托记录
 */

public class OrderRecodeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.mine_order_recode_rv)
    RecyclerView mineOrderRecodeRv;
    private ArrayList<OrderRecodeRes.TransactionPendOrderRecordListBean> mData;
    private OrderRecodeRecyAdapter orderRecodeRecyAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getTradeCenterInfo(req,1,true);
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
        orderRecodeRecyAdapter = new OrderRecodeRecyAdapter(mContext, mData, R.layout.mine_order_reocde_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineOrderRecodeRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        orderRecodeRecyAdapter.setEmptyView(mEmptyView);
        mineOrderRecodeRv.setAdapter(orderRecodeRecyAdapter);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        OrderRecodeRes recodeRes = (OrderRecodeRes) response;
        if(recodeRes!=null&&recodeRes.getTransactionPendOrderRecordList()!=null){
            orderRecodeRecyAdapter.addData(recodeRes.getTransactionPendOrderRecordList());
            orderRecodeRecyAdapter.notifyDataSetChanged();
        }
    }
}
