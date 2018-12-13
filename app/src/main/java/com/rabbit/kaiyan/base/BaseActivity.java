package com.rabbit.kaiyan.base;


import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.di.component.ActivityComponent;
import com.rabbit.kaiyan.di.component.DaggerActivityComponent;
import com.rabbit.kaiyan.di.module.ActivityModule;

import javax.inject.Inject;

/**
     * @type    
     * @explain  基础Acitivity,定义了Activity通用方法
     * @author Rabbit.
     * @creat time 2018/11/19 21:32.
**/
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView{
    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(getActivityModule()).build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }



    @Override
    protected void onViewCreate() {
        super.onViewCreate();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String s) {

    }

    protected abstract void initInject();

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateStart() {

    }
}
