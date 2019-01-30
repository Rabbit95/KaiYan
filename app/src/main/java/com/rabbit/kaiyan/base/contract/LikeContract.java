package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.LikeBean;

import java.util.List;

public interface LikeContract {
    interface View extends BaseView {
        void setLikeData(List<LikeBean> likeData);
        void startDetailActivity(DataBean dataBean);
    }
    interface Presenter extends BasePresenter<LikeContract.View> {
        void getLikeData();
        void getLikeDataByID(int id);
    }
}
