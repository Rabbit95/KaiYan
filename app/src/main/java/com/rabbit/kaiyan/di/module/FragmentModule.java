package com.rabbit.kaiyan.di.module;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.rabbit.kaiyan.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;
/**
 * @type
 * @explain 提供Fragment单例
 * @author Rabbit.
 * @creat time 2018/11/27 16:02.
 **/
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity() {
        return fragment.getActivity();
    }
}
