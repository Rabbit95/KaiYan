package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.Data;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.ArrayList;
import java.util.List;

public class FollowRecyclerAdapter extends RecyclerView.Adapter {

    private static final String TAG = "FollowRecyclerAdapter";

    private List<ItemListBean> itemDataList = new ArrayList<>();
    private Context context = null;

    private List<Data> data = new ArrayList<>();

    public FollowRecyclerAdapter(Context context, List<ItemListBean> itemDataList,List<Data> data) {
        this.itemDataList = itemDataList;
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_follow, parent, false);
        final RecyclerView.ViewHolder holder = new RecyclerItemNormalHolder(context, v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RecyclerItemNormalHolder recyclerItemViewHolder = (RecyclerItemNormalHolder) holder;
        recyclerItemViewHolder.setRecyclerBaseAdapter(this);
//        recyclerItemViewHolder.onBind(position, itemDataList.get(position));
        recyclerItemViewHolder.onBind(position,data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public void setListData(List<ItemListBean> itemDataList) {
        itemDataList = itemDataList;
        notifyDataSetChanged();
    }
}
