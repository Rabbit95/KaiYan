package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.LoginContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.UserInfoBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void checkAccount(String username, String password) {
        addSubscribe(mDataManager.login(username, password)
        .compose(RxUtil.<UserInfoBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {
            @Override
            public void onNext(UserInfoBean userInfoBean) {
                if(userInfoBean.getLoginstatus() == 3){
                    mDataManager.saveUserInfo(userInfoBean);
                    Log.d(TAG, "userinfo:"+userInfoBean.toString());
                    mView.showToastMsg("登录成功");
                    mView.closeView();
                }else if (userInfoBean.getLoginstatus() == 2){
                    mView.showToastMsg("密码错误");
                }else if(userInfoBean.getLoginstatus() == 1){
                    mView.showToastMsg("用户名不存在");
                }
            }
        }));
    }
}
