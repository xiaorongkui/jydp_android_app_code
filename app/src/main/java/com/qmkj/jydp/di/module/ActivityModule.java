package com.qmkj.jydp.di.module;

import android.app.Activity;


import com.qmkj.jydp.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 提供activity的实例对象
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

}
