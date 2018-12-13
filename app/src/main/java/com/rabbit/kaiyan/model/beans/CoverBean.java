package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CoverBean implements Serializable {
    /**
     * feed : http://img.kaiyanapp.com/0b3e0b1c206b96a69799bda7576e3988.png?imageMogr2/quality/60/format/jpg
     * detail : http://img.kaiyanapp.com/0b3e0b1c206b96a69799bda7576e3988.png?imageMogr2/quality/60/format/jpg
     * blurred : http://img.kaiyanapp.com/3752a26f3ecf65bf07298876b88f369c.jpeg?imageMogr2/quality/60/format/jpg
     * sharing : null
     * homepage : null
     */

    private String feed;
    private String detail;
    private String blurred;
    private Object sharing;
    private Object homepage;

    public static CoverBean objectFromData(String str) {

        return new Gson().fromJson(str, CoverBean.class);
    }

    public static CoverBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CoverBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CoverBean> arrayCoverBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CoverBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CoverBean> arrayCoverBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CoverBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public Object getSharing() {
        return sharing;
    }

    public void setSharing(Object sharing) {
        this.sharing = sharing;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }
}
