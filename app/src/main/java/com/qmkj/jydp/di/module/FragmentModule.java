package com.qmkj.jydp.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.qmkj.jydp.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * 这里提供的是v4包下的fragment，不要使用V7下的
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    Context provideConetxt() {
        return fragment.getActivity();
    }
}
