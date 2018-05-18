package com.qmkj.jydp.module.exchangecenter.view;

import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.request.ExchangeCenterReq;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.EntrustRecodeRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeSoldPriceRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangebuyPriceRecAdapter;
import com.qmkj.jydp.ui.widget.MyViewPager;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.SelectorFactory;

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

public class ExchangeFragment extends BaseMvpFragment<ExchangeCenterPresenter> implements View.OnClickListener {

    private static final int EXCHANGE_TYPE_BUY = 1;
    private static final int EXCHANGE_TYPE_SOLD = 2;
    private static final int EXCHANGE_TYPE_RECODE = 3;
    private static final int EXCHANGE_CENTER_DATA_TAG = 1;
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
    Unbinder unbinder1;
    @BindView(R.id.exchange_exchange_day_volume_tv)
    TextView exchangeExchangeDayVolumeTv;


    private ExchangebuyPriceRecAdapter priceBuyRecAdapter;
    private ExchangeSoldPriceRecAdapter priceSoldRecAdapter;
    private boolean isShowDrawLayout = false;
    private String currencyId;
    private String currencyName;

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
    }

    private void initViewPager() {
        exchangeContinerVp.setOffscreenPageLimit(1);
        CommonUtil.setScrollerTime(mContext, 0, exchangeContinerVp);
        final MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(ExchangeBuyFragment.newInstance(EXCHANGE_TYPE_BUY));
        adapter.addFragment(ExchangeSoldFragment.newInstance(EXCHANGE_TYPE_SOLD));
        exchangeContinerVp.setAdapter(adapter);
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

    private void initRecycleView() {
        List<String> dataBuys = new ArrayList<>();
        List<String> dataSolds = new ArrayList<>();
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

        //委托记录
        List datas = new ArrayList();
        for (int i = 0; i < 5; i++) {
            datas.add("");

        }

        EntrustRecodeRecAdapter entrustRecodeRecAdapter = new EntrustRecodeRecAdapter(mContext, datas, R.layout
                .exchange_entrust_recode_item);
        View mEmptyView = View.inflate(getContext(), R.layout.empty, null);
        mEmptyView.setScaleX(0.8f);
        mEmptyView.setScaleY(0.8f);
        entrustRecodeRecAdapter.setEmptyView(mEmptyView);
        entrustRecodeRv.setLayoutManager(new LinearLayoutManager(mContext));
        entrustRecodeRv.setAdapter(entrustRecodeRecAdapter);
        entrustRecodeRecAdapter.setOnItemClickListener((adapter, view, position) -> CommonUtil.gotoActivity(mContext,
                EntrustRecodeActivity.class));
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
        exchangeTitleTv.setText(currencyName);
        getExchangeCenterData(true);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            currencyName = arguments.getString(Constants.INTENT_PARAMETER_1);
            currencyId = arguments.getString(Constants.INTENT_PARAMETER_2);
        }
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
                List<?> transactionPendOrderBuyList = centerRes.getTransactionPendOrderBuyList();

                break;
        }
    }

    private void refreshHeader(ExchangeCenterRes.StandardParameterBean standardParameter) {
        exchangeCurrentPriceTv.setText(NumberUtil.doubleFormat(standardParameter.getNowPrice(), 2));
        exchangeHighPriceTv.setText(NumberUtil.doubleFormat(standardParameter.getTodayMax(), 2));
        exchangeLowestPriceTv.setText(NumberUtil.doubleFormat(standardParameter.getTodayMin(), 2));
        exchangeBuyOnePriceTv.setText(NumberUtil.doubleFormat(standardParameter.getBuyOne(), 2));
        exchangeSellOnePriceTv.setText(NumberUtil.doubleFormat(standardParameter.getSellOne(), 2));
        exchangeExchangeDayVolumeTv.setText(NumberUtil.doubleFormat(standardParameter.getDayTurnove(), 2));
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
    }

    @Override
    public void onResume() {
        super.onResume();
        getExchangeCenterData(true);
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
                CommonUtil.gotoActivity(mContext, KlineActivity.class);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
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
}
