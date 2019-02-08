package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.SearchContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.SearchResultBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {

    List<String> hotSearch = new ArrayList<>();
    String nextUrl;
    List<ItemListBean> listBeans = new ArrayList<>();
    List<ItemListBean> moreListBeans = new ArrayList<>();

    @Inject
    public SearchPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * @explain 获取热门搜索词
     **/
    @Override
    public void getHotSearchData() {
        addSubscribe(mDataManager.getHotSearch()
                .compose(RxUtil.<List<String>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<String>>(mView) {
                    @Override
                    public void onNext(List<String> strings) {
                        Log.d("hzj", "onNext: strings" + strings);
                        hotSearch = strings;
                        mView.showHotSearch(strings);
                    }
                }));
    }

    /**
     * @explain 根据Query获取搜索内容
     **/
    @Override
    public void getSearchData(String query) {
        addSubscribe(mDataManager.getSearchResultBean(0, 10, query)
                .compose(RxUtil.<SearchResultBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<SearchResultBean>(mView) {
                    @Override
                    public void onNext(SearchResultBean searchResultBean) {
                        nextUrl = searchResultBean.getNextPageUrl();
                        listBeans.clear();
                        for (ItemListBean resultBean : searchResultBean.getItemList()) {
                            if (resultBean.getType().equals("video")) {
                                listBeans.add(resultBean);
                            }
                        }
                        mView.showResult(listBeans, searchResultBean.getTotal());
                    }
                }));
    }

    /**
     * @explain 获取更多搜索内容
     **/
    @Override
    public void getMoreData(String query) {
        if (nextUrl == null) {
            return;
        } else {
            final String startS = nextUrl.substring(nextUrl.indexOf("=") + 1, nextUrl.indexOf("&"));
            Log.d("hzj", "getMoreReplyData: " + startS);
            int start = Integer.valueOf(startS).intValue();
            addSubscribe(mDataManager.getSearchResultBean(start, 10, query)
                    .compose(RxUtil.<SearchResultBean>rxSchedulerHelper())
                    .subscribeWith(new CommonSubscriber<SearchResultBean>(mView) {
                        @Override
                        public void onNext(SearchResultBean searchResultBean) {
                            for (ItemListBean itemListBean : searchResultBean.getItemList()) {
                                if (itemListBean.getType().equals("video")) {
                                    moreListBeans.add(itemListBean);
                                }
                            }
                            mView.showMoreResult(moreListBeans);
                            nextUrl = searchResultBean.getNextPageUrl();
                        }
                    }));
        }
    }
}
