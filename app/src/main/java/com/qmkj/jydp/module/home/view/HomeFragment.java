package com.qmkj.jydp.module.home.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.BannerModel;
import com.qmkj.jydp.bean.HomeNoticeInfo;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.module.home.presenter.BannerImageLoader;
import com.qmkj.jydp.module.home.presenter.HomePresenter;
import com.qmkj.jydp.module.home.presenter.HomeRecyAdapter;
import com.qmkj.jydp.ui.widget.SmoothScrollView;
import com.qmkj.jydp.ui.widget.UPMarqueeView;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:首页展示
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> {
    @BindView(R.id.home_fragment_ll)
    LinearLayout homeFragmentLl;
    @BindView(R.id.home_auto_ll)
    Banner homeAutoLl;
    @BindView(R.id.marquee_home_header_notice)
    UPMarqueeView marqueeHomeHeaderNotice;
    @BindView(R.id.home_list_rv)
    RecyclerView homeListRv;
    @BindView(R.id.home_introduce_gv)
    GridView homeIntroduceGv;
    @BindView(R.id.home_scroll_view)
    SmoothScrollView homeScrollView;
    @BindView(R.id.home_fragment_hcswipe_refresh)
    XRefreshLayout homeFragmentRefresh;
    @BindView(R.id.home_ll)
    LinearLayout homeLl;
    boolean isCanRefresh = true;


    @Override
    protected void initView() {
        initStatus();
        initMarquee();
        initAuto(null);
        initRecycleView();
        initGrideView();
        initRefreshView();

//        RxPermissionUtils.getInstance(mContext).getPermission();
    }

    private void initRefreshView() {
        homeFragmentRefresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Observable.timer(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        homeFragmentRefresh.refreshComplete();
                    }
                });
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return isCanRefresh && homeScrollView.isTop();
            }
        });
    }

    private void initGrideView() {
        int[] icon = {R.mipmap.compare, R.mipmap.compare, R.mipmap.compare, R.mipmap.compare};
        String[] iconName = {"盛源九州", "生源九州", "生源九州", "kjhgffgjj"};
        ArrayList<Map<String, Object>> data_list = new ArrayList<>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        String[] from = {"image", "text"};
        int[] to = {R.id.gride_image, R.id.gride_text};
        SimpleAdapter sim_adapter = new SimpleAdapter(mContext, data_list, R.layout
                .home_item_grideview, from, to);
        homeIntroduceGv.setAdapter(sim_adapter);
        homeIntroduceGv.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    showNetErrorView(homeListRv, true);
                    break;
                case 1:
                    showNetErrorView(homeListRv, false);
                    break;
                case 2:

                    break;
            }
        });
    }

    private void initRecycleView() {
        ArrayList<String> exchangeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            exchangeList.add("");
        }

        HomeRecyAdapter homeRecyAdapter = new HomeRecyAdapter(mContext, exchangeList, R.layout
                .home_exchange_price_item);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeListRv.setLayoutManager(mLayoutManager);
        homeListRv.setAdapter(homeRecyAdapter);
    }

    private void initAuto(List<BannerModel> bannerList) {
        if (bannerList == null) {
            List<BannerModel> deafeultRollItems = new ArrayList<>();
            deafeultRollItems.add(new BannerModel("https://gss0.baidu" +
                    ".com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/3b87e950352ac65c10d23307fbf2b21192138aea.jpg",
                    "", ""));
            deafeultRollItems.add(new BannerModel("http://img2.imgtn.bdimg.com/it/u=3588772980," +
                    "2454248748&fm=27&gp=0.jpg", "", ""));
            bannerList = deafeultRollItems;
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
//            BannerModel model = bannerList.get(position);

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

    private void initMarquee() {
        noticeViews.clear();

        noticeViews.add(createNoticeView(new HomeNoticeInfo("", "", "1234tfrefer", "", 1l)));
        noticeViews.add(createNoticeView(new HomeNoticeInfo("", "", "232323", "", 1l)));
        noticeViews.add(createNoticeView(new HomeNoticeInfo("", "", "fvdfcvreew", "", 1l)));
        marqueeHomeHeaderNotice.setViews(noticeViews);
        if (noticeViews.size() <= 1) {
            marqueeHomeHeaderNotice.stopFlipping();
        }
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            presenter.getCurrentPrice(AppNetConfig.getBaseMaps(), 1, true);
        }
    }

    /**
     * 创建公告item View
     */
    private View createNoticeView(HomeNoticeInfo model) {
        View itemView = View.inflate(mContext, R.layout.home_notice_item, null);
        TextView mTvNotice = itemView.findViewById(R.id.tv_home_header_notice);
        TextView tv_home_header_notice_more = itemView.findViewById(R.id.tv_home_header_notice_more);

        mTvNotice.setText(model.getNoticeTitle());
        tv_home_header_notice_more.setText(CommonUtil.getString(R.string.more));
        itemView.setTag(model);

        itemView.setOnClickListener(v -> {
            HomeNoticeInfo tagModel = (HomeNoticeInfo) v.getTag();
            if (tagModel != null) {
                String noticeTitle = model.getNoticeTitle();
                //去h5页面
            }
        });

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
    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
    }

}
