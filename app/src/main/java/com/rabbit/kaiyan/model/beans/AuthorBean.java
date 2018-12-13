package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AuthorBean implements Serializable{
    /**
     * id : 2098
     * icon : http://img.kaiyanapp.com/608dafdede807bd3748b582a32dc0a33.png?imageMogr2/quality/60/format/jpg
     * name : 火星人俱乐部
     * description : 科普趣味生活，亲子科学实验；北大老师陪你趣学科学；
     * link :
     * latestReleaseTime : 1544439267000
     * videoNum : 425
     * adTrack : null
     * follow : {"itemType":"author","itemId":2098,"followed":false}
     * shield : {"itemType":"author","itemId":2098,"shielded":false}
     * approvedNotReadyVideoCount : 0
     * ifPgc : true
     * expert : false
     */

    private int id;
    private String icon;
    private String name;
    private String description;
    private String link;
    private long latestReleaseTime;
    private int videoNum;
    private Object adTrack;
    private FollowBean follow;
    private ShieldBean shield;
    private int approvedNotReadyVideoCount;
    private boolean ifPgc;
    private boolean expert;

    public static AuthorBean objectFromData(String str) {

        return new Gson().fromJson(str, AuthorBean.class);
    }

    public static AuthorBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AuthorBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AuthorBean> arrayAuthorBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AuthorBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<AuthorBean> arrayAuthorBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AuthorBean>>() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public void setLatestReleaseTime(long latestReleaseTime) {
        this.latestReleaseTime = latestReleaseTime;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public FollowBean getFollow() {
        return follow;
    }

    public void setFollow(FollowBean follow) {
        this.follow = follow;
    }


    public int getApprovedNotReadyVideoCount() {
        return approvedNotReadyVideoCount;
    }

    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
    }

    public boolean isIfPgc() {
        return ifPgc;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

    public boolean isExpert() {
        return expert;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }

}
