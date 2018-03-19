package com.qmkj.jydp.module.home.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.bean.HomeNoticeInfo;
import com.qmkj.jydp.module.home.presenter.HomePresenter;
import com.qmkj.jydp.ui.widget.AutoRollLayout;
import com.qmkj.jydp.ui.widget.UPMarqueeView;
import com.qmkj.jydp.ui.widget.viewbean.RollItem;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.LogUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
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
    Unbinder unbinder;

    private HomePresenter homePresenter;

    @Override
    protected void initView() {
        initStatus();
        initMarquee();
        initAuto(null);
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
        homeAutoLl.setOnAutoItemClickListener(new AutoRollLayout.OnAutoItemClickListener() {
            @Override
            public void OnAutoItemClick(View view, int position) {
                LogUtil.i("点击图片=" + position);
            }
        });
    }

    //公告Views
    List<View> noticeViews = new ArrayList<>();

    private void initMarquee() {
        noticeViews.clear();

        noticeViews.add(createNoticeView(new HomeNoticeInfo("", "", "", "", 1l)));
        noticeViews.add(createNoticeView(new HomeNoticeInfo("", "", "", "", 1l)));
        noticeViews.add(createNoticeView(new HomeNoticeInfo("", "", "", "", 1l)));
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

        mTvNotice.setText(model.getNoticeTitle());
        mTvTime.setText(DateUtil.longToTimeStr(model.getTopTime(), DateUtil.dateFormat4));

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
            homeFragmentLl.addView(statusView, 0, lp);
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
}
