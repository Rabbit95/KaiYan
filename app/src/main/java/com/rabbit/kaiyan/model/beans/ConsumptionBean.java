package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionBean implements Serializable {
    /**
     * collectionCount : 2
     * shareCount : 0
     * replyCount : 0
     */

    private int collectionCount;
    private int shareCount;
    private int replyCount;

    public static ConsumptionBean objectFromData(String str) {

        return new Gson().fromJson(str, ConsumptionBean.class);
    }

    public static ConsumptionBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ConsumptionBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ConsumptionBean> arrayConsumptionBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ConsumptionBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ConsumptionBean> arrayConsumptionBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ConsumptionBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
}