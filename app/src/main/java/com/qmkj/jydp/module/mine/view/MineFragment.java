package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.ShadowLayout;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.MinelistInfo;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.bean.response.MineRes;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.DataManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.manager.SystemManager;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.module.mine.ChainWithdrawActivity;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.MineRecyAdapter;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.ui.widget.ScrollRecycleView;
import com.qmkj.jydp.ui.widget.SmoothScrollView;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.DensityHelper;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:我的页面功能
 */

public class MineFragment extends BaseMvpFragment<MinePresenter> {
    public static final int ACTIVITY_REQUEST_CODE = 101;
    private static final int REQUEST_GET_DATA = 1;

    @BindView(R.id.mine_info_rl)
    XRefreshLayout refreshLayout;
    @BindView(R.id.mine_info_sv)
    SmoothScrollView scrollView;  //滑动
    @BindView(R.id.mine_rv)
    ScrollRecycleView mineRv;
    @BindView(R.id.mine_ll)
    LinearLayout mineLl;
    @BindView(R.id.mine_assets_ll)
    LinearLayout mine_assets_ll;
    @BindView(R.id.mine_distributor_tv)
    TextView mine_distributor_tv;
    @BindView(R.id.mine_available_money_ll)
    LinearLayout mineAvailableMoneyLl;
    @BindView(R.id.mine_available_money_sl)
    ShadowLayout mine_available_money_sl;
    @BindView(R.id.mine_userAccount_tv)  //身份类别（是否是经销商）
            TextView mine_userAccount_tv;
    @BindView(R.id.mine_tittle_nptv)        //账号总资产
            NoPaddingTextView mine_tittle_nptv;
    @BindView(R.id.mine_totalUserBalance_tv)    //账号总资产
            NoPaddingTextView mine_totalUserBalance_tv;
    @BindView(R.id.mine_userBalance_tv)        //可用资产
            NoPaddingTextView mine_userBalance_tv;
    @BindView(R.id.mine_userBalanceLock_tv)     //冻结资产
            NoPaddingTextView mine_userBalanceLock_tv;

