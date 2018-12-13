package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HeaderBean implements Serializable {
    /**
     * id : 0
     * title : null
     * font : null
     * subTitle : null
     * subTitleFont : null
     * textAlign : left
     * cover : null
     * label : null
     * actionUrl : null
     * labelList : null
     * rightText : null
     * icon : null
     * description : null
     */

    private FollowBean follow;


    private int id;
    private Object title;
    private Object font;
    private Object subTitle;
    private Object subTitleFont;
    private String textAlign;
    private Object cover;
    private Object label;
    private Object actionUrl;
    private Object labelList;
    private Object rightText;
    private Object icon;
    private Object description;

    public static HeaderBean objectFromData(String str) {

        return new Gson().fromJson(str, HeaderBean.class);
    }

    public static HeaderBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), HeaderBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<HeaderBean> arrayHeaderBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<HeaderBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<HeaderBean> arrayHeaderBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<HeaderBean>>() {
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

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getFont() {
        return font;
    }

    public void setFont(Object font) {
        this.font = font;
    }

    public Object getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Object subTitle) {
        this.subTitle = subTitle;
    }

    public Object getSubTitleFont() {
        return subTitleFont;
    }

    public void setSubTitleFont(Object subTitleFont) {
        this.subTitleFont = subTitleFont;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(Object actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Object getLabelList() {
        return labelList;
    }

    public void setLabelList(Object labelList) {
        this.labelList = labelList;
    }

    public Object getRightText() {
        return rightText;
    }

    public void setRightText(Object rightText) {
        this.rightText = rightText;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }
}