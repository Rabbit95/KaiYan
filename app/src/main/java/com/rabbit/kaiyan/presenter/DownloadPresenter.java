package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.contract.DownloadContract;
import com.rabbit.kaiyan.model.DataManager;

import javax.inject.Inject;

public class DownloadPresenter extends RxPresenter<DownloadContract.View> implements DownloadContract.Presenter {


    @Inject
    public DownloadPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getCacheData() {
        mView.setCacheData(mDataManager.getDownloadBeans());
    }

    @Override
    public void getCacheDataByID(int id) {

    }
}
