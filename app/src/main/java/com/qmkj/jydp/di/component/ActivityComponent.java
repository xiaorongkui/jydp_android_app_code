package com.qmkj.jydp.di.component;

import android.app.Activity;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.di.module.ActivityModule;
import com.qmkj.jydp.di.scope.ActivityScope;
import com.qmkj.jydp.module.login.view.LoginActivity;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

}
