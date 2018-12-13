package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.base.contract.MainContract;
import com.rabbit.kaiyan.model.DataManager;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    @Override
    public void checkPremission() {

    }

    @Override
    public void getSearchSuggestions() {

    }
}
