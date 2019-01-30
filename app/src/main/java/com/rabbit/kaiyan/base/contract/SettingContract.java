package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;

public interface SettingContract  {
    interface View extends BaseView {
    }
    interface Presenter extends BasePresenter<SettingContract.View> {
        boolean getPlaySetting();
        void setPlaySetting(boolean playSetting);
        boolean getDownloadSetting();
        void setDownloadSetting(boolean downloadSetting);
    }
}
