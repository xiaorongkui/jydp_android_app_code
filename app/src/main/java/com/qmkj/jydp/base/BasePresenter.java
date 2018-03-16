package com.qmkj.jydp.base;

import com.qmkj.jydp.ui.activity.ViewInterface;

/**
 * mvp模式中的中间层连接model和view的桥梁
 */
public abstract class BasePresenter<V extends ViewInterface> {

    protected V mView;

    public String getName() {
        return mView.getClass().getSimpleName();
    }

    public void detachView(V view) {
        mView = null;
    }

    public void attachView(V view) {
        this.mView = view;
    }
}
