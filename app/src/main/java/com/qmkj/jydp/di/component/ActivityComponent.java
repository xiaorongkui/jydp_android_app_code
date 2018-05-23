package com.qmkj.jydp.di.component;

import android.app.Activity;
import android.content.Context;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.di.module.ActivityModule;
import com.qmkj.jydp.di.scope.ActivityScope;
import com.qmkj.jydp.module.exchangecenter.view.KlineActivity;
import com.qmkj.jydp.module.exchangoutsidee.view.OutSideBuyActivity;
import com.qmkj.jydp.module.exchangoutsidee.view.OutSideBuyDetailActivity;
import com.qmkj.jydp.module.exchangoutsidee.view.OutSideSoldActivity;
import com.qmkj.jydp.module.login.view.AreaCodeSecActivity;
import com.qmkj.jydp.module.login.view.CertificationActivity;
import com.qmkj.jydp.module.login.view.ForgetLoginPwdActivity;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.module.mine.view.ContactServiceActivity;
import com.qmkj.jydp.module.mine.view.CurrencyAssetsActivity;
import com.qmkj.jydp.module.mine.view.CurrencyWithDrawRecodeActivity;
import com.qmkj.jydp.module.mine.view.DealerManagementActivity;
import com.qmkj.jydp.module.mine.view.HelpCenterActivity;
import com.qmkj.jydp.module.mine.view.HelpCenterDetailsActivity;
import com.qmkj.jydp.module.mine.view.HotTopicActivity;
import com.qmkj.jydp.module.mine.view.ModifyLoginPwdActivity;
import com.qmkj.jydp.module.mine.view.ModifyPaymentActivity;
import com.qmkj.jydp.module.mine.view.ModifyPhoneActivity;
import com.qmkj.jydp.module.mine.view.OrderRecodeActivity;
import com.qmkj.jydp.module.mine.view.OutSideExchangeRecodeActivity;
import com.qmkj.jydp.module.mine.view.PersonInfoActivity;
import com.qmkj.jydp.module.mine.view.PublishAdvertisementActivity;
import com.qmkj.jydp.module.mine.view.ReceivablesActivity;
import com.qmkj.jydp.module.mine.view.SystemNoticeActivity;
import com.qmkj.jydp.module.mine.view.TransactionRecodeActivity;

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

    void inject(PersonInfoActivity personInfoActivity);

    void inject(ModifyLoginPwdActivity modifyLoginPwdActivity);

    void inject(ModifyPaymentActivity modifyPaymentActivity);

    void inject(ModifyPhoneActivity modifyPhoneActivity);

    void inject(CurrencyWithDrawRecodeActivity currencyWithDrawRecodeActivity);

    void inject(OutSideExchangeRecodeActivity outSideExchangeRecodeActivity);

    void inject(SystemNoticeActivity systemNoticeActivity);

    void inject(TransactionRecodeActivity transactionRecodeActivity);

    void inject(ContactServiceActivity contactServiceActivity);

    void inject(HotTopicActivity hotTopicActivity);

    void inject(HelpCenterActivity helpCenterActivity);

    void inject(KlineActivity klineActivity);

    void inject(PublishAdvertisementActivity publishAdvertisementActivity);

    void inject(HelpCenterDetailsActivity helpCenterDetailsActivity);

    void inject(OutSideBuyActivity outSideBuyActivity);

    void inject(OutSideSoldActivity outSideSoldActivity);

    void inject(OutSideBuyDetailActivity outSideBuyDetailActivity);

    void inject(ReceivablesActivity receivablesActivity);
}
