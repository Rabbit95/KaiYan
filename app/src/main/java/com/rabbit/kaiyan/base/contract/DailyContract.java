package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;
public interface DailyContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> listBeans);
    }
    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getMoreData();
        void refreshAll();
    }
}
