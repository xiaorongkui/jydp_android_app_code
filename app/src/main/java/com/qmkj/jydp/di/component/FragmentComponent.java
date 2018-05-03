package com.qmkj.jydp.di.component;


import android.support.v4.app.Fragment;

import com.qmkj.jydp.di.module.FragmentModule;
import com.qmkj.jydp.di.scope.FragmentScope;
import com.qmkj.jydp.module.exchangecenter.view.ExchangeFragment;
import com.qmkj.jydp.module.home.view.HomeFragment;
import com.qmkj.jydp.module.mine.view.MineFragment;
import com.qmkj.jydp.module.exchangoutsidee.view.OutsideExchangeFragment;

import dagger.Component;

/**
 * 将对象注解到实力对象的接口
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment getFragment();

    void inject(HomeFragment homeFragment);

    void inject(ExchangeFragment exchangeFragment);

    void inject(MineFragment mineFragment);

    void inject(OutsideExchangeFragment outsideExchangeFragment);

}
