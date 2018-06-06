package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.HelpCenterReq;
import com.qmkj.jydp.bean.response.HelpCenterRes;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.DateUtil;

import butterknife.BindView;

/**
 * 创建日期：2018/6/6
 * @author Yi Shan Xiang
 * 文件名称： 帮助中心详情界面 此界面暂时没有使用，采用的是web界面来加载详情
 * email: 380948730@qq.com
 */
public class HelpCenterDetailsActivity extends BaseMvpActivity<MinePresenter> {
    public static final String ACTIVITY_TITLE_KEY = "activity_title_key";
    public static final String ID_KEY = "id_key";
    private String activityTitleStr;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.help_details_tittle_tv)
    TextView help_details_tittle_tv;
    @BindView(R.id.help_details_time_tv)
    TextView help_details_time_tv;
    @BindView(R.id.help_details_meg_tv)
    TextView help_details_meg_tv;
    @BindView(R.id.webview)
    WebView webview;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        id = getIntent().getExtras().getString(ID_KEY);
        activityTitleStr = getIntent().getExtras().getString(ACTIVITY_TITLE_KEY);
        super.onCreate(savedInstanceState);
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
    }

    @Override
    protected void initData() {
        HelpCenterReq helpCenterReq = new HelpCenterReq();
        helpCenterReq.setHelpId(id);
        presenter.getHelpCenterInfo(helpCenterReq, 1, false);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(activityTitleStr);
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

        HelpCenterRes centerRes = (HelpCenterRes) response;
        if (centerRes.getSystemHelpDO() != null) {
            help_details_tittle_tv.setText(centerRes.getSystemHelpDO().
                    getHelpTitle());
            help_details_time_tv.setText(DateUtil.longToTimeStr(
                    centerRes.getSystemHelpDO().getAddTime(),
                    DateUtil.dateFormat2));
//            help_details_meg_tv.setText(Html.fromHtml(centerRes.getSystemHelpDO().getContent()));
            webview.loadDataWithBaseURL(AppNetConfig.BASE_URL + AppNetConfig.urlPath, centerRes.getSystemHelpDO()
                            .getContent(),
                    "text/html", "utf-8", null);
        }
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
