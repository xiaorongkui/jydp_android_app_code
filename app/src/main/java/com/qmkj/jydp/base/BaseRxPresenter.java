package com.qmkj.jydp.base;

import android.content.Context;

import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.net.HttpCallBack;
import com.qmkj.jydp.net.api.BaseNetFunction;
import com.qmkj.jydp.net.exception.HandlerException;
import com.qmkj.jydp.net.observer.BaseShowLoadingObserver;
import com.qmkj.jydp.util.ProgressDialogUtil;
import com.qmkj.jydp.util.RxUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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

    private SoftReference<Context> contextSoftReference;

    public BaseRxPresenter(Context context) {

        if ((context instanceof RxAppCompatActivity)) {
            this.contextSoftReference = new SoftReference<>(context);
        }
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

        BaseShowLoadingObserver progressObserver = new BaseShowLoadingObserver<>(contextSoftReference.get(), this,
                isShowProgress, tag);
        progressObserver.setCancel(isCancel);

        mObservable.compose(RxUtil.rxSchedulerHelper())
                .map(baseNetFunction)
                .subscribe(progressObserver);

        addSubscribe(progressObserver);
    }

    /**
     * Send http request.默认不可以取消对话框，显示进度框
     *
     * @param mObservable the m observable
     * @param tag         the tag
     */
    protected void sendHttpRequest(Observable mObservable, int tag) {
        sendHttpRequest(mObservable, tag, true, false);
    }

    /**
     * Send http request.
     *
     * @param mObservable    the m observable
     * @param isShowProgress the is show progress
     * @param tag            the tag
     */
    protected void sendHttpRequest(Observable mObservable, int tag, boolean isShowProgress) {
        sendHttpRequest(mObservable, tag, isShowProgress, false);
    }

    @Override
    public void onNext(Object o, int tag) {
        mView.onSuccess(o, tag);
    }

    @Override
    public void onError(HandlerException.ResponeThrowable e, int tag) {
        switch (e.getCode()) {
            case NetResponseCode.HMC_SUCCESS_NULL:
                onNext(new Object(), tag);
                break;
            default:
                mView.onError(e.getMessage(), e.getCode(), tag, e.getData());
                break;
        }
    }

    @Override
    public void onCancel(int tag) {

    }
}
