package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.DownloadBean;

import java.util.List;

public interface DownloadContract {
    interface View extends BaseView {
        void setCacheData(List<DownloadBean> downloadBeans);
        void startDetailActivity(DataBean dataBean);
    }
    interface Presenter extends BasePresenter<DownloadContract.View> {
        void getCacheData();
        void getCacheDataByID(int id);
    }
}
