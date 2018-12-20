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
import com.rabbit.kaiyan.ui.adapter.TopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemHotAuthorView extends LinearLayout {

    private static final String TAG = "ItemHotAuthorView";
    Context mContext;
    List<ItemListBean> mAuthor;
    List<ItemListBean> mVideoList = new ArrayList<>();
    @BindView(R.id.iv_rec_head)
    ImageView ivRecHead;
    @BindView(R.id.tv_rec_title)
    TextView tvRecTitle;
    @BindView(R.id.tv_rec_des)
    TextView tvRecDes;
    @BindView(R.id.vp_author)
    ViewPager viewPager;

    TopAdapter mAdapter;

    public ItemHotAuthorView(Context context) {
        this(context, null);
    }

    public ItemHotAuthorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemHotAuthorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.item_author, this);
        ButterKnife.bind(this);

    }

    public void setData(List<ItemListBean> hotAuthor) {
        mAuthor = hotAuthor;
        mVideoList = new ArrayList<>();
        ImageLoader.loadCircle(mContext, String.valueOf(mAuthor.get(0).getData().getHeader().getIcon()), ivRecHead);
        tvRecTitle.setText(String.valueOf(mAuthor.get(0).getData().getHeader().getTitle()));
        tvRecDes.setText(String.valueOf(mAuthor.get(0).getData().getHeader().getDescription()));

        for (ItemListBean itemListBean : hotAuthor.get(0).getData().getItemList()) {
            itemListBean.titleVisible = 1;
            mVideoList.add(itemListBean);
        }

        mAdapter = new TopAdapter(mContext,mVideoList);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mAdapter);
        setOnPageChange();
    }

    private void setOnPageChange() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
