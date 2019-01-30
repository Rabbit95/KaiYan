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

    /**
    * @explain 添加到浏览记录中
    **/
    @Override
    public void addToHistory(ItemListBean itemListBean) {
        mDataManager.insertReadId(itemListBean);
    }

    /**
    * @explain 检查是否已经浏览过了
    **/
    @Override
    public boolean isRead(int id) {
        if(mDataManager.getHistoryBean(id) == null){
            return false;
        }else{
            return true;
        }
    }

    /**
    * @explain 检查是否已经点赞过了
    **/
    @Override
    public void isLike(int id) {
        boolean like = mDataManager.checkLike(id);
        mView.setLike(like);
    }

    /**
    * @explain 取消点赞
    **/
    @Override
    public void deleteLikeId(int id) {
        mDataManager.deleteLikeId(id);
    }

    /**
    * @explain 删除浏览记录
    **/
    @Override
    public void deleteReadId(int id) {
        mDataManager.deleteReadId(id);
    }
    /**
    * @explain 保存点赞
    **/
    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        mDataManager.insertLikeId(itemListBean);
    }

    @Override
    public void download(String url, ItemListBean itemListBean) {
        switch (mDataManager.checkDownload(itemListBean.getData().getId())) {
            case 0:
                mDataManager.insertDownloadId(itemListBean);
                mDataManager.download(url, itemListBean);
                mView.showDownload();
                break;
            case 1:
                mView.showIsDownload();
                break;
            case 2:
                mView.showHadDownload();
                break;
        }
    }
    /**
     * @explain 获取 是否使用流量下播放
     **/
    @Override
    public boolean getPlaySetting() {
        return mDataManager.getPlaySetting();
    }
    /**
    * @explain 设置 是否使用流量播放
    **/
    @Override
    public void setPlaySetting(boolean playSetting) {
        mDataManager.setPlaySetting(playSetting);
    }
    /**
    * @explain 获取 是否使用流量下载
    **/
    @Override
    public boolean getDownloadSetting() {
        return mDataManager.getDownloadSetting();
    }
    /**
    * @explain 设置 是否使用流量下载
    **/
    @Override
    public void setDownloadSetting(boolean downloadSetting) {
        mDataManager.setDownloadStting(downloadSetting);
    }
}
