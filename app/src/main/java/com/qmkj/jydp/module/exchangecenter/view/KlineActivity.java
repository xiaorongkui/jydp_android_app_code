package com.qmkj.jydp.module.exchangecenter.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.ExchangeDealRecodeReq;
import com.qmkj.jydp.bean.request.KlineReq;
import com.qmkj.jydp.bean.response.DealRecodeRes;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.bean.response.KlineRes;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.DealRecodeRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.ui.widget.CustomWebView;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/30
 * email：dovexiaoen@163.com
 * description:k线图界面
 */

public class KlineActivity extends BaseMvpActivity<ExchangeCenterPresenter> {

    private static final int KLINE_DATA_TAG = 1;
    private static final int EXCHANGE_DEAL_RECODE_TAG = 2;
    @BindView(R.id.kline_tv_close)
    TextView mTvClose;
    //开，收，高，低，量，换，额，查，比
    @BindView(R.id.exchange_high_title_tv)
    TextView exchangeHighTitleTv;
    @BindView(R.id.kline_tv_max)
    TextView mTvMax;
    @BindView(R.id.exchange_lowest_title_tv)
    TextView exchangeLowestTitleTv;
    @BindView(R.id.kline_tv_min)
    TextView mTvMin;
    @BindView(R.id.exchange_buy_one_title_tv)
    TextView exchangeBuyOneTitleTv;
    @BindView(R.id.exchange_buy_one_price_tv)
    TextView exchangeBuyOnePriceTv;
    @BindView(R.id.exchange_sell_one_title_tv)
    TextView exchangeSellOneTitleTv;
    @BindView(R.id.exchange_sell_one_price_tv)
    TextView exchangeSellOnePriceTv;
    @BindView(R.id.kline_tv_num)
    TextView mTvNum;
    @BindView(R.id.exchange_deal_recode_rv)
    RecyclerView exchangeDealRecodeRv;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.kline_webview)
    FrameLayout klineWebview;

    private String currencyId;
    private String currencyName;
    private DealRecodeRecAdapter dealRecodeRecAdapter;
    List<DealRecodeRes.DealListBean> recodeDatas = new ArrayList<>();
    public String node = "1d";
    private CustomWebView mWebView;
    private ExchangeCenterRes.StandardParameterBean headerData;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
//        getKlineData(false);
        getExchangeDealRecode(false);
        mWebView.loadUrl(AppNetConfig.kline_url + currencyId);
    }

    private void getExchangeDealRecode(boolean b) {
        ExchangeDealRecodeReq exchangeDealRecodeReq = new ExchangeDealRecodeReq();
        exchangeDealRecodeReq.setCurrencyId(currencyId);
        presenter.getExchangeDealRecode(exchangeDealRecodeReq, EXCHANGE_DEAL_RECODE_TAG, b);
    }

    private void getKlineData(boolean b) {
        KlineReq klineReq = new KlineReq();
        klineReq.setCurrencyId(currencyId);
        klineReq.setNode(node);
        presenter.getKlineData(klineReq, KLINE_DATA_TAG, b);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(currencyName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.exchange_activity_k_line;
    }

    @Override
    protected void initView() {
        currencyId = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        currencyName = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        headerData = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_3);
        initRecycleView();
        initWebViw();
        refreshHeader(headerData);
    }

    private void refreshHeader(ExchangeCenterRes.StandardParameterBean standardParameter) {
        mTvClose.setText(NumberUtil.format2Point(standardParameter.getNowPrice()));
        mTvMax.setText(NumberUtil.format2Point(standardParameter.getTodayMax()));
        mTvMin.setText(NumberUtil.format2Point(standardParameter.getTodayMin()));
        exchangeBuyOnePriceTv.setText(NumberUtil.format2Point(standardParameter.getBuyOne()));
        exchangeSellOnePriceTv.setText(NumberUtil.format2Point(standardParameter.getSellOne()));
        mTvNum.setText(NumberUtil.format2Point(standardParameter.getDayTurnove()));
    }

    private void initWebViw() {
        //用XML引入，仍然会内存泄露https://stackoverflow.com/questions/3130654/memory-leak-in-webview
        mWebView = new CustomWebView(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        mWebView.setInitialScale(40);
        klineWebview.addView(mWebView);
    }

    private void initRecycleView() {
        dealRecodeRecAdapter = new DealRecodeRecAdapter(recodeDatas, R.layout
                .exchange_deal_recode_item);
        exchangeDealRecodeRv.setLayoutManager(new LinearLayoutManager(mContext));
        dealRecodeRecAdapter.setEmptyView(View.inflate(mContext, R.layout.empty, null));
        exchangeDealRecodeRv.setAdapter(dealRecodeRecAdapter);
    }


    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case KLINE_DATA_TAG:
                KlineRes klineRes = (KlineRes) response;

                break;
            case EXCHANGE_DEAL_RECODE_TAG:

                DealRecodeRes dealRecodeRes = (DealRecodeRes) response;
                if (dealRecodeRes == null || dealRecodeRes.getDealList().size() == 0) {
                    LogUtil.i("最近成交记录为空");
                    return;
                }
                recodeDatas.clear();
                recodeDatas.addAll(dealRecodeRes.getDealList());
                dealRecodeRecAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            // destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            try {
                mWebView.destroy();
            } catch (Throwable ex) {

            }
        }
    }
}
