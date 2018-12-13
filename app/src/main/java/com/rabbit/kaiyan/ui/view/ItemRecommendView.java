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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemRecommendView extends LinearLayout {


    Context mContext;
    ItemListBean itemListBeans;

    @BindView(R.id.tv_rec_covertitle)
    public TextView tvRecCovertitle;

    @BindView(R.id.iv_rec_image)
    public ImageView ivRecImage;
    @BindView(R.id.iv_rec_head)
    public ImageView ivRecHead;
    @BindView(R.id.tv_rec_title)
    public TextView tvRecTitle;
    @BindView(R.id.tv_rec_des)
    public TextView tvRecDes;

    //R.id.iv_rec_image2,R.id.iv_rec_title2,R.id.iv_rec_des2
    @BindView(R.id.iv_rec_image2)
    public ImageView ivRecImage2;
    @BindView(R.id.iv_rec_title2)
    public TextView ivRecTitle2;
    @BindView(R.id.iv_rec_des2)
    public TextView ivRecDes2;

    @BindView(R.id.iv_rec_image3)
    public ImageView ivRecImage3;
    @BindView(R.id.iv_rec_title3)
    public TextView ivRecTitle3;
    @BindView(R.id.iv_rec_des3)
    public TextView ivRecDes3;

    @BindView(R.id.tv_rec_more)
    public TextView tvRecMore;

    String TAG = "ItemRecommendView";

    public ItemRecommendView(Context context) {
        this(context, null);
    }

    public ItemRecommendView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemRecommendView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public void initView() {
        View.inflate(mContext, R.layout.item_recommend, this);
        ButterKnife.bind(this);
    }

    //第一项
    @OnClick({R.id.iv_rec_image,R.id.iv_rec_head,R.id.tv_rec_title,R.id.tv_rec_des})
    public void topItemClick(){
        Log.d("-----------", "top item: click");
        startActivity(0);
    }

    //第二项
    @OnClick({R.id.iv_rec_image2,R.id.iv_rec_title2,R.id.iv_rec_des2})
    public void itemChild1Click(){
        Log.d("-----------", "item1: click");
        startActivity(1);
    }

    //第三项
    @OnClick({R.id.iv_rec_image3,R.id.iv_rec_title3,R.id.iv_rec_des3})
    public void itemChild2Click (){
        Log.d("-----------", "item2: click");
        startActivity(2);
    }
    //分类title和更多
    @OnClick({R.id.tv_rec_covertitle,R.id.tv_rec_more})
    public void coverAndmore(){
        Log.d(TAG, "coverAndmore: click");
    }

    public void startActivity(int id){
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        intent.putExtra("itemListBean", itemListBeans.getData().getItemList().get(id));
        mContext.startActivity(intent);
    }

    public void setData(ItemListBean itemListBean){
        this.itemListBeans = itemListBean;
        tvRecCovertitle.setText(itemListBeans.getData().getHeader().getTitle().toString() + " >");
//        tvRecCovertitle.setText(itemListBean.getData().getItemList().get(0).getData().getCategory() + " >");
        ImageLoader.loadRound(mContext,itemListBeans.getData().getItemList().get(0).getData().getCover().getFeed(),ivRecImage);
        ImageLoader.loadCircle(mContext,itemListBeans.getData().getItemList().get(0).getData().getAuthor().getIcon(),ivRecHead);
        tvRecTitle.setText(itemListBeans.getData().getItemList().get(0).getData().getTitle());
        tvRecDes.setText(itemListBeans.getData().getItemList().get(0).getData().getAuthor().getName() + " / #" + itemListBeans.getData().getItemList().get(0).getData().getCategory());

        ImageLoader.loadRound(mContext,itemListBeans.getData().getItemList().get(1).getData().getCover().getFeed(),ivRecImage2);
        ivRecTitle2.setText(itemListBeans.getData().getItemList().get(1).getData().getTitle());
        ivRecDes2.setText("#"+itemListBeans.getData().getItemList().get(1).getData().getCategory());

        ImageLoader.loadRound(mContext,itemListBeans.getData().getItemList().get(2).getData().getCover().getFeed(),ivRecImage3);
        ivRecTitle3.setText(itemListBeans.getData().getItemList().get(2).getData().getTitle());
        ivRecDes3.setText("#"+itemListBeans.getData().getItemList().get(2).getData().getCategory());
    }
}
