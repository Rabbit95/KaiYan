package com.rabbit.kaiyan.di.module;


import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.model.DB.DBHelper;
import com.rabbit.kaiyan.model.DB.RealmHelper;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.http.ApiHelper;
import com.rabbit.kaiyan.model.http.RetrofitHelper;
import com.rabbit.kaiyan.model.prefs.ImplPreferenceHelper;
import com.rabbit.kaiyan.model.prefs.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
     * @type
     * @explain 全局单例
     * @author Rabbit.
     * @creat time 2018/11/27 16:02.
**/
@Module
public class AppModule {
    private final App app;

    public AppModule(App app){
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp(){
        return app;
    }

    @Provides
    @Singleton
    ApiHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper){
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper provideImplPreferenceHelper(ImplPreferenceHelper implPreferenceHelper){
        return implPreferenceHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApiHelper apiHelper, DBHelper dbHelper, PreferenceHelper preferenceHelper){
        return new DataManager(apiHelper,dbHelper,preferenceHelper);
    }

}
