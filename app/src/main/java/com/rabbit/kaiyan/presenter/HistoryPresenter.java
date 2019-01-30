package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.HistoryContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.HistoryBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

public class HistoryPresenter extends RxPresenter<HistoryContract.View> implements HistoryContract.Presenter {
    private static final String TAG = "HistoryPresenter";

    @Inject
    public HistoryPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getHistoryData() {
        List<HistoryBean> historyBeans = mDataManager.getHistoryBeans();
        if (historyBeans != null) {
            for (int i = 0; i < historyBeans.size(); i++) {
                Log.d(TAG, i + "id:" + historyBeans.get(i).getId());
            }
            mView.setHistoryData(historyBeans);
        } else {
            Log.d(TAG, "null data !");
        }
    }

    @Override
    public void getHistoryDataByID(int id) {
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
