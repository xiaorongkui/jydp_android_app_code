package com.qmkj.jydp.module.mine.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseActivity;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:币种提现界面
 */

public class CurrencyWithDrawalActivity extends MvpBaseActivity {
    @BindView(R.id.currency_withdrawals_header)
    LinearLayout currency_withdrawals_header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(R.style.mainActivityTheme);
            CommonUtil.setStatusBarInvisible(this, false);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_currency_withdrawals;
    }

    @Override
    protected void initView() {
        initStatusBar();
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(mContext);
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            currency_withdrawals_header.addView(statusView, 0, lp);
        }
    }

    @Override
    public boolean isImmersiveStatusBar() {
        return true;
    }

}
