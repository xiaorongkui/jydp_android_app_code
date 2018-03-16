package com.qmkj.jydp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qmkj.jydp.R;
import com.qmkj.jydp.manager.AppManager;
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
    public List<Disposable> disposableList = new ArrayList<>();
    protected Activity mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.getInstance().addActivity(this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        for (Disposable disposable : disposableList) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
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


}
