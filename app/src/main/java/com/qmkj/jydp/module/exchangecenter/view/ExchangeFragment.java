package com.qmkj.jydp.module.exchangecenter.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.event.ExchangeEvent;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.response.CancleOrderReq;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.bean.response.ExchangeEntrustRecodeRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.EntrustRecodeRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeSoldPriceRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangebuyPriceRecAdapter;
import com.qmkj.jydp.ui.widget.MyViewPager;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.ui.widget.dialog.base.BaseDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.RxBus;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description: 交易中心页面
 */

public class ExchangeFragment extends BaseMvpFragment<ExchangeCenterPresenter> implements View.OnClickListener {

    private static final int EXCHANGE_TYPE_BUY = 1;
    private static final int EXCHANGE_TYPE_SOLD = 2;
    private static final int EXCHANGE_TYPE_RECODE = 3;
    private static final int EXCHANGE_CENTER_DATA_TAG = 1;
    private static final int EXCHANGE_CANCLE_ORDER_TAG = 2;
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
    NestedScrollView exchangeSl;
    @BindView(R.id.exchange_continer_vp)
    MyViewPager exchangeContinerVp;
    @BindView(R.id.buy_title)
    TextView buyTitle;
    @BindView(R.id.buy_bottom_line)
    View buyBottomLine;
    @BindView(R.id.buy_ll)
    LinearLayout buyLl;
    @BindView(R.id.sold_title)
    TextView soldTitle;
    @BindView(R.id.sold_bottom_line)
    View soldBottomLine;
    @BindView(R.id.sold_ll)
    LinearLayout soldLl;
    @BindView(R.id.buy_or_sell_header_ll)
    LinearLayout buy0rSellHeaderLl;
    @BindView(R.id.exchange_recode_header_ll)
    LinearLayout exchangeRecodeHeaderLl;
    Unbinder unbinder;
    @BindView(R.id.exchange_entrust_recode_rv)
    RecyclerView entrustRecodeRv;
    @BindView(R.id.exchange_center_kline_iv)
    ImageView exchange_center_kline_iv;
    @BindView(R.id.exchange_title_tv)
    TextView exchangeTitleTv;
    @BindView(R.id.exchange_current_price_tv)
    TextView exchangeCurrentPriceTv;
    @BindView(R.id.exchange_high_price_tv)
    TextView exchangeHighPriceTv;
    @BindView(R.id.exchange_lowest_price_tv)
    TextView exchangeLowestPriceTv;
    @BindView(R.id.exchange_buy_one_price_tv)
    TextView exchangeBuyOnePriceTv;
    @BindView(R.id.exchange_sell_one_price_tv)
    TextView exchangeSellOnePriceTv;
    @BindView(R.id.exchange_exchange_day_volume_tv)
    TextView exchangeExchangeDayVolumeTv;
    @BindView(R.id.exchange_current_price_xt_tv)
    TextView exchangeCurrentPriceXtTv;


    private ExchangebuyPriceRecAdapter priceBuyRecAdapter;
    private ExchangeSoldPriceRecAdapter priceSoldRecAdapter;
    private String currencyId;
    private String currencyName;
    private EntrustRecodeRecAdapter entrustRecodeRecAdapter;
    private MyPagerAdapter pagerAdapter;
    private Disposable subscribe;
    private Disposable disposable;
    private CommonDialog commonCancleDialog;


    public String getCurrencyId() {
        return currencyId;
    }

