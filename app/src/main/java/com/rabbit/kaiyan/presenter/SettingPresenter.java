package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.contract.SettingContract;
import com.rabbit.kaiyan.model.DataManager;

import javax.inject.Inject;

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {
    @Inject
    public SettingPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public boolean getPlaySetting() {
        return mDataManager.getPlaySetting();
    }

    @Override
    public void setPlaySetting(boolean playSetting) {
        mDataManager.setPlaySetting(playSetting);
    }

    @Override
    public boolean getDownloadSetting() {
        return mDataManager.getDownloadSetting();
    }

    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        mDataManager.setDownloadStting(downloadSetting);
    }
}
