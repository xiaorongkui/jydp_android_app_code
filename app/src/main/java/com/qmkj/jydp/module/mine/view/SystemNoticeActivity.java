package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemNoticeActivity extends BaseMvpActivity<MinePresenter> {

    @BindView(R.id.system_notice_rv)
    RecyclerView systemNoticeRv;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    private ArrayList<SystemNoticeRes.SystemNoticeListBean> datas;
    private SystemNoticeRecyAdapter systemNoticeAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getSystemNoticeInfo(req,1,true);

    }

    private void initRecycleView() {
        datas = new ArrayList();
        systemNoticeAdapter = new SystemNoticeRecyAdapter(mContext, datas, R.layout
                .mine_system_notice_item);
        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        systemNoticeAdapter.setEmptyView(mEmptyView);
        systemNoticeRv.setLayoutManager(new LinearLayoutManager(mContext));
        systemNoticeRv.setAdapter(systemNoticeAdapter);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.system_notice));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_system_notice;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }


    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        SystemNoticeRes systemNoticeRes = (SystemNoticeRes) response;
        if(systemNoticeRes.getSystemNoticeList()!=null){
            systemNoticeAdapter.addData(systemNoticeRes.getSystemNoticeList());
            systemNoticeAdapter.notifyDataSetChanged();
        }
    }
}
