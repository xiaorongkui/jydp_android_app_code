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
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.module.exchange.view.ExchangeFragment;
import com.qmkj.jydp.module.home.presenter.CurrencyRecyAdapter;
import com.qmkj.jydp.module.home.view.HomeFragment;
import com.qmkj.jydp.module.mine.view.MineFragment;
import com.qmkj.jydp.rxbus.RxBus;
import com.qmkj.jydp.rxbus.event.DrawEvent;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class MainActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.iv_mine)
    ImageView mineBottomIv;
    @BindView(R.id.tv_mine)
    TextView mineBottomTv;
    @BindView(R.id.ll_mine)
    LinearLayout mineBottom;

    @BindView(R.id.drawer_view)
    LinearLayout drawer_view;
    @BindView(R.id.drawer_dl)
    DrawerLayout drawerDl;
    @BindView(R.id.currency_select_close_iv)
    ImageView currencySelectCloseIv;
    @BindView(R.id.exchange_draw_rv)
    RecyclerView exchangeDrawRv;

    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ExchangeFragment exchangeFragment;
    private MineFragment mineFragment;
    public static final int HOME = 0;
    public static final int PRODUCT = 1;
    public static final int ACCOUNT = 2;
    public static final int MORE = 3;

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
        homeBottom = (LinearLayout) findViewById(R.id.ll_home);
        homeBottomTv = (TextView) findViewById(R.id.tv_home);
        homeBottomIv = (ImageView) findViewById(R.id.iv_home);

        exchangeBottom = (LinearLayout) findViewById(R.id.ll_exchange);
        exchangeBottomTv = (TextView) findViewById(R.id.tv_exchange);
        exchangeBottomIv = (ImageView) findViewById(R.id.iv_exchange);

        mineBottom = (LinearLayout) findViewById(R.id.ll_mine);
        mineBottomTv = (TextView) findViewById(R.id.tv_mine);
        mineBottomIv = (ImageView) findViewById(R.id.iv_mine);

        homeBottom.setOnClickListener(this);
        exchangeBottom.setOnClickListener(this);
        mineBottom.setOnClickListener(this);
        setSelect(0);

        initDrawerLayout();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            int index = 1;
            switch (index) {
                case HOME:
                    setSelect(0);
                    break;
                case PRODUCT:
                    setSelect(1);
                    break;
                case ACCOUNT:
                    setSelect(2);
                    break;
                case MORE:
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_exchange:
                setSelect(1);
                break;
            case R.id.ll_mine:
                setSelect(2);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragments();
        resetImages();

        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_container, homeFragment);
                }
                ft.show(homeFragment);
                homeBottomIv.setImageResource(R.mipmap.home_select);
                homeBottomTv.setTextColor(CommonUtil.getColor(R.color.textRed_2));
                break;
            case 1:
                if (exchangeFragment == null) {
                    exchangeFragment = new ExchangeFragment();
                    ft.add(R.id.main_container, exchangeFragment);
                }
                ft.show(exchangeFragment);
                exchangeBottomIv.setImageResource(R.mipmap.exchange_select);
                exchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.textRed_2));
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.main_container, mineFragment);
                }
                ft.show(mineFragment);
                mineBottomIv.setImageResource(R.mipmap.mine_select);
                mineBottomTv.setTextColor(CommonUtil.getColor(R.color.textRed_2));
                break;
        }
        ft.commit();
    }

    private void hideFragments() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (exchangeFragment != null) {
            ft.hide(exchangeFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }

    private void resetImages() {

        homeBottomIv.setImageResource(R.mipmap.home_unselect);
        exchangeBottomIv.setImageResource(R.mipmap.exchange_unselect);
        mineBottomIv.setImageResource(R.mipmap.mine_unselect);

        homeBottomTv.setTextColor(CommonUtil.getColor(R.color.textGray));
        exchangeBottomTv.setTextColor(CommonUtil.getColor(R.color.textGray));
        mineBottomTv.setTextColor(CommonUtil.getColor(R.color.textGray));
    }

    /**
     * 是否使用沉浸式状态栏，默认为true；
     */
    @Override
    public boolean isImmersiveStatusBar() {
        return Build.VERSION.SDK_INT < 21;
    }

    private void initDrawerLayout() {
        drawerDl.setScrimColor(CommonUtil.getColor(R.color.colorBlack_3));
        drawerDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(mContext);
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.colorBlack_3));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            drawer_view.addView(statusView, 0, lp);
        }
        List mdatas = new ArrayList();
        for (int i = 0; i < 6; i++) {
            mdatas.add("");
        }
        CurrencyRecyAdapter recyAdapter = new CurrencyRecyAdapter(mContext, mdatas, R.layout.currency_icon_item);
        exchangeDrawRv.setLayoutManager(new LinearLayoutManager(mContext));
        exchangeDrawRv.setAdapter(recyAdapter);
        recyAdapter.setOnItemClickLitener((view, position) -> {
            if (exchangeFragment != null) {
                exchangeFragment.updateCurrencySelect(position);
            }
        });
        currencySelectCloseIv.setOnClickListener(v -> {
            showDrawerLayout(false);
        });
    }

    public void showDrawerLayout(boolean b) {
        if (b) {
            drawerDl.openDrawer(drawer_view);
        } else {
            drawerDl.closeDrawer(drawer_view);
        }
    }
}
