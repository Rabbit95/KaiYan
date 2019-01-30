package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.view.ItemFollowCard;
import com.rabbit.kaiyan.ui.view.ItemTitleView;
import com.rabbit.kaiyan.ui.view.ItemTopImageView;
import com.rabbit.kaiyan.ui.view.ItemVideoSmallCard;
import com.rabbit.kaiyan.ui.view.TopPageView;
import com.rabbit.kaiyan.util.DiffUtilCallBack;

import java.util.List;

public class CategoryAdapter extends BaseRecyclerAdapter<ItemListBean> {
    private static final String TAG = "CategoryAdapter";

    public CategoryAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).getType().equals("videoCollectionOfHorizontalScrollCard")) {
            return ITEM_TYPE.TYPE_HORIZONTALSCROLLCARD.ordinal();
        } else if (datas.get(position).getType().equals("banner2") || datas.get(position).getType().equals("banner")) {
            return ITEM_TYPE.TYPE_TOPIMG.ordinal();
        } else if (datas.get(position).getType().equals("textCard") || datas.get(position).getType().equals("textHeader") || datas.get(position).getType().equals("textFooter")) {
            return ITEM_TYPE.TYPE_TEXTCARD.ordinal();
        } else if (datas.get(position).getType().equals("videoSmallCard")) {
            return ITEM_TYPE.TYPE_VIDEOSMALLCARD.ordinal();
        } else {
            return ITEM_TYPE.TYPE_FOLLOWCARD.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE.TYPE_HORIZONTALSCROLLCARD.ordinal()) {
            //水平滚动卡
            itemView = new TopPageView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_TOPIMG.ordinal()) {
            //顶部图片
            itemView = new ItemTopImageView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_TEXTCARD.ordinal()) {
            //文字卡
            itemView = new ItemTitleView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_VIDEOSMALLCARD.ordinal()) {
            //小详情卡
            itemView = new ItemVideoSmallCard(mContext);
        } else {
            //大详情卡
            itemView = new ItemFollowCard(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        if (view instanceof TopPageView) {
            ((TopPageView) view).setData(datas.get(position).getData().getItemList(), true);
        } else if (view instanceof ItemTopImageView) {
            ((ItemTopImageView) view).setData(datas.get(position));
        } else if (view instanceof ItemTitleView) {
            //titlePosition 设置标题位置 1为左
            ((ItemTitleView) view).setData(datas.get(position).getData().getText(), 1);
        } else if (view instanceof ItemVideoSmallCard) {
            ((ItemVideoSmallCard) view).setData(datas.get(position));
        } else {
            ((ItemFollowCard) view).setData(datas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addCategoryData(List<ItemListBean> itemListBeans) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallBack(datas, itemListBeans), false);
        datas.clear();
        datas.addAll(itemListBeans);
        diffResult.dispatchUpdatesTo(this);
    }

    public enum ITEM_TYPE {
        TYPE_TOPIMG,
        TYPE_TEXTCARD,
        TYPE_FOLLOWCARD,
        TYPE_VIDEOSMALLCARD,
        TYPE_HORIZONTALSCROLLCARD,
        TYPE_TEXTCARE_FOOTER,
    }

    private static class Holder extends RecyclerView.ViewHolder {
        private Holder(View itemView) {
            super(itemView);
        }
    }
}