    @Override
    protected void initView() {
//        initStatus();
        initRefreshLayout();
        initRecycleView();
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x1))
                .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_gray_1))
                .setStrokeWidth((int) ResourcesManager.getDimen(R.dimen.x1))
                .setDefaultBgColor(ResourcesManager.getColor(R.color.color_white_1));
        mine_distributor_tv.setBackground(shapeSelector.create());

        if (DataManager.getLoginInfo() == null) {
            mine_tittle_nptv.setVisibility(View.GONE);
            mine_totalUserBalance_tv.setVisibility(View.GONE);
            mine_available_money_sl.setVisibility(View.GONE);
            mine_userAccount_tv.setText("还未登录，请先登录");
            mine_assets_ll.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    (int) (DensityHelper.pt2px(mContext, 134))));
        }

        if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser() != null && DataManager
                .getLoginInfo().getUser().getIsDealer() == 2) {
            mine_distributor_tv.setVisibility(View.VISIBLE);

        }

        mine_assets_ll.setOnClickListener(view -> {
            if (DataManager.getLoginInfo() == null) {
                CommonDialog commonDialog = new CommonDialog(mContext);
                commonDialog.setContentText("请先登录");
                commonDialog.setOnPositiveButtonClickListener((dialog, v) -> {
                    ActivityManager.gotoActivity(mContext, LoginActivity.class);
                    commonDialog.dismiss();
                });
                commonDialog.show();
            }
        });
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMineInfo();
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return scrollView.isTop() && !refreshLayout.isRefreshing();
            }
        });

    }

    /**
     * 初始化个人中心界面布局
     */
    private void initRecycleView() {
        List<MinelistInfo> datas = new ArrayList<>();
        datas.add(new MinelistInfo(R.mipmap.mine_info, getString(R.string.mine_info), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.currency_assets, getString(R.string.currency_assets), R.mipmap.more_arrow));
        if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser() != null && DataManager
                .getLoginInfo().getUser().getIsDealer() == 2) {
            datas.add(new MinelistInfo(R.mipmap.distributor_manager, getString(R.string.dealer_managment), R.mipmap
                    .more_arrow));
        }
        datas.add(new MinelistInfo(R.mipmap.ic_chain_withdraw, getString(R.string.chain_withdraw), R.mipmap
                .more_arrow));
        datas.add(new MinelistInfo(R.mipmap.mine_recode, getString(R.string.mine_recode), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.system_notice, getString(R.string.system_notice), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.hot_topic, getString(R.string.hot_topic), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.help_center, getString(R.string.help_center), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.connect_service, getString(R.string.connect_service), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.software_info, getString(R.string.software_info), R.mipmap.more_arrow));

        MineRecyAdapter mineRecyAdapter = new MineRecyAdapter(mContext, datas, R.layout.mine_item);
        mineRv.setLayoutManager(new LinearLayoutManager(mContext));
        mineRv.setAdapter(mineRecyAdapter);
        //是否是经销商
        if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser() != null && DataManager
                .getLoginInfo().getUser().getIsDealer() == 2) {
            mineRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
                if (DataManager.getLoginInfo() == null) {
                    CommonDialog commonDialog = new CommonDialog(mContext);
                    commonDialog.setContentText("请先登录");
                    commonDialog.setOnPositiveButtonClickListener((dialog, v) -> {
                        ActivityManager.gotoActivity(mContext, LoginActivity.class);
                        commonDialog.dismiss();
                    });
                    commonDialog.show();
                    return;
                }
                switch (position) {
                    case 0://个人信息
                        ActivityManager.gotoActivity(mContext, PersonInfoActivity.class);
                        break;
                    case 1://币种资产
                        Intent intent = new Intent(mContext, CurrencyAssetsActivity.class);
                        startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
                        break;
                    case 2://经销商管理
                        ActivityManager.gotoActivity(mContext, DealerManagementActivity.class);
                        break;
                    case 3://链提取
                        ActivityManager.gotoActivity(mContext, ChainWithdrawActivity.class);
                        break;
                    case 4://我的记录
                        ActivityManager.gotoActivity(mContext, MineRecodeActivity.class);
                        break;
                    case 5://系统公告
                        ActivityManager.gotoActivity(mContext, SystemNoticeActivity.class);
                        break;
                    case 6://热门话题
                        ActivityManager.gotoActivity(mContext, HotTopicActivity.class);
                        break;
                    case 7://帮助中心
                        ActivityManager.gotoActivity(mContext, HelpCenterActivity.class);
                        break;
                    case 8://联系客服
                        ActivityManager.gotoActivity(mContext, ContactServiceActivity.class);
                        break;
                    case 9://软件信息
                        ActivityManager.gotoActivity(mContext, SoftwareInfoActivity.class);
                        break;
                }
            });
        } else {
            mineRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
                if (DataManager.getLoginInfo() == null) {
                    CommonDialog commonDialog = new CommonDialog(mContext);
                    commonDialog.setContentText("请先登录");
                    commonDialog.setOnPositiveButtonClickListener((dialog, v) -> {
                        ActivityManager.gotoActivity(mContext, LoginActivity.class);
                        commonDialog.dismiss();
                    });
                    commonDialog.show();
                    return;
                }
                switch (position) {
                    case 0://个人信息
                        ActivityManager.gotoActivity(mContext, PersonInfoActivity.class);
                        break;
                    case 1://币种资产
                        Intent intent = new Intent(mContext, CurrencyAssetsActivity.class);
                        startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
                        break;
                    case 2://链提取
                        ActivityManager.gotoActivity(mContext, ChainWithdrawActivity.class);
                        break;
                    case 3://我的记录
                        ActivityManager.gotoActivity(mContext, MineRecodeActivity.class);
                        break;
                    case 4://系统公告
                        ActivityManager.gotoActivity(mContext, SystemNoticeActivity.class);
                        break;
                    case 5://热门话题
                        ActivityManager.gotoActivity(mContext, HotTopicActivity.class);
                        break;
                    case 6://帮助中心
                        ActivityManager.gotoActivity(mContext, HelpCenterActivity.class);
                        break;
                    case 7://联系客服
                        ActivityManager.gotoActivity(mContext, ContactServiceActivity.class);
                        break;
                    case 8://软件信息
                        ActivityManager.gotoActivity(mContext, SoftwareInfoActivity.class);
                        break;
                }
            });
        }

    }

    private void initStatus() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(ResourcesManager.getColor(R.color.status_bar_color));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, SystemManager.getStatusBarHeight());
            mineLl.addView(statusView, 0, lp);
        }
    }

    @Override
    protected void initData() {
//        getMineInfo();
    }

    /**
     * 获取个人中心数据
     */
    private void getMineInfo() {
        if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser() != null) {
            presenter.getMineInfo(REQUEST_GET_DATA, false);
        } else {
            refreshLayout.refreshComplete();
        }
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
        switch (tag) {
            case REQUEST_GET_DATA:
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.refreshComplete();
                }
                if (response == null) {
                    return;
                }
                LogUtil.e(response.toString());
                MineRes mineRes = (MineRes) response;
                MineRes.UserInfoBean userInfoBean = mineRes.getUserInfo();
                mine_userAccount_tv.setText(userInfoBean.getUserAccount() + "");
                mine_totalUserBalance_tv.setText(NumberUtil.doubleFormat(Double.parseDouble(userInfoBean
                        .getTotalUserBalance()), 4) + "");
                mine_userBalance_tv.setText(NumberUtil.doubleFormat(Double.parseDouble(userInfoBean
                        .getUserBalance()), 4) + "");
                mine_userBalanceLock_tv.setText(NumberUtil.doubleFormat(Double.parseDouble(userInfoBean
                        .getUserBalanceLock()), 4) + "");
                if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser() != null) {
                    LoginRes loginRes = DataManager.getLoginInfo();
                    loginRes.getUser().setIsDealer(mineRes.getIsDealer());
                    DataManager.setLoginInfo(loginRes);
                }
                if (mineRes.getIsDealer() == 2) { //是经销商
                    mine_distributor_tv.setVisibility(View.VISIBLE);
                } else {
                    mine_distributor_tv.setVisibility(View.GONE);
                }
                initRecycleView();
                refreshLayout.refreshComplete();
                break;
        }

    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        refreshLayout.refreshComplete();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //点击了链提取页面的去交易按钮，进去交易中心的去交易界面
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == CurrencyAssetsActivity.ACTIVITY_RESULT_CODE) {
            if ((getActivity()) != null) {
                ((MainActivity) getActivity()).setSelect(1);
            }
            String name = data.getStringExtra(CurrencyAssetsActivity.CURRENT_NAME);
            String id = data.getStringExtra(CurrencyAssetsActivity.CURRENT_ID);
            ((MainActivity) getActivity()).showExchangeFrament(name, id);

        }
    }

    /**
     * Fragment获取焦点时刷新数据
     */
    @Override
    protected void onViewResume() {
        super.onViewResume();
        getMineInfo();
    }

}
