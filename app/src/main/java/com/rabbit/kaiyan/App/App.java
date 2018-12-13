package com.rabbit.kaiyan.App;

import android.app.Activity;
import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.rabbit.kaiyan.di.component.AppComponent;
import com.rabbit.kaiyan.di.component.DaggerAppComponent;
import com.rabbit.kaiyan.di.module.AppModule;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;

/**
     * @type
     * @explain  Application对整个APP进行归纳
     * @author Rabbit.
     * @creat time 2018/11/26 15:45.
**/
public class App extends Application {

    public static AppComponent appComponent;
    private static App app;
    private static Set<Activity> activities;

    public static synchronized App getApp() {
        return app;
    }

    /**
    * @explain  提供appComponent
    **/
    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
        return appComponent;
    }
    
    /**
    * @explain 退出APP并销毁进程 
    **/
    public static void exitApp() {
        if (activities != null) {
            synchronized (activities) {
                for (Activity activity : activities) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化Realm
        Realm.init(getApplicationContext());
        //初始化BlockCanary，LeakCanary
        BlockCanary.install(this, new BlockCanaryContext());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        } else {
            LeakCanary.install(this);
        }
    }
    /**
    * @explain 添加Acitivity 
    **/
    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new HashSet<>();
        }
        if (activity instanceof DetailActivity) {
            for (Activity activity1 : activities) {
                if (activity1 instanceof DetailActivity) {
                    removeActivity(activity1);
                    activity1.finish();
                }
            }
        }
        activities.add(activity);
    }
    /**
    * @explain 移除Activity
    **/
    public void removeActivity(Activity activity) {
        if (activities != null) {
            activities.remove(activity);
        }
    }
}
