package com.rabbit.kaiyan.ui.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.DiscoveryContract;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.presenter.DiscoverPresenter;
import com.rabbit.kaiyan.ui.adapter.DiscoverAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoveryFragment extends RootFragment<DiscoverPresenter> implements DiscoveryContract.View {
    private static final String TAG = "DiscoveryFragment";

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_discovery)
    SwipeRefreshLayout mSwipeRefreshLayout;

    LinearLayoutManager mLinearLayoutManager;

    List<ItemListBean> mDiscoveryList = new ArrayList<>();
    DiscoverAdapter mAdapter;



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.d(TAG, "initEventAndData: ");
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDiscoveryList = new ArrayList<>();
                mPresenter.refreshAll();
            }
        });
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DiscoverAdapter(mContext, mDiscoveryList);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getDiscoverData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    public void showContent(List<ItemListBean> listBeans) {
        mDiscoveryList = listBeans;
        mAdapter.addDatas(mDiscoveryList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setBanner(List<ItemListBean> banner) {
        mAdapter.setBanner(banner);
    }

    @Override
    public void setTitles(List<ItemListBean> titles) {
        mAdapter.setTitles(titles);
    }

    @Override
    public void setCategoryCard(List<ItemListBean> categoryCard) {
        mAdapter.setCategory(categoryCard);
    }


    @Override
    public void setRankList(List<ItemListBean> rankList) {
        mAdapter.setRankList(rankList);
    }

    @Override
    public void setTopicCard(List<ItemListBean> topicCard) {
        mAdapter.setTopic(topicCard);
    }

    @Override
    public void setHotAuthor(List<ItemListBean> hotAuthor) {
        mAdapter.setHotAuthor(hotAuthor);
    }

    @Override
    public void setHotReply(List<ItemListBean> hotReply) {
        mAdapter.setHotReply(hotReply);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
