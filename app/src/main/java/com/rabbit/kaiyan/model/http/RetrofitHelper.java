package com.rabbit.kaiyan.model.http;

import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.CategoryInfo;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.DiscoveryBean;
import com.rabbit.kaiyan.model.beans.FollowBean;
import com.rabbit.kaiyan.model.beans.RankListBean;
import com.rabbit.kaiyan.model.beans.RelateBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.model.beans.UserInfoBean;
import com.rabbit.kaiyan.model.beans.VideoFlowBean;

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
    public Flowable<CategoryBean> getCategoryBean(int categoryID) {
        return api.getCategoryBean(categoryID);
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
    public Flowable<FollowBean> getFollowBean() {
        return api.getFollowBean();
    }

    @Override
    public Flowable<CategoryInfo> getCategoryInfoByID(int categoryID) {
        return api.getCategoryInfoByID(categoryID);
    }

    @Override
    public Flowable<CategoryBean> getCategoryPageDataByID(String categoryID) {
        return api.getCategoryPageDataByID(categoryID);
    }

    @Override
    public Flowable<RankListBean> getRankListDataByCycle(String cycle) {
        return api.getRankListDataByCycle(cycle);
    }

    @Override
    public Flowable<UserInfoBean> login(String username, String password) {
        return api.login(username,password);
    }

//    @Override
//    public Flowable<String> connectionTest() {
//        return api.connectionTest();
//    }

    @Override
    public Flowable<RelateBean> getRelateBean(int id) {
        return api.getRelateBean(id);
    }

    @Override
    public Flowable<DataBean> getDataBean(int id) {
        return api.getDataBean(id);
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

    @Override
    public Flowable<VideoFlowBean> getVideoFlowBean() {
        return api.getVideoFlow();
    }
}
