package com.rabbit.kaiyan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.BaseActivity;
import com.rabbit.kaiyan.base.contract.LikeContract;
import com.rabbit.kaiyan.model.beans.DataBean;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.LikeBean;
import com.rabbit.kaiyan.presenter.LikePresenter;
import com.rabbit.kaiyan.ui.adapter.LikeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LikeActivity extends BaseActivity<LikePresenter> implements LikeContract.View {
    private static final String TAG = "LikeActivity";
    @BindView(R.id.iv_like_back)
    ImageView ivLikeBack;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;

    private LikeAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private List<LikeBean> likeBeans = new ArrayList<>();

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        initRecyclerView();
        mPresenter.getLikeData();
    }

    private void initRecyclerView() {
        adapter = new LikeAdapter(mContext, likeBeans);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new LikeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Log.d(TAG, "onItemClick: "+id);
                Log.d(TAG, "item data id"+likeBeans.get(id).getId());
                mPresenter.getLikeDataByID(likeBeans.get(id).getId());
            }
        });
    }

    @OnClick(R.id.iv_like_back)
    public void closeActivity() {
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_like;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void setLikeData(List<LikeBean> likeData) {
        this.likeBeans = likeData;
        adapter.addLikeData(likeData);
    }

    @Override
    public void startDetailActivity(DataBean dataBean) {
        Log.d(TAG, "startDetailActivity: ");
        ItemListBean itemListBean = new ItemListBean();
        itemListBean.setType("video");
        itemListBean.setData(dataBean);
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        intent.putExtra("itemListBean", itemListBean);
        mContext.startActivity(intent);
    }
}
