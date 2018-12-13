package com.rabbit.kaiyan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.di.component.DaggerFragmentComponent;
import com.rabbit.kaiyan.di.component.FragmentComponent;
import com.rabbit.kaiyan.di.module.FragmentModule;

import javax.inject.Inject;
/**
     * @type
     * @explain  基础BaseFragment,定义了Fragment通用方法
     * @author Rabbit.
     * @creat time 2018/11/27 15:55.
**/
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected
    T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).fragmentModule(getFragmentModule()).build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String s) {

    }

    protected abstract void initInject();
}
