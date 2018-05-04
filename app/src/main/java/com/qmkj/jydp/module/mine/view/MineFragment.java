package com.qmkj.jydp.module.mine.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.MinelistInfo;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.MineRecyAdapter;
import com.qmkj.jydp.ui.widget.MyRecycleView;
import com.qmkj.jydp.util.CommonUtil;
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


    @BindView(R.id.mine_rv)
    MyRecycleView mineRv;
    @BindView(R.id.mine_ll)
    LinearLayout mineLl;
    @BindView(R.id.mine_distributor_tv)
    TextView mine_distributor_tv;

    @Override
    protected void initView() {
//        initStatus();
        initRecycleView();
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_gray_1))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        mine_distributor_tv.setBackground(shapeSelector.create());
    }

    private void initRecycleView() {
        List<MinelistInfo> datas = new ArrayList<>();
        datas.add(new MinelistInfo(R.mipmap.mine_info, getString(R.string.mine_info), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.currency_assets, getString(R.string.currency_assets), R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.distributor_manager, getString(R.string.dealer_managment), R.mipmap
                .more_arrow));
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
                case 3://我的记录
                    CommonUtil.gotoActivity(mContext, MineRecodeActivity.class);
                    break;
                case 4://系统公告
                    CommonUtil.gotoActivity(mContext, SystemNoticeActivity.class);
                    break;
                case 5://热门话题
                    CommonUtil.gotoActivity(mContext, HotTopicActivity.class);
                    break;
                case 6://帮助中心
                    CommonUtil.gotoActivity(mContext, HelpCenterActivity.class);
                    break;
                case 7://联系客服
                    CommonUtil.gotoActivity(mContext, ContactServiceActivity.class);
                    break;
                case 8://软件信息
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

    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
    }

}
