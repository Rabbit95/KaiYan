package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.adapter.BannerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopBannerView extends FrameLayout {

    private static final String TAG = "TopBannerView";
    Context context;


    BannerAdapter bannerAdapter;
    List<ItemListBean> listBeans;

    int newPosition;
    @BindView(R.id.vp_top)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_more)
    TextView tvMore;

    public TopBannerView(@NonNull Context context) {
        this(context, null);

    }

    public TopBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }


    public TopBannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void initView() {
        View.inflate(context, R.layout.item_banner, this);
        newPosition = 1;
        ButterKnife.bind(this);
        tvTitle.setVisibility(View.GONE);
        tvMore.setVisibility(View.GONE);
    }

    public void setData(List<ItemListBean> itemListBeans,int position) {
        this.listBeans = itemListBeans;
        bannerAdapter = new BannerAdapter(context, listBeans);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(bannerAdapter);
        setOnPageChange();
        if (position == 1){
            tvTitle.setVisibility(View.VISIBLE);
            tvMore.setVisibility(View.VISIBLE);
            tvTitle.setText("近期专题");
            tvMore.setText("查看全部专题 >");
        }else{
            tvTitle.setVisibility(View.GONE);
            tvMore.setVisibility(View.GONE);
        }
    }


    private void setOnPageChange() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                topDes.setWithAnimation(true);
//                topTitle.setWithAnimation(true);
                newPosition = position + 1;
//                topDes.setText(listBeans.get(position).getData().getSlogan());
//                topTitle.setText(listBeans.get(position).getData().getTitle());
//                ImageLoader.loadCircle(context, listBeans.get(position).getData().getAuthor().getIcon(), imageView);
//                for (int j = 0; j < listBeans.size(); j++) {
////                    if (j == position) {
////                        ((Indicator) indicators.getChildAt(j)).setImageView(true);
////                    } else {
////                        ((Indicator) indicators.getChildAt(j)).setImageView(false);
////                    }
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void stopText() {
//        topDes.stopText();
//        topTitle.stopText();
    }

    public void startText() {
//        topDes.startText();
//        topTitle.startText();
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
