package com.qmkj.jydp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Created by Yun on 2018/3/13 0013.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public List<Disposable> disposableList = new ArrayList<>();
    protected Context mContext;
    private Unbinder unbinder;

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
    protected abstract int contentViewRes();

    /**
     * 初始化view
     */
    protected abstract void initView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(contentViewRes());
        unbinder = ButterKnife.bind(this);
        if (savedInstanceState != null) {
            initSavedInstanceState(savedInstanceState);
        }
        if (getIntent() != null) {
            initIntentData(getIntent());
        }
        initView();
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
}
