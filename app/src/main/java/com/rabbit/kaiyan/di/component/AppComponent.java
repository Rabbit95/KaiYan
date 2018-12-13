package com.rabbit.kaiyan.di.component;


import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.DB.RealmHelper;
import com.rabbit.kaiyan.di.module.AppModule;
import com.rabbit.kaiyan.di.module.HttpModule;
import com.rabbit.kaiyan.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
     * @type
     * @explain 依赖注入中的AppComponent,提供全局单例类的实例
     * @author Rabbit.
     * @creat time 2018/11/19 21:39.
**/
@Singleton
@Component(modules = {AppModule.class,HttpModule.class})
public interface AppComponent {
    App getContext();
    RetrofitHelper retrofitHelper();
    RealmHelper realmHelper();
    DataManager getDataManager();
}
