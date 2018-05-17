package com.qmkj.jydp.module.home.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseActivity;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.ui.widget.CustomWebView;
import com.qmkj.jydp.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 加载web页
 *
 * @author neo.duan
 */
public class WebActivity extends BaseActivity {

    private static final String TITLE = "title";
    private static final String URL = "url";
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.web_container_fl)
    FrameLayout webContainerFl;

    private WebView mWebView;

    private String mUrl;
    private String mTitle;

    public static Intent getActivityIntent(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(URL, url);
        return intent;
    }

    @Override
    public void initIntentData(Intent intent) {
        mTitle = intent.getStringExtra(Constants.INTENT_PARAMETER_1);
        mUrl = intent.getStringExtra(Constants.INTENT_PARAMETER_2);
        LogUtil.i("title=" + mTitle + ";url=" + mUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        //用XML引入，仍然会内存泄露https://stackoverflow.com/questions/3130654/memory-leak-in-webview
        mWebView = new CustomWebView(getApplicationContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        webContainerFl.addView(mWebView);
    }


    @Override
    public void initData() {
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(mTitle);
    }

    @Override
    protected void onTitleBackPress() {
        View backView = findViewById(R.id.title_left_back);
        if (backView != null) {
            backView.setOnClickListener(v -> {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    AppManager.getInstance().removeCurrent();
                }
            });
        }
    }

    /*
         * 处理回退
         */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            // destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            try {
                mWebView.destroy();
            } catch (Throwable ex) {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
