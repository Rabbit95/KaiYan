package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAuthorItemView extends CardView {
    Context context;
    @BindView(R.id.iv_daily_item_image)
    ImageView ivDailyItemImage;
    @BindView(R.id.item_text_title)
    TextView itemTextTitle;
    @BindView(R.id.item_text_tag)
    TextView itemTextTag;

    public SearchAuthorItemView(@NonNull Context context) {
        this(context, null);
    }

    public SearchAuthorItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.item_search_authoritem, this);
        ButterKnife.bind(this);
    }

    public SearchAuthorItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setData(ItemListBean itemListBean){
        itemTextTag.setText("# " + itemListBean.getData().getCategory());
        itemTextTitle.setText(itemListBean.getData().getTitle());
        ImageLoader.load(context, itemListBean.getData().getCover().getFeed(), ivDailyItemImage);
    }
}
