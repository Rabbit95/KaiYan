package com.rabbit.kaiyan.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.HotTopContract;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.presenter.WeekPresenter;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.rabbit.kaiyan.ui.adapter.RankListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hzj on 2017/12/26.
 */

public abstract class BaseHotFragment extends RootFragment<WeekPresenter> implements HotTopContract.View {

    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RankListAdapter mAdapter;
    private List<ItemListBean> itemListBeans = new ArrayList<>();

    @Override
    public void showContents(List<ItemListBean> listBean) {
        for (ItemListBean itemListBean : listBean) {
            if (itemListBean.getType().equals("video")) {
                if (itemListBean.getData().getAuthor() != null) {
                    itemListBeans.add(itemListBean);
                }
            }
        }
        stateStart();
        mAdapter.addRankListData(itemListBeans);
    }

    @Override
    protected void initInject() {
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecyclerView();
        stateLoading();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RankListAdapter(mContext, itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        getHotData();
        mAdapter.setOnItemClickListener(new RankListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, DetailActivity.class);
                ItemListBean itemListBean = itemListBeans.get(id);
                intent.putExtra("itemListBean", itemListBean);
                mContext.startActivity(intent);
            }
        });
    }

    protected abstract void getHotData();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

}
