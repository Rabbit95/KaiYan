package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.CategoryActivityContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.CategoryInfo;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class CategoryActivityPresenter extends RxPresenter<CategoryActivityContract.View> implements CategoryActivityContract.Presenter {
    private static final String TAG = "CategoryActivityPresent";

    @Inject
    public CategoryActivityPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getCategoryInfoByID(final int categoryID) {
        addSubscribe(mDataManager.getCategoryInfoByID(categoryID)
                .compose(RxUtil.<CategoryInfo>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<CategoryInfo>(mView) {
                    @Override
                    public void onNext(CategoryInfo categoryInfo) {
                        if (categoryInfo != null) {
                            mView.showCategoryInfo(categoryInfo);
                            getCategoryPageDataByID(categoryID);
                        }
                    }
                }));
    }

    @Override
    public void getCategoryPageDataByID(int categoryID) {
//        String id = (String.valueOf(categoryID)) + "&udid=b301c597351746f2b68004e7dd15b72e7baec89e";
        addSubscribe(mDataManager.getCategoryPageDataByID(String.valueOf(categoryID))
                .compose(RxUtil.<CategoryBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<CategoryBean>(mView) {
                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        if (categoryBean != null) {
                            Log.d(TAG, "categorybena count:" + categoryBean.getCount());
//                    Log.d(TAG, "test："+categoryBean.getItemList().get(0).getData().getItemList().get(0).getData().getHeader().getTitle());
//                            Log.d(TAG, "categorybean size:" + categoryBean.getItemList().size());
//                            Log.d(TAG, "itemList get(0).getType"+ categoryBean.getItemList().get(0).getType());
                              mView.setPageData(categoryBean);
                        }
                    }
                }));
    }
}
