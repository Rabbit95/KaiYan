package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.contract.LikeContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class LikePresenter extends RxPresenter<LikeContract.View> implements LikeContract.Presenter {

    private static final String TAG = "LikePresenter";

    @Inject
    public LikePresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getLikeData() {
        mView.setLikeData(mDataManager.getLikeBeans());
    }

    @Override
    public void getLikeDataByID(int id) {
        addSubscribe(mDataManager.getDataBean(id)
        .compose(RxUtil.<DataBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<DataBean>(mView) {
            @Override
            public void onNext(DataBean dataBean) {
                mView.startDetailActivity(dataBean);
            }
        }));
    }
}
