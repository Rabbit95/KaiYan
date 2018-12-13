package com.rabbit.kaiyan.presenter;

import android.util.Log;

import com.rabbit.kaiyan.base.contract.DetailContract;
import com.rabbit.kaiyan.model.DataManager;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.RelateBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.util.RxUtil;
import com.rabbit.kaiyan.widget.CommonSubscriber;

import javax.inject.Inject;

public class DetailPresenter extends RxPresenter<DetailContract.View> implements DetailContract.Presenter{

    String NextUrl;
    String TAG = "DetailPresenter";
    @Inject
    public DetailPresenter(DataManager manager){
        this.mDataManager = manager;
    }
    
    /**
    * @explain 获取被点击的Item的视频数据
    **/
    @Override
    public void getVideoData(int id) {
        addSubscribe(mDataManager.getRelateBean(id)
        .compose(RxUtil.<RelateBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<RelateBean>(mView) {
            @Override
            public void onNext(RelateBean relateBean) {
                Log.d(TAG, "RelateBean size:"+relateBean.getItemList().size());
                mView.showContent(relateBean.getItemList());
            }
        }));
    }

    /**
     * @explain 获取被点击的Item的评论数据
     **/
    @Override
    public void getReplyData(int id) {
        addSubscribe(mDataManager.getReplyBean(id)
        .compose(RxUtil.<ReplyBean>rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<ReplyBean>(mView) {
            @Override
            public void onNext(ReplyBean replyBean) {
                mView.showReply(replyBean);
                NextUrl = replyBean.getNextPageUrl();
            }
        }));
    }

    /**
     * @explain 获取被点击的Item的更多评论数据
     **/
    @Override
    public void getMoreReplyData(int id) {
        if(NextUrl == null){
            return;
        }else{
            String lastIdName = NextUrl.substring(NextUrl.indexOf("=") + 1,NextUrl.indexOf("&"));
            Log.d("DPr:MRDL:",lastIdName);
            int lastId = Integer.valueOf(lastIdName).intValue();
            addSubscribe(mDataManager.getMoreReplyBean(id,lastId,10)
            .compose(RxUtil.<ReplyBean>rxSchedulerHelper())
            .subscribeWith(new CommonSubscriber<ReplyBean>(mView) {
                @Override
                public void onNext(ReplyBean replyBean) {
                    mView.showMoreReply(replyBean);
                    NextUrl = replyBean.getNextPageUrl();
                }
            }));
        }
    }

    @Override
    public void addToHistory(ItemListBean itemListBean) {

    }

    @Override
    public boolean isRead(int id) {
        return false;
    }

    @Override
    public void isLike(int id) {

    }

    @Override
    public void deleteLikeId(int id) {

    }

    @Override
    public void deleteReadId(int id) {

    }

    @Override
    public void insertLikeId(ItemListBean itemListBean) {

    }

    @Override
    public void download(String url, ItemListBean itemListBean) {

    }

    @Override
    public boolean getPlaySetting() {
        return false;
    }

    @Override
    public void setPlaySetting(boolean playSetting) {

    }

    @Override
    public boolean getDownloadSetting() {
        return false;
    }

    @Override
    public void setDownloadSetting(boolean downloadSetting) {

    }
}
