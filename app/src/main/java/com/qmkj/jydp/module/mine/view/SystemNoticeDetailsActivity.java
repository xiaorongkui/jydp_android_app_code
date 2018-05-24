package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;

import butterknife.BindView;

public class SystemNoticeDetailsActivity extends BaseMvpActivity {
    public static final String ACTIVITY_TITLE_KEY = "activity_title_key";
    public final static String NOTICE_TITTLE = "notice_tittle" ;
    public final static String NOTICE_TIMES = "notice_time" ;
    public final static String NOTICE_DETAILS = "notice_details" ;
    private String activityTitleStr;
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
        if (getIntent().getExtras().containsKey(ACTIVITY_TITLE_KEY)) {
            activityTitleStr = getIntent().getExtras().getString(ACTIVITY_TITLE_KEY);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(activityTitleStr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notice_details;
    }

    @Override
    protected void initView() {
        notice_details_tittle_tv.setText(getIntent().getStringExtra(NOTICE_TITTLE));
        notice_details_time_tv.setText(getIntent().getStringExtra(NOTICE_TIMES));
        notice_details_meg_tv.setText(Html.fromHtml(getIntent().getStringExtra(NOTICE_DETAILS)));

    }

    @Override
    protected void injectPresenter() {

    }
}
