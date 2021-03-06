package com.rabbit.kaiyan.model.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DownloadBean extends RealmObject {
    @PrimaryKey
    private int id;

    private String image;
    private String authorName;
    private String slogen;
    private String authorIcon;
    private String title;
    private long time;
    private int isdownload;

    public DownloadBean() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSlogen() {
        return slogen;
    }

    public void setAuthorSlogen(String authorSlogen) {
        this.slogen = authorSlogen;
    }

    public String getAuthorIcon() {
        return authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIsdownload() {
        return isdownload;
    }

    public void setIsdownload(int isdownload) {
        this.isdownload = isdownload;
    }
}
