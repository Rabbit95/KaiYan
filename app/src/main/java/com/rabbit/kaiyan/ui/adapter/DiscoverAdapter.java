package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.view.ItemHorizontalCardView;
import com.rabbit.kaiyan.ui.view.ItemHotAuthorView;
import com.rabbit.kaiyan.ui.view.ItemHotReplyView;
import com.rabbit.kaiyan.ui.view.ItemRankListView;
import com.rabbit.kaiyan.ui.view.ItemTitleView;
import com.rabbit.kaiyan.ui.view.ListEndView;
import com.rabbit.kaiyan.ui.view.TopBannerView;
import com.rabbit.kaiyan.util.DiffUtilCallBack;

import java.util.ArrayList;
import java.util.List;

public class DiscoverAdapter extends BaseRecyclerAdapter<ItemListBean> {

    private static final String TAG = "DiscoverAdapter";
    List<ItemListBean> mBannerList;
    List<ItemListBean> mCategoryCard;
    List<ItemListBean> mTopicCard;
    List<ItemListBean> mHotAuthor;
    List<ItemListBean> mHotReply;
    List<ItemListBean> mTitleCard;
    List<ItemListBean> mRankList;

    public DiscoverAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE.TYPE_BANNER.ordinal();
        }else if(position == 1){
            return ITEM_TYPE.TYPE_CATEGORY.ordinal();
        }else if(position == 2){
            //本周排行
            return ITEM_TYPE.TYPE_TITLE.ordinal();
        }else if(position == 3){
            return ITEM_TYPE.TYPE_RANK.ordinal();
        }else if(position == 4){
            //全部热门排行榜
            return ITEM_TYPE.TYPE_TITLE.ordinal();
        }else if(position == 5){
            return ITEM_TYPE.TYPE_BANNER.ordinal();
        }else if(position == 6){
            //热门作者
            return ITEM_TYPE.TYPE_TITLE.ordinal();
        }else if(position == 7){
            return ITEM_TYPE.TYPE_HOTAUTHOR.ordinal();
        }else if(position == 8){
            //全部作者
            return ITEM_TYPE.TYPE_TITLE.ordinal();
        }else if(position == 9){
            //热门评论
            return ITEM_TYPE.TYPE_TITLE.ordinal();
        }else if(position == 10){
            return ITEM_TYPE.TYPE_HOTREPLY.ordinal();
        }else if(position == 11){
            return ITEM_TYPE.TYPE_TITLE.ordinal();
        }else {
            return ITEM_TYPE.TYPE_LISTEND.ordinal();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == ITEM_TYPE.TYPE_BANNER.ordinal()){
            itemView = new TopBannerView(mContext);
        }else if(viewType == ITEM_TYPE.TYPE_TITLE.ordinal()){
            itemView = new ItemTitleView(mContext);
        }else if(viewType == ITEM_TYPE.TYPE_CATEGORY.ordinal()){
            itemView = new ItemHorizontalCardView(mContext);
        }else if(viewType == ITEM_TYPE.TYPE_RANK.ordinal()){
            itemView = new ItemRankListView(mContext);
        }else if(viewType == ITEM_TYPE.TYPE_HOTAUTHOR.ordinal()){
            itemView = new ItemHotAuthorView(mContext);
        }else if(viewType == ITEM_TYPE.TYPE_HOTREPLY.ordinal()){
            itemView = new ItemHotReplyView(mContext);
        }else{
            itemView = new ListEndView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        View view = holder.itemView;
        if(view instanceof TopBannerView){
            if(position == 0) {
                ((TopBannerView) view).setData(mBannerList,0);
            }else if(position == 5){
                ((TopBannerView) view).setData(mTopicCard,1);
            }
        }else if(view instanceof ItemTitleView){
            ((ItemTitleView) view).setData(datas.get(position - 1).getData().getText(),datas.get(position - 1).getTitlePosition());
        }else if(view instanceof ItemHorizontalCardView){
            ((ItemHorizontalCardView) view).setData(mCategoryCard);
        }else if(view instanceof ItemRankListView){
            ((ItemRankListView) view).setData(mRankList);
        }else if(view instanceof ItemHotAuthorView){
            ((ItemHotAuthorView) view).setData(mHotAuthor);
        }else if(view instanceof ItemHotReplyView){
            ((ItemHotReplyView) view).setData(mHotReply);
        }else{
            ((ListEndView) view).setData(this.LIST_END);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addDatas(List<ItemListBean> itemListBeans){
        Log.d(TAG, "data size:"+ itemListBeans.size());
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallBack(datas, itemListBeans), false);
        datas.clear();
        datas.addAll(itemListBeans);
        diffResult.dispatchUpdatesTo(this);
    }

    public void setBanner(List<ItemListBean> banner){
        mBannerList = new ArrayList<>();
        mBannerList = banner;
    }

    public void setTitles(List<ItemListBean> titles) {
        mTitleCard = new ArrayList<>();
        mTitleCard = titles;
    }


    public void setRankList(List<ItemListBean> rankList) {
        mRankList = new ArrayList<>();
        mRankList = rankList;
    }

    public void setCategory(List<ItemListBean> category) {
        mCategoryCard = new ArrayList<>();
        mCategoryCard = category;
    }

    public void setTopic(List<ItemListBean> topic) {
        mTopicCard = new ArrayList<>();
        mTopicCard = topic;
    }

    public void setHotAuthor(List<ItemListBean> hotAuthor) {
        mHotAuthor = new ArrayList<>();
        Log.d(TAG, "HotAuthor size: "+ mHotAuthor.size());
        mHotAuthor = hotAuthor;
    }

    public void setHotReply(List<ItemListBean> hotReply) {
        mHotReply = new ArrayList<>();
        mHotReply = hotReply;
    }

    private enum ITEM_TYPE{
        TYPE_BANNER,
        TYPE_TITLE,
        TYPE_DOUBLE_TITLE,
        TYPE_CATEGORY,
        TYPE_RANK,
        TYPE_HOTAUTHOR,
        TYPE_HOTREPLY,
        TYPE_LISTEND
    }

    private static class Holder extends ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
