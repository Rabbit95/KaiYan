package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class TopAdapter extends PagerAdapter {
    private static final String TAG = "TopAdapter";

    Context context;
    List<ItemListBean> listBeans = new ArrayList<>();

    public TopAdapter(Context context, List<ItemListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_image, container, false);
        ImageView imageView = view.findViewById(R.id.iv_top);
        TextView title = view.findViewById(R.id.tv_title);
        TextView category = view.findViewById(R.id.tv_category);
        title.setVisibility(View.GONE);
        category.setVisibility(View.GONE);
        ImageLoader.loadRound(context, listBeans.get(position).getData().getCover().getFeed(), imageView);
        if(listBeans.get(position).getTitleVisible() == 1){
            title.setVisibility(View.VISIBLE);
            category.setVisibility(View.VISIBLE);
            title.setText(listBeans.get(position).getData().getTitle());
            category.setText("#"+listBeans.get(position).getData().getCategory());
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, DetailActivity.class);
                ItemListBean itemListBean = listBeans.get(position);
                intent.putExtra("itemListBean", itemListBean);
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
