package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.rabbit.kaiyan.ui.adapter.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultView extends RelativeLayout {

    @BindView(R.id.rv_search_result)
    public RecyclerView recyclerView;

    public LinearLayoutManager manager;
    SearchResultAdapter adapter;
    List<ItemListBean> itemListBeans = new ArrayList<>();
    Context context;


    public SearchResultView(Context context) {
        this(context, null);
    }

    public SearchResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public SearchResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void initView() {
        View.inflate(context, R.layout.item_search_result, this);
        ButterKnife.bind(this);
        adapter = new SearchResultAdapter(context, itemListBeans);
        manager = new LinearLayoutManager(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(context, DetailActivity.class);
                ItemListBean itemListBean = itemListBeans.get(id);
                intent.putExtra("itemListBean", itemListBean);
                context.startActivity(intent);
            }
        });
    }
    public void setData(List<ItemListBean> itemListBeans){
        this.itemListBeans = itemListBeans;
        adapter.setData(itemListBeans);
    }

    public void setMoreData(List<ItemListBean> itemListBeans){
        this.itemListBeans = itemListBeans;
        adapter.setData(itemListBeans);
    }


}
