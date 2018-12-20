package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

public interface RecommendContract {
    interface View extends BaseView {
        void showDateContent(String date);
        void showTopContent(List<ItemListBean> topListBeans);
        void showContent(List<ItemListBean> listBeans);
        void setEndState();
    }
    interface Presenter extends BasePresenter<View> {
        void getDailyData();
        void getCategoryData();
        void getMoreCategoryData();
        void refreshAll();
    }
}
