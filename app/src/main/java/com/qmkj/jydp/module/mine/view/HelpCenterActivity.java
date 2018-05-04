package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.HelpCenterRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:帮助中心
 */

public class HelpCenterActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.help_center_rv)
    RecyclerView helpCenterRv;
    private ArrayList<Object> mData;
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
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        helpCenterRecyAdapter = new HelpCenterRecyAdapter(mContext, mData, R.layout.single_click_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        helpCenterRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        helpCenterRecyAdapter.setEmptyView(mEmptyView);
        helpCenterRv.setAdapter(helpCenterRecyAdapter);
        helpCenterRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
        });
    }
}
