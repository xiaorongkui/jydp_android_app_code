package com.qmkj.jydp.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/22
 * @author Yi Shan Xiang
 * 文件名称： BaseRefreshRecycleMvpActivity
 * email: 380948730@qq.com
 */

public abstract class BaseRefreshRecycleMvpActivity<T extends BasePresenter> extends BaseMvpActivity<T>
        implements XRefreshLayout.OnRefreshListener{
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.system_hot_rv)
    RecyclerView systemNoticeRv;
    @BindView(R.id.dealer_management_refresh)
    XRefreshLayout dealer_management_refresh;

    @BindView(R.id.dealer_publish_advertise_bt)
    Button dealerPublishAdvertiseBt;

    @Override
    public boolean isImmersiveStatusBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_base_hot_topic;
    }

    @Override
    protected void initTitle() {
        if(getTittle()!=null){
            titleHeaderTv.setText(getTittle());
        }
    }

    @Override
    protected void initView() {
        dealer_management_refresh.setOnRefreshListener(this);
        BaseRecycleAdapter adapter= getRecycleAdapter();
        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        adapter.setEmptyView(mEmptyView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        systemNoticeRv.setLayoutManager(layoutManager);
        systemNoticeRv.setAdapter(adapter);

        View inflate = View.inflate(mContext, R.layout.more_view, null);
        adapter.addFooterView(inflate);
    }

    @Override
    public void onRefresh() {
        initData();
    }


    public Button getBottomButton(){
        return dealerPublishAdvertiseBt;
    }

    @Override
    public boolean checkCanDoRefresh(View content, View header) {
        return !dealer_management_refresh.isRefreshing();
    }

    public abstract BaseRecycleAdapter getRecycleAdapter();

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        if(dealer_management_refresh.isRefreshing()){
            if(getData()!=null){
                getData().clear();
            }
            dealer_management_refresh.refreshComplete();
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        if(dealer_management_refresh.isRefreshing()){
            dealer_management_refresh.refreshComplete();
        }
    }

    public abstract List getData();
    public abstract String getTittle();
}
