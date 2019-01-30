package com.rabbit.kaiyan.model.DB;

import android.util.Log;

import com.rabbit.kaiyan.model.beans.DownloadBean;
import com.rabbit.kaiyan.model.beans.HistoryBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.LikeBean;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
     * @type
     * @explain 数据库操作实现类
     * @author Rabbit.
     * @creat time 2018/11/27 16:07.
**/
public class RealmHelper implements DBHelper{

    private static final String TAG = "RealmHelper";
    private static final String DB_NAME = "myrealm.vedio";
    private Realm mRealm;

    @Inject
    public RealmHelper(){
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
    * @explain 保存浏览记录
    **/
    @Override
    public void insertReadId(ItemListBean itemListBean) {
        HistoryBean historyBean = new HistoryBean();
        if(itemListBean.getType().equals("followCard")){
            historyBean.setId(itemListBean.getData().getContent().getData().getId());
            historyBean.setAuthorIcon(itemListBean.getData().getContent().getData().getAuthor().getIcon());
            historyBean.setAuthorName(itemListBean.getData().getContent().getData().getAuthor().getName());
            historyBean.setAuthorSlogen(itemListBean.getData().getContent().getData().getAuthor().getDescription());
            historyBean.setImage(itemListBean.getData().getContent().getData().getCover().getFeed());
            historyBean.setTitle(itemListBean.getData().getContent().getData().getTitle());
        }else if(itemListBean.getData().getDataType()!=null && itemListBean.getData().getDataType().equals("VideoBeanForClientV1")){
            historyBean.setId(itemListBean.getData().getId());
            historyBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
            historyBean.setAuthorName(itemListBean.getData().getAuthor().getName());
            historyBean.setAuthorSlogen(itemListBean.getData().getAuthor().getDescription());
            historyBean.setImage(itemListBean.getData().getCoverForFeed());
            historyBean.setTitle(itemListBean.getData().getTitle());
        }else{
            historyBean.setId(itemListBean.getData().getId());
            historyBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
            historyBean.setAuthorName(itemListBean.getData().getAuthor().getName());
            historyBean.setAuthorSlogen(itemListBean.getData().getAuthor().getDescription());
            historyBean.setImage(itemListBean.getData().getCover().getFeed());
            historyBean.setTitle(itemListBean.getData().getTitle());
        }
        historyBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(historyBean);
        mRealm.commitTransaction();
    }

    /**
    * @explain 保存点赞记录
    **/
    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        Log.d(TAG, "RealmHelper 保存点赞");
        LikeBean likeBean = new LikeBean();
        if(itemListBean.getType().equals("followCard")){
            likeBean.setId(itemListBean.getData().getContent().getData().getId());
            likeBean.setAuthorIcon(itemListBean.getData().getContent().getData().getAuthor().getIcon());
            likeBean.setAuthorName(itemListBean.getData().getContent().getData().getAuthor().getName());
            likeBean.setAuthorSlogen(itemListBean.getData().getContent().getData().getCategory());
            likeBean.setImage(itemListBean.getData().getContent().getData().getCover().getFeed());
            likeBean.setTitle(itemListBean.getData().getContent().getData().getTitle());
        }else if(itemListBean.getData().getDataType()!=null && itemListBean.getData().getDataType().equals("VideoBeanForClientV1")){
            likeBean.setId(itemListBean.getData().getId());
            likeBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
            likeBean.setAuthorName(itemListBean.getData().getAuthor().getName());
            likeBean.setAuthorSlogen(itemListBean.getData().getAuthor().getDescription());
            likeBean.setImage(itemListBean.getData().getCoverForFeed());
            likeBean.setTitle(itemListBean.getData().getTitle());
        }else{
            likeBean.setId(itemListBean.getData().getId());
            likeBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
            likeBean.setAuthorName(itemListBean.getData().getAuthor().getName());
            likeBean.setAuthorSlogen(itemListBean.getData().getCategory());
            likeBean.setImage(itemListBean.getData().getCover().getFeed());
            likeBean.setTitle(itemListBean.getData().getTitle());
        }
        likeBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(likeBean);
        mRealm.commitTransaction();
    }

    /**
    * @explain  删除点赞记录
    **/
    @Override
    public void deleteLikeId(int id) {
        LikeBean bean = mRealm.where(LikeBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    /**
    * @explain 删除浏览记录
    **/
    @Override
    public void deleteReadId(int id) {
        HistoryBean bean = mRealm.where(HistoryBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }
    
    /**
    * @explain 获取所有浏览记录
    **/
    @Override
    public List<HistoryBean> getHistoryBeans() {
        RealmResults<HistoryBean> realmResults = mRealm.where(HistoryBean.class).findAllSorted("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(realmResults);
    }

    /**
    * @explain 获取点赞记录
    **/
    @Override
    public List<LikeBean> getLikeBeans() {
        RealmResults<LikeBean> realmResults = mRealm.where(LikeBean.class).findAllSorted("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(realmResults);
    }
    /**
    * @explain 检查是否有此条浏览记录
    **/
    @Override
    public HistoryBean getHistoryBean(int id) {
        HistoryBean historyBean;
        historyBean = mRealm.where(HistoryBean.class).equalTo("id",id).findFirst();
        if (historyBean == null){
            return null;
        }else{
            return mRealm.copyFromRealm(historyBean);
        }
    }

    /**
    * @explain 检查是否有此条点赞记录
    **/
    @Override
    public boolean checkLike(int id) {
        LikeBean likeBean = mRealm.where(LikeBean.class).equalTo("id", id).findFirst();
        if (likeBean == null) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
    * @explain 检查是否有此条下载记录 
    **/
    @Override
    public int checkDownload(int id) {
        DownloadBean downloadBean = mRealm.where(DownloadBean.class).equalTo("id", id).findFirst();
        if (downloadBean == null) {
            return 0;
        } else {
            return downloadBean.getIsdownload();
        }
    }
    
    /**
    * @explain 获取所有的下载记录 
    **/
    @Override
    public List<DownloadBean> getDownloadBeans() {
        RealmResults<DownloadBean> list = mRealm.where(DownloadBean.class).findAllSorted("time", Sort.ASCENDING);
        return mRealm.copyFromRealm(list);
    }
    /**
    * @explain 添加新的下载记录 
    **/
    @Override
    public void insertDownloadId(ItemListBean itemListBean) {
        //可能存在followCard问题
        DownloadBean downloadBean = new DownloadBean();
        downloadBean.setId(itemListBean.getData().getId());
        downloadBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        downloadBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        downloadBean.setAuthorSlogen(itemListBean.getData().getCategory());
        downloadBean.setIsdownload(1);
        if (itemListBean.getData().getCover() == null) {
            downloadBean.setImage(itemListBean.getData().getCoverForFeed());
        } else {
            downloadBean.setImage(itemListBean.getData().getCover().getFeed());
        }
        downloadBean.setTitle(itemListBean.getData().getTitle());
        downloadBean.setTime(System.currentTimeMillis());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(downloadBean);
        mRealm.commitTransaction();
    }
    /**
    * @explain 根据ID删除下载记录 
    **/
    @Override
    public void deleteDownloadId(int id) {
        DownloadBean bean = mRealm.where(DownloadBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }
    /**
    * @explain 根据ID设置下载为完成状态
    **/
    @Override
    public void setDownload(int id) {
        DownloadBean bean = mRealm.where(DownloadBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (bean != null) {
            bean.setIsdownload(2);
        }
        mRealm.commitTransaction();
    }
}
