package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayInfoBean implements Serializable {

    /**
     * height : 720
     * width : 1280
     * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146091&resourceType=video&editionType=high&source=aliyun","size":185996294},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146091&resourceType=video&editionType=high&source=qcloud","size":185996294},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146091&resourceType=video&editionType=high&source=ucloud","size":185996294}]
     * name : 高清
     * type : high
     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146091&resourceType=video&editionType=high&source=aliyun
     */

    private int height;
    private int width;
    private String name;
    private String type;
    private String url;
    private List<UrlListBean> urlList;

    public static PlayInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, PlayInfoBean.class);
    }

    public static PlayInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PlayInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PlayInfoBean> arrayPlayInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PlayInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PlayInfoBean> arrayPlayInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PlayInfoBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<UrlListBean> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<UrlListBean> urlList) {
        this.urlList = urlList;
    }

    public static class UrlListBean implements Serializable{
        /**
         * name : aliyun
         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146091&resourceType=video&editionType=high&source=aliyun
         * size : 185996294
         */

        private String name;
        private String url;
        private int size;

        public static UrlListBean objectFromData(String str) {

            return new Gson().fromJson(str, UrlListBean.class);
        }

        public static UrlListBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), UrlListBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<UrlListBean> arrayUrlListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UrlListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<UrlListBean> arrayUrlListBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<UrlListBean>>() {
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
