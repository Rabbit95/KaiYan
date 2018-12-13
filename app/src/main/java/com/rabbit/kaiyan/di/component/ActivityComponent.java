package com.rabbit.kaiyan.di.component;


import android.app.Activity;

import com.rabbit.kaiyan.di.module.ActivityModule;
import com.rabbit.kaiyan.di.scope.ActivityScope;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.rabbit.kaiyan.ui.activity.MainActivity;

import dagger.Component;

/**
     * @type
     * @explain 依赖注入中的AcitivityComponent
     * @author Rabbit.
     * @creat time 2018/11/27 16:00.
**/
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);
//    void inject(WelcomeActivity welcomeActivity);
//
//
//    void inject(TagActivity tagActivity);

    void inject(DetailActivity detailActivity);

//    void inject(HistoryActivity historyActivity);
//
//    void inject(LikeActivity likeActivity);
//
//    void inject(DownloadActivity downloadActivity);
//
//    void inject(SearchActivity searchActivity);
//
//    void inject(SettingActivity settingActivity);
//
//    void inject(AboutActivity aboutActivity);
}
