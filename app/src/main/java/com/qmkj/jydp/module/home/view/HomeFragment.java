package com.qmkj.jydp.module.home.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.bean.HomeNoticeInfo;
import com.qmkj.jydp.module.home.presenter.HomePresenter;
import com.qmkj.jydp.module.home.presenter.HomeRecyAdapter;
import com.qmkj.jydp.ui.widget.AutoRollLayout;
import com.qmkj.jydp.ui.widget.UPMarqueeView;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.ui.widget.viewbean.RollItem;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.LogUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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

public class HomeFragment extends MvpBaseFragment {
    @BindView(R.id.home_fragment_ll)
    LinearLayout homeFragmentLl;
    @BindView(R.id.home_auto_ll)
    AutoRollLayout homeAutoLl;
    @BindView(R.id.marquee_home_header_notice)
    UPMarqueeView marqueeHomeHeaderNotice;
    @BindView(R.id.home_list_rv)
    RecyclerView homeListRv;
    @BindView(R.id.home_introduce_gv)
    GridView homeIntroduceGv;
    @BindView(R.id.home_scroll_view)
    ScrollView homeScrollView;
    @BindView(R.id.home_fragment_hcswipe_refresh)
    XRefreshLayout homeFragmentRefresh;
    @BindView(R.id.home_ll)
    LinearLayout homeLl;

    private HomePresenter homePresenter;

    @Override
    protected void initView() {
        initStatus();
        initMarquee();
        initAuto(null);
        initRecycleView();
        initGrideView();
        initRefreshView();
    }

    private void initRefreshView() {
        homeFragmentRefresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Observable.timer(5, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        homeFragmentRefresh.refreshComplete();
                    }
                });
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return false;
            }
        });
    }

    private void initGrideView() {
        int[] icon = {R.mipmap.compare, R.mipmap.compare, R.mipmap.compare, R.mipmap.compare};
        String[] iconName = {"生源九州", "生源九州", "生源九州", "kjhgffgjj"};
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

        HomeRecyAdapter homeRecyAdapter = new HomeRecyAdapter(mContext, exchangeList, R.layout.home_exchange_item);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeListRv.setLayoutManager(mLayoutManager);
        homeListRv.setAdapter(homeRecyAdapter);
    }

    private void initAuto(List<RollItem> rollItems) {
        if (rollItems == null) {
            List<RollItem> deafeultRollItems = new ArrayList<>();
            deafeultRollItems.add(new RollItem("https://gss0.baidu" +
                    ".com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/3b87e950352ac65c10d23307fbf2b21192138aea.jpg",
                    R.mipmap.logo, ""));
            deafeultRollItems.add(new RollItem("", R.mipmap.ic_launcher, ""));
            rollItems = deafeultRollItems;
        }
        homeAutoLl.setItems(rollItems);
        homeAutoLl.setAutoRoll(true);
        homeAutoLl.setOnAutoItemClickListener((View view, int position) -> {
                    LogUtil.i("点击图片=" + position);
                }
        );
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

    }

    /**
     * 创建公告item View
     */
    private View createNoticeView(HomeNoticeInfo model) {
        View itemView = View.inflate(mContext, R.layout.item_home_notice, null);
        TextView mTvNotice = itemView.findViewById(R.id.tv_home_header_notice);
        TextView mTvTime = itemView.findViewById(R.id.tv_home_header_notice_time);
        TextView tv_home_header_notice_more = itemView.findViewById(R.id.tv_home_header_notice_more);

        mTvNotice.setText(model.getNoticeTitle());
        mTvTime.setText(DateUtil.longToTimeStr(model.getTopTime(), DateUtil.dateFormat4));
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
        return R.layout.fragment_home;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    protected void injectPresenter() {
        this.homePresenter = new HomePresenter((RxAppCompatActivity) getActivity());
        p.add(homePresenter);
    }

    private void initStatus() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            homeLl.addView(statusView, 0, lp);
        }
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
