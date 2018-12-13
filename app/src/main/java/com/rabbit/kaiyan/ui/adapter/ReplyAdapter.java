package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.ui.view.ItemReplyTitleView;
import com.rabbit.kaiyan.ui.view.ItemReplyView;
import com.rabbit.kaiyan.ui.view.ListEndView;

import java.util.List;

public class ReplyAdapter extends BaseRecyclerAdapter<ReplyBean.ItemListBean>{


    String TAG = "ReplyAdapter";
    public enum ITEM_TYPE {
        TYPE_TITLE,
        TYPE_END,
        TYPE_REPLY,
    }

    public ReplyAdapter(Context context, List<ReplyBean.ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "datas size:"+ getItemCount());
        if (position == datas.size()) {
            this.loadState = this.LIST_END;
            return ITEM_TYPE.TYPE_END.ordinal();
        }
        switch (datas.get(position).getType()) {
            case "reply":
                return ITEM_TYPE.TYPE_REPLY.ordinal();

            case "leftAlignTextHeader":
                return ITEM_TYPE.TYPE_TITLE.ordinal();

            default:
                return 0;

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == ITEM_TYPE.TYPE_END.ordinal()){
            itemView = new ListEndView(mContext);
        }else if(viewType == ITEM_TYPE.TYPE_TITLE.ordinal()){
            itemView = new ItemReplyTitleView(mContext);
        }else{
            itemView = new ItemReplyView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(itemView);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        if (itemView instanceof ItemReplyView) {
            ((ItemReplyView) itemView).setData(datas.get(position));
        } else if (itemView instanceof ItemReplyTitleView) {
            ((ItemReplyTitleView) itemView).setData(datas.get(position));
        } else if (itemView instanceof ListEndView) {
            ((ListEndView) itemView).textEnd.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    public void getData(ReplyBean replyBean){
        for (ReplyBean.ItemListBean itemListBean : replyBean.getItemList()) {
            if(itemListBean.getType().equals("videoSmallCard")) {
                replyBean.getItemList().remove(itemListBean);
            }
        }
        datas = replyBean.getItemList();
    }

    public void getMoreData(ReplyBean replyBean){
        for (ReplyBean.ItemListBean itemListBean : replyBean.getItemList()) {
            if(itemListBean.getType().equals("videoSmallCard")) {
                replyBean.getItemList().remove(itemListBean);
            }
        }
        datas.addAll(replyBean.getItemList());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
