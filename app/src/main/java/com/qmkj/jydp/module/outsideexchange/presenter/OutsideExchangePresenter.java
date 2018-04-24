package com.qmkj.jydp.module.outsideexchange.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.base.BaseView;

import javax.inject.Inject;

/**
 * @author wujiangming
 * @date 2018/4/23
 * @desc
 */

public class OutsideExchangePresenter extends BaseRxPresenter<BaseView> {
    @Inject
    public OutsideExchangePresenter(Fragment fragment) {
        super(fragment);
    }
}
