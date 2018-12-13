package com.rabbit.kaiyan.model.DB;

import com.rabbit.kaiyan.model.beans.ItemListBean;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
     * @type
     * @explain 数据库操作实现类
     * @author Rabbit.
     * @creat time 2018/11/27 16:07.
**/
public class RealmHelper implements DBHelper{
    private static final String DB_NAME = "myrealm.vedio";
    private Realm mRealm;

    @Inject
    public RealmHelper(){
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }


    @Override
    public void insertReadId(ItemListBean itemListBean) {

    }

    @Override
    public void insertLikeId(ItemListBean itemListBean) {

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
}
