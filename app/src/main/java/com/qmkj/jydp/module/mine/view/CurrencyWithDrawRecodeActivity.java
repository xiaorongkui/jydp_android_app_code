package com.qmkj.jydp.module.mine.view;


import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.module.mine.presenter.CurrencyWithDrawRecodeRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:币种提现记录
 */

public class CurrencyWithDrawRecodeActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private ArrayList<PresentRecordRes.CoinOutRecordListBean> mData;
    private CurrencyWithDrawRecodeRecyAdapter currencyWithDrawRecodeRecyAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getPresentRecordInfo(req,1,true);
    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return currencyWithDrawRecodeRecyAdapter;
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        currencyWithDrawRecodeRecyAdapter = new CurrencyWithDrawRecodeRecyAdapter(mContext, mData, R.layout
                .mine_currency_withdraw_reocde_item);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        PresentRecordRes recordRes = (PresentRecordRes)response;
        if(recordRes.getCoinOutRecordList()!=null){
            currencyWithDrawRecodeRecyAdapter.addData(recordRes.getCoinOutRecordList());
            currencyWithDrawRecodeRecyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.currency_withdraw_recode);
    }
}
