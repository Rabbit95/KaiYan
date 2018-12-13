package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WebUrlBean implements Serializable {
    /**
     * raw : http://www.eyepetizer.net/detail.html?vid=141834
     * forWeibo : http://www.eyepetizer.net/detail.html?vid=141834&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
     */

    private String raw;
    private String forWeibo;

    public static WebUrlBean objectFromData(String str) {

        return new Gson().fromJson(str, WebUrlBean.class);
    }

    public static WebUrlBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), WebUrlBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<WebUrlBean> arrayWebUrlBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<WebUrlBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<WebUrlBean> arrayWebUrlBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<WebUrlBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getForWeibo() {
        return forWeibo;
    }

    public void setForWeibo(String forWeibo) {
        this.forWeibo = forWeibo;
    }
}

