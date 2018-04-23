package com.qmkj.jydp.module.login.modle;

import com.qmkj.jydp.net.HttpCallBack;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Map;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginImpl implements Login {
    private final RxAppCompatActivity activity;

    public LoginImpl(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getCurrentPrice(final Map<String, Object> maps, HttpCallBack listener, boolean isShowProgress) {
//        BaseNetFunction api = new BaseNetFunction(listener, activity) {
//            @Override
//            public Observable getObservable(Retrofit retrofit) {
//                LoginService loginService = retrofit.create(LoginService.class);
//                return loginService.getCurrentPrice(maps);
//            }
//        };
//        api.setshowProgressDialog(isShowProgress);//是否显示对话框
//        HttpCore.getInstance().sendHttpRequest(api);
    }
}
