package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class HotReplyAdapter extends RecyclerView.Adapter<HotReplyAdapter.Holder> {

    List<ItemListBean> mHotReply;
    Context mContext;


    public HotReplyAdapter(Context context, List<ItemListBean> hotReply) {
        this.mContext = context;
        this.mHotReply = hotReply;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_single_reply, viewGroup, false);
        RecyclerView.ViewHolder holder = new Holder(view);
        return (Holder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ImageLoader.loadCircle(mContext,mHotReply.get(position).getData().getUser().getAvatar(),holder.ivReplyHead);
        holder.tvReplyName.setText(mHotReply.get(position).getData().getUser().getNickname());
        holder.tvReplyContent.setText(mHotReply.get(position).getData().getReply().getMessage());
        ImageLoader.loadRound(mContext,mHotReply.get(position).getData().getSimpleVideo().getCover().getFeed(),holder.ivVideoImg);
        holder.tvVideoTitle.setText(mHotReply.get(position).getData().getSimpleVideo().getTitle());
        holder.tvVideoCategory.setText("#"+mHotReply.get(position).getData().getSimpleVideo().getCategory());
        holder.tvReplyDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(Long.parseLong(mHotReply.get(position).getData().getCreateDate())));
        holder.tvReplyLikeNum.setText(String.valueOf(mHotReply.get(position).getData().getReply().getLikeCount()));
    }

    @Override
    public int getItemCount() {
        return mHotReply.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView ivReplyHead;
        TextView tvReplyName;
        TextView tvReplyContent;
        ImageView ivVideoImg;
        TextView tvVideoTitle;
        TextView tvVideoCategory;
        TextView tvReplyDate;
        TextView tvReplyLikeNum;
        ImageView tvReplyLikeImg;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ivReplyHead = itemView.findViewById(R.id.iv_reply_head);
            tvReplyName = itemView.findViewById(R.id.tv_reply_name);
            tvReplyContent = itemView.findViewById(R.id.tv_reply_content);
            ivVideoImg = itemView.findViewById(R.id.iv_video_img);
            tvVideoTitle = itemView.findViewById(R.id.tv_video_title);
            tvVideoCategory = itemView.findViewById(R.id.tv_video_category);
            tvReplyDate = itemView.findViewById(R.id.tv_reply_date);
            tvReplyLikeNum = itemView.findViewById(R.id.tv_reply_like_num);
            tvReplyLikeImg = itemView.findViewById(R.id.tv_reply_like_img);
        }
    }
}
