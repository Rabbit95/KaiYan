package com.rabbit.kaiyan.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.RecommendContract;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.presenter.RecommendPresenter;
import com.rabbit.kaiyan.ui.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecommendFragment extends RootFragment<RecommendPresenter> implements RecommendContract.View {
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_recommend)
    SwipeRefreshLayout mSwipeRefreshLayout;

    LinearLayoutManager mLinearLayoutManager;

    RecommendAdapter mAdapter;
    String TAG = "RecommendFragment";
    private List<ItemListBean> itemListBeans = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemListBeans = new ArrayList<>();
                mPresenter.refreshAll();
                mAdapter.loadState = mAdapter.LOADING;
            }
        });
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new RecommendAdapter(mContext,itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getDailyData();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPositon = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions = mLinearLayoutManager.getItemCount();
//                Log.d("lastItemPositon", "lastItemPositon:"+lastItemPositon);
//                Log.d("totalPotions", "totalPotions:"+totalPotions);
                if(totalPotions - lastItemPositon == 1){
                    if(mAdapter.loadState != mAdapter.LOADING_END) {
                        mPresenter.getMoreCategoryData();
                    }else{
                        mAdapter.loadState = mAdapter.LOADING_END;
                    }
                }
            }
        });

        mAdapter.setOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Log.d(TAG, "dateItem click" + id);
            }
        });
    }

    @Override
    public void setEndState(){
        mAdapter.loadState = mAdapter.LOADING_END;
        mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
    }


    @Override
    public void showDateContent(String date) {
        mAdapter.addDailyDate(date);
    }

    @Override
    public void showTopContent(List<ItemListBean> topListBeans) {
        mAdapter.addTopData(topListBeans);
    }


    @Override
    public void showContent(List<ItemListBean > listBeans) {
        mSwipeRefreshLayout.setRefreshing(false);
        itemListBeans = listBeans;
        mAdapter.addDailyData(itemListBeans);
    }

}
