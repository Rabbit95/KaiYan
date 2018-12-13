package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TagsBean implements Serializable {
    /**
     * id : 44
     * name : 科普
     * actionUrl : eyepetizer://tag/44/?title=%E7%A7%91%E6%99%AE
     * adTrack : null
     * desc : null
     * bgPicture : http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
     * headerImage : http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
     * tagRecType : IMPORTANT
     * childTagList : null
     * childTagIdList : null
     * communityIndex : 0
     */

    private int id;
    private String name;
    private String actionUrl;
    private Object adTrack;
    private Object desc;
    private String bgPicture;
    private String headerImage;
    private String tagRecType;
    private Object childTagList;
    private Object childTagIdList;
    private int communityIndex;

    public static TagsBean objectFromData(String str) {

        return new Gson().fromJson(str, TagsBean.class);
    }

    public static TagsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str),TagsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TagsBean> arrayTagsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TagsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TagsBean> arrayTagsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TagsBean>>() {
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

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getTagRecType() {
        return tagRecType;
    }

    public void setTagRecType(String tagRecType) {
        this.tagRecType = tagRecType;
    }

    public Object getChildTagList() {
        return childTagList;
    }

    public void setChildTagList(Object childTagList) {
        this.childTagList = childTagList;
    }

    public Object getChildTagIdList() {
        return childTagIdList;
    }

    public void setChildTagIdList(Object childTagIdList) {
        this.childTagIdList = childTagIdList;
    }

    public int getCommunityIndex() {
        return communityIndex;
    }

    public void setCommunityIndex(int communityIndex) {
        this.communityIndex = communityIndex;
    }
}
