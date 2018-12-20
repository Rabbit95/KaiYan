package com.rabbit.kaiyan.model.http;

import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.DiscoveryBean;
import com.rabbit.kaiyan.model.beans.RelateBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
     * @type
     * @explain Retrofit操作接口
     * @author Rabbit.
     * @creat time 2018/11/27 16:10.
**/
public class RetrofitHelper implements ApiHelper {
    private Api api;

    @Inject
    public RetrofitHelper(Api api){
        this.api = api;
    }


    @Override
    public Flowable<DailyBean> getDailyBean() {
        return api.getDailyBean();
    }

    @Override
    public Flowable<DailyBean> getDailyBean(Long date) {
        return api.getDailyBean(date);
    }

    @Override
    public Flowable<CategoryBean> getCategoryBean() {
        return api.getCategoryBean();
    }

    @Override
    public Flowable<CategoryBean> getMoreCategoryBean(int num) {
        return api.getMoreCategoryBean(num);
    }

    @Override
    public Flowable<DiscoveryBean> getDiscoveryBean() {
        return api.getDiscoverBean();
    }

    @Override
    public Flowable<RelateBean> getRelateBean(int id) {
        return api.getRelateBean(id);
    }

    @Override
    public Flowable<ReplyBean> getReplyBean(int id) {
        return api.getReplyBean(id);
    }

    @Override
    public Flowable<ReplyBean> getMoreReplyBean(int id, int lastId, int num) {
        return api.getMoreReplyBean(id, lastId, num);
    }

    @Override
    public Flowable<List<String>> getHotSearch() {
        return null;
    }
}
