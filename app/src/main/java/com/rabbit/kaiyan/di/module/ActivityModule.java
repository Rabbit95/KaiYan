package com.rabbit.kaiyan.di.module;

import android.app.Activity;

import com.rabbit.kaiyan.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
     * @type
     * @explain 提供Activity单例
     * @author Rabbit.
     * @creat time 2018/11/27 16:02.
**/
@Module
public class ActivityModule {
    private Activity activity;
    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return activity;
    }
}