package com.qmkj.jydp.base;

/**
 * mvp模式中的中间层连接model和view的桥梁
 */
public interface BasePresenter<V extends BaseView> {


    void detachView();

    void attachView(V view);
}
