package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.HistoryBean;

import java.util.List;

public interface HistoryContract {
    interface View extends BaseView {
        void setHistoryData(List<HistoryBean> historyData);
        void startDetailActivity(DataBean dataBean);
    }
    interface Presenter extends BasePresenter<HistoryContract.View> {
        void getHistoryData();
        void getHistoryDataByID(int id);
    }
}
