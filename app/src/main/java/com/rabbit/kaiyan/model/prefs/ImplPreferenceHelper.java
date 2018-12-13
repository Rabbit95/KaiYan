package com.rabbit.kaiyan.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.App.Constants;

import javax.inject.Inject;


/**
     * @type
     * @explain 获取或设置本地配置
     * @author Rabbit.
     * @creat time 2018/11/20 14:54.
**/
public class ImplPreferenceHelper implements  PreferenceHelper{

    private static final Boolean DEFAULT_DOWNLOADSETTING = false;
    private static final Boolean DEFAULT_PLAYSETTING = false;

    private final SharedPreferences preferences;

    @Inject
    public ImplPreferenceHelper() {
        preferences = App.getApp().getSharedPreferences("my_ky", Context.MODE_PRIVATE);
    }

    /**
         * @explain 获取关于是否使用流量播放的设置
    **/
    @Override
    public boolean getPlaySetting() {
        return preferences.getBoolean(Constants.PLAYSETTING,DEFAULT_PLAYSETTING);
    }

    /**
         * @explain  设置是否使用流量播放
    **/
    @Override
    public void setPlaySetting(boolean playSetting) {
        preferences.edit().putBoolean(Constants.PLAYSETTING,playSetting).apply();
    }

    /**
         * @explain 获取关于是否使用流量下载的设置
    **/
    @Override
    public boolean getDownloadSetting() {
        return preferences.getBoolean(Constants.DOWNLOADSETTING,DEFAULT_DOWNLOADSETTING);
    }

    /**
         * @explain 设置是否使用流量下载
    **/
    @Override
    public void setDownloadStting(boolean downloadStting) {
        preferences.edit().putBoolean(Constants.DOWNLOADSETTING,downloadStting).apply();
    }
}
