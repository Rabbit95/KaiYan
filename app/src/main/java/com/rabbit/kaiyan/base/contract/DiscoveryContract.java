package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

public interface DiscoveryContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> listBeans);
        void setBanner(List<ItemListBean> banner);
        void setTitles(List<ItemListBean> titles);
        void setCategoryCard(List<ItemListBean> categoryCard);
        void setRankList(List<ItemListBean> rankList);
        void setTopicCard(List<ItemListBean> topicCard);
        void setHotAuthor(List<ItemListBean> hotAuthor);
        void setHotReply(List<ItemListBean> hotReply);
    }
    interface Presenter extends BasePresenter<View>{
        void getDiscoverData();
        void refreshAll();
    }
}
