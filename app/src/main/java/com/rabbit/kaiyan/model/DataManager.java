package com.rabbit.kaiyan.model;

import com.rabbit.kaiyan.model.DB.DBHelper;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.DiscoveryBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.RelateBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.model.http.ApiHelper;
import com.rabbit.kaiyan.model.prefs.PreferenceHelper;

import java.util.List;

import io.reactivex.Flowable;
/**
     * @type
     * @explain APP中的数据管理中心，所有跟数据有关的都由DataManager进行操作
     * @author Rabbit.
     * @creat time 2018/11/27 16:11.
**/
public class DataManager implements ApiHelper,DBHelper,PreferenceHelper{
    ApiHelper mApiHelper;
    DBHelper mDBHelper;
    PreferenceHelper mPreferenceHelper;

    public DataManager(ApiHelper apiHelper, DBHelper dbHelper, PreferenceHelper preferenceHelper) {
        this.mApiHelper = apiHelper;
        this.mDBHelper = dbHelper;
        this.mPreferenceHelper = preferenceHelper;
    }

    @Override
    public boolean getPlaySetting() {
        return mPreferenceHelper.getPlaySetting();
    }

    @Override
    public void setPlaySetting(boolean playSetting) {
        mPreferenceHelper.setPlaySetting(playSetting);
    }

    @Override
    public boolean getDownloadSetting() {
        return mPreferenceHelper.getDownloadSetting();
    }

    @Override
    public void setDownloadStting(boolean downloadStting) {
        mPreferenceHelper.setDownloadStting(downloadStting);
    }

    @Override
    public void insertReadId(ItemListBean historyBean) {
        mDBHelper.insertReadId(historyBean);
    }

    @Override
    public void insertLikeId(ItemListBean likeBean) {
        mDBHelper.insertReadId(likeBean);
    }

    @Override
    public void deleteLikeId(int id) {

    }

    @Override
    public void deleteReadId(int id) {

    }

    @Override
    public boolean checkLike(int id) {
        return false;
    }

    @Override
    public int checkDownload(int id) {
        return 0;
    }

    @Override
    public void insertDownloadId(ItemListBean itemListBean) {

    }

    @Override
    public void deleteDownloadId(int id) {

    }

    @Override
    public void setDownload(int id) {

    }

    @Override
    public Flowable<DailyBean> getDailyBean() {
        return mApiHelper.getDailyBean();
    }

    @Override
    public Flowable<DailyBean> getDailyBean(Long date) {
        return mApiHelper.getDailyBean(date);
    }

    @Override
    public Flowable<CategoryBean> getCategoryBean() {
        return mApiHelper.getCategoryBean();
    }

    @Override
    public Flowable<CategoryBean> getMoreCategoryBean(int num) {
        return mApiHelper.getMoreCategoryBean(num);
    }

    @Override
    public Flowable<DiscoveryBean> getDiscoveryBean() {
        return mApiHelper.getDiscoveryBean();
    }

    @Override
    public Flowable<RelateBean> getRelateBean(int id) {
        return mApiHelper.getRelateBean(id);
    }

    @Override
    public Flowable<ReplyBean> getReplyBean(int id) {
        return mApiHelper.getReplyBean(id);
    }

    @Override
    public Flowable<ReplyBean> getMoreReplyBean(int id, int lastId, int num) {
        return mApiHelper.getMoreReplyBean(id, lastId, num);
    }

    @Override
    public Flowable<List<String>> getHotSearch() {
        return null;
    }
}
