package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
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

public class ItemVideoSmallCard extends LinearLayout {
    @BindView(R.id.iv_vsc_image)
    ImageView ivVscImage;
    @BindView(R.id.iv_vsc_title)
    TextView ivVscTitle;
    @BindView(R.id.iv_vsc_des)
    TextView ivVscDes;
    @BindView(R.id.videosmallcard_view)
    LinearLayout videosmallcardView;
    private Context mContext;

    private ItemListBean mVideoSmallCardData;

    public ItemVideoSmallCard(Context context) {
        this(context, null);
    }

    public ItemVideoSmallCard(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemVideoSmallCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.item_videosmallcard, this);
        ButterKnife.bind(this);
        videosmallcardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, DetailActivity.class);
                intent.putExtra("itemListBean",mVideoSmallCardData);
                mContext.startActivity(intent);
            }
        });
    }

    public void setData(ItemListBean itemListBean) {
        this.mVideoSmallCardData = itemListBean;
        ImageLoader.loadRound(mContext, itemListBean.getData().getCover().getFeed(), ivVscImage);
        ivVscTitle.setText(itemListBean.getData().getTitle());
        ivVscDes.setText("#" + itemListBean.getData().getCategory() + " / 开眼精选");
    }

}
