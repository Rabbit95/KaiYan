package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.activity.WEBViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTopImageView extends RelativeLayout {
    @BindView(R.id.iv_single_img)
    ImageView ivSingleImg;
    private Context mContext;
    private ItemListBean mTopImageData;
    public ItemTopImageView(Context context) {
        this(context, null);
    }

    public ItemTopImageView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemTopImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.item_single_image, this);
        ButterKnife.bind(this);
        ivSingleImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, WEBViewActivity.class);
                intent.putExtra("itemListBean", mTopImageData);
                mContext.startActivity(intent);
            }
        });
    }

    public void setData(ItemListBean itemListBean ){
        this.mTopImageData = itemListBean;
        ImageLoader.loadRound(mContext,itemListBean.getData().getImage(),ivSingleImg);
    }
}
