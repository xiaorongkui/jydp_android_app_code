package com.qmkj.jydp.module.home.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.response.HomeDataRes;
import com.qmkj.jydp.module.home.presenter.BannerImageLoader;
import com.qmkj.jydp.module.home.presenter.HomeGrideAdapter;
import com.qmkj.jydp.module.home.presenter.HomePresenter;
import com.qmkj.jydp.module.home.presenter.HomeRecyAdapter;
import com.qmkj.jydp.module.mine.view.SystemNoticeActivity;
import com.qmkj.jydp.module.mine.view.SystemNoticeDetailsActivity;
import com.qmkj.jydp.ui.widget.AutoHeighBanner;
import com.qmkj.jydp.ui.widget.FullGridView;
import com.qmkj.jydp.ui.widget.SmoothScrollView;
import com.qmkj.jydp.ui.widget.UPMarqueeView;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:首页展示
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> {
    private static final int HOME_DATA_REQUEST_TAG = 1;
    @BindView(R.id.home_fragment_ll)
    LinearLayout homeFragmentLl;
    @BindView(R.id.home_auto_ll)
    AutoHeighBanner homeAutoLl;
    @BindView(R.id.marquee_home_header_notice)
    UPMarqueeView marqueeHomeHeaderNotice;
    @BindView(R.id.home_list_rv)
    RecyclerView homeListRv;
    @BindView(R.id.home_introduce_gv)
    FullGridView homeIntroduceGv;
    @BindView(R.id.home_scroll_view)
    SmoothScrollView homeScrollView;
    @BindView(R.id.home_fragment_hcswipe_refresh)
    XRefreshLayout homeFragmentRefresh;
    @BindView(R.id.home_ll)
    LinearLayout homeLl;
    boolean isCanRefresh = true;
    private HomeRecyAdapter homeRecyAdapter;


    @Override
    protected void initView() {
        initStatus();
        initMarquee(null);
        initAuto(null);
        initRecycleView();
        initGrideView(null);
        initRefreshView();
    }

    private void initRefreshView() {
        homeFragmentRefresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomeData(false);
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return isCanRefresh && homeScrollView.isTop();
            }
        });
    }

    private void getHomeData(boolean isShowProgress) {
        presenter.getCurrentPrice(HOME_DATA_REQUEST_TAG, isShowProgress);
    }

    private void initGrideView(List<HomeDataRes.SystemBusinessesPartnerListBean> data) {
        if (data == null) {
            return;
        }
        HomeGrideAdapter homeGrideAdapter = new HomeGrideAdapter(mContext, R.layout.home_item_grideview, data);
        homeIntroduceGv.setAdapter(homeGrideAdapter);
        homeIntroduceGv.setOnItemClickListener((parent, view, position, id) -> {
            HomeDataRes.SystemBusinessesPartnerListBean listBean = data.get(position);
            if (listBean == null) return;
            if (!TextUtils.isEmpty(listBean.getWebLinkUrl()) && listBean.getWebLinkUrl().startsWith("http")) {
                Intent intent = WebActivity.getActivityIntent(mContext, listBean.getBusinessesName(), listBean
                        .getWebLinkUrl());
                CommonUtil.gotoActivity(mContext, intent);
            }

        });
    }

    @Override
    protected void onViewResume() {
        super.onViewResume();
        getHomeData(false);//当首页可见时刷新数据
        marqueeHomeHeaderNotice.startFlipping();//解决重影问题
    }

    @Override
    protected void onViewPause() {
        super.onViewPause();
        marqueeHomeHeaderNotice.stopFlipping();
    }

    ArrayList<HomeDataRes.TransactionUserDealListBean> exchangeList = new ArrayList<>();

    private void initRecycleView() {
        homeRecyAdapter = new HomeRecyAdapter(mContext, exchangeList, R.layout.home_exchange_price_item);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeListRv.setLayoutManager(mLayoutManager);
        View mEmptyView = View.inflate(getContext(), R.layout.empty, null);
        homeRecyAdapter.setEmptyView(mEmptyView);
        homeListRv.setAdapter(homeRecyAdapter);
        homeRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
            try {
                HomeDataRes.TransactionUserDealListBean userDealListBean = exchangeList.get(position);
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).showExchangeFrament(userDealListBean.getCurrencyName(),
                            userDealListBean.getCurrencyId() + "");//去交易中心核心页面
                }
            } catch (Exception e) {
                e.printStackTrace();
                toast(getString(R.string.cerrecy_select_failed));
            }
        });
    }

    //初始化首页自动轮播图
    private void initAuto(List<HomeDataRes.SystemAdsHomepagesListBean> bannerList) {
        if (bannerList == null) {
            return;
        }
        //设置banner样式
        homeAutoLl.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        homeAutoLl.setImageLoader(new BannerImageLoader());
        //设置图片集合
        homeAutoLl.setImages(bannerList);
        //设置banner动画效果
        homeAutoLl.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        homeAutoLl.isAutoPlay(true);
        homeAutoLl.setOnBannerListener(position -> {
            HomeDataRes.SystemAdsHomepagesListBean model = bannerList.get(position);
            if (model == null) return;
            if (!TextUtils.isEmpty(model.getWebLinkUrl())) {
                Intent intent = WebActivity.getActivityIntent(mContext, model.getAdsTitle(), model.getWebLinkUrl());
                CommonUtil.gotoActivity(mContext, intent);
            }
        });
        //设置轮播时间
        homeAutoLl.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        homeAutoLl.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        homeAutoLl.start();
    }

    //公告Views
    List<View> noticeViews = new ArrayList<>();

    /*系统公告的跑马灯*/
    private void initMarquee(List<HomeDataRes.SystemNoticeListBean> homeNoticeInfos) {
        if (homeNoticeInfos == null) return;
        noticeViews.clear();

        for (HomeDataRes.SystemNoticeListBean homeNoticeInfo : homeNoticeInfos) {
            noticeViews.add(createNoticeView(homeNoticeInfo));
        }
        marqueeHomeHeaderNotice.setViews(noticeViews);
        if (noticeViews.size() <= 1) {
            marqueeHomeHeaderNotice.stopFlipping();
        }
        marqueeHomeHeaderNotice.setOnItemClickListener((position, view) -> {
            Intent intent = new Intent(mContext, SystemNoticeDetailsActivity.class);
            intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TITTLE,
                    homeNoticeInfos.get(position).getNoticeTitle());
            intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TIMES,
                    DateUtil.longToTimeStr(homeNoticeInfos.get(position).getAddTime(),
                            DateUtil.dateFormat2));
            intent.putExtra(SystemNoticeDetailsActivity.NOTICE_DETAILS,
                    homeNoticeInfos.get(position).getContent());
            intent.putExtra(SystemNoticeDetailsActivity.ACTIVITY_TITLE_KEY, "公告详情");
            CommonUtil.gotoActivity(mContext, intent);
        });
        marqueeHomeHeaderNotice.startFlipping();
    }

    @Override
    protected void initData() {
        homeFragmentRefresh.callRefresh();
    }

    /**
     * 创建公告item View
     */
    private View createNoticeView(HomeDataRes.SystemNoticeListBean noticeListBean) {
        View itemView = View.inflate(mContext, R.layout.home_notice_item, null);
        TextView mTvNotice = itemView.findViewById(R.id.tv_home_header_notice);
        TextView notice_title_type_tv = itemView.findViewById(R.id.notice_title_type_tv);
        TextView tv_home_header_notice_more = itemView.findViewById(R.id.tv_home_header_notice_more);

        mTvNotice.setText(noticeListBean.getNoticeTitle());
        tv_home_header_notice_more.setText(CommonUtil.getString(R.string.more));
        tv_home_header_notice_more.setOnClickListener(v -> CommonUtil.gotoActivity(mContext, SystemNoticeActivity
                .class));
        notice_title_type_tv.setText(String.format("[%s]", noticeListBean.getNoticeType()));
        itemView.setTag(noticeListBean);

        return itemView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    private void initStatus() {
        //状态栏占用的兼容性
//        if (Build.VERSION.SDK_INT >= 21) {
//            View statusView = new View(getActivity());
//            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup
//                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
//            homeLl.addView(statusView, 0, lp);
//        }

//        int height = (int) (CommonUtil.getScreenWidth(mContext) * (169 / (float) 375));
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
//        homeAutoLl.setLayoutParams(params);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case HOME_DATA_REQUEST_TAG://广告首页的数据返回
                homeFragmentRefresh.refreshComplete();
                HomeDataRes homeDataRes = (HomeDataRes) response;
                if (homeDataRes == null) return;
                List<HomeDataRes.SystemAdsHomepagesListBean> systemAdsHomepagesList = homeDataRes
                        .getSystemAdsHomepagesList();//首页广告
                List<HomeDataRes.SystemBusinessesPartnerListBean> systemBusinessesPartnerList = homeDataRes
                        .getSystemBusinessesPartnerList();//商家
                List<HomeDataRes.SystemNoticeListBean> systemNoticeList = homeDataRes.getSystemNoticeList();//系统公告
                List<HomeDataRes.TransactionUserDealListBean> transactionUserDealList = homeDataRes
                        .getTransactionUserDealList();//币行情信息
                if (systemAdsHomepagesList != null && systemAdsHomepagesList.size() > 0) {
                    initAuto(systemAdsHomepagesList);
                }
                if (systemNoticeList != null && systemNoticeList.size() > 0) {
                    initMarquee(systemNoticeList);
                }
                if (transactionUserDealList != null && transactionUserDealList.size() > 0) {
                    exchangeList.clear();
                    exchangeList.addAll(transactionUserDealList);
                    homeRecyAdapter.notifyDataSetChanged();
                }
                if (systemBusinessesPartnerList != null && systemBusinessesPartnerList.size() > 0) {
                    initGrideView(systemBusinessesPartnerList);
                }

                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        switch (tag) {
            case HOME_DATA_REQUEST_TAG:
                homeFragmentRefresh.refreshComplete();
                break;
        }
    }

}
