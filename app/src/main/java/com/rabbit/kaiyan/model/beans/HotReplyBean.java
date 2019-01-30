package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HotReplyBean implements Serializable {
    /**
     * id : 1071394071269867520
     * videoId : 141514
     * videoTitle : 300 万中国底层快递员现状
     * message : 我想说，他们真的是想浪迹天涯？笑傲人生吗？你去送一个月你就知道了
     * likeCount : 10
     * showConversationButton : false
     * parentReplyId : 0
     * rootReplyId : 1071394071269867520
     * ifHotReply : true
     * liked : false
     * parentReply : null
     */

    private long id;
    private int videoId;
    private String videoTitle;
    private String message;
    private int likeCount;
    private boolean showConversationButton;
    private int parentReplyId;
    private long rootReplyId;
    private boolean ifHotReply;
    private boolean liked;
    private Object parentReply;

    public static HotReplyBean objectFromData(String str) {

        return new Gson().fromJson(str, HotReplyBean.class);
    }

    public static HotReplyBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), HotReplyBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<HotReplyBean> arrayHotReplyBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<HotReplyBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<HotReplyBean> arrayHotReplyBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<HotReplyBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isShowConversationButton() {
        return showConversationButton;
    }

    public void setShowConversationButton(boolean showConversationButton) {
        this.showConversationButton = showConversationButton;
    }

    public int getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(int parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

    public long getRootReplyId() {
        return rootReplyId;
    }

    public void setRootReplyId(long rootReplyId) {
        this.rootReplyId = rootReplyId;
    }

    public boolean isIfHotReply() {
        return ifHotReply;
    }

    public void setIfHotReply(boolean ifHotReply) {
        this.ifHotReply = ifHotReply;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Object getParentReply() {
        return parentReply;
    }

    public void setParentReply(Object parentReply) {
        this.parentReply = parentReply;
    }
}
