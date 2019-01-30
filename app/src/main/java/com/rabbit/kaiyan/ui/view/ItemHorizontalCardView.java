package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.adapter.HorizontaCardAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemHorizontalCardView extends FrameLayout {
    private static final String TAG = "ItemHorizontalCardView";
    Context context;
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;
    HorizontaCardAdapter mAdapter;
    List<ItemListBean> cardList;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;


    public ItemHorizontalCardView(@NonNull Context context) {
        this(context, null);
    }

    public ItemHorizontalCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemHorizontalCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.item_square, this);
        ButterKnife.bind(this);

    }

    public void setData(List<ItemListBean> listBeans) {
        this.cardList = listBeans;

        tvLeft.setText(String.valueOf(listBeans.get(0).getData().getHeader().getTitle()));
        tvRight.setText(String.valueOf(listBeans.get(0).getData().getHeader().getRightText()) + " >");
        mLinearLayoutManager = new LinearLayoutManager(context);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new HorizontaCardAdapter(context, cardList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });
    }
}
