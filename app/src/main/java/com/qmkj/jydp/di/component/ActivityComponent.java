package com.qmkj.jydp.di.component;

import android.app.Activity;
import android.content.Context;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.di.module.ActivityModule;
import com.qmkj.jydp.di.scope.ActivityScope;
import com.qmkj.jydp.module.login.view.AreaCodeSecActivity;
import com.qmkj.jydp.module.login.view.CertificationActivity;
import com.qmkj.jydp.module.login.view.ForgetLoginPwdActivity;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.module.mine.view.CurrencyAssetsActivity;
import com.qmkj.jydp.module.mine.view.CurrencyWithDrawRecodeActivity;
import com.qmkj.jydp.module.mine.view.DealerManagementActivity;
import com.qmkj.jydp.module.mine.view.OrderRecodeActivity;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    Context getContext();

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(AreaCodeSecActivity areaCodeSecActivity);

    void inject(CertificationActivity certificationActivity);

    void inject(ForgetLoginPwdActivity forgetLoginPwdActivity);

    void inject(CurrencyAssetsActivity currencyAssetsActivity);

    void inject(DealerManagementActivity dealerManagementActivity);

    void inject(OrderRecodeActivity orderRecodeActivity);
}
