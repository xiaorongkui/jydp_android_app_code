package com.qmkj.jydp.module.mine.view;


import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.OrderRecodeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:挂单委托记录
 */

public class OrderRecodeActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
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
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return orderRecodeRecyAdapter;
    }
    private void initRecycleView() {
        mData = new ArrayList<>();
        orderRecodeRecyAdapter = new OrderRecodeRecyAdapter(mContext, mData, R.layout.mine_order_reocde_item);
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

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.exchange_entrust_recod);
    }
}
