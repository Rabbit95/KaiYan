package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.ui.adapter.ReplyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReplyView extends RelativeLayout {

    @BindView(R.id.iv_replyclose)
    public ImageView replyClose;

    @BindView(R.id.view_main)
    public RecyclerView recyclerView;

    public LinearLayoutManager linearLayoutManager;
    Context context;
    ReplyAdapter replyAdapter;
    List<ReplyBean.ItemListBean> listBeans = new ArrayList<>();

    public ReplyView(Context context) {
        this(context, null);
    }

    public ReplyView(Context context, AttributeSet attrs) {
        this(context, null,0);
    }

    public ReplyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.layout_reply, this);
        ButterKnife.bind(this);
        replyAdapter = new ReplyAdapter(context,listBeans);
        recyclerView.setAdapter(replyAdapter);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void getData(ReplyBean replyBean) {
        replyAdapter.getData(replyBean);
    }

    public void getMoreData(ReplyBean replyBean){
        replyAdapter.getMoreData(replyBean);
    }

}
