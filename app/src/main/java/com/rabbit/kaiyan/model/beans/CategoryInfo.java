package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryInfo implements Serializable {


    /**
     * categoryInfo : {"id":10,"name":"动画","description":"有趣的人永远不缺童心","headerImage":"http://img.kaiyanapp.com/41015e6fe963cf58866ad0d061c073f1.png","actionUrl":"eyepetizer://category/10/?title=%E5%8A%A8%E7%94%BB"}
     */

    private CategoryInfoBean categoryInfo;

    public static CategoryInfo objectFromData(String str) {

        return new Gson().fromJson(str, CategoryInfo.class);
    }

    public static CategoryInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CategoryInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CategoryInfo> arrayCategoryInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<CategoryInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CategoryInfo> arrayCategoryInfoFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CategoryInfo>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public CategoryInfoBean getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfoBean categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public static class CategoryInfoBean implements Serializable{
        /**
         * id : 10
         * name : 动画
         * description : 有趣的人永远不缺童心
         * headerImage : http://img.kaiyanapp.com/41015e6fe963cf58866ad0d061c073f1.png
         * actionUrl : eyepetizer://category/10/?title=%E5%8A%A8%E7%94%BB
         */

        private int id;
        private String name;
        private String description;
        private String headerImage;
        private String actionUrl;

        public static CategoryInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, CategoryInfoBean.class);
        }

        public static CategoryInfoBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), CategoryInfoBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<CategoryInfoBean> arrayCategoryInfoBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<CategoryInfoBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<CategoryInfoBean> arrayCategoryInfoBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<CategoryInfoBean>>() {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHeaderImage() {
            return headerImage;
        }

        public void setHeaderImage(String headerImage) {
            this.headerImage = headerImage;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }
    }
}
