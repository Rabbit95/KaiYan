package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.view.ItemDailyView;
import com.rabbit.kaiyan.ui.view.ListEndView;
import com.rabbit.kaiyan.ui.view.SearchAuthorView;

import java.util.List;

public class SearchResultAdapter extends BaseRecyclerAdapter<ItemListBean>{

    public SearchResultAdapter(Context context, List<ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return ITEM_TYPE.TYPE_END.ordinal();
        } else if (datas.get(position).getType().equals("videoCollectionWithBrief")) {
            return ITEM_TYPE.TYPE_AUTHOR.ordinal();
        } else {
            return ITEM_TYPE.TYPE_NEW.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE.TYPE_AUTHOR.ordinal()) {
            itemView = new SearchAuthorView(mContext);
        } else if (viewType == ITEM_TYPE.TYPE_END.ordinal()) {
            itemView = new ListEndView(mContext);
        } else {
            itemView = new ItemDailyView(mContext);
        }
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(itemView);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, final int position) {
        View view = holder.itemView;
        if (view instanceof SearchAuthorView) {
            ((SearchAuthorView) view).setData(datas.get(position));
        } else if (view instanceof ItemDailyView) {
            Log.e("adapter", "position"+position);
            Log.e("adapter", "data size"+datas.size() );
            ((ItemDailyView) view).setData(datas.get(position));
            ((ItemDailyView) view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
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

    public void setData(List<ItemListBean> itemListBeans){
        datas.clear();
        datas.addAll(itemListBeans);
        notifyDataSetChanged();
    }

    public enum ITEM_TYPE {
        TYPE_AUTHOR,
        TYPE_NEW,
        TYPE_END
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
