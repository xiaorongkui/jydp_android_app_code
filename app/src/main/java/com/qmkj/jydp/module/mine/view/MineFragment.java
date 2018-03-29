package com.qmkj.jydp.module.mine.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.bean.MinelistInfo;
import com.qmkj.jydp.module.mine.presenter.MineRecyAdapter;
import com.qmkj.jydp.ui.widget.MyRecycleView;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:我的页面功能
 */

public class MineFragment extends MvpBaseFragment {


    @BindView(R.id.mine_rv)
    MyRecycleView mineRv;
    @BindView(R.id.mine_ll)
    LinearLayout mineLl;

    @Override
    protected void initView() {
        initStatus();
        initRecycleView();
    }

    private void initRecycleView() {
        List<MinelistInfo> datas = new ArrayList();
        datas.add(new MinelistInfo(R.mipmap.mine_info, "个人信息", R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.mine_info, "币种资产", R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.mine_recod, "我的记录", R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.mine_withdrawals, "立即提现", R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.system_notice, "系统公告", R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.help_center, "帮助中心", R.mipmap.more_arrow));
        datas.add(new MinelistInfo(R.mipmap.connect_service, "联系客服", R.mipmap.more_arrow));

        MineRecyAdapter mineRecyAdapter = new MineRecyAdapter(mContext, datas, R.layout.mine_item);
        mineRv.setLayoutManager(new LinearLayoutManager(mContext));
        mineRv.setAdapter(mineRecyAdapter);
        mineRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3://立即提现
                    CommonUtil.gotoActivity(mContext, CurrencyWithDrawalActivity.class);
                    break;
                case 4://系统公告
                    CommonUtil.gotoActivity(mContext, SystemNoticeActivity.class);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
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
