package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView {
        void showHotSearch(List<String> stringList);

        void showResult(List<ItemListBean> listBeans, int total);

        void showMoreResult(List<ItemListBean> listBeans);

        void addProgressView();
    }

    interface Presenter extends BasePresenter<View> {
        void getHotSearchData();

        void getSearchData(String query);

        void getMoreData(String query);
    }
}
