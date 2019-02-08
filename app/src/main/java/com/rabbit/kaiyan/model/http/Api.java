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
import com.rabbit.kaiyan.model.beans.SearchResultBean;
import com.rabbit.kaiyan.model.beans.UserInfoBean;
import com.rabbit.kaiyan.model.beans.VideoFlowBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
     * @type
     * @explain API接口
     * @author Rabbit.
     * @creat time 2018/11/27 16:07.
**/
public interface Api {
    String KaiYan_API = "http://baobab.kaiyanapp.com/";
    String My_API = "http://134.175.184.111:8080/";

     /**
     * @explain  获取每日数据
     **/
//    @GET("v2/feed?num=2")
//    Flowable<DailyBean> getDailyBean();
     @Headers({"URL:KaiYan"})
     @GET("api/v4/tabs/selected")
     Flowable<DailyBean> getDailyBean();

     /**
     * @explain 多地址测试
     **/
//     @Headers({"URL:My"})
//     @GET("smdemo/user/connectionTest")
//     Flowable<String> connectionTest();

     /**
     * @explain 用户登录
     **/
     @Headers({"URL:My"})
     @GET("my/api/login?")
    Flowable<UserInfoBean> login(@Query("username") String username,@Query("password") String password);

     /**
     * @explain 用户注册
     **/
     @Headers({"URL:My"})
     @GET("my/api/registered?")
     Flowable<UserInfoBean> register(@Query("username") String username,@Query("password") String password, @Query("email") String email);


     /**
     * @explain 获取流视频
     **/
     @Headers({"URL:My"})
     @GET("my/api/getFlow")
     Flowable<VideoFlowBean> getVideoFlow();



    /**
     * 获取之前日期数据
     *
     * @param date
     * @return
     */

    /**
    * @explain  根据时间戳获取往日数据
    **/
    @GET("api/v4/tabs/selected?")
    Flowable<DailyBean> getDailyBean(@Query("date") long date);

    /**
    * @explain 获取发现页数据
    **/
    @GET("api/v5/index/tab/discovery?udid=b301c597351746f2b68004e7dd15b72e7baec89e&vc=421&vn=4.7&size=1080X1920&deviceModel=vivo%20y55a")
    Flowable<DiscoveryBean> getDiscoverBean();

    /**
    * @explain
    **/
    @GET("api/v5/index/tab/category/{id}?udid=b301c597351746f2b68004e7dd15b72e7baec89e")
    Flowable<CategoryBean> getCategoryBean(@Path("id") int categoryID);


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
    @GET("api/v4/discovery/category")
    Flowable<CategoryBean> getCategoryBean();
    
    
    /**
    * @explain 获取下一页分类
    **/

    @GET("api/v4/discovery/category?")
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
    @GET("api/v4/video/related?")
    Flowable<RelateBean> getRelateBean(@Query("id") int id);

    /**
     * 根据ID来获取dataBean
     *
     * @param id
     * @return
     */
    @GET("api/v1/video/{id}")
    Flowable<DataBean> getDataBean(@Path("id") int id);

    /**
    * @explain  根据ID获取评论内容
    **/
    @GET("api/v2/replies/video?")
    Flowable<ReplyBean> getReplyBean(@Query("videoId") int id);


    /**
    * @explain  根据ID获取更多评论内容
    **/
    @GET("api/v2/replies/video?")
    Flowable<ReplyBean> getMoreReplyBean(@Query("videoId") int id, @Query("lastId") int lastId, @Query("num") int num);



    /**
    * @explain  获取搜索热门
    **/
    @GET("api/v3/queries/hot")
    Flowable<List<String>> getHotSearch();


    /**
    * @explain 获取关注数据
    **/
    @GET("api/v6/community/tab/follow")
    Flowable<FollowBean> getFollowBean();

    /**
    * @explain 根据ID，获取分类的分类信息
    **/

    @GET("api/v4/categories/detail/tab?")
    Flowable<CategoryInfo> getCategoryInfoByID(@Query("id") int id);

    /**
    * @explain 根据分类ID，获取该分类首页的数据
    **/

    @GET("api/v4/categories/detail/index?")
    Flowable<CategoryBean> getCategoryPageDataByID(@Query("id") String id);

    /**
    * @explain 根据周、月、总，获取排行榜数据
    **/
    @GET("api/v4/rankList/videos?")
    Flowable<RankListBean> getRankListDataByCycle(@Query("strategy") String cycle);


    /**
     * 根据搜索关键词获取内容
     *
     * @param query
     * @param start
     * @return
     */
    @GET("api/v1/search?")
    Flowable<SearchResultBean> getSearchResultBean(@Query("start") int start, @Query("num") int num, @Query("query") String query);

}
