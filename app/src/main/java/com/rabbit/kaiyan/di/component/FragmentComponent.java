package com.rabbit.kaiyan.di.component;


import android.app.Activity;

import com.rabbit.kaiyan.di.module.FragmentModule;
import com.rabbit.kaiyan.di.scope.FragmentScope;
import com.rabbit.kaiyan.ui.fragment.AllHotFragment;
import com.rabbit.kaiyan.ui.fragment.CategoryFragment;
import com.rabbit.kaiyan.ui.fragment.DailyFragment;
import com.rabbit.kaiyan.ui.fragment.DiscoveryFragment;
import com.rabbit.kaiyan.ui.fragment.FollowFragment;
import com.rabbit.kaiyan.ui.fragment.MineFragment;
import com.rabbit.kaiyan.ui.fragment.MonthFragment;
import com.rabbit.kaiyan.ui.fragment.RankHomeFragment;
import com.rabbit.kaiyan.ui.fragment.RecommendFragment;
import com.rabbit.kaiyan.ui.fragment.VideoFlowFragment;
import com.rabbit.kaiyan.ui.fragment.WeekFragment;

import dagger.Component;

/**
     * @type
     * @explain 依赖注入中的FragmentComponent
     * @author Rabbit.
     * @creat time 2018/11/27 16:01.
**/
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(DailyFragment dailyFragment);

    void inject(RecommendFragment recommendFragment);

    void inject(DiscoveryFragment discoveryFragment);

    void inject(FollowFragment followFragment);

    void inject(CategoryFragment categoryFragment);

    void inject(RankHomeFragment rankHomeFragment);

    void inject(WeekFragment weekListFragment);

    void inject(MonthFragment monthListFragment);

    void inject(AllHotFragment allHotFragment);

    void inject(MineFragment mineFragment);

    void inject(VideoFlowFragment videoFlowFragment);
//    void inject(TestFragment testFragment);

//    void inject(HotFragment hotFragment);
//
//    void inject(TagsFragment tagsFragment);
//
//    void inject(WeekFragment weekFragment);
//
//    void inject(MonthFragment MonthFragment);
//
//    void inject(AllHotFragment AllHotFragment);
}
