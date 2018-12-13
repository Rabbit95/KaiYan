package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShieldBean implements Serializable {
    /**
     * itemType : author
     * itemId : 2098
     * shielded : false
     */

    private String itemType;
    private int itemId;
    private boolean shielded;

    public static ShieldBean objectFromData(String str) {

        return new Gson().fromJson(str, ShieldBean.class);
    }

    public static ShieldBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ShieldBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ShieldBean> arrayShieldBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ShieldBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ShieldBean> arrayShieldBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ShieldBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isShielded() {
        return shielded;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }
}