package com.qmkj.jydp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/6.
 */
public abstract class BaseFragment extends RxFragment {

    protected Unbinder unbinder;
    protected View rootView;
    protected Activity mContext;

    protected boolean isViewInitiated = false;
    protected boolean isVisibleToUser = false;
    protected boolean isDataInitiated = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (rootView == null) {// 生命周期方法会反复调用，但如果反复去加载布局是浪费，所以避免反复加载
            rootView = inflater.inflate(getLayoutId(), null);
            unbinder = ButterKnife.bind(this, rootView);
            initView();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isViewInitiated) {
            initTitle();
            isViewInitiated = true;
            prepareFetchData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isViewInitiated = false;
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    protected abstract void initView();

    protected abstract void initData();

    protected void initTitle() {
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        LogUtil.i("isVisibleToUser=" + isVisibleToUser + ";isViewInitiated=" + isViewInitiated +
                ";isDataInitiated=" + isDataInitiated);
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            initData();//走网络请求数据
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    public abstract int getLayoutId();

    protected void toast(int res) {
        toast(mContext.getResources().getText(res));
    }

    protected void toastdefine(int str) {
        toastdefine(mContext.getResources().getText(str));
    }

    protected void toastdefine(CharSequence s) {
        ToastUtil.showDefinedToast(mContext, s);
    }

    protected void toast(CharSequence res) {
        ToastUtil.showShort(res);
    }

    protected abstract String getSimpleNme();

    protected boolean isNeedCountPage() {
        return true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        prepareFetchData();
        LogUtil.i("onHiddenChanged=" + hidden);
    }
}
