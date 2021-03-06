package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.adapter.TopAdapter;
import com.rabbit.kaiyan.widget.JumpShowTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopPageView extends FrameLayout{

    private static final String TAG = "TopPageView";

    Context context;

    @BindView(R.id.vp_top)
    ViewPager viewPager;
//    @BindView(R.id.iv_top_show)
//    ImageView imageView;
    @BindView(R.id.tv_top_des)
    JumpShowTextView topDes;
    @BindView(R.id.tv_top_title)
    JumpShowTextView topTitle;
//    @BindView(R.id.ll_top_indicator)
//    LinearLayout indicators;
    @BindView(R.id.iv_top_head)
    ImageView imageView;


    TopAdapter topAdapter;
//    List<ItemListBean> listBeans;
    List<ItemListBean> listBeans;

    int newPosition;

    boolean isCategoryData = false;

    public TopPageView(@NonNull Context context) {
        this(context, null);

    }

    public TopPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }


    public TopPageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void initView() {

        View.inflate(context, R.layout.item_top, this);
        newPosition = 1;
        ButterKnife.bind(this);
    }

    public void setData(List<ItemListBean> itemListBeans,boolean isCategoryData) {
        this.isCategoryData = isCategoryData;
        this.listBeans = itemListBeans;
        topAdapter = new TopAdapter(context, listBeans);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(topAdapter);
        setOnPageChange();
        addIndicator(listBeans.size());
        Log.d(TAG, "data size:"+listBeans.size());
        if(this.isCategoryData){
            topTitle.setText(listBeans.get(0).getData().getTitle());
            topDes.setText(listBeans.get(0).getData().getAuthor().getName() + "  #"+listBeans.get(0).getData().getCategory());
            ImageLoader.loadCircle(context,listBeans.get(0).getData().getAuthor().getIcon(),imageView);
        }else{
            topDes.setText(listBeans.get(0).getData().getSlogan());
            topTitle.setText(listBeans.get(0).getData().getTitle());
            ImageLoader.loadCircle(context,listBeans.get(0).getData().getAuthor().getIcon(),imageView);
        }
    }

    private void addIndicator(int size) {
//        indicators.removeAllViews();
//        for (int i = 0; i < size; i++) {
//            Indicator indicator = new Indicator(context);
//            indicators.addView(indicator);
//            if (i == 0) indicator.setImageView(true);
//        }
    }

    private void setOnPageChange() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                topDes.setWithAnimation(true);
                topTitle.setWithAnimation(true);
                newPosition = position + 1;
                if(isCategoryData) {
                    topTitle.setText(listBeans.get(position).getData().getTitle());
                    topDes.setText(listBeans.get(position).getData().getAuthor().getName() + " #"+listBeans.get(0).getData().getCategory());
                    ImageLoader.loadCircle(context,listBeans.get(position).getData().getAuthor().getIcon(),imageView);
                }else {
                    topDes.setText(listBeans.get(position).getData().getSlogan());
                    topTitle.setText(listBeans.get(position).getData().getTitle());
                    ImageLoader.loadCircle(context, listBeans.get(position).getData().getAuthor().getIcon(), imageView);
                }

                for (int j = 0; j < listBeans.size(); j++) {
//                    if (j == position) {
//                        ((Indicator) indicators.getChildAt(j)).setImageView(true);
//                    } else {
//                        ((Indicator) indicators.getChildAt(j)).setImageView(false);
//                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void stopText() {
        topDes.stopText();
        topTitle.stopText();
    }

    public void startText() {
        topDes.startText();
        topTitle.startText();
    }

    public void changeTopPageView() {
        if (newPosition < listBeans.size()) {
            viewPager.setCurrentItem(newPosition);
        } else {
            newPosition = 0;
            viewPager.setCurrentItem(newPosition);
        }
    }
}
