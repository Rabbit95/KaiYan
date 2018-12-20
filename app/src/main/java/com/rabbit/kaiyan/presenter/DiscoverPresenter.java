package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.DiscoveryContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.DiscoveryBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DiscoverPresenter extends RxPresenter<DiscoveryContract.View> implements DiscoveryContract.Presenter {
    private static final String TAG = "DiscoverPresenter";

    List<ItemListBean> mBannerList = new ArrayList<>();
    List<ItemListBean> mCategoryCard = new ArrayList<>();
    List<ItemListBean> mTopicCard = new ArrayList<>();
    List<ItemListBean> mHotAuthor = new ArrayList<>();
    List<ItemListBean> mHotReply = new ArrayList<>();
    List<ItemListBean> mTitleCard = new ArrayList<>();
    List<ItemListBean> mDiscoverData = new ArrayList<>();
    List<ItemListBean> mRankList = new ArrayList<>();
    int titlePosition = 1;

    @Inject
    public DiscoverPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    @Override
    public void getDiscoverData() {
        addSubscribe(mDataManager.getDiscoveryBean()
        .compose(RxUtil.<DiscoveryBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<DiscoveryBean>(mView) {
            @Override
            public void onNext(DiscoveryBean discoveryBean) {
                Log.d(TAG, "discoveryBean size"+discoveryBean.getCount());
                for (ItemListBean itemListBean : discoveryBean.getItemList()) {
                    //取banner2
                   if(itemListBean.getType().equals("horizontalScrollCard")){
                       for (ItemListBean listBean : itemListBean.getData().getItemList()) {
                           if(listBean.getType().equals("banner")){
                               mBannerList.add(listBean);
                           }
                       }
                   }

                    //取分类卡
                    if(itemListBean.getType().equals("squareCardCollection")){
                       if (itemListBean.getData().getHeader().getTitle().equals("热门分类")){
                           mCategoryCard.add(itemListBean);
                       }else if(itemListBean.getData().getHeader().getTitle().equals("近期专题")){
                           for (ItemListBean listBean : itemListBean.getData().getItemList()) {
                               mTopicCard.add(listBean);
                           }
                       }
                       mDiscoverData.add(itemListBean);
                    }

                    //取标题
                   if(itemListBean.getType().equals("textCard")){
                       if(itemListBean.getData().getDataType().equals("TextCard")){
                           itemListBean.setTitlePosition(titlePosition);
                           mDiscoverData.add(itemListBean);
                           mTitleCard.add(itemListBean);
                           titlePosition = ~titlePosition;
                       }
                   }

                    //取排行榜 大卡片
                    if(itemListBean.getType().equals("followCard")){
                        mDiscoverData.add(itemListBean);
                        mRankList.add(itemListBean);
                    }
                    //取排行榜 小卡片
                    if(itemListBean.getType().equals("videoSmallCard")){
                        mRankList.add(itemListBean);
                    }

                    //取热门作者
                    if(itemListBean.getType().equals("videoCollectionWithBrief")){
                        mHotAuthor.add(itemListBean);
                        mDiscoverData.add(itemListBean);

                    }
                    //取热门评论
                    if(itemListBean.getType().equals("DynamicInfoCard")){
                        mHotReply.add(itemListBean);
                        mDiscoverData.add(itemListBean);
                    }

                }

                mDiscoverData.remove(10);
                mDiscoverData.add(10,mTitleCard.get(mTitleCard.size() - 1));
//                for (int i1 = 0; i1 < mDiscoverData.size(); i1++) {
//                    if(mDiscoverData.get(i1).getType().equals("textCard")){
//                        Log.d(TAG, "title position"+ i1 +" / " + mDiscoverData.get(i1).getData().getText());
//                    }
//                }
                mView.setBanner(mBannerList);
                mView.setTitles(mTitleCard);
                mView.setCategoryCard(mCategoryCard);
                mView.setRankList(mRankList);
                mView.setTopicCard(mTopicCard);
                mView.setHotAuthor(mHotAuthor);
                mView.setHotReply(mHotReply);
                mView.showContent(mDiscoverData);
            }
        }));

    }

    @Override
    public void refreshAll() {
        mBannerList = new ArrayList<>();
        mCategoryCard = new ArrayList<>();
        mTopicCard = new ArrayList<>();
        mHotAuthor = new ArrayList<>();
        mHotReply = new ArrayList<>();
        mTitleCard = new ArrayList<>();
        mDiscoverData = new ArrayList<>();
        mRankList = new ArrayList<>();
        getDiscoverData();
    }
}
