package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemNoticeRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/22
 * email：dovexiaoen@163.com
 * description:系统公告
 */

public class SystemNoticeActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
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
        systemNoticeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SystemNoticeActivity.this,SystemNoticeDetailsActivity.class);
                intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TITTLE,
                        systemNoticeAdapter.getData().get(position).getNoticeTitle());
                intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TIMES,
                        DateUtil.longToTimeStr(systemNoticeAdapter.getData().get(position).getAddTime(),
                                DateUtil.dateFormat2));
                intent.putExtra(SystemNoticeDetailsActivity.NOTICE_DETAILS,
                        systemNoticeAdapter.getData().get(position).getContent());
                CommonUtil.gotoActivity(mContext,intent);
            }
        });
    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return systemNoticeAdapter;
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

    @Override
    public List getData() {
        return datas;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.system_notice);
    }
}
