package com.rabbit.kaiyan.di.component;


import android.app.Activity;

import com.rabbit.kaiyan.di.module.ActivityModule;
import com.rabbit.kaiyan.di.scope.ActivityScope;
import com.rabbit.kaiyan.ui.activity.DownloadActivity;
import com.rabbit.kaiyan.ui.activity.CategoryActivity;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.rabbit.kaiyan.ui.activity.LikeActivity;
import com.rabbit.kaiyan.ui.activity.LoginActivity;
import com.rabbit.kaiyan.ui.activity.MainActivity;
import com.rabbit.kaiyan.ui.activity.HistoryActivity;
import com.rabbit.kaiyan.ui.activity.SearchActivity;
import com.rabbit.kaiyan.ui.activity.SettingActivity;

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

    void inject(CategoryActivity categoryActivity);

    void inject(LoginActivity loginActivity);

//    void inject(HistoryActivity historyActivity);
//
    void inject(LikeActivity likeActivity);

    void inject(HistoryActivity recorderActivity);

    void inject(DownloadActivity cacheActivity);

    void inject(SettingActivity settingActivity);

    void inject(SearchActivity searchActivity);
//
//    void inject(DownloadActivity downloadActivity);
//
//    void inject(SearchActivity searchActivity);
//
//    void inject(SettingActivity settingActivity);
//
//    void inject(AboutActivity aboutActivity);
}
