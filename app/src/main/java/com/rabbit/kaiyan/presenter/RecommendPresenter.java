package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.contract.RecommendContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.util.DateUtil;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecommendPresenter extends RxPresenter<RecommendContract.View> implements RecommendContract.Presenter {
    String mDate;
    int nextNum;
    DataManager mDataManager;
    List<ItemListBean> itemListBeans = new ArrayList<>();
    List<ItemListBean> topItemListBeans = new ArrayList<>();
    List<ItemListBean> categoryListBeans = new ArrayList<>();
    List<ItemListBean> moreCategoryListBeans = new ArrayList<>();


    String TAG = "--------------";
    @Inject
    public RecommendPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getDailyData() {
        addSubscribe(mDataManager.getDailyBean()
        .compose(RxUtil.<DailyBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
            @Override
            public void onNext(DailyBean dailyBeans) {
                mDate = DateUtil.dateFormat(dailyBeans.getDate());
                mView.showDateContent(mDate);

                if (null != dailyBeans.getItemList().get(0)) {
                    List<ItemListBean> item = new ArrayList<>();
                    item = dailyBeans.getItemList();
                    topItemListBeans = new ArrayList<>();
                    for (ItemListBean itemListBean : item) {
                        if (itemListBean.getTag() != null && itemListBean.getTag().equals("0") && !itemListBean.getType().equals("banner2")) {
                            topItemListBeans.add(itemListBean);
                        }
                    }
                }
                mView.showTopContent(topItemListBeans);
                for (int i = 0; i < topItemListBeans.size(); i++) {
                    itemListBeans.add(topItemListBeans.get(i));
                }
                getCategoryData();
            }
        }));
    }

    @Override
    public void getCategoryData() {
        addSubscribe(mDataManager.getCategoryBean()
        .compose(RxUtil.<CategoryBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<CategoryBean>(mView) {
            @Override
            public void onNext(CategoryBean category) {
                if(category.getNextPageUrl() != null) {
                    String nextUrl = category.getNextPageUrl();
                    nextNum = Integer.valueOf(nextUrl.substring(nextUrl.indexOf("?") + 7, nextUrl.indexOf("&")));
                }
                for (ItemListBean itemListBean : category.getItemList()) {
                    itemListBeans.add(itemListBean);
                }
                mView.showContent(itemListBeans);
            }
        }));
    }

    @Override
    public void getMoreCategoryData() {
        addSubscribe(mDataManager.getMoreCategoryBean(nextNum)
        .compose(RxUtil.<CategoryBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<CategoryBean>(mView) {
            @Override
            public void onNext(CategoryBean category) {
                if(category.getNextPageUrl() != null) {
                    String nextUrl = category.getNextPageUrl();
                    nextNum = Integer.valueOf(nextUrl.substring(nextUrl.indexOf("?") + 7, nextUrl.indexOf("&")));
                }else {
                    mView.setEndState();
                }
                for (ItemListBean itemListBean : category.getItemList()) {
                    itemListBeans.add(itemListBean);
                }
                mView.showContent(itemListBeans);
            }
        }));
    }

    @Override
    public void refreshAll() {
        itemListBeans = new ArrayList<>();
        mDate = null;
        nextNum = 0;
        getDailyData();
    }
}
