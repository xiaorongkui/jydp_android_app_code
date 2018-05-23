package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;

import butterknife.BindView;

public class SystemNoticeDetailsActivity extends BaseMvpActivity {
    public final static String NOTICE_TITTLE = "notice_tittle" ;
    public final static String NOTICE_TIMES = "notice_time" ;
    public final static String NOTICE_DETAILS = "notice_details" ;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.notice_details_tittle_tv)
    TextView notice_details_tittle_tv;
    @BindView(R.id.notice_details_time_tv)
    TextView notice_details_time_tv;
    @BindView(R.id.notice_details_meg_tv)
    TextView notice_details_meg_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.system_notice_details));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notice_details;
    }

    @Override
    protected void initView() {
        notice_details_tittle_tv.setText(getIntent().getStringExtra(NOTICE_TITTLE));
        notice_details_time_tv.setText(getIntent().getStringExtra(NOTICE_TIMES));
        notice_details_meg_tv.setText(getIntent().getStringExtra(NOTICE_DETAILS));

    }

    @Override
    protected void injectPresenter() {

    }
}
