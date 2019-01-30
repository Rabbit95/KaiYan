package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VideoFlowBean {


    /**
     * count : 10
     * videoFlowDomainList : [{"description":"年少有为","id":8,"image":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/63761ca640fd67f6809f721f09947e14.jpg","playurl":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/4ed49f2540ba058a808b4a89d1a01bff.mp4"},{"description":"莫斯科","id":9,"image":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/fb8134b540ef5da2804c7445bc65e5fa.jpg","playurl":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/e52725f340259d7580ca46556b5eecca.mp4"},{"description":"视频11","id":11,"image":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/4328b2914037ec59802757723c6eee95.jpg","playurl":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/37bb619940ae3e8880526294b38718ff.mp4"},{"description":"14","id":14,"image":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/4328b2914037ec59802757723c6eee95.jpg","playurl":"http://restest.ccwb.cn/public/Upload/2/20181221/20181221154059PW37XX.mp4"},{"description":"15","id":15,"image":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/4328b2914037ec59802757723c6eee95.jpg","playurl":"http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/37bb619940ae3e8880526294b38718ff.mp4"},{"description":"16","id":16,"image":"","playurl":"http://restest.ccwb.cn/public/Upload/2/20181221/20181221154059PW37XX.mp4"},{"description":"19","id":19,"image":"","playurl":"http://restest.ccwb.cn/public/Upload/2/20181221/20181221154059PW37XX.mp4"},{"description":"20","id":20,"image":"","playurl":"http://restest.ccwb.cn/public/Upload/2/20181221/20181221154059PW37XX.mp4"},{"description":"21","id":21,"image":"","playurl":"http://restest.ccwb.cn/public/Upload/2/20181221/20181221154059PW37XX.mp4"},{"description":"22","id":22,"image":"","playurl":"http://restest.ccwb.cn/public/Upload/2/20181221/20181221154059PW37XX.mp4"}]
     */

    private int count;
    @SerializedName("videoFlowDomainList")
    private List<VideoFlow> videoFlowList;

    public static VideoFlowBean objectFromData(String str) {

        return new Gson().fromJson(str, VideoFlowBean.class);
    }

    public static VideoFlowBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), VideoFlowBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<VideoFlowBean> arrayVideoFlowBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<VideoFlowBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<VideoFlowBean> arrayVideoFlowBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<VideoFlowBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<VideoFlow> getVideoFlowList() {
        return videoFlowList;
    }

    public void setVideoFlowList(List<VideoFlow> videoFlowList) {
        this.videoFlowList = videoFlowList;
    }

    public static class VideoFlow {
        /**
         * description : 年少有为
         * id : 8
         * image : http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/63761ca640fd67f6809f721f09947e14.jpg
         * playurl : http://bmob-cdn-23651.b0.upaiyun.com/2019/01/30/4ed49f2540ba058a808b4a89d1a01bff.mp4
         */

        private String description;
        private int id;
        private String image;
        private String playurl;

        public static VideoFlow objectFromData(String str) {

            return new Gson().fromJson(str, VideoFlow.class);
        }

        public static VideoFlow objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), VideoFlow.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<VideoFlow> arrayVideoFlowFromData(String str) {

            Type listType = new TypeToken<ArrayList<VideoFlow>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<VideoFlow> arrayVideoFlowFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<VideoFlow>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPlayurl() {
            return playurl;
        }

        public void setPlayurl(String playurl) {
            this.playurl = playurl;
        }
    }
}
