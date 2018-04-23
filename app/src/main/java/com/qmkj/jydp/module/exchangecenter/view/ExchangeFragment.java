package com.qmkj.jydp.module.exchangecenter.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangePresenter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeSoldPriceRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangebuyPriceRecAdapter;
import com.qmkj.jydp.ui.widget.MyViewPager;
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

public class ExchangeFragment extends BaseMvpFragment<ExchangePresenter> implements View.OnClickListener {

    private static final int EXCHANGE_TYPE_BUY = 1;
    private static final int EXCHANGE_TYPE_SOLD = 2;
    private static final int EXCHANGE_TYPE_RECODE = 3;
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
    @BindView(R.id.entrust_recod_title)
    TextView entrustRecodTitle;
    @BindView(R.id.entrust_recod_bottom_line)
    View entrustRecodBottomLine;
    @BindView(R.id.entrust_ll)
    LinearLayout entrustLl;
    @BindView(R.id.kline_ll)
    LinearLayout klineLl;
    Unbinder unbinder;


    private ExchangebuyPriceRecAdapter priceBuyRecAdapter;
    private ExchangeSoldPriceRecAdapter priceSoldRecAdapter;
    private boolean isShowDrawLayout = false;

    @Override
    protected void initView() {
        initStatusBar();
        initRecycleView();
        initListener();
        initViewPager();
        setViewpagerIndicotr(0);
    }

    private void initViewPager() {
        exchangeContinerVp.setOffscreenPageLimit(1);
        CommonUtil.setScrollerTime(mContext, 0, exchangeContinerVp);
        final MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(ExchangeBuyFragment.newInstance(EXCHANGE_TYPE_BUY));
        adapter.addFragment(ExchangeSoldFragment.newInstance(EXCHANGE_TYPE_SOLD));
        adapter.addFragment(ExchangeRecodeFragment.newInstance(EXCHANGE_TYPE_RECODE));
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
        entrustLl.setOnClickListener(this);
        klineLl.setOnClickListener(this);
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
            case R.id.buy_ll:
                setViewpagerIndicotr(0);
                break;
            case R.id.sold_ll:
                setViewpagerIndicotr(1);
                break;
            case R.id.entrust_ll:
                setViewpagerIndicotr(2);
                break;
            case R.id.kline_ll:
                CommonUtil.gotoActivity(mContext, KlineActivity.class);
                break;
        }
    }

    public void updateCurrencySelect(int i) {
        LogUtil.i("选择了币种=" + i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        buyTitle.setTextColor(CommonUtil.getColor(R.color.colorBlack_9));
        soldTitle.setTextColor(CommonUtil.getColor(R.color.colorBlack_9));
        entrustRecodTitle.setTextColor(CommonUtil.getColor(R.color.colorBlack_9));
        buyBottomLine.setVisibility(View.INVISIBLE);
        soldBottomLine.setVisibility(View.INVISIBLE);
        entrustRecodBottomLine.setVisibility(View.INVISIBLE);

        switch (index) {
            case 0:
                buyTitle.setTextColor(CommonUtil.getColor(R.color.colorRed_4));
                buyBottomLine.setVisibility(View.VISIBLE);
                exchangeContinerVp.setCurrentItem(0);
                break;
            case 1:
                soldTitle.setTextColor(CommonUtil.getColor(R.color.colorRed_4));
                soldBottomLine.setVisibility(View.VISIBLE);
                exchangeContinerVp.setCurrentItem(1);
                break;
            case 2:
                exchangeContinerVp.setCurrentItem(2);
                entrustRecodTitle.setTextColor(CommonUtil.getColor(R.color.colorRed_4));
                entrustRecodBottomLine.setVisibility(View.VISIBLE);
                break;
        }
    }
}
