package com.qmkj.jydp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.qmkj.jydp.R;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.net.HttpCore;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.RxPermissionUtils;
import com.qmkj.jydp.util.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Created by Yun on 2018/3/13 0013.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
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
        View backView = findViewById(R.id.title_left_back);
        if (backView != null) {
            backView.setOnClickListener(v -> AppManager.getInstance().removeCurrent());
        }

        initView();
        initTitle();
        initData();
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
        HttpCore.getInstance().unregisterObserver();
    }

    protected abstract void initData();

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


    protected void toast(int res) {
        toast(mContext.getResources().getText(res));
    }


    protected void toastdefine(int str) {
        toastdefine(mContext.getResources().getText(str));
    }

    protected void toast(CharSequence res) {
        ToastUtil.showShort(res);
    }

    protected void toastdefine(CharSequence s) {
        ToastUtil.showDefinedToast(mContext, s);
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

    //点击错误界面时触发刷新
    protected void tryData(int id) {
        toast("点击重新加载");
    }

    //可以自定义错误界面
    protected int getNetErrorLayoutRes() {
        return R.layout.net_load_error;
    }

}
