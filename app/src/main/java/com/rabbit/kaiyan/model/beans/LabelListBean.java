package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LabelListBean implements Serializable {
    /**
     * text : 广告
     * actionUrl : null
     */

    private String text;
    private Object actionUrl;

    public static LabelListBean objectFromData(String str) {

        return new Gson().fromJson(str, LabelListBean.class);
    }

    public static LabelListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LabelListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<LabelListBean> arrayLabelListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<LabelListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<LabelListBean> arrayLabelListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<LabelListBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(Object actionUrl) {
        this.actionUrl = actionUrl;
    }
}