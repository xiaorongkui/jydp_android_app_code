package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.HelpCenterBean;
import com.qmkj.jydp.bean.request.HelpCenterReq;
import com.qmkj.jydp.bean.response.HelpCenterRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;

import butterknife.BindView;

public class HelpCenterDetailsActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.help_details_tittle_tv)
    TextView help_details_tittle_tv;
    @BindView(R.id.help_details_time_tv)
    TextView help_details_time_tv;
    @BindView(R.id.help_details_meg_tv)
    TextView help_details_meg_tv;
    private HelpCenterBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bean = (HelpCenterBean)getIntent().
                        getSerializableExtra(HelpCenterActivity.HELP_CENTER_TAG);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        HelpCenterReq helpCenterReq = new HelpCenterReq();
        helpCenterReq.setHelpId(bean.getId());
        presenter.getHelpCenterInfo(helpCenterReq,1,true);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(bean.getName());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help_center_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);

        HelpCenterRes centerRes = (HelpCenterRes)response;
        if(centerRes.getSystemHelpDO()!=null){
            help_details_tittle_tv.setText(centerRes.getSystemHelpDO().
                    getHelpTitle());
            help_details_time_tv.setText(DateUtil.longToTimeStr(
                    centerRes.getSystemHelpDO().getAddTime(),
                    DateUtil.dateFormat2));
            help_details_meg_tv.setText(centerRes.getSystemHelpDO().getContent());

        }
    }
}
