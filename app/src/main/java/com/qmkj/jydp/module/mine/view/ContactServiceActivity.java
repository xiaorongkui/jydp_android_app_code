package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.module.mine.presenter.ContactServiceRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:联系客服
 */

public class ContactServiceActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.contact_service_rv)
    RecyclerView contactServiceRv;
    private ContactServiceRecyAdapter contactServiceRecyAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getCustomerServiceInfo(req,1,true);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.connect_service));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_contact_service;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        ArrayList<CustomerServiceRes.UserFeedbackListBean> datas = new ArrayList();

        contactServiceRecyAdapter = new ContactServiceRecyAdapter(mContext, datas, R.layout
                .mine_contact_service_item);
        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        contactServiceRecyAdapter.setEmptyView(mEmptyView);
        contactServiceRv.setLayoutManager(new LinearLayoutManager(mContext));
        contactServiceRv.setAdapter(contactServiceRecyAdapter);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        CustomerServiceRes customerServiceRes = (CustomerServiceRes)response;
        if(customerServiceRes.getUserFeedbackList()!=null){
            contactServiceRecyAdapter.addData(customerServiceRes.getUserFeedbackList());
            contactServiceRecyAdapter.notifyDataSetChanged();
        }
    }
}
