package com.qmkj.jydp.module.mine.view;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.common.AppNetConfig;

import butterknife.BindView;

/**
 * 创建日期：2018/6/7
 * @author Yi Shan Xiang
 * 文件名称： 系统公告详情
 * email: 380948730@qq.com
 */
public class SystemNoticeDetailsActivity extends BaseMvpActivity {
    public static final String ACTIVITY_TITLE_KEY = "activity_title_key";
    public final static String NOTICE_TITTLE = "notice_tittle";
    public final static String NOTICE_TIMES = "notice_time";
    public final static String NOTICE_DETAILS = "notice_details";
    private String activityTitleStr;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.notice_details_tittle_tv)
    TextView notice_details_tittle_tv;
    @BindView(R.id.notice_details_time_tv)
    TextView notice_details_time_tv;
    @BindView(R.id.notice_details_meg_tv)
    TextView notice_details_meg_tv;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        if (getIntent().getExtras().containsKey(ACTIVITY_TITLE_KEY)) {
            activityTitleStr = getIntent().getExtras().getString(ACTIVITY_TITLE_KEY);
        }
        titleHeaderTv.setText(activityTitleStr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notice_details;
    }

    @Override
    protected void initView() {
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                return true;
            }
        });
        notice_details_tittle_tv.setText(getIntent().getStringExtra(NOTICE_TITTLE));
        notice_details_time_tv.setText(getIntent().getStringExtra(NOTICE_TIMES));
//        notice_details_meg_tv.setText(Html.fromHtml(getIntent().getStringExtra(NOTICE_DETAILS)));
        webview.loadDataWithBaseURL(AppNetConfig.BASE_URL + AppNetConfig.urlPath, getIntent().getStringExtra
                        (NOTICE_DETAILS),
                "text/html", "utf-8", null);
    }

    @Override
    protected void injectPresenter() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webview != null) {
            // destory()
            ViewParent parent = webview.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webview);
            }
            webview.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webview.getSettings().setJavaScriptEnabled(false);
            webview.clearHistory();
            webview.clearView();
            webview.removeAllViews();
            try {
                webview.destroy();
            } catch (Throwable ex) {

            }
        }
    }
}
