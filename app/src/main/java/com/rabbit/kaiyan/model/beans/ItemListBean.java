package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemListBean implements Serializable {
    /**
     * type : banner2
     * data : {"dataType":"Banner","id":1169,"title":"","description":"","image":"http://img.kaiyanapp.com/ac8fcae923e36b0499a49e50d892e950.jpeg?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=%E4%B8%80%E8%A7%81%E5%80%BE%E5%BF%83%EF%BC%8C%E9%A9%AD%E7%BD%A2%E4%B8%8D%E8%83%BD&url=http%3A%2F%2Fwww.kaiyanapp.com%2Fcampaign%2Fchapter_activity%2Fchapter.html%3Fnid%3D1264%26shareable%3Dtrue","adTrack":null,"shade":false,"label":{"text":"广告","card":"广告","detail":null},"labelList":[{"text":"广告","actionUrl":null}],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false}
     * tag : 0
     * id : 0
     * adIndex : -1
     */
    private String type;
    private DataBean data;
    private String tag;
    private int id;
    private int adIndex;
    private long date;
    public static ItemListBean objectFromData(String str) {

        return new Gson().fromJson(str, ItemListBean.class);
    }

    public static ItemListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ItemListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ItemListBean> arrayItemListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ItemListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ItemListBean> arrayItemListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ItemListBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }


}
