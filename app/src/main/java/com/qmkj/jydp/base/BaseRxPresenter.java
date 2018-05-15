package com.qmkj.jydp.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.qmkj.jydp.net.HttpCallBack;
import com.qmkj.jydp.net.api.BaseNetFunction;
import com.qmkj.jydp.net.exception.HandlerException;
import com.qmkj.jydp.net.observer.BaseShowLoadingObserver;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.ProgressDialogUtil;
import com.qmkj.jydp.util.RxUtil;
import com.qmkj.jydp.util.ToastUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.ref.SoftReference;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author：rongkui.xiao --2018/4/3
 * email：dovexiaoen@163.com
 * description:rx网络请求的基类
 *
 * @param <T> the type parameter
 */
public class BaseRxPresenter<T extends BaseView> implements BasePresenter<T>, HttpCallBack {

    /**
     * The M view.
     */
    protected T mView;

    /**
     * The Base net function.
     */
    @Inject
    BaseNetFunction baseNetFunction;

    private SoftReference<RxAppCompatActivity> activitySoftReference;
    private SoftReference<RxFragment> fragmentSoftReference;

    private BaseRxPresenter() {
    }

    /**
     * Instantiates a new Base rx presenter.
     *
     * @param activity the activity
     */
    public BaseRxPresenter(Activity activity) {
        if (!(activity instanceof RxAppCompatActivity)) {
            throw new RuntimeException("创建RxPresenter失败,请用当前activity继承BaseActivity或RxAppCompatActivity");
        }
        this.activitySoftReference = new SoftReference<>((RxAppCompatActivity) activity);
    }

    /**
     * Instantiates a new Base rx presenter.
     *
     * @param fragment the fragment
     */
    public BaseRxPresenter(Fragment fragment) {
        if (!(fragment instanceof RxFragment)) {
            throw new RuntimeException("创建RxPresenter失败,请用当前Fragment继承BaseFragment或RxFragment");
        }
        this.fragmentSoftReference = new SoftReference<>((RxFragment) fragment);
    }

    /**
     * 解除activity和view的绑定
     */
    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
        ProgressDialogUtil.stopWaitDialog();
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    private CompositeDisposable mCompositeDisposable;

    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    /**
     * 添加Disposable到集合中
     *
     * @param subscription the subscription
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 处理http请求，所有的http请求入口
     *
     * @param mObservable    the m observable
     * @param isShowProgress 是否在网络请求时显示进度框
     * @param isCancel       是否可以取消对话框
     * @param tag            the tag
     */
    @SuppressWarnings("unchecked")
    protected void sendHttpRequest(Observable mObservable, int tag, boolean isShowProgress, boolean isCancel) {

        LifecycleTransformer<Object> lifecycleTransformer = null;
        Context context = null;
        if (activitySoftReference != null && activitySoftReference.get() != null) {
            context = activitySoftReference.get();
            lifecycleTransformer = activitySoftReference.get().bindToLifecycle();
        }
        if (fragmentSoftReference != null && fragmentSoftReference.get() != null) {
            context = fragmentSoftReference.get().getContext();
            lifecycleTransformer = fragmentSoftReference.get().bindToLifecycle();
        }
        if (lifecycleTransformer == null || context == null) {
            LogUtil.i("当前网络请求的context为空");
            return;
        }

        BaseShowLoadingObserver progressObserver = new BaseShowLoadingObserver<>(context, this,
                isShowProgress, tag);
        progressObserver.setCancel(isCancel);

        mObservable.compose(lifecycleTransformer)
                .compose(RxUtil.rxSchedulerHelper())
                .map(baseNetFunction)
                .subscribe(progressObserver);

        addSubscribe(progressObserver);
    }

    /**
     * Send http request.默认可以取消对话框，显示进度框
     *
     * @param mObservable the m observable
     * @param tag         the tag
     */
    protected void sendHttpRequest(Observable mObservable, int tag) {
        sendHttpRequest(mObservable, tag, true, true);
    }

    /**
     * Send http request.
     *
     * @param mObservable    the m observable
     * @param isShowProgress the is show progress
     * @param tag            the tag
     */
    protected void sendHttpRequest(Observable mObservable, int tag, boolean isShowProgress) {
        sendHttpRequest(mObservable, tag, isShowProgress, true);
    }

    @Override
    public void onNext(Object o, int tag) {
        mView.onSuccess(o, tag);
    }

    @Override
    public void onError(HandlerException.ResponeThrowable e, int tag) {
        mView.onError(e.getMessage(), e.getCode(), tag);

    }

    @Override
    public void onCancel(int tag) {

    }
}
