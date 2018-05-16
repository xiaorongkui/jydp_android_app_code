package com.qmkj.jydp.module.exchangoutsidee.presenter;

import android.content.Context;
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
    public OutsideExchangePresenter(Context context) {
        super(context);
    }
}
