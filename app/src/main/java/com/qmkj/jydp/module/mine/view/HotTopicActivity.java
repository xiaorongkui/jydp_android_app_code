package com.qmkj.jydp.module.mine.view;


import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.SystemHotRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.module.mine.presenter.SystemHotRecyAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:热门话题
 */

public class HotTopicActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    public final static String HOT_TOPIC_OBJECT = "hot_object" ;

    private ArrayList<SystemHotRes.SystemHotListBean> datas;
    private SystemHotRecyAdapter systemHotAdapter;

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
    public String getTittle() {
        return CommonUtil.getString(R.string.hot_topic);
    }

    @Override
    public List getData() {
        return datas;
    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        datas = new ArrayList();
        systemHotAdapter = new SystemHotRecyAdapter(mContext, datas, R.layout
                .mine_system_notice_item);
        systemHotAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HotTopicActivity.this,SystemNoticeDetailsActivity.class);
                intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TITTLE,
                        systemHotAdapter.getData().get(position).getNoticeTitle());
                intent.putExtra(SystemNoticeDetailsActivity.NOTICE_TIMES,
                        DateUtil.longToTimeStr(systemHotAdapter.getData().get(position).getAddTime(),
                                DateUtil.dateFormat2));
                intent.putExtra(SystemNoticeDetailsActivity.NOTICE_DETAILS,
                        systemHotAdapter.getData().get(position).getContent());
                CommonUtil.gotoActivity(mContext,intent);
            }
        });
        return systemHotAdapter;
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
