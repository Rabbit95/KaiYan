package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemRankListView extends LinearLayout {

    Context mContext;
    List<ItemListBean> mRankList;

    @BindView(R.id.iv_rec_image)
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

    public void setData(List<ItemListBean> rankList){
        this.mRankList = rankList;

        ImageLoader.loadRound(mContext,rankList.get(0).getData().getContent().getData().getCover().getFeed(),ivRecImage);
        ImageLoader.loadCircle(mContext,rankList.get(0).getData().getContent().getData().getAuthor().getIcon(),ivRecHead);
        tvRecTitle.setText(rankList.get(0).getData().getContent().getData().getTitle());
        tvRecDes.setText(rankList.get(0).getData().getContent().getData().getAuthor().getName() + " / #" + rankList.get(0).getData().getContent().getData().getCategory());

        ImageLoader.loadRound(mContext,rankList.get(1).getData().getCover().getFeed(),ivRecImage2);
        ivRecTitle2.setText(rankList.get(1).getData().getTitle());
        ivRecDes2.setText("#"+rankList.get(1).getData().getCategory() + " / 开眼精选");

        ImageLoader.loadRound(mContext,rankList.get(2).getData().getCover().getFeed(),ivRecImage3);
        ivRecTitle3.setText(rankList.get(2).getData().getTitle());
        ivRecDes3.setText("#"+rankList.get(2).getData().getCategory() + " / 开眼精选");
    }
}
