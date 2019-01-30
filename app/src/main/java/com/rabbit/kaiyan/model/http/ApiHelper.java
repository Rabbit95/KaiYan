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

import io.reactivex.Flowable;

/**
     * @type
     * @explain API操作接口
     * @author Rabbit.
     * @creat time 2018/11/27 16:08.
**/
public interface ApiHelper {

    Flowable<DailyBean> getDailyBean();

    Flowable<DailyBean> getDailyBean(Long date);

    Flowable<CategoryBean> getCategoryBean();

    Flowable<CategoryBean> getCategoryBean(int categoryID);

    Flowable<CategoryBean> getMoreCategoryBean(int num);

    Flowable<DiscoveryBean> getDiscoveryBean();

    Flowable<FollowBean> getFollowBean();

    Flowable<CategoryInfo> getCategoryInfoByID(int categoryID);

    Flowable<CategoryBean> getCategoryPageDataByID(String categoryID);

    Flowable<RankListBean> getRankListDataByCycle(String cycle);

//    Flowable<String> connectionTest();

    Flowable<UserInfoBean> login(String username,String password);

//    Flowable<HotBean> getWeekHotBean(String type);
//
//    Flowable<List<TagsBean>> getTagsBean();
//
//    Flowable<TagChildBean> getTagChildBean(int start, int num, int id);
//
    Flowable<RelateBean> getRelateBean(int id);
//
    Flowable<DataBean> getDataBean(int id);

    Flowable<ReplyBean> getReplyBean(int id);

    Flowable<ReplyBean> getMoreReplyBean(int id, int lastId, int num);

    Flowable<List<String>> getHotSearch();

    Flowable<VideoFlowBean> getVideoFlowBean();

//    Flowable<SearchResultBean> getSearchResultBean(int start, int num, String query);
}
