package com.qmkj.jydp.module.exchange.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.module.exchange.presenter.ExchangeSoldPriceRecAdapter;
import com.qmkj.jydp.module.exchange.presenter.ExchangebuyPriceRecAdapter;
import com.qmkj.jydp.rxbus.RxBus;
import com.qmkj.jydp.rxbus.event.DrawEvent;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description: 交易中心页面
 */

public class ExchangeFragment extends MvpBaseFragment implements View.OnClickListener {

    @BindView(R.id.exchange_price_recycle_buy)
    RecyclerView exchangePriceRecycleBuy;
    @BindView(R.id.exchange_price_recycle_sold)
    RecyclerView exchangePriceRecycleSold;
    @BindView(R.id.exchange_header_ll)
    LinearLayout exchangeHeaderLl;
    @BindView(R.id.exchange_ll)
    LinearLayout exchangeLl;
    @BindView(R.id.currency_select_iv)
    ImageView currencySelectIv;
    @BindView(R.id.exchange_sl)
    ScrollView exchangeSl;


    private ExchangebuyPriceRecAdapter priceBuyRecAdapter;
    private ExchangeSoldPriceRecAdapter priceSoldRecAdapter;
    private boolean isShowDrawLayout = false;

    @Override
    protected void initView() {
        initStatusBar();
        initRecycleView();
        initListener();
    }

    private void initListener() {
        currencySelectIv.setOnClickListener(this);
    }

    private void initRecycleView() {
        List<String> dataBuys = new ArrayList();
        List<String> dataSolds = new ArrayList();
        for (int i = 0; i < 5; i++) {
            dataBuys.add("");
            dataSolds.add("");
        }
        priceBuyRecAdapter = new ExchangebuyPriceRecAdapter(mContext, dataBuys, R.layout.exchange_price_item);
        priceSoldRecAdapter = new ExchangeSoldPriceRecAdapter(mContext, dataSolds, R.layout.exchange_price_item);

        LinearLayoutManager mBuyLayoutManager = new LinearLayoutManager(mContext);
        mBuyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager mSoldLayoutManager = new LinearLayoutManager(mContext);
        mSoldLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        exchangePriceRecycleBuy.setLayoutManager(mBuyLayoutManager);
        exchangePriceRecycleSold.setLayoutManager(mSoldLayoutManager);
        exchangePriceRecycleBuy.setAdapter(priceBuyRecAdapter);
        exchangePriceRecycleSold.setAdapter(priceSoldRecAdapter);
    }

    private void initStatusBar() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            exchangeLl.addView(statusView, 0, lp);
        }
    }


    @Override
    protected void initData() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_exchange;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    protected void injectPresenter() {
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);

    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.currency_select_iv://币种选择
                try {
                    ((MainActivity) getActivity()).showDrawerLayout(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.i("点击选择币种异常=" + e.getMessage());
                }
                break;
        }
    }

    public void updateCurrencySelect(int i) {
        LogUtil.i("选择了币种=" + i);
    }
}
