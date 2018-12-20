package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserBean {
    /**
     * uid : 300052664
     * nickname : 洋
     * avatar : http://img.kaiyanapp.com/0da7be0ae994782f225df82faf4b4283.jpeg?imageMogr2/quality/60/format/jpg
     * userType : NORMAL
     * ifPgc : false
     * description : 我为什么出成都？
     * area : null
     * gender : male
     * registDate : 1476546686000
     * releaseDate : 1544961264000
     * cover : null
     * actionUrl : eyepetizer://pgc/detail/300052664/?title=%E6%B4%8B&userType=NORMAL&tabIndex=0
     * followed : false
     * limitVideoOpen : true
     * library : BLOCK
     * expert : false
     */

    private int uid;
    private String nickname;
    private String avatar;
    private String userType;
    private boolean ifPgc;
    private String description;
    private Object area;
    private String gender;
    private long registDate;
    private long releaseDate;
    private Object cover;
    private String actionUrl;
    private boolean followed;
    private boolean limitVideoOpen;
    private String library;
    private boolean expert;

    public static UserBean objectFromData(String str) {

        return new Gson().fromJson(str, UserBean.class);
    }

    public static UserBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UserBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<UserBean> arrayUserFromData(String str) {

        Type listType = new TypeToken<ArrayList<UserBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<UserBean> arrayUserFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<UserBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isIfPgc() {
        return ifPgc;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getRegistDate() {
        return registDate;
    }

    public void setRegistDate(long registDate) {
        this.registDate = registDate;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean isLimitVideoOpen() {
        return limitVideoOpen;
    }

    public void setLimitVideoOpen(boolean limitVideoOpen) {
        this.limitVideoOpen = limitVideoOpen;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public boolean isExpert() {
        return expert;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }
}
