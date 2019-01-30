package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.contract.CategoryFragmentContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class CategoryFragmentPresenter extends RxPresenter<CategoryFragmentContract.View> implements CategoryFragmentContract.Presenter{

    private static final String TAG = "CategoryPresenter";

    @Inject
    public CategoryFragmentPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getCategoryDataByID(int categoryID) {
        addSubscribe(mDataManager.getCategoryBean(categoryID)
        .compose(RxUtil.<CategoryBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<CategoryBean>(mView) {
            @Override
            public void onNext(CategoryBean categoryBean) {
                if(categoryBean != null) {
                    mView.setCategoryData(categoryBean);
                }
            }
        }));
    }

}
