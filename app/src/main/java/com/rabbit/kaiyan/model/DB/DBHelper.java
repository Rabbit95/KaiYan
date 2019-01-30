package com.rabbit.kaiyan.model.DB;

import com.rabbit.kaiyan.model.beans.DownloadBean;
import com.rabbit.kaiyan.model.beans.HistoryBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.LikeBean;

import java.util.List;

/**
     * @type
     * @explain 数据库操作接口
     * @author Rabbit.
     * @creat time 2018/11/27 16:07.
**/
public interface DBHelper {
    void insertReadId(ItemListBean itemListBean);

    void insertLikeId(ItemListBean itemListBean);


    void deleteLikeId(int id);

    void deleteReadId(int id);

    List<HistoryBean> getHistoryBeans();

    List<LikeBean> getLikeBeans();

    HistoryBean getHistoryBean(int id);

    boolean checkLike(int id);

    int checkDownload(int id);

    List<DownloadBean> getDownloadBeans();

    void insertDownloadId(ItemListBean itemListBean);

    void deleteDownloadId(int id);

    void setDownload(int id);
}
