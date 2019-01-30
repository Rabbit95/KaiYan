package com.rabbit.kaiyan.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootActivity;
import com.rabbit.kaiyan.base.contract.DownloadContract;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.DownloadBean;
import com.rabbit.kaiyan.presenter.DownloadPresenter;
import com.rabbit.kaiyan.ui.adapter.DownloadAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadActivity extends RootActivity<DownloadPresenter> implements DownloadContract.View {
    @BindView(R.id.iv_like_back)
    ImageView ivLikeBack;
    @BindView(R.id.tv_like_title)
    TextView tvLikeTitle;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    private DownloadAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    List<DownloadBean> downloadBeans = new ArrayList<>();

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        //与like界面通用,需要改title
        return R.layout.activity_like;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        tvLikeTitle.setText("我的缓存");
        initRecyclerView();
        mPresenter.getCacheData();
    }

    private void initRecyclerView() {
        adapter = new DownloadAdapter(mContext,downloadBeans);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DownloadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {

            }
        });
    }


    @Override
    public void setCacheData(List<DownloadBean> downloadBeans) {
        this.downloadBeans = downloadBeans;
        adapter.addDownloadData(downloadBeans);
    }

    @Override
    public void startDetailActivity(DataBean dataBean) {

    }

    @OnClick(R.id.iv_like_back)
    public void closeActivity(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
