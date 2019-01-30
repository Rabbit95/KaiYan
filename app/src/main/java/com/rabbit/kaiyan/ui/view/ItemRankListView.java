package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.activity.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemRankListView extends LinearLayout {

    private static final String TAG = "ItemRankListView";

    Context mContext;
    List<ItemListBean> mRankList;

    @BindView(R.id.iv_rec_image) //R.id.iv_rec_image,R.id.iv_rec_head,R.id.tv_rec_title,R.id.tv_rec_des
            ImageView ivRecImage;
    @BindView(R.id.iv_rec_head)
    ImageView ivRecHead;
    @BindView(R.id.tv_rec_title)
    TextView tvRecTitle;
    @BindView(R.id.tv_rec_des)
    TextView tvRecDes;
    @BindView(R.id.iv_rec_image2)
    ImageView ivRecImage2;
    @BindView(R.id.iv_rec_title2)
    TextView ivRecTitle2;
    @BindView(R.id.iv_rec_des2)
    TextView ivRecDes2;
    @BindView(R.id.iv_rec_image3)
    ImageView ivRecImage3;
    @BindView(R.id.iv_rec_title3)
    TextView ivRecTitle3;
    @BindView(R.id.iv_rec_des3)
    TextView ivRecDes3;


    public ItemRankListView(Context context) {
        this(context, null);
    }

    public ItemRankListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemRankListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.item_rank, this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_rec_image, R.id.iv_rec_head, R.id.tv_rec_title, R.id.tv_rec_des})
    public void item1click() {
        Log.d(TAG, "item 1 click: ");
        jumpToDetailActivity(0);
    }

    @OnClick({R.id.iv_rec_image2, R.id.iv_rec_title2, R.id.iv_rec_des2})
    public void item2click() {
        Log.d(TAG, "item 2 click: ");
        jumpToDetailActivity(1);
    }

    @OnClick({R.id.iv_rec_image3, R.id.iv_rec_title3, R.id.iv_rec_des3})
    public void item3click() {
        Log.d(TAG, "item 3 click: ");
        jumpToDetailActivity(2);
    }

    private void jumpToDetailActivity(int position) {
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        intent.putExtra("itemListBean", mRankList.get(position));
        mContext.startActivity(intent);
    }

    public void setData(List<ItemListBean> rankList) {
        this.mRankList = rankList;

        ImageLoader.loadRound(mContext, rankList.get(0).getData().getContent().getData().getCover().getFeed(), ivRecImage);
        ImageLoader.loadCircle(mContext, rankList.get(0).getData().getContent().getData().getAuthor().getIcon(), ivRecHead);
        tvRecTitle.setText(rankList.get(0).getData().getContent().getData().getTitle());
        tvRecDes.setText(rankList.get(0).getData().getContent().getData().getAuthor().getName() + " / #" + rankList.get(0).getData().getContent().getData().getCategory());

        ImageLoader.loadRound(mContext, rankList.get(1).getData().getCover().getFeed(), ivRecImage2);
        ivRecTitle2.setText(rankList.get(1).getData().getTitle());
        ivRecDes2.setText("#" + rankList.get(1).getData().getCategory() + " / 开眼精选");

        ImageLoader.loadRound(mContext, rankList.get(2).getData().getCover().getFeed(), ivRecImage3);
        ivRecTitle3.setText(rankList.get(2).getData().getTitle());
        ivRecDes3.setText("#" + rankList.get(2).getData().getCategory() + " / 开眼精选");
    }
}
