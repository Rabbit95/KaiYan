package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

public class RankListAdapter extends BaseRecyclerAdapter<ItemListBean> {

    private static final String TAG = "RankListAdapter";

    public RankListAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_ranklist,parent,false));
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        String detail = datas.get(position).getData().getAuthor().getName() + " / #" + datas.get(position).getData().getCategory();
        int duration = datas.get(position).getData().getDuration();
        String time = duration / 60 + ":" + duration % 60;
        ((ItemViewHolder) holder).mListTag.setText(detail);
        ((ItemViewHolder) holder).mListTitle.setText(datas.get(position).getData().getTitle());
        ((ItemViewHolder) holder).mListTime.setText(time);
        ImageLoader.loadRound(mContext, datas.get(position).getData().getCover().getFeed(), ((ItemViewHolder) holder).mListImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addRankListData(List<ItemListBean> rankDatas){
        datas.clear();
        datas.addAll(rankDatas);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView mListImage;
        TextView mListTitle;
        TextView mListTag;
        TextView mListTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mListImage = itemView.findViewById(R.id.iv_ranklist_image);
            mListTitle = itemView.findViewById(R.id.tv_ranklist_title);
            mListTag = itemView.findViewById(R.id.tv_ranklist_tag);
            mListTime = itemView.findViewById(R.id.tv_ranklist_time);
        }
    }
}
