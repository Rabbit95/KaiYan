package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.DownloadBean;
import com.rabbit.kaiyan.ui.view.ListEndView;

import java.util.List;


public class DownloadAdapter extends BaseRecyclerAdapter<DownloadBean> {

    private static final String TAG = "HistoryAdapter";
    public DownloadAdapter(Context context, List<DownloadBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == datas.size()){
            return ITEM_TYPE.TYPE_END.ordinal();
        }else{
            return ITEM_TYPE.TYPE_NEW.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == ITEM_TYPE.TYPE_NEW.ordinal()){
            return new LikeHolder(mLayoutInflater.inflate(R.layout.item_videosmallcard,parent,false));
        }else{
            itemView = new ListEndView(mContext);
            itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            ((ListEndView)itemView).setData(DATA_END);
            return new Holder(itemView);
        }
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof LikeHolder){
            String detail = datas.get(position).getAuthorName() + " / #" + datas.get(position).getAuthorSlogen();
            ((LikeHolder) holder).desc.setText(detail);
            ((LikeHolder) holder).title.setText(datas.get(position).getTitle());
            ((LikeHolder) holder).line.setVisibility(View.VISIBLE);
            ImageLoader.loadRound(mContext, datas.get(position).getImage(), ((LikeHolder) holder).imageView);
            ((LikeHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }
    
    public void test(){
        Log.d(TAG, "test: ");
    }
    
    public void addDownloadData(List<DownloadBean> downloadBeans){
        datas.clear();
        datas.addAll(downloadBeans);
        notifyDataSetChanged();
    }

    public class LikeHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,desc;
        View line;
        public LikeHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_vsc_image);
            title = itemView.findViewById(R.id.iv_vsc_title);
            desc = itemView.findViewById(R.id.iv_vsc_des);
            line = itemView.findViewById(R.id.view_line);
        }
    }

    public class Holder extends RecyclerView.ViewHolder{

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public enum ITEM_TYPE {
        TYPE_NEW,
        TYPE_END
    }
}
