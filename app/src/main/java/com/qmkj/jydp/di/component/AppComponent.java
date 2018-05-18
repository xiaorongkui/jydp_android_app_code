package com.qmkj.jydp.di.component;


import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.di.module.AppModule;
import com.qmkj.jydp.di.module.HttpModule;
import com.qmkj.jydp.net.api.BaseNetFunction;
import com.qmkj.jydp.net.api.ExchangeService;
import com.qmkj.jydp.net.api.HomeService;
import com.qmkj.jydp.net.api.LoginService;
import com.qmkj.jydp.net.api.MineService;
import com.qmkj.jydp.util.SPHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 引入dragger2依赖注入，创建全局对象
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    JYDPExchangeApp getContext();

    LoginService provideLoginService();

    HomeService provideHomeService();

    ExchangeService provideExchangeService();

    MineService provideMineService();

    BaseNetFunction provideBaseNetFunction();

    SPHelper providePreferencesHelper();

//    LoginPresenter provideLoginPresenter();
//
//    HomePresenter provideHomePresenter();
//
//    ExchangePresenter provideExchangePresenter();
//
//    ExchangeCenterPresenter provideExchangeCenterPresenter();
//
//    MinePresenter provideExchangeMinePresenter();

}
