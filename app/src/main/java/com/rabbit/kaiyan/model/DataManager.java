package com.rabbit.kaiyan.model;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rabbit.kaiyan.App.Constants;
import com.rabbit.kaiyan.model.DB.DBHelper;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.CategoryInfo;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.DiscoveryBean;
import com.rabbit.kaiyan.model.beans.DownloadBean;
import com.rabbit.kaiyan.model.beans.FollowBean;
import com.rabbit.kaiyan.model.beans.HistoryBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.LikeBean;
import com.rabbit.kaiyan.model.beans.RankListBean;
import com.rabbit.kaiyan.model.beans.RelateBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.model.beans.SearchResultBean;
import com.rabbit.kaiyan.model.beans.UserInfoBean;
import com.rabbit.kaiyan.model.beans.VideoFlowBean;
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

    private static final String TAG = "DataManager";

    ApiHelper mApiHelper;
    DBHelper mDBHelper;
    PreferenceHelper mPreferenceHelper;

    public DataManager(ApiHelper apiHelper, DBHelper dbHelper, PreferenceHelper preferenceHelper) {
        this.mApiHelper = apiHelper;
        this.mDBHelper = dbHelper;
        this.mPreferenceHelper = preferenceHelper;
    }
    /**
    * @explain 是否使用流量下载的设置
    **/
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
    public void saveUserInfo(UserInfoBean user) {
        mPreferenceHelper.saveUserInfo(user);
    }

    @Override
    public String getUserName() {
        return mPreferenceHelper.getUserName();
    }

    @Override
    public void insertReadId(ItemListBean historyBean) {
        mDBHelper.insertReadId(historyBean);
    }

    @Override
    public void insertLikeId(ItemListBean likeBean) {
        mDBHelper.insertLikeId(likeBean);
    }

    @Override
    public void deleteLikeId(int id) {
        mDBHelper.deleteLikeId(id);
    }

    @Override
    public void deleteReadId(int id) {
        mDBHelper.deleteReadId(id);
    }

    @Override
    public List<HistoryBean> getHistoryBeans() {
        return mDBHelper.getHistoryBeans();
    }

    /**
    * @explain 获取本地所有点赞记录
    **/
    @Override
    public List<LikeBean> getLikeBeans() {
        return mDBHelper.getLikeBeans();
    }

    @Override
    public HistoryBean getHistoryBean(int id) {
        return mDBHelper.getHistoryBean(id);
    }

    @Override
    public boolean checkLike(int id) {
        return mDBHelper.checkLike(id);
    }

    @Override
    public int checkDownload(int id) {
        return mDBHelper.checkDownload(id);
    }

    @Override
    public List<DownloadBean> getDownloadBeans() {
        return mDBHelper.getDownloadBeans();
    }

    @Override
    public void insertDownloadId(ItemListBean itemListBean) {
        mDBHelper.insertDownloadId(itemListBean);
    }

    @Override
    public void deleteDownloadId(int id) {
        mDBHelper.deleteDownloadId(id);
    }

    @Override
    public void setDownload(int id) {
        mDBHelper.setDownload(id);
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
    public Flowable<CategoryBean> getCategoryBean(int categoryID) {
        return mApiHelper.getCategoryBean(categoryID);
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
    public Flowable<FollowBean> getFollowBean() {
        return mApiHelper.getFollowBean();
    }

    @Override
    public Flowable<CategoryInfo> getCategoryInfoByID(int categoryID) {
        return mApiHelper.getCategoryInfoByID(categoryID);
    }

    @Override
    public Flowable<CategoryBean> getCategoryPageDataByID(String categoryID) {
        return mApiHelper.getCategoryPageDataByID(categoryID);
    }

    @Override
    public Flowable<RankListBean> getRankListDataByCycle(String cycle) {
        return mApiHelper.getRankListDataByCycle(cycle);
    }

    @Override
    public Flowable<UserInfoBean> login(String username, String password) {
        return mApiHelper.login(username, password);
    }

    @Override
    public Flowable<RelateBean> getRelateBean(int id) {
        return mApiHelper.getRelateBean(id);
    }

    @Override
    public Flowable<DataBean> getDataBean(int id) {
        return mApiHelper.getDataBean(id);
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
        return mApiHelper.getHotSearch();
    }

    @Override
    public Flowable<VideoFlowBean> getVideoFlowBean() {
        return mApiHelper.getVideoFlowBean();
    }

    @Override
    public Flowable<SearchResultBean> getSearchResultBean(int start, int num, String query) {
        return mApiHelper.getSearchResultBean(start, num, query);
    }

    @Override
    public Flowable<UserInfoBean> register(String username, String password, String email) {
        return mApiHelper.register(username, password, email);
    }

    /**
    * @explain 文件下载
    **/
    public void download(String url,final ItemListBean itemListBean){
        FileDownloader.getImpl().create(url)
                .setPath(Constants.PATH_DOWNLOAD)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        setDownload(itemListBean.getData().getId());
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                }).start();
    }
}
