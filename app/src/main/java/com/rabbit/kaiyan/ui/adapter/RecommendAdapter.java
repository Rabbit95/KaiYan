package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.view.ItemRecommendDateView;
import com.rabbit.kaiyan.ui.view.ItemRecommendView;
import com.rabbit.kaiyan.ui.view.ListEndView;
import com.rabbit.kaiyan.ui.view.TopPageView;
import com.rabbit.kaiyan.util.DiffUtilCallBack;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseRecyclerAdapter<ItemListBean> {



    String mDate;
    List<ItemListBean> topDataList = new ArrayList<>();
    List<ItemListBean> categoryList = new ArrayList<>();
    public RecommendAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.TYPE_DATE.ordinal();
        } else if(position == 1){
            return ITEM_TYPE.TYPE_TOP.ordinal();
        }else if(position + 1 == getItemCount()){
            return ITEM_TYPE.TYPE_END.ordinal();
        }
        return ITEM_TYPE.TYPE_CATEGORY.ordinal();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE.TYPE_DATE.ordinal()) {
            itemView = new ItemRecommendDateView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_TOP.ordinal()) {
            itemView = new TopPageView(mContext);
        } else if(viewType == ITEM_TYPE.TYPE_END.ordinal()){
            itemView = new ListEndView(mContext);
        }else {
            itemView = new ItemRecommendView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    @Override
    public void convert(ViewHolder holder, final int position) {
        View view = holder.itemView;
        if (view instanceof ItemRecommendDateView) {
            ((ItemRecommendDateView) view).setDate(mDate);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        } else if (view instanceof TopPageView) {
            ((TopPageView)view).setData(topDataList,false);
        } else if(view instanceof ListEndView){
            ((ListEndView) view).setData(loadState);
        } else {
            for (int i = 0; i < datas.size(); i++) {
                if(datas.get(i).getTag() == null){
                    ((ItemRecommendView) view).setData(datas.get(position + 4));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() - 3;
    }

    public void addDailyData(List<ItemListBean> itemListBeans) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallBack(datas, itemListBeans), false);
        datas.clear();
        datas.addAll(itemListBeans);
        diffResult.dispatchUpdatesTo(this);
    }


    public void addDailyDate(String date){
        this.mDate = date;
    }

    public void addTopData(List<ItemListBean> topDailyList){
        this.topDataList = topDailyList;
    }

    public void addCategoryData(List<ItemListBean> categoryListBean){
        this.categoryList = categoryListBean;

    }

    public static class Holder extends ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public enum ITEM_TYPE {
        TYPE_DATE,
        TYPE_CATEGORY,
        TYPE_TOP,
        TYPE_END
    }
}
