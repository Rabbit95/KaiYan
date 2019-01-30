package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.VideoFlowContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.VideoFlowBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class VideoFlowPresenter extends RxPresenter<VideoFlowContract.View> implements VideoFlowContract.Presenter {
    private static final String TAG = "VideoFlowPresenter";
    @Inject
    public VideoFlowPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getVideoFlowData() {
        addSubscribe(mDataManager.getVideoFlowBean()
        .compose(RxUtil.<VideoFlowBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<VideoFlowBean>(mView) {
            @Override
            public void onNext(VideoFlowBean videoFlowBean) {
                if(videoFlowBean != null){
                    mView.setVideoData(videoFlowBean);
                }else{
                    Log.d(TAG, "没有取到数据");
                }
            }
        }));
    }

    @Override
    public void getMoreVideoFlowData() {
        Log.d(TAG, "getMoreVideoFlowData: ");
        addSubscribe(mDataManager.getVideoFlowBean()
                .compose(RxUtil.<VideoFlowBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<VideoFlowBean>(mView) {
                    @Override
                    public void onNext(VideoFlowBean videoFlowBean) {
                        if(videoFlowBean != null){
                            mView.setMoreVideoData(videoFlowBean);
                        }else{
                            Log.d(TAG, "没有取到数据");
                        }
                    }
                }));
    }
}
