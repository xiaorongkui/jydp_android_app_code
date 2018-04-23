package com.qmkj.jydp.di.module;


import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.util.SPHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * 提供全局的对象，通过注解生成
 */
@Module
public class AppModule {
    private JYDPExchangeApp myApplication;

    public AppModule(JYDPExchangeApp myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    JYDPExchangeApp provideMyApplication() {
        return myApplication;
    }

    @Provides
    @Singleton
    SPHelper providePreferencesHelper() {
        return SPHelper.getInstance();
    }
}
