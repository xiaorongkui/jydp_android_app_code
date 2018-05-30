package com.qmkj.jydp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.base.BaseActivity;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeCurrencySelectFrament;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeFragment;
import com.qmkj.jydp.module.home.presenter.CurrencyRecyAdapter;
import com.qmkj.jydp.module.home.view.HomeFragment;
import com.qmkj.jydp.module.mine.view.MineFragment;
import com.qmkj.jydp.module.exchangoutside.view.OutsideExchangeFragment;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_home)
    ImageView homeBottomIv;
    @BindView(R.id.tv_home)
    TextView homeBottomTv;
    @BindView(R.id.ll_home)
    LinearLayout homeBottom;
    @BindView(R.id.iv_exchange)
    ImageView exchangeBottomIv;
    @BindView(R.id.tv_exchange)
    TextView exchangeBottomTv;
    @BindView(R.id.ll_exchange)
    LinearLayout exchangeBottom;
    @BindView(R.id.iv_outside_exchange)
    ImageView outsideExchangeBottomIv;
    @BindView(R.id.tv_outside_exchange)
    TextView outsideExchangeBottomTv;
    @BindView(R.id.ll_outside_exchange)
    LinearLayout outsideExchangeBottom;
    @BindView(R.id.iv_mine)
    ImageView mineBottomIv;
    @BindView(R.id.tv_mine)
    TextView mineBottomTv;
    @BindView(R.id.ll_mine)
    LinearLayout mineBottom;

    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ExchangeCurrencySelectFrament exchangeCurrencySelectFrament;
    private ExchangeFragment exchangeFragment;
    private OutsideExchangeFragment outsideExchangeFragment;
    private MineFragment mineFragment;
    public static final int HOME = 0;
    public static final int PRODUCT = 1;
    public static final int OUTSIDE_EXCHANGE = 2;
    public static final int ACCOUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(R.style.mainActivityTheme);
            CommonUtil.setStatusBarInvisible(this, false);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        LogUtil.i("MainActivity onCreate,状态栏高度=" + CommonUtil.getStatusBarHeight() + ";\t 分辨率=" +
                CommonUtil.getAndroidPix(mContext) + ";\t设备信息");
    }

    private long recodeTime = 0;

    @Override
    protected void initView() {
        setSelect(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            int index = intent.getIntExtra(Constants.INTENT_PARAMETER_1, 1);//默认进入交易中心页面
            switch (index) {
                case HOME:
                    setSelect(0);
                    break;
                case PRODUCT:
                    setSelect(1);
                    break;
                case OUTSIDE_EXCHANGE:
                    setSelect(2);
                    break;
                case ACCOUNT:
                    setSelect(3);
                    break;
            }
        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - recodeTime < 2000) {
                //退出程序
                JYDPExchangeApp.exit();
            } else {
                ToastUtil.toast(this, getResources().getString(R.string.click_exit));
                recodeTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.ll_home, R.id.ll_exchange, R.id.ll_outside_exchange, R.id.ll_mine})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_exchange:
                setSelect(1);
                break;
            case R.id.ll_outside_exchange:
                setSelect(2);
                break;
            case R.id.ll_mine:
                setSelect(3);
                break;
        }
    }

    public void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragments();
        resetImages();

        switch (i) {
            case 0://首页
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_container, homeFragment);
                }
                ft.show(homeFragment);
                homeBottomIv.setImageResource(R.mipmap.home_select);
                homeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                break;
            case 1://币种选择页面
                if (exchangeCurrencySelectFrament == null) {
                    exchangeCurrencySelectFrament = new ExchangeCurrencySelectFrament();
                    ft.add(R.id.main_container, exchangeCurrencySelectFrament);
                }
                ft.show(exchangeCurrencySelectFrament);
                exchangeBottomIv.setImageResource(R.mipmap.exchange_select);
                exchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                break;
            case 2://场外交易页面
                if (outsideExchangeFragment == null) {
                    outsideExchangeFragment = new OutsideExchangeFragment();
                    ft.add(R.id.main_container, outsideExchangeFragment);
                }
                ft.show(outsideExchangeFragment);
                outsideExchangeBottomIv.setImageResource(R.mipmap.outside_exchange_select);
                outsideExchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                break;
            case 3://我的页面
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.main_container, mineFragment);
                }
                ft.show(mineFragment);
                mineBottomIv.setImageResource(R.mipmap.mine_select);
                mineBottomTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                break;
        }
        ft.commit();
    }


    private void hideFragments() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (exchangeCurrencySelectFrament != null) {
            ft.hide(exchangeCurrencySelectFrament);
        }
        if (outsideExchangeFragment != null) {
            ft.hide(outsideExchangeFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
        if (exchangeFragment != null) {
            ft.hide(exchangeFragment);
        }
    }

    //交易中心核心界面
    public void showExchangeFrament(String currencyName, String currencyId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.INTENT_PARAMETER_1, currencyName);
        bundle.putString(Constants.INTENT_PARAMETER_2, currencyId);
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragments();
        resetImages();
        if (exchangeFragment == null) {
            exchangeFragment = new ExchangeFragment();
            ft.add(R.id.main_container, exchangeFragment);
        }
        exchangeFragment.setArguments(bundle);
        exchangeBottomIv.setImageResource(R.mipmap.exchange_select);
        exchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
        ft.show(exchangeFragment);
        ft.commit();
    }

    private void resetImages() {

        homeBottomIv.setImageResource(R.mipmap.home_unselect);
        exchangeBottomIv.setImageResource(R.mipmap.exchange_unselect);
        outsideExchangeBottomIv.setImageResource(R.mipmap.outside_exchange_unselect);
        mineBottomIv.setImageResource(R.mipmap.mine_unselect);

        homeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_black_5));
        exchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_black_5));
        outsideExchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.color_black_5));
        mineBottomTv.setTextColor(CommonUtil.getColor(R.color.color_black_5));
    }

    /**
     * 是否使用沉浸式状态栏，默认为true；
     */
    @Override
    public boolean isImmersiveStatusBar() {
        return Build.VERSION.SDK_INT < 21;
    }

//    private void initDrawerLayout() {
//        drawerDl.setScrimColor(CommonUtil.getColor(R.color.colorBlack_3));
//        drawerDl.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//
//            }
//
//            @Override
//            public void onDrawerOpened(@NonNull View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(@NonNull View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
//
//        //状态栏占用的兼容性
//        if (Build.VERSION.SDK_INT >= 21) {
//            View statusView = new View(mContext);
//            statusView.setBackgroundColor(CommonUtil.getColor(R.color.colorBlack_3));
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
//                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
//            drawer_view.addView(statusView, 0, lp);
//        }
//        List mdatas = new ArrayList();
//        for (int i = 0; i < 6; i++) {
//            mdatas.add("");
//        }
//        CurrencyRecyAdapter recyAdapter = new CurrencyRecyAdapter(mContext, mdatas, R.layout
//                .exchange_currency_icon_item);
//        exchangeDrawRv.setLayoutManager(new LinearLayoutManager(mContext));
//        exchangeDrawRv.setAdapter(recyAdapter);
//        recyAdapter.setOnItemClickListener((adapter, view, position) -> {
////            if (exchangeFragment != null) {
////                exchangeFragment.updateCurrencySelect(position);
////            }
//        });
//        currencySelectCloseIv.setOnClickListener(v -> {
//            showDrawerLayout(false);
//        });
//    }

//    public void showDrawerLayout(boolean b) {
//        if (b) {
//            drawerDl.openDrawer(drawer_view);
//        } else {
//            drawerDl.closeDrawer(drawer_view);
//        }
//    }


}
