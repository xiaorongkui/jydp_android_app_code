package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.qmkj.jydp.R;
import com.qmkj.jydp.util.CommonUtil;


/**
 * @author neo.duan
 * @date 2018/1/6 10:25
 * @desc 自定义带进度条的WebView
 */
public class CustomWebView extends WebView {

    private ProgressBar progressbar;

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    private void init(Context context) {
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, CommonUtil.dp2px(2), 0, 0));
        progressbar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
        addView(progressbar);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        webSettings.setLoadWithOverviewMode(true);

        // 设置可以支持缩放
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);

        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        setWebViewClient(new CommonWebViewClient());
        setWebChromeClient(new CommonWebChromeClient());
    }

    class CommonWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            // handler.cancel();// Android默认的处理方式
            handler.proceed();// 接受所有网站的证书
            // handleMessage(Message msg);// 进行其他处理
        }
    }

    class CommonWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

}
