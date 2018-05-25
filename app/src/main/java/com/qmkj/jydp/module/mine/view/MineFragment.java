package com.qmkj.jydp.module.mine.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.MinelistInfo;
import com.qmkj.jydp.bean.response.MineRes;
import com.qmkj.jydp.module.mine.ChainWithdrawActivity;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.MineRecyAdapter;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.ui.widget.ScrollRecycleView;
import com.qmkj.jydp.ui.widget.SmoothScrollView;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:我的页面功能
 */

public class MineFragment extends BaseMvpFragment<MinePresenter> {

    @BindView(R.id.mine_info_rl)
    XRefreshLayout refreshLayout;
    @BindView(R.id.mine_info_sv)
    SmoothScrollView scrollView;
    @BindView(R.id.mine_rv)
    ScrollRecycleView mineRv;
    @BindView(R.id.mine_ll)
    LinearLayout mineLl;
    @BindView(R.id.mine_distributor_tv)
    TextView mine_distributor_tv;
    @BindView(R.id.mine_available_money_ll)
    LinearLayout mineAvailableMoneyLl;
    Unbinder unbinder;
    @BindView(R.id.mine_userAccount_tv)
    TextView mine_userAccount_tv;
    @BindView(R.id.mine_totalUserBalance_tv)
    NoPaddingTextView mine_totalUserBalance_tv;
    @BindView(R.id.mine_userBalance_tv)
    NoPaddingTextView mine_userBalance_tv;
    @BindView(R.id.mine_userBalanceLock_tv)
    NoPaddingTextView mine_userBalanceLock_tv;

    @Override
    protected void initView() {
//        initStatus();
        initRefreshLayout();
        initRecycleView();
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_gray_1))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        mine_distributor_tv.setBackground(shapeSelector.create());

        if(CommonUtil.getLoginInfo().getUser().getIsDealer()==2){
            mine_distributor_tv.setVisibility(View.VISIBLE);

        }
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMineInfo();
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return scrollView.isTop()&&!refreshLayout.isRefreshing();
            }
        });

    }

    private void initRecycleView() {
        List<MinelistInfo> datas = new ArrayList<>();
        datas.add(new MinelistInfo(R.mipmap.mine_info, getString(R.string.mine_info), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.currency_assets, getString(R.string.currency_assets), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.distributor_manager, getString(R.string.dealer_managment), R.mipmap
                .more_arrow));
        datas.add(new MinelistInfo(R.mipmap.ic_chain_withdraw, getString(R.string.chain_withdraw), R.mipmap.more_arrow));
//        if(CommonUtil.getLoginInfo().getUser().getIsDealer()==2){
//            datas.add(new MinelistInfo(R.mipmap.distributor_manager, getString(R.string.dealer_managment), R.mipmap
//                    .more_arrow));
//        }
        datas.add(new MinelistInfo(R.mipmap.mine_recode, getString(R.string.mine_recode), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.system_notice, getString(R.string.system_notice), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.hot_topic, getString(R.string.hot_topic), R.mipmap
                .more_arrow));
        datas.add(new MinelistInfo(R.mipmap.help_center, getString(R.string.help_center), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.connect_service, getString(R.string.connect_service), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.software_info, getString(R.string.software_info), R.mipmap.more_arrow));

        MineRecyAdapter mineRecyAdapter = new MineRecyAdapter(mContext, datas, R.layout.mine_item);
        mineRv.setLayoutManager(new LinearLayoutManager(mContext));
        mineRv.setAdapter(mineRecyAdapter);
        mineRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0://个人信息
                    CommonUtil.gotoActivity(mContext, PersonInfoActivity.class);
                    break;
                case 1://币种资产
                    CommonUtil.gotoActivity(mContext, CurrencyAssetsActivity.class);
                    break;
                case 2://经销商管理
                    CommonUtil.gotoActivity(mContext, DealerManagementActivity.class);
                    break;
                case 3://链提取
                    CommonUtil.gotoActivity(mContext, ChainWithdrawActivity.class);
                    break;
                case 4://我的记录
                    CommonUtil.gotoActivity(mContext, MineRecodeActivity.class);
                    break;
                case 5://系统公告
                    CommonUtil.gotoActivity(mContext, SystemNoticeActivity.class);
                    break;
                case 6://热门话题
                    CommonUtil.gotoActivity(mContext, HotTopicActivity.class);
                    break;
                case 7://帮助中心
                    CommonUtil.gotoActivity(mContext, HelpCenterActivity.class);
                    break;
                case 8://联系客服
                    CommonUtil.gotoActivity(mContext, ContactServiceActivity.class);
                    break;
                case 9://软件信息
                    CommonUtil.gotoActivity(mContext, SoftwareInfoActivity.class);
                    break;
            }
        });
    }

    private void initStatus() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            mineLl.addView(statusView, 0, lp);
        }
    }

    @Override
    protected void initData() {
        getMineInfo();
    }

    private void getMineInfo() {
        presenter.getMineInfo(1, false);
    }


    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_mine;
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
        if(refreshLayout.isRefreshing()){
            refreshLayout.refreshComplete();
        }
        LogUtil.e(response.toString());
        MineRes mineRes = (MineRes) response;
        if(mineRes!=null){
            MineRes.UserInfoBean userInfoBean = mineRes.getUserInfo();
            mine_userAccount_tv.setText(userInfoBean.getUserAccount()+"");
            mine_totalUserBalance_tv.setText(userInfoBean.getTotalUserBalance()+"");
            mine_userBalance_tv.setText(userInfoBean.getUserBalance()+"");
            mine_userBalanceLock_tv.setText(userInfoBean.getUserBalanceLock()+"");
        }
        refreshLayout.refreshComplete();
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        refreshLayout.refreshComplete();
        super.onError(errorMsg, code, tag, o);
        refreshLayout.refreshComplete();
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
