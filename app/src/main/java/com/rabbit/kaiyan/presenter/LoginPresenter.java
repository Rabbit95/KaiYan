package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.contract.LoginContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.UserInfoBean;
import com.rabbit.kaiyan.util.AccountValidatorUtil;
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
        if(username.equals("") || username == null || password.equals("") || password == null){
            mView.showToastMsg("是不是漏填了啥");
        }else {
            addSubscribe(mDataManager.login(username, password)
                    .compose(RxUtil.<UserInfoBean>rxSchedulerHelper())
                    .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {
                        @Override
                        public void onNext(UserInfoBean userInfoBean) {
                            if (userInfoBean.getMsgCode() == 3) {
                                mDataManager.saveUserInfo(userInfoBean);
                                mView.showToastMsg("登录成功");
                                mView.closeView();
                            } else if (userInfoBean.getMsgCode() == 2) {
                                mView.showToastMsg("密码错误");
                            } else if (userInfoBean.getMsgCode() == 1) {
                                mView.showToastMsg("用户名不存在");
                            }
                        }
                    }));
        }
    }

    @Override
    public void register(String username, String password, String email) {
        if(username.equals("") || username == null || password.equals("") || password == null || email.equals("") || email == null){
            mView.showToastMsg("是不是漏填了啥");
        }else{
            if (username.length() >= 8) {
                if(password.length() >= 8) {
                    if (AccountValidatorUtil.isUsername(username)) {
                        if (AccountValidatorUtil.isPassword(password)) {
                            if (AccountValidatorUtil.isEmail(email)) {
                                addSubscribe(mDataManager.register(username, password, email)
                                .compose(RxUtil.<UserInfoBean>rxSchedulerHelper())
                                .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {
                                    @Override
                                    public void onNext(UserInfoBean userInfoBean) {
                                        if (userInfoBean != null){
                                            if(userInfoBean.getMsgCode() == 5){
                                                mView.showToastMsg("注册成功，快登录叭~");
                                                mView.changeView();
                                            }else if(userInfoBean.getMsgCode() == 4){
                                                mView.showToastMsg("用户名被人用啦，换一个吧~");
                                            }
                                        }
                                    }
                                }));
                            }else{
                                mView.showToastMsg("这是哪个星球的邮箱？");
                            }
                        }else{
                            mView.showToastMsg("密码不规范啊！");
                        }
                    } else {
                        mView.showToastMsg("用户名不规范啊！");
                    }
                }else{
                    mView.showToastMsg("密码长度不够八位鸭！");
                }
            }else{
                mView.showToastMsg("用户名长度不够八位鸭！");
            }

        }
    }
}
