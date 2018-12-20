package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.ui.adapter.HotReplyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemHotReplyView extends RelativeLayout {

    List<ItemListBean> mHotReply;

    Context mContext;
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;

    HotReplyAdapter mAdapter;
    LinearLayoutManager mLinearLayoutManager;

    public ItemHotReplyView(Context context) {
        this(context, null);
    }

    public ItemHotReplyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemHotReplyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.item_hotreply, this);
        ButterKnife.bind(this);
    }

    public void setData(List<ItemListBean> hotReply){
        this.mHotReply = hotReply;
        mAdapter = new HotReplyAdapter(mContext,mHotReply);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
