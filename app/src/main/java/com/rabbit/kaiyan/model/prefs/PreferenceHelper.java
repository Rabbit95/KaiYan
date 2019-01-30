package com.rabbit.kaiyan.model.prefs;

import com.rabbit.kaiyan.model.beans.UserInfoBean;

/**
     * @type
     * @explain  sharedpreferenced操作接口
     * @author Rabbit.
     * @creat time 2018/11/27 16:10.
**/
public interface PreferenceHelper {
    boolean getPlaySetting();
    void setPlaySetting(boolean playSetting);
    boolean getDownloadSetting();
    void setDownloadStting(boolean downloadStting);

    void saveUserInfo(UserInfoBean user);
    String getUserName();
}
