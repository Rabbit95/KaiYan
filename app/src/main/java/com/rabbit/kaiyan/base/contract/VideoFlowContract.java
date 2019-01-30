package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.VideoFlowBean;

public interface VideoFlowContract {
    interface View extends BaseView {
        void setVideoData(VideoFlowBean videoData);
        void setMoreVideoData(VideoFlowBean videoData);
    }
    interface Presenter extends BasePresenter<VideoFlowContract.View> {
        void getVideoFlowData();
        void getMoreVideoFlowData();
    }
}
