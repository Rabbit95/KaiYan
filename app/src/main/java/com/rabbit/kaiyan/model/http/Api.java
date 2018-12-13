package com.rabbit.kaiyan.model.http;

import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.RelateBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
     * @type
     * @explain API接口
     * @author Rabbit.
     * @creat time 2018/11/27 16:07.
**/
public interface Api {
    String HOST = "http://baobab.kaiyanapp.com/api/";

     /**
     * @explain  获取每日数据
     **/
//    @GET("v2/feed?num=2")
//    Flowable<DailyBean> getDailyBean();
     @GET("v4/tabs/selected")
     Flowable<DailyBean> getDailyBean();



    /**
     * 获取之前日期数据
     *
     * @param date
     * @return
     */

    /**
    * @explain  根据时间戳获取往日数据
    **/
    @GET("v4/tabs/selected?")
    Flowable<DailyBean> getDailyBean(@Query("date") long date);


    /**
     * 获取热门数据，
     *
     * @param type week,month,history
     * @return
     */
//    @GET("v3/ranklist?num=10")
//    Flowable<HotBean> getWeekHotBean(@Query("strategy") String type);


    /**
     * 获取所有分类
     *
     * @return
     */
//    @GET("v2/categories")
//    Flowable<List<TagsBean>> getTagsBean();
    
    /**
    * @explain 获取第一页分类 
    **/
    @GET("v4/discovery/category")
    Flowable<CategoryBean> getCategoryBean();
    
    
    /**
    * @explain 获取下一页分类
    **/

    @GET("v4/discovery/category?")
    Flowable<CategoryBean> getMoreCategoryBean(@Query("start") int num);

    /**
     *
     * 获取该分类下所有视频
     *
     * @param start
     * @param num
     * @param id
     * @return
     */
//    @GET("v4/categories/videoList?strategy=date")
//    Flowable<TagChildBean> getTagChildBean(@Query("start") int start, @Query("num") int num, @Query("id") int id);


    /**
    * @explain 根据视频ID，获取相关推荐视频
    **/
    @GET("v4/video/related?")
    Flowable<RelateBean> getRelateBean(@Query("id") int id);

    /**
     * 根据ID来获取dataBean
     *
     * @param id
     * @return
     */
//    @GET("v1/video/{id}")
//    Flowable<ItemListBean.DataBean> getDataBean(@Path("id") int id);

    /**
    * @explain  根据ID获取评论内容
    **/
    @GET("v2/replies/video?")
    Flowable<ReplyBean> getReplyBean(@Query("videoId") int id);


    /**
    * @explain  根据ID获取更多评论内容
    **/
    @GET("v2/replies/video?")
    Flowable<ReplyBean> getMoreReplyBean(@Query("videoId") int id, @Query("lastId") int lastId, @Query("num") int num);



    /**
    * @explain  获取搜索热门
    **/
    @GET("v3/queries/hot")
    Flowable<List<String>> getHotSearch();

    /**
     * 根据搜索关键词获取内容
     *
     * @param query
     * @param start
     * @return
     */
//    @GET("v1/search?")
//    Flowable<SearchResultBean> getSearchResultBean(@Query("start") int start, @Query("num") int num, @Query("query") String query);

}
