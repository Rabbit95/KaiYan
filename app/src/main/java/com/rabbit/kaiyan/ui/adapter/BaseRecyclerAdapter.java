package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter{

    public int loadState = 1;
    public final int LOADING = 1;
    public final int LOADING_COMPLETE = 2;
    public final int LOADING_END = 3;
    public final int LIST_END = 4;
    public final int DATA_END = 5;

    protected List<T> datas = new ArrayList<>();
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected OnItemClickListener onItemClickListener;
    protected OnButtonClickListener onButtonClickListener;


    public BaseRecyclerAdapter(Context context, List<T> datas) {
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        convert(holder, position);
    }

    public abstract void convert(RecyclerView.ViewHolder holder, int position);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public interface OnButtonClickListener {
        void onButtonClick(View view, int position);
    }

}
