package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;
/**
     * @type
     * @explain  DailyMVP约束接口
     * @author Rabbit.
     * @creat time 2018/11/27 15:53.
**/
public interface DailyContract {
    interface View extends BaseView{
        void showContent(List<ItemListBean> listBeans);
        void showFirstContent(List<ItemListBean> ItemListBean);
        void changeTopPageView();
    }
    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getMoreData();
        void startInterval();
        void stopInterval();
        void refreshAll();
    }
}
