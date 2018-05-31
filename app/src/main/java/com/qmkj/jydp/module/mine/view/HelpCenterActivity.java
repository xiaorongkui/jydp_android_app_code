package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.HelpCenterBean;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.module.home.view.WebActivity;
import com.qmkj.jydp.module.mine.presenter.HelpCenterRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:帮助中心
 */

public class HelpCenterActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.help_center_rv)
    RecyclerView helpCenterRv;
    private ArrayList<HelpCenterBean> mData;
    private HelpCenterRecyAdapter helpCenterRecyAdapter;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.help_center));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_help_center;
    }

    @Override
    protected void initView() {
        initRecycleView();

    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        mData.add(new HelpCenterBean("注册协议","101010"));
        mData.add(new HelpCenterBean("联系我们","101013"));
        mData.add(new HelpCenterBean("公司简介","101014"));
        mData.add(new HelpCenterBean("充值流程","101015"));
        mData.add(new HelpCenterBean("注册指南","101016"));
        mData.add(new HelpCenterBean("交易指南","101017"));
        helpCenterRecyAdapter = new HelpCenterRecyAdapter(mContext, mData, R.layout.single_click_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        helpCenterRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        helpCenterRecyAdapter.setEmptyView(mEmptyView);
        helpCenterRv.setAdapter(helpCenterRecyAdapter);
        helpCenterRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = WebActivity.getActivityIntent(mContext, mData.get(position).getName()+"详情", AppNetConfig.HELP_CENTER_URL + mData.get(position).getId());
            CommonUtil.gotoActivity(mContext, intent);
        });
    }

}
