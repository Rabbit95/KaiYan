package com.rabbit.kaiyan.presenter;

import android.view.View;

import com.rabbit.kaiyan.base.contract.MineContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.UserInfoBean;

import javax.inject.Inject;

public class MinePresenter extends RxPresenter<MineContract.View> implements MineContract.Presenter {
    private static final String TAG = "MinePresenter";
    
    @Inject
    public MinePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void exitAccount() {
        mDataManager.saveUserInfo(new UserInfoBean());
    }

    @Override
    public boolean checkUserStatus() {
        UserInfoBean user = new UserInfoBean();
        if(mDataManager.getUserName() != null){
            //已登录
            user.setUsername(mDataManager.getUserName());
            mView.showUserInfo(user);
            mView.setExitAccount(View.VISIBLE);
            return true;
        }else{
            //未登录
            user.setUsername("登录解锁更多功能");
            mView.showUserInfo(user);
            mView.setExitAccount(View.GONE);
            return false;
        }
    }
}
