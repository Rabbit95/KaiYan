package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.UserInfoBean;

public interface MineContract {
    interface View extends BaseView {
        void showUserInfo(UserInfoBean user);
        void showActivity(Class cls);
        void setExitAccount(int visibility);
    }

    interface Presenter extends BasePresenter<MineContract.View> {
        void exitAccount();
        boolean checkUserStatus();
    }
}
