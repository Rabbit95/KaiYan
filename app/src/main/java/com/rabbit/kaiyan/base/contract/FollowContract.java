package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.FollowBean;

public interface FollowContract {
    interface View extends BaseView {
        void addFollowData(FollowBean followBean);
    }
    interface Presenter extends BasePresenter<FollowContract.View> {
        void getFollowData();
    }
}
