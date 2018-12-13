package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;

import java.util.List;
/**
     * @type    
     * @explain  DetailMVP约束接口
     * @author Rabbit.
     * @creat time 2018/11/27 15:53.
**/
public interface DetailContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> itemListBeans);
        void setLike(boolean like);
        void showReply(ReplyBean replyBean);
        void showMoreReply(ReplyBean replyBean);
        void showDownload();
        void showIsDownload();
        void showHadDownload();
        void showDownloadDialog();
    }
    interface Presenter extends BasePresenter<View>{
        void getVideoData(int id);
        void getReplyData(int id);
        void getMoreReplyData(int id);
        void addToHistory(ItemListBean itemListBean);
        boolean isRead(int id);
        void isLike(int id);
        void deleteLikeId(int id);
        void deleteReadId(int id);
        void insertLikeId(ItemListBean itemListBean);
        void download(String url, ItemListBean itemListBean);
        boolean getPlaySetting();
        void setPlaySetting(boolean playSetting);
        boolean getDownloadSetting();
        void setDownloadSetting(boolean downloadSetting);
    }
}
