package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseActivity;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemNoticeActivity extends MvpBaseActivity {

    @BindView(R.id.system_notice_rv)
    RecyclerView systemNoticeRv;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {
        initRecycleView();
    }

    private void initRecycleView() {
        List datas = new ArrayList();

        for (int i = 0; i < 10; i++) {
            datas.add(null);
        }
        SystemNoticeRecyAdapter systemNoticeAdapter = new SystemNoticeRecyAdapter(mContext, datas, R.layout
                .system_notice_item);
        systemNoticeRv.setLayoutManager(new LinearLayoutManager(mContext));
        systemNoticeRv.setAdapter(systemNoticeAdapter);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.system_notice));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notice;
    }

    @Override
    protected void initView() {

    }

}
