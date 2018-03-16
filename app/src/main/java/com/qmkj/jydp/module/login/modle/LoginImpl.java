package com.qmkj.jydp.module.login.modle;

import com.qmkj.jydp.bean.BaseResponse;
import com.qmkj.jydp.net.HttpCore;
import com.qmkj.jydp.net.HttpOnNextListener;
import com.qmkj.jydp.net.api.BaseApi;
import com.qmkj.jydp.net.api.LoginService;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginImpl implements LoginInterface {
    private final RxAppCompatActivity activity;

    public LoginImpl(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getCurrentPrice(final Map<String, Object> maps, HttpOnNextListener listener, boolean isShowProgress) {
        BaseApi api = new BaseApi(listener, activity) {
            @Override
            public Observable getObservable(Retrofit retrofit) {
                LoginService loginService = retrofit.create(LoginService.class);
                return loginService.getCurrentPrice(maps);
            }
        };
        api.setshowProgressDialog(isShowProgress);//是否显示对话框
        HttpCore.getInstance().sendHttpRequest(api);
    }
}
