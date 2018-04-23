package com.qmkj.jydp.util;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus
 */
public class RxBus {

    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();
    private static volatile RxBus defaultInstance;

    private RxBus() {
    }

    public static RxBus getInstance() {
        RxBus rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBus();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }

    /**
     * 注册
     *
     * @param tag
     * @return
     */
    public <T> Flowable<T> register(@NonNull Class<T> tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        PublishSubject<T> subject = PublishSubject.create();

        subjectList.add(subject);
        return subject.toFlowable(BackpressureStrategy.BUFFER);
    }

    /**
     * 解除注册
     *
     * @param tag
     */
    public <T> void unregister(@NonNull Class<T> tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (subjects.isEmpty()) {
                subjectMapper.remove(tag);
            }
        }
    }

    /**
     * 发送消息
     *
     * @param event
     */
    public <T> void post(@NonNull Object event) {
        List<Subject> subjectList = subjectMapper.get(event.getClass());
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Subject subject : subjectList) {
                subject.onNext(event);
            }
        }
    }

    /**
     * 清除订阅
     */
    public void clear() {
        if (subjectMapper.isEmpty()) {
            return;
        }
        subjectMapper.clear();
    }
}
