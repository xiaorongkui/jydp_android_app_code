package com.qmkj.jydp.module.mine.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.SystemHotRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemHotRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:热门话题
 */

public class HotTopicActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.system_hot_rv)
    RecyclerView systemNoticeRv;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    private ArrayList<SystemHotRes.SystemHotListBean> datas;
    private SystemHotRecyAdapter systemHotAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getSystemHotInfo(req,1,true);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.hot_topic));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_hot_topic;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        datas = new ArrayList();
        systemHotAdapter = new SystemHotRecyAdapter(mContext, datas, R.layout
                .mine_system_notice_item);
        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        systemHotAdapter.setEmptyView(mEmptyView);
        systemNoticeRv.setLayoutManager(new LinearLayoutManager(mContext));
        systemNoticeRv.setAdapter(systemHotAdapter);
    }
    @Override
    public boolean isImmersiveStatusBar() {
        return true;
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
       SystemHotRes systemHotRes =  (SystemHotRes) response;
       if(systemHotRes.getSystemHotList()!=null){
           systemHotAdapter.addData(systemHotRes.getSystemHotList());
           systemHotAdapter.notifyDataSetChanged();
       }

    }
}
