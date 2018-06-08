package com.qmkj.jydp.di.component;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.qmkj.jydp.di.module.FragmentModule;
import com.qmkj.jydp.di.scope.FragmentScope;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeBuyFragment;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeCurrencySelectFrament;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeFragment;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeSoldFragment;
import com.qmkj.jydp.module.exchangoutside.view.OutsideExchangeFragment;
import com.qmkj.jydp.module.home.view.HomeFragment;
import com.qmkj.jydp.module.login.view.CertifyNameFragment;
import com.qmkj.jydp.module.login.view.CertifyNameStatusFragment;
import com.qmkj.jydp.module.mine.view.MineFragment;

import dagger.Component;

/**
 * 将对象注解到实力对象的接口
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment getFragment();

    Context getContext();

    //
    void inject(HomeFragment homeFragment);

    void inject(ExchangeFragment exchangeFragment);

    void inject(MineFragment mineFragment);

    void inject(OutsideExchangeFragment outsideExchangeFragment);

    void inject(CertifyNameFragment certifyNameFragment);

    void inject(CertifyNameStatusFragment certifyNameStatusFragment);

    void inject(ExchangeCurrencySelectFrament exchangeCurrencySelectFrament);

    void inject(ExchangeBuyFragment exchangeBuyFragment);

    void inject(ExchangeSoldFragment exchangeSoldFragment);
}
