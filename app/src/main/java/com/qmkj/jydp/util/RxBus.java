package com.qmkj.jydp.util;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;

/**
 * The type Rx bus. 用于组件间的事件传递
 */
public class RxBus {
    private static volatile RxBus instance;
    private final Relay<Object> mBus;

    /**
     * Instantiates a new Rx bus.
     */
    public RxBus() {
        this.mBus = PublishRelay.create().toSerialized();
    }

    /**
     * Gets default.
     *
     * @return the default
     */
    public static RxBus getDefault() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = Holder.BUS;
                }
            }
        }
        return instance;
    }

    /**
     * Post.
     *
     * @param obj the obj
     */
    public void post(Object obj) {
        mBus.accept(obj);
    }

    /**
     * To observable observable.
     *
     * @param <T>    the type parameter
     * @param tClass the t class
     * @return the observable
     */
    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    /**
     * To observable observable.
     *
     * @return the observable
     */
    public Observable<Object> toObservable() {
        return mBus;
    }

    /**
     * Has observers boolean.
     *
     * @return the boolean
     */
    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

}