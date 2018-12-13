package com.rabbit.kaiyan.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.rabbit.kaiyan.App.App;

public class SystemUtil {

    /**
         * @explain  检查WIFI是否连接
    **/
    public static boolean isWifiConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getApp().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }

    /**
         * @explain  检查手机网络是否连接
    **/
    public static boolean isMobileNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getApp().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }

    /**
         * @explain 检查是否有可用网络
    **/
    public static boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getApp().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
         * @explain 将手机分辨率dp单位转成px
    **/
    public static int dp2px(Context context,float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(float dpValue){
        final float scale = App.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
