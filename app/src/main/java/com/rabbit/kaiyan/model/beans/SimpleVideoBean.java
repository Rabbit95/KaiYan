package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SimpleVideoBean {
    /**
     * id : 141514
     * resourceType : video
     * uid : 0
     * title : 300 万中国底层快递员现状
     * description : 中国现有超过 300 万人从事快递行业，它的某些考核机制，逼迫快递员追求速度。人们享受快递带来便捷，却同时指责他们在路上跑不安全，这其实是矛盾的。为了更了解这个群体，看看到底和想象中有什么不同？摄影师葛亚琪来到杭州龙翔桥十字路口，拍下了 3000 多张照片关于快递员、外卖员的照片。
     * cover : {"feed":"http://img.kaiyanapp.com/7dcb263eab1fcf83357f6291b2fe27e3.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/7dcb263eab1fcf83357f6291b2fe27e3.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/f4770a61be4b7ed3acc59a9a589971ef.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/7dcb263eab1fcf83357f6291b2fe27e3.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
     * category : 记录
     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=141514&resourceType=video&editionType=default&source=aliyun
     * duration : 225
     * releaseTime : 1544271810000
     * consumption : null
     * collected : false
     * actionUrl : eyepetizer://ugcResourceDetail?id=141514&resourceType=video
     * onlineStatus : ONLINE
     * count : 0
     */

    private int id;
    private String resourceType;
    private int uid;
    private String title;
    private String description;
    private CoverBean cover;
    private String category;
    private String playUrl;
    private int duration;
    private long releaseTime;
    private Object consumption;
    private boolean collected;
    private String actionUrl;
    private String onlineStatus;
    private int count;

    public static SimpleVideoBean objectFromData(String str) {

        return new Gson().fromJson(str, SimpleVideoBean.class);
    }

    public static SimpleVideoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SimpleVideoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SimpleVideoBean> arraySimpleVideoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SimpleVideoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SimpleVideoBean> arraySimpleVideoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SimpleVideoBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Object getConsumption() {
        return consumption;
    }

    public void setConsumption(Object consumption) {
        this.consumption = consumption;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class CoverBean {
        /**
         * feed : http://img.kaiyanapp.com/7dcb263eab1fcf83357f6291b2fe27e3.jpeg?imageMogr2/quality/60/format/jpg
         * detail : http://img.kaiyanapp.com/7dcb263eab1fcf83357f6291b2fe27e3.jpeg?imageMogr2/quality/60/format/jpg
         * blurred : http://img.kaiyanapp.com/f4770a61be4b7ed3acc59a9a589971ef.jpeg?imageMogr2/quality/60/format/jpg
         * sharing : null
         * homepage : http://img.kaiyanapp.com/7dcb263eab1fcf83357f6291b2fe27e3.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
         */

        private String feed;
        private String detail;
        private String blurred;
        private Object sharing;
        private String homepage;

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

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }
    }
}
