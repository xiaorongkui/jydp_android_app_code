package com.qmkj.jydp.module.mine.presenter;


import android.support.v4.app.Fragment;

import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.net.api.MineService;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/4/8
 * email：dovexiaoen@163.com
 * description:
 */


public class MinePresenter extends BaseRxPresenter {
    @Inject
    MineService mineService;

    @Inject
    public MinePresenter(Fragment fragment) {
        super(fragment);
    }

    public void getMineInfo(int tag, boolean isShowProgress) {

        sendHttpRequest(mineService.getMineInfo(), tag);
    }
}
