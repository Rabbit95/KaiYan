package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.DailyContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.DailyBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{

    private static final String TAG = "DailyPresenter";
    List<ItemListBean> itemListBeans = new ArrayList<>();
    String nextUrl;
    long nextDate = 0;
    long nextPageDate = 0;

    @Inject
    public DailyPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    /**
    * @explain  获取每日数据
    **/
    @Override
    public void getDailyData() {
        Log.d(TAG, "getDailyData: ");
        addSubscribe(mDataManager.getDailyBean()
                .compose(RxUtil.<DailyBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
                    @Override
                    public void onNext(DailyBean dailyBean) {
                        if (null != dailyBean.getItemList().get(0)) {
                            //取出下一个日期的时间戳（下一页的时间戳）
                            nextUrl = dailyBean.getNextPageUrl();
                            String nextDateName = nextUrl.substring(nextUrl.indexOf("=") + 1, nextUrl.indexOf("&"));
                            nextPageDate = Long.decode(nextDateName);


                            //取出Type为video的Item
                            List<ItemListBean> videoList = new ArrayList<>();
                            for (ItemListBean itemListBean : dailyBean.getItemList()) {
                                if(itemListBean.getType().equals("video")){
                                    videoList.add(itemListBean);
                                }
                            }
                            //以日期划分，遍历videoList
                            int datesize = 0;
                            int videosize = 0;
                            for (ItemListBean itemListBean : videoList) {
                                if(itemListBean.getData().getDate() != nextDate){
                                    //发现不是同一个日期，则直接在总List里添加日期Item
                                    nextDate = itemListBean.getData().getDate();
                                    ItemListBean dateItem = new ItemListBean();
                                    dateItem.setDate(nextDate);
                                    itemListBeans.add(dateItem);
                                    datesize++;
                                    Log.d(TAG, "date size:"+datesize);
                                }
                                itemListBeans.add(itemListBean);
                                videosize++;
                            }
                            Log.d(TAG, "video size:"+videosize);
                        }
                        Log.d(TAG, "itemlist size:"+itemListBeans.size());
                        mView.showContent(itemListBeans);
                    }
                }));
    }
    /**
    * @explain  获取往日数据
    **/
    @Override
    public void getMoreData() {
        Log.d("DPR.getMoreData","");
        addSubscribe(mDataManager.getDailyBean(nextPageDate)
        .compose(RxUtil.<DailyBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<DailyBean>(mView) {
            @Override
            public void onNext(DailyBean dailyBean) {
                if (null != dailyBean.getItemList().get(0)) {
                    //取出下一个日期的时间戳（下一页的时间戳）
                    nextUrl = dailyBean.getNextPageUrl();
                    String nextDateName = nextUrl.substring(nextUrl.indexOf("=") + 1, nextUrl.indexOf("&"));
                    nextPageDate = Long.decode(nextDateName);

                    //取出Type为video的Item
                    List<ItemListBean> videoList = new ArrayList<>();
                    for (ItemListBean itemListBean : dailyBean.getItemList()) {
                        if(itemListBean.getType().equals("video")){
                            videoList.add(itemListBean);
                        }
                    }
                    //以日期划分，遍历videoList
                    int datesize = 0;
                    int videosize = 0;
                    for (ItemListBean itemListBean : videoList) {
                        if(itemListBean.getData().getDate() != nextDate){
                            //发现不是同一个日期，则直接在总List里添加日期Item
                            nextDate = itemListBean.getData().getDate();
                            ItemListBean dateItem = new ItemListBean();
                            dateItem.setDate(nextDate);
                            itemListBeans.add(dateItem);
                            datesize++;
                            Log.d(TAG, "date size:"+datesize);
                        }
                        itemListBeans.add(itemListBean);
                        videosize++;
                    }
                    Log.d(TAG, "video size:"+videosize);
                }
                Log.d(TAG, "itemlist size:"+itemListBeans.size());
                mView.showContent(itemListBeans);
            }
        }));
    }


    @Override
    public void refreshAll() {
        nextUrl = null;
        nextDate = 0;
        nextPageDate = 0;
        itemListBeans = new ArrayList<>();
        getDailyData();
    }
}
