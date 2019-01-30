package com.rabbit.kaiyan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootActivity;
import com.rabbit.kaiyan.base.contract.HistoryContract;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.HistoryBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.presenter.HistoryPresenter;
import com.rabbit.kaiyan.ui.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryActivity extends RootActivity<HistoryPresenter> implements HistoryContract.View {

    private static final String TAG = "HistoryActivity";

    @BindView(R.id.iv_like_back)
    ImageView ivLikeBack;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.tv_like_title)
    TextView tvLikeTitle;

    private List<HistoryBean> historyBeans = new ArrayList<>();
    private HistoryAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        tvLikeTitle.setText("观看记录");
        initRecyclerView();
        mPresenter.getHistoryData();
    }

    private void initRecyclerView() {
        adapter = new HistoryAdapter(mContext, historyBeans);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                mPresenter.getHistoryDataByID(historyBeans.get(id).getId());
            }
        });
    }

    @Override
    protected int getLayout() {
        //与like界面通用,需要改title
        return R.layout.activity_like;
    }

    @OnClick({R.id.iv_like_back})
    public void exitActivity() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void setHistoryData(List<HistoryBean> historyBeans) {
        this.historyBeans = historyBeans;
        adapter.addHistoryData(historyBeans);
    }

    @Override
    public void startDetailActivity(DataBean dataBean) {
        ItemListBean itemListBean = new ItemListBean();
        itemListBean.setType("video");
        itemListBean.setData(dataBean);
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        intent.putExtra("itemListBean", itemListBean);
        mContext.startActivity(intent);
    }
}
