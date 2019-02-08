package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.rabbit.kaiyan.ui.view.SearchAuthorItemView;

public class SearchAuthorAdapter extends PagerAdapter {

    Context context;
    ItemListBean itemListBean;

    public SearchAuthorAdapter(Context context, ItemListBean itemListBean) {
        this.context = context;
        this.itemListBean = itemListBean;
    }

    @Override
    public int getCount() {
        return itemListBean.getData().getItemList().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        SearchAuthorItemView view = new SearchAuthorItemView(context);
        view.setData(itemListBean.getData().getItemList().get(position));
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, DetailActivity.class);
                intent.putExtra("itemListBean", itemListBean.getData().getItemList().get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
