package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.FollowContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.FollowBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class FollowPresenter extends RxPresenter<FollowContract.View> implements FollowContract.Presenter {

    private static final String TAG = "FollowPresenter";

    @Inject
    public FollowPresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }

    @Override
    public void getFollowData() {
        addSubscribe(mDataManager.getFollowBean()
        .compose(RxUtil.<FollowBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<FollowBean>(mView) {
            @Override
            public void onNext(FollowBean followBean) {
                if (followBean != null) {
                    Log.d(TAG, "followBean size" + followBean.getCount());
                    mView.addFollowData(followBean);
                }
            }
        }));
    }
}
