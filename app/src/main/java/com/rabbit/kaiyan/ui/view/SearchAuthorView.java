package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.adapter.SearchAuthorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAuthorView extends LinearLayout {
    Context context;
    @BindView(R.id.iv_item_author)
    ImageView ivItemAuthor;
    @BindView(R.id.tv_item_author_name)
    TextView tvItemAuthorName;
    @BindView(R.id.tv_item_author_dis)
    TextView tvItemAuthorDis;
    @BindView(R.id.vp_search_author)
    ViewPager vpSearchAuthor;

    ItemListBean resultBean;
    SearchAuthorAdapter adapter;

    public SearchAuthorView(Context context) {
        this(context, null);
    }

    public SearchAuthorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public SearchAuthorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void initView() {
        View.inflate(context, R.layout.item_search_author, this);
        ButterKnife.bind(this);
    }

    public void setData(ItemListBean resultBean){
        this.resultBean = resultBean;
        ImageLoader.loadCircle(context, resultBean.getData().getHeader().getIcon().toString(), ivItemAuthor);
        tvItemAuthorDis.setText(resultBean.getData().getHeader().getDescription().toString());
        tvItemAuthorName.setText(resultBean.getData().getHeader().getTitle().toString());
        adapter = new SearchAuthorAdapter(context, resultBean);
        vpSearchAuthor.setAdapter(adapter);
        vpSearchAuthor.setOffscreenPageLimit(3);
        vpSearchAuthor.setPageMargin(8);
    }

}
