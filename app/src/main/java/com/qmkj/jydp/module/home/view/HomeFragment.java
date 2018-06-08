package com.qmkj.jydp.module.home.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
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

    @BindView(R.id.home_list_rv)
    RecyclerView homeListRv;
    @BindView(R.id.home_fragment_hcswipe_refresh)
    XRefreshLayout homeFragmentRefresh;
    private AutoHeighBanner autoHeighBanner;
    private View upMarqueeViewFl;
    private UPMarqueeView upMarqueeView;
    private View businessPartnerLl;
    private FullGridView fullGridView;

    boolean isCanRefresh = true;
    private HomeRecyAdapter homeRecyAdapter;
    private List<HomeDataRes.SystemAdsHomepagesListBean> systemAdsHomepagesList = new ArrayList<>();  //首页轮播图
    private List<HomeDataRes.SystemBusinessesPartnerListBean> systemBusinessesPartnerList = new ArrayList<>();  //合作商家信息
    private List<HomeDataRes.SystemNoticeListBean> systemNoticeList = new ArrayList<>();  //系统公告
    private List<HomeDataRes.TransactionUserDealListBean> transactionUserDealList = new ArrayList<>();  //币种行情列表
    private List<View> noticeViews = new ArrayList<>();

    @Override
    protected void initView() {
        homeFragmentRefresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomeData();
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return isCanRefresh;
            }
        });
        homeListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                isCanRefresh = firstCompletelyVisibleItemPosition <= 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        homeRecyAdapter = new HomeRecyAdapter(mContext, transactionUserDealList, R.layout.home_exchange_price_item);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeListRv.setLayoutManager(mLayoutManager);
        View mEmptyView = View.inflate(getContext(), R.layout.empty, null);
        homeRecyAdapter.setEmptyView(mEmptyView);
        homeListRv.setAdapter(homeRecyAdapter);
        homeRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
            try {
                HomeDataRes.TransactionUserDealListBean userDealListBean = transactionUserDealList.get(position);
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).showExchangeFrament(userDealListBean.getCurrencyName(),
                            userDealListBean.getCurrencyId() + "");//去交易中心核心页面
                }
            } catch (Exception e) {
                e.printStackTrace();
                toast(getString(R.string.cerrecy_select_failed));
            }
        });
        //初始化头部布局
        View headerView = initHeaderView();
        //初始化底部布局
        View footerView = initFooterView();
        homeRecyAdapter.addHeaderView(headerView);
        homeRecyAdapter.addFooterView(footerView);
    }

    /**
     * 初始化头部View
     *
     * @return headerView
     */
    private View initHeaderView() {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_home_fragment_rv, null);
        autoHeighBanner = headerView.findViewById(R.id.home_auto_ll);
        upMarqueeViewFl = headerView.findViewById(R.id.upMarqueeView_fl);
        upMarqueeView = headerView.findViewById(R.id.marquee_home_header_notice);
        //初始化Banner
        initBanner();
        //初始化Marquee
        initMarquee();
        return headerView;
    }

    /**
     * 初始化底部View
     *
     * @return footerView
     */
    private View initFooterView() {
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.footer_home_fragment_rv, null);
        businessPartnerLl = footerView.findViewById(R.id.business_partner_ll);
        fullGridView = footerView.findViewById(R.id.home_introduce_gv);
        //初始化GridView
        initGridView();
        return footerView;
    }

    /**
     * 获取数据
     */
    private void getHomeData() {
        presenter.getCurrentPrice(HOME_DATA_REQUEST_TAG, false);
    }

    /**
     * 初始化合作商家
     */
    private void initGridView() {
        HomeGrideAdapter homeGrideAdapter = new HomeGrideAdapter(mContext, R.layout.home_item_grideview, systemBusinessesPartnerList);
        fullGridView.setAdapter(homeGrideAdapter);
        fullGridView.setOnItemClickListener((parent, view, position, id) -> {
            HomeDataRes.SystemBusinessesPartnerListBean listBean = systemBusinessesPartnerList.get(position);
            if (listBean == null) {
                return;
            }
            if (!TextUtils.isEmpty(listBean.getWebLinkUrl()) && listBean.getWebLinkUrl().startsWith("http")) {
                Intent intent = WebActivity.getActivityIntent(mContext, listBean.getBusinessesName(), listBean
                        .getWebLinkUrl());
                CommonUtil.gotoActivity(mContext, intent);
            }
        });
        if (systemBusinessesPartnerList.size() == 0) {
            businessPartnerLl.setVisibility(View.GONE);
        } else {
            businessPartnerLl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onViewResume() {
        super.onViewResume();
        if (upMarqueeView != null) {
            upMarqueeView.startFlipping();//解决重影问题
        }
    }

    @Override
    protected void onViewPause() {
        super.onViewPause();
        if (upMarqueeView != null) {
            upMarqueeView.stopFlipping();
        }
    }

    /**
     * 初始化Banner
     */
    private void initBanner() {
        //设置banner样式
        autoHeighBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        autoHeighBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        autoHeighBanner.setImages(systemAdsHomepagesList);
        //设置banner动画效果
        autoHeighBanner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        autoHeighBanner.isAutoPlay(true);
        autoHeighBanner.setOnBannerListener(position -> {
            HomeDataRes.SystemAdsHomepagesListBean model = systemAdsHomepagesList.get(position);
            if (model == null) return;
            if (!TextUtils.isEmpty(model.getWebLinkUrl())) {
                Intent intent = WebActivity.getActivityIntent(mContext, model.getAdsTitle(), model.getWebLinkUrl());
                CommonUtil.gotoActivity(mContext, intent);
            }
        });
        //设置轮播时间
        autoHeighBanner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        autoHeighBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        autoHeighBanner.start();
        if (systemAdsHomepagesList.size() == 0) {
            autoHeighBanner.setVisibility(View.GONE);
        } else {
            autoHeighBanner.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化系统公告的跑马灯
     */
    private void initMarquee() {
        noticeViews.clear();
        for (HomeDataRes.SystemNoticeListBean homeNoticeInfo : systemNoticeList) {
            noticeViews.add(createNoticeView(homeNoticeInfo));
        }
        upMarqueeView.setViews(noticeViews);
        if (noticeViews.size() <= 1) {
            upMarqueeView.stopFlipping();
        }
        upMarqueeView.setOnItemClickListener((position, view) -> {
            Intent intent = new Intent(mContext, SystemNoticeDetailsActivity.class);
            intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TITTLE,
                    systemNoticeList.get(position).getNoticeTitle());
            intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TIMES,
                    DateUtil.longToTimeStr(systemNoticeList.get(position).getAddTime(),
                            DateUtil.dateFormat2));
            intent.putExtra(SystemNoticeDetailsActivity.NOTICE_DETAILS,
                    systemNoticeList.get(position).getContent());
            intent.putExtra(SystemNoticeDetailsActivity.ACTIVITY_TITLE_KEY, "公告详情");
            CommonUtil.gotoActivity(mContext, intent);
        });
        upMarqueeView.startFlipping();
        if (systemNoticeList.size() == 0) {
            upMarqueeViewFl.setVisibility(View.GONE);
        } else {
            upMarqueeViewFl.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            //广告首页的数据返回
            case HOME_DATA_REQUEST_TAG:
                homeFragmentRefresh.refreshComplete();
                HomeDataRes homeDataRes = (HomeDataRes) response;
                if (homeDataRes == null) {
                    return;
                }
                if (homeDataRes.getTransactionUserDealList() != null) {
                    transactionUserDealList = homeDataRes.getTransactionUserDealList();
                }
                if (homeDataRes.getSystemAdsHomepagesList() != null) {
                    systemAdsHomepagesList = homeDataRes.getSystemAdsHomepagesList();
                }
                if (homeDataRes.getSystemNoticeList() != null) {
                    systemNoticeList = homeDataRes.getSystemNoticeList();
                }
                if (homeDataRes.getSystemBusinessesPartnerList() != null) {
                    systemBusinessesPartnerList = homeDataRes.getSystemBusinessesPartnerList();
                }
                initView();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        switch (tag) {
            //广告首页的数据返回
            case HOME_DATA_REQUEST_TAG:
                homeFragmentRefresh.refreshComplete();
                break;
        }
    }

}
