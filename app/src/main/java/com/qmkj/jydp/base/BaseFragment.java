package com.qmkj.jydp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmkj.jydp.R;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment implements View.OnClickListener {

    private Unbinder unbinder;
    protected View rootView;
    protected Activity mContext;

    protected boolean isViewInitiated = false;
    protected boolean isVisibleToUser = false;
    protected boolean isDataInitiated = false;
    private View mNetErrorView;
    private boolean hidden;
    private boolean onResume;
    private boolean onPause;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
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
        this.onResume = true;
        this.onPause = false;
        onFragmentResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.onResume = false;
        this.onPause = true;
        onFragmentPause();
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
        onFragmentResume();
    }


    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (!isNeedLazyInitData()) {
            isVisibleToUser = true;
        }
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            initData();//走网络请求数据
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    /*当fragment被用户看到时，一定会调用的方法*/
    private void onFragmentResume() {
        if (isVisibleToUser && isViewInitiated && (!hidden || onResume)) {
            onViewResume();
            onResume = false;
        }
    }

    /*当fragment被用户看到时，一定会调用的方法*/
    private void onFragmentPause() {
        if (hidden || onPause) {
            onViewPause();
            onPause = false;
        }
    }

    protected void onViewResume() {
        LogUtil.i(getSimpleNme() + "onViewResume;onHiddenChanged=" + hidden + ";onResume=" + onResume);
    }

    protected void onViewPause() {
        LogUtil.i(getSimpleNme() + "onViewPause;onHiddenChanged=" + hidden + ";onPause=" + onPause);
    }

    //是否需要懒加载，如果需要必须是使用FragmentPageAdapter
    protected boolean isNeedLazyInitData() {
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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        prepareFetchData();
        onFragmentResume();
        onFragmentPause();
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
            mNetErrorView.setVisibility(View.VISIBLE);
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

    //可以自定义错误界面
    protected int getNetErrorLayoutRes() {
        return R.layout.net_load_error;
    }

    @Override
    public void onClick(View v) {
    }
}
