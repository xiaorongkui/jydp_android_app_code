package com.qmkj.jydp.module.home.presenter;


import android.support.v4.app.Fragment;

import com.qmkj.jydp.base.BaseRxPresenter;
import com.qmkj.jydp.base.BaseView;
import com.qmkj.jydp.bean.request.BaseReq;
import com.qmkj.jydp.net.api.HomeService;

import java.util.Map;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class HomePresenter extends BaseRxPresenter<BaseView> {
    @Inject
    HomeService homeService;

    @Inject
    public HomePresenter(Fragment fragment) {
        super(fragment);
    }

    public void getCurrentPrice(int tag, boolean isShowProgress) {
        sendHttpRequest(homeService.getCurrentPrice(), tag, isShowProgress);
    }

}
