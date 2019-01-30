package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemFollowCard extends LinearLayout {

    private static final String TAG = "ItemFollowCard";

    @BindView(R.id.iv_follow_image)
    ImageView ivFollowImage;
    @BindView(R.id.iv_follow_head)
    ImageView ivFollowHead;
    @BindView(R.id.tv_follow_title)
    TextView tvFollowTitle;
    @BindView(R.id.tv_follow_des)
    TextView tvFollowDes;
    @BindView(R.id.followcard_view)
    LinearLayout followcardView;
    private Context mContext;
    private ItemListBean mFollowCardData;

    public ItemFollowCard(Context context) {
        this(context, null);
    }

    public ItemFollowCard(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemFollowCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.item_followcard, this);
        ButterKnife.bind(this);
        followcardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, DetailActivity.class);
                intent.putExtra("itemListBean", mFollowCardData);
                mContext.startActivity(intent);
            }
        });
    }

    public void setData(ItemListBean itemListBean) {
        this.mFollowCardData = itemListBean;
        if (itemListBean.getType().equals("video")) {
            Log.e(TAG, "this is video:");
            ImageLoader.loadRound(mContext, itemListBean.getData().getCover().getFeed(), ivFollowImage);
            ImageLoader.loadCircle(mContext, itemListBean.getData().getAuthor().getIcon(), ivFollowHead);
            tvFollowTitle.setText(itemListBean.getData().getTitle());
            tvFollowDes.setText(itemListBean.getData().getAuthor().getName() + "  #" + itemListBean.getData().getCategory());
        } else {
            //videosmallfollowcard
            Log.e(TAG, "this is followcard" );
            ImageLoader.loadRound(mContext, itemListBean.getData().getContent().getData().getCover().getFeed(), ivFollowImage);
            ImageLoader.loadCircle(mContext, itemListBean.getData().getContent().getData().getAuthor().getIcon(), ivFollowHead);
            tvFollowTitle.setText(itemListBean.getData().getHeader().getTitle().toString());
            tvFollowDes.setText(itemListBean.getData().getHeader().getDescription().toString());
        }
    }
}
