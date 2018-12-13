package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProviderBean implements Serializable {
    /**
     * name : PGC
     * alias : PGC
     * icon :
     */

    private String name;
    private String alias;
    private String icon;

    public static ProviderBean objectFromData(String str) {

        return new Gson().fromJson(str, ProviderBean.class);
    }

    public static ProviderBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ProviderBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ProviderBean> arrayProviderBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ProviderBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ProviderBean> arrayProviderBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ProviderBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}