package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void showToastMsg(String msg);
        void closeView();
    }
    interface Presenter extends BasePresenter<LoginContract.View> {
        void checkAccount(String username,String password);
    }
}