    @Override
    protected void initView() {
        initStatusBar();
        initRecycleView();
        initListener();
        initViewPager();
        setViewpagerIndicotr(0);

        StateListDrawable stateListDrawable = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x2))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_1))
                .create();
        buy0rSellHeaderLl.setBackground(stateListDrawable);
        exchangeRecodeHeaderLl.setBackground(stateListDrawable);
        Bundle arguments = getArguments();
        if (arguments != null) {
            currencyName = arguments.getString(Constants.INTENT_PARAMETER_1);
            currencyId = arguments.getString(Constants.INTENT_PARAMETER_2);
        }
        subscribe = RxBus.getDefault().toObservable(ExchangeEvent.class).subscribe(exchangeEvent -> {
            getExchangeCenterData(true);
        });
        exchangeTitleTv.setText(currencyName);
    }

    private void initViewPager() {
        exchangeContinerVp.setOffscreenPageLimit(1);
        CommonUtil.setScrollerTime(mContext, 0, exchangeContinerVp);
        pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(ExchangeBuyFragment.newInstance(EXCHANGE_TYPE_BUY));
        pagerAdapter.addFragment(ExchangeSoldFragment.newInstance(EXCHANGE_TYPE_SOLD));
        exchangeContinerVp.setAdapter(pagerAdapter);
        exchangeContinerVp.setScanScroll(false);
        exchangeContinerVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setViewpagerIndicotr(position);
            }
        });
    }

    private void initListener() {
        currencySelectIv.setOnClickListener(this);
        buyLl.setOnClickListener(this);
        soldLl.setOnClickListener(this);
        exchange_center_kline_iv.setOnClickListener(this);
    }

    List<ExchangeCenterRes.TransactionPendOrderBuyListBean> dataBuys = new ArrayList<>();
    List<ExchangeCenterRes.TransactionPendOrderSellListBean> dataSolds = new ArrayList<>();
    //委托记录
    List<ExchangeCenterRes.TransactionPendOrderListBean> entrustRecodeDatas = new ArrayList<>();


    private void initRecycleView() {
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

        entrustRecodeRecAdapter = new EntrustRecodeRecAdapter(mContext, entrustRecodeDatas, R.layout
                .exchange_entrust_recode_item);

        entrustRecodeRv.setLayoutManager(new LinearLayoutManager(mContext));
        entrustRecodeRv.setAdapter(entrustRecodeRecAdapter);
        entrustRecodeRecAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.entrust_cancel_tv:
                    showCancleDialog(position);//撤销
                    break;
            }
        });

    }

    private void showCancleDialog(int position) {
        commonCancleDialog = new CommonDialog(mContext);
        commonCancleDialog.setContentText("是否撤销此单?");
        commonCancleDialog.setOnPositiveButtonClickListener((dialog, view) -> {
            CancleOrderReq cancleOrderReq = new CancleOrderReq();
            cancleOrderReq.setPendingOrderNo(entrustRecodeDatas.get(position).getPendingOrderNo());
            presenter.cancleOrder(cancleOrderReq, EXCHANGE_CANCLE_ORDER_TAG, true);
        });
        commonCancleDialog.setOnNegativeButtonClickListener((dialog, view) -> commonCancleDialog.dismiss());
        commonCancleDialog.show();
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
        getExchangeCenterData(true);
        initCountTimer();
    }

    private void initCountTimer() {
        disposable = Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> getExchangeCenterData(false));
    }

    private void getExchangeCenterData(boolean isShowProgress) {
        if (TextUtils.isEmpty(currencyId)) {
            LogUtil.i("currencyId is null");
            return;
        }
        ExchangeCenterReq exchangeCenterReq = new ExchangeCenterReq();
        exchangeCenterReq.setCurrencyId(currencyId);
        presenter.getExchangeCenterData(exchangeCenterReq, EXCHANGE_CENTER_DATA_TAG, isShowProgress);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            currencyName = arguments.getString(Constants.INTENT_PARAMETER_1);
            currencyId = arguments.getString(Constants.INTENT_PARAMETER_2);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.exchange_fragment_exchange;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case EXCHANGE_CENTER_DATA_TAG:
                ExchangeCenterRes centerRes = (ExchangeCenterRes) response;
                if (centerRes == null) {
                    LogUtil.i("交易中心数据返回为空");
                    return;
                }
                ExchangeCenterRes.StandardParameterBean standardParameter = centerRes.getStandardParameter();
                if (standardParameter != null) {
                    refreshHeader(standardParameter);
                }
                refreshBuyFivePrice(centerRes.getTransactionPendOrderBuyList());
                refreshSoldFivePrice(centerRes.getTransactionPendOrderSellList());

                refreshBuyAccountInfo(centerRes);
                refreshSellAccountInfo(centerRes);

                refreshEntrustRecod(centerRes.getTransactionPendOrderList());
                break;
            case EXCHANGE_CANCLE_ORDER_TAG:
                if (commonCancleDialog != null && commonCancleDialog.isShowing()) commonCancleDialog.dismiss();
                toast("撤单成功");
                getExchangeCenterData(false);
                break;
        }
    }


    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
    }

    @Override
    public void onResume() {
        super.onResume();
//        getExchangeCenterData(true);
        LogUtil.i("交易中心 onResume");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initCountTimer();
        } else {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            currencyName = arguments.getString(Constants.INTENT_PARAMETER_1);
            currencyId = arguments.getString(Constants.INTENT_PARAMETER_2);
        }
        exchangeTitleTv.setText(currencyName);
        LogUtil.i("交易中心 onHiddenChanged");

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
            case R.id.buy_ll:
                setViewpagerIndicotr(0);
                break;
            case R.id.sold_ll:
                setViewpagerIndicotr(1);
                break;
            case R.id.exchange_center_kline_iv:
                Intent intent = new Intent(mContext, KlineActivity.class);
                intent.putExtra(Constants.INTENT_PARAMETER_1, currencyId);
                intent.putExtra(Constants.INTENT_PARAMETER_2, currencyName);
                CommonUtil.gotoActivity(mContext, intent);
                break;
        }
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final FragmentManager fm;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public Fragment instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fm.beginTransaction().show(fragment).commit();
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Fragment fragment = mFragments.get(position);
            fm.beginTransaction().hide(fragment).commit();
        }
    }

    private void setViewpagerIndicotr(int index) {
        buyTitle.setTextColor(CommonUtil.getColor(R.color.color_red_5));
        soldTitle.setTextColor(CommonUtil.getColor(R.color.color_green_4));
        buyBottomLine.setVisibility(View.INVISIBLE);
        soldBottomLine.setVisibility(View.INVISIBLE);
        CommonUtil.hideKeyBoard(mContext);
        switch (index) {
            case 0:
                buyTitle.setTextColor(CommonUtil.getColor(R.color.color_red_5));
                buyBottomLine.setVisibility(View.VISIBLE);
                exchangeContinerVp.setCurrentItem(0);
                break;
            case 1:
                soldTitle.setTextColor(CommonUtil.getColor(R.color.color_green_4));
                soldBottomLine.setVisibility(View.VISIBLE);
                exchangeContinerVp.setCurrentItem(1);
                break;
        }
    }


    private void refreshEntrustRecod(List<ExchangeCenterRes.TransactionPendOrderListBean>
                                             transactionPendOrderList) {
        entrustRecodeDatas.clear();
        if (transactionPendOrderList == null) return;
        entrustRecodeDatas.addAll(transactionPendOrderList);
        entrustRecodeRecAdapter.notifyDataSetChanged();
    }

    private void refreshSoldFivePrice(List<ExchangeCenterRes.TransactionPendOrderSellListBean>
                                              transactionPendOrderSellList) {
        dataSolds.clear();
        if (transactionPendOrderSellList != null && transactionPendOrderSellList.size() > 0) {
            int size = transactionPendOrderSellList.size();
            if (size < 5) {
                for (int i = 0; i < (5 - size); i++) {
                    dataSolds.add(new ExchangeCenterRes.TransactionPendOrderSellListBean("--", "--"));
                }
                dataSolds.addAll(transactionPendOrderSellList);
            } else if (size == 5) {
                dataSolds.addAll(transactionPendOrderSellList);
            } else {
                dataSolds.addAll(transactionPendOrderSellList.subList(0, 5));
            }

        } else {
            for (int i = 0; i < 5; i++) {
                dataSolds.add(new ExchangeCenterRes.TransactionPendOrderSellListBean("--", "--"));
            }
        }
        priceSoldRecAdapter.notifyDataSetChanged();
    }

    private void refreshBuyFivePrice(List<ExchangeCenterRes.TransactionPendOrderBuyListBean>
                                             transactionPendOrderBuyList) {
        dataBuys.clear();
        if (transactionPendOrderBuyList != null && transactionPendOrderBuyList.size() > 0) {
            int size = transactionPendOrderBuyList.size();
            if (size < 5) {
                for (int i = 0; i < (5 - size); i++) {
                    dataBuys.add(new ExchangeCenterRes.TransactionPendOrderBuyListBean("--", "--"));
                }
                dataBuys.addAll(transactionPendOrderBuyList);
            } else if (size == 5) {
                dataBuys.addAll(transactionPendOrderBuyList);
            } else {
                dataBuys.addAll(transactionPendOrderBuyList.subList(0, 5));
            }
        } else {
            for (int i = 0; i < 5; i++) {
                dataBuys.add(new ExchangeCenterRes.TransactionPendOrderBuyListBean("--", "--"));
            }
        }
        priceBuyRecAdapter.notifyDataSetChanged();
    }

    private void refreshHeader(ExchangeCenterRes.StandardParameterBean standardParameter) {
        exchangeCurrentPriceTv.setText(NumberUtil.format2Point(standardParameter.getNowPrice()));
        exchangeHighPriceTv.setText(NumberUtil.format2Point(standardParameter.getTodayMax()));
        exchangeLowestPriceTv.setText(NumberUtil.format2Point(standardParameter.getTodayMin()));
        exchangeBuyOnePriceTv.setText(NumberUtil.format2Point(standardParameter.getBuyOne()));
        exchangeSellOnePriceTv.setText(NumberUtil.format2Point(standardParameter.getSellOne()));
        exchangeExchangeDayVolumeTv.setText(NumberUtil.format2Point(standardParameter.getDayTurnove()));
        exchangeCurrentPriceXtTv.setText(NumberUtil.format2Point(standardParameter.getNowPrice()));
    }

    private void refreshSellAccountInfo(ExchangeCenterRes centerRes) {
        ExchangeBuyFragment item = (ExchangeBuyFragment) pagerAdapter.getItem(0);
        item.refreshData(centerRes);
    }

    private void refreshBuyAccountInfo(ExchangeCenterRes centerRes) {
        ExchangeSoldFragment item = (ExchangeSoldFragment) pagerAdapter.getItem(1);
        item.refreshData(centerRes);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (commonCancleDialog != null && commonCancleDialog.isShowing()) commonCancleDialog.dismiss();
    }
}
