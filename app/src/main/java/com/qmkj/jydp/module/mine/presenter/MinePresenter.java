package com.qmkj.jydp.module.mine.presenter;


import android.content.Context;
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
    public MinePresenter(Context context) {
        super(context);
    }

    public void getMineInfo(int tag, boolean isShowProgress) {

        sendHttpRequest(mineService.getMineInfo(), tag);
    }

    public void loginOut(int tag, boolean isShowProgress){
        sendHttpRequest(mineService.loginOut(), tag);
    }

    public void getCurrencyAssetsInfo(int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getCurrencyAssetsInfo(), tag);
    }

    public void getDealerManagmentInfo(int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getDealerManagmentInfo(), tag);
    }

    public void getTradeCenterInfo(String id,int tag, boolean isShowProgress){
        sendHttpRequest(mineService.getTradeCenterInfo(id), tag);
    }

}
