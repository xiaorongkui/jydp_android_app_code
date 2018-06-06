package com.qmkj.jydp.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.qmkj.jydp.R;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yun on 2018/3/13 0013.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
    protected Activity mContext;
    private Unbinder unbinder;
    private View mNetErrorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (isImmersiveStatusBar())
            CommonUtil.setStatusBar(this, CommonUtil.getColor(immersiveStatusBarColor()));
        unbinder = ButterKnife.bind(this);
        if (savedInstanceState != null) {
            initSavedInstanceState(savedInstanceState);
        }
        if (getIntent() != null) {
            initIntentData(getIntent());
        }
        onTitleBackPress();
        initView();
        initTitle();
        initData();
    }

    protected void onTitleBackPress() {
        View backView = findViewById(R.id.title_left_back);
        if (backView != null) {
            backView.setOnClickListener(v -> AppManager.getInstance().removeCurrent());
        }
    }

    /**
     * 是否使用沉浸式状态栏，默认不使用
     */
    public boolean isImmersiveStatusBar() {
        return true;
    }

    /**
     * 沉浸式状态栏颜色，默认蓝色
     */
    protected int immersiveStatusBarColor() {
        return R.color.status_bar_color;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    protected abstract void initTitle();

    /**
     * 初始化Activity异常销毁保存的数据
     */
    public void initSavedInstanceState(Bundle bundle) {

    }

    /**
     * 初始化intent传递的数据
     */
    public void initIntentData(Intent intent) {

    }

    /**
     * 获取布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    protected void toast(int res) {
        toast(mContext.getResources().getText(res));
    }

    protected void toast(CharSequence res) {
        ToastUtil.showShort(res);
    }

    /**
     * Show net error view.
     *
     * @param view   被错误界面替换
     * @param isShow 是否显示错误界面
     */
    protected void showNetErrorView(ViewGroup view, boolean isShow) {
        if (mNetErrorView == null) {
            mNetErrorView = View.inflate(mContext, getNetErrorLayoutRes(), null);
            mNetErrorView.setOnClickListener(v -> tryData(view.getId()));
        }
        if (isShow) {
            view.setVisibility(View.GONE);
            ViewGroup showViewParent = (ViewGroup) view.getParent();
            int indexOfChild = showViewParent.indexOfChild(view);
            int indexOfChildError = showViewParent.indexOfChild(mNetErrorView);
            if (indexOfChildError < 0)//表示当前错误界面不存在
                showViewParent.addView(mNetErrorView, indexOfChild);
        } else {
            view.setVisibility(View.VISIBLE);
            mNetErrorView.setVisibility(View.GONE);
        }

    }

    /**
     * Show net error view.
     *
     * @param view   将view隐藏
     * @param isShow 是否显示成功界面 true显示成功页面；false将页面隐藏
     */
    protected void showSuccessView(ViewGroup view, boolean isShow) {
        view.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    //点击错误界面时触发刷新
    protected void tryData(int id) {
    }

    /*可以自定义错误界面*/
    protected int getNetErrorLayoutRes() {
        return R.layout.net_load_error;
    }

    @Override
    public void onClick(View v) {
    }
}
