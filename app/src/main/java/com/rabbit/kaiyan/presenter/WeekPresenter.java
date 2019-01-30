package com.rabbit.kaiyan.presenter;


import com.rabbit.kaiyan.base.contract.HotTopContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.RankListBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/26.
 */

public class WeekPresenter extends RxPresenter<HotTopContract.View> implements HotTopContract.Presenter {

    List<ItemListBean> itemListBeans;

    @Inject
    public WeekPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }


    @Override
    public void getHotData(String cycler) {
        addSubscribe(mDataManager.getRankListDataByCycle(cycler)
                .compose(RxUtil.<RankListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<RankListBean>(mView) {
                    @Override
                    public void onNext(RankListBean rankListBean) {
                        itemListBeans = rankListBean.getItemList();
                        mView.showContents(itemListBeans);
                    }
                }));
    }
}
