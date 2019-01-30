package com.rabbit.kaiyan.presenter;


import com.rabbit.kaiyan.base.contract.HotContract;
import com.rabbit.kaiyan.model.DataManager;

import javax.inject.Inject;

/**
 * Created by hzj on 2017/12/26.
 * HotFragment所关联类
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {

    @Inject
    public HotPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }

}
