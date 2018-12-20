package com.rabbit.kaiyan.model.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataBean implements Serializable {


    //分类里的
    private String dataType;
    private int count;
    private Object footer;

    private String library;
    private ConsumptionBean consumption;
    private String resourceType;
    private String slogan;
    private ProviderBean provider;
    private String category;
    private AuthorBean author;
    private CoverBean cover;
    private String playUrl;
    private Object thumbPlayUrl;
    private int duration;
    private WebUrlBean webUrl;
    private long releaseTime;
    private Object campaign;
    private Object waterMarks;
    private boolean ad;
    private String type;
    private String titlePgc;
    private String descriptionPgc;
    private String remark;
    private boolean ifLimitVideo;
    private int searchWeight;
    private int idx;
    private Object shareAdTrack;
    private Object favoriteAdTrack;
    private Object webAdTrack;
    private long date;
    private Object promotion;
    private String descriptionEditor;
    private boolean collected;
    private boolean played;
    private Object lastViewTime;
    private Object playlists;
    private Object src;
    private List<TagsBean> tags;
    private List<?> playInfo;
    private List<?> subtitles;
    private String text;
    private List<ItemListBean> itemList;

    //首页里的
    private int id;
    private String title;
    private String description;
    private String image;
    private String actionUrl;
    private Object adTrack;
    private boolean shade;
    private LabelBean label;
    private HeaderBean header;
    private boolean autoPlay;
    private List<LabelListBean> labelList;

    //分类里的
    private String icon;

    //发现
    private Content content;
    private UserBean user;
    private HotReplyBean reply;
    private SimpleVideoBean simpleVideo;
    private String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public SimpleVideoBean getSimpleVideo() {
        return simpleVideo;
    }

    public void setSimpleVideo(SimpleVideoBean simpleVideo) {
        this.simpleVideo = simpleVideo;
    }

    public HotReplyBean getReply() {
        return reply;
    }

    public void setReply(HotReplyBean reply) {
        this.reply = reply;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getFooter() {
        return footer;
    }

    public void setFooter(Object footer) {
        this.footer = footer;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public ConsumptionBean getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionBean consumption) {
        this.consumption = consumption;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Object getThumbPlayUrl() {
        return thumbPlayUrl;
    }

    public void setThumbPlayUrl(Object thumbPlayUrl) {
        this.thumbPlayUrl = thumbPlayUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WebUrlBean getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrlBean webUrl) {
        this.webUrl = webUrl;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Object getCampaign() {
        return campaign;
    }

    public void setCampaign(Object campaign) {
        this.campaign = campaign;
    }

    public Object getWaterMarks() {
        return waterMarks;
    }

    public void setWaterMarks(Object waterMarks) {
        this.waterMarks = waterMarks;
    }

    public boolean isAd() {
        return ad;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitlePgc() {
        return titlePgc;
    }

    public void setTitlePgc(String titlePgc) {
        this.titlePgc = titlePgc;
    }

    public String getDescriptionPgc() {
        return descriptionPgc;
    }

    public void setDescriptionPgc(String descriptionPgc) {
        this.descriptionPgc = descriptionPgc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isIfLimitVideo() {
        return ifLimitVideo;
    }

    public void setIfLimitVideo(boolean ifLimitVideo) {
        this.ifLimitVideo = ifLimitVideo;
    }

    public int getSearchWeight() {
        return searchWeight;
    }

    public void setSearchWeight(int searchWeight) {
        this.searchWeight = searchWeight;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Object getShareAdTrack() {
        return shareAdTrack;
    }

    public void setShareAdTrack(Object shareAdTrack) {
        this.shareAdTrack = shareAdTrack;
    }

    public Object getFavoriteAdTrack() {
        return favoriteAdTrack;
    }

    public void setFavoriteAdTrack(Object favoriteAdTrack) {
        this.favoriteAdTrack = favoriteAdTrack;
    }

    public Object getWebAdTrack() {
        return webAdTrack;
    }

    public void setWebAdTrack(Object webAdTrack) {
        this.webAdTrack = webAdTrack;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public String getDescriptionEditor() {
        return descriptionEditor;
    }

    public void setDescriptionEditor(String descriptionEditor) {
        this.descriptionEditor = descriptionEditor;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Object getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(Object lastViewTime) {
        this.lastViewTime = lastViewTime;
    }

    public Object getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Object playlists) {
        this.playlists = playlists;
    }

    public Object getSrc() {
        return src;
    }

    public void setSrc(Object src) {
        this.src = src;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<?> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<?> playInfo) {
        this.playInfo = playInfo;
    }

    public List<?> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<?> subtitles) {
        this.subtitles = subtitles;
    }



    public static DataBean objectFromData(String str) {

        return new Gson().fromJson(str, DataBean.class);
    }

    public static DataBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DataBean> arrayDataBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DataBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<DataBean> arrayDataBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public LabelBean getLabel() {
        return label;
    }

    public void setLabel(LabelBean label) {
        this.label = label;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public List<LabelListBean> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<LabelListBean> labelList) {
        this.labelList = labelList;
    }

}