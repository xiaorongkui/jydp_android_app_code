package com.qmkj.jydp.module.mine.view;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.module.mine.presenter.CurrencyAssetsRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:币种资产页面
 */

public class CurrencyAssetsActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private List<CurrencyAssetsRes.UserCurrencyAssetsBean> mData;
    private CurrencyAssetsRecyAdapter assetsRecyAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        presenter.getCurrencyAssetsInfo(1,true);
    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return assetsRecyAdapter;
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        assetsRecyAdapter = new CurrencyAssetsRecyAdapter(mContext, mData, R.layout.mine_currency_assets_item);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        CurrencyAssetsRes res =(CurrencyAssetsRes)response;
        if(res.getUserCurrencyAssets()!=null){
            assetsRecyAdapter.addData(res.getUserCurrencyAssets());
            assetsRecyAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public List getData() {
        return mData;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.currency_assets);
    }
}
