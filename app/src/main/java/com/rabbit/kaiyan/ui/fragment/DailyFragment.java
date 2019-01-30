package com.rabbit.kaiyan.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.DailyContract;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.presenter.DailyPresenter;
import com.rabbit.kaiyan.ui.activity.DetailActivity;
import com.rabbit.kaiyan.ui.adapter.DailyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
     * @type
     * @explain DailyFragment : SwipeRefreshLayout +  RecyclerView + 自定义ToolBar;
     *           SwipeRefreshLayout用于下拉刷新,RecyclerView用于显示列表数据,ToolBar用于显示状态栏;
     * @author Rabbit.
     * @creat time 2018/11/26 16:57.
**/
public class DailyFragment extends RootFragment<DailyPresenter> implements DailyContract.View {


    private static final String TAG = "DailyFragment";
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    LinearLayoutManager mLayoutManager;
    DailyRecyclerAdapter mAdapter;

    boolean isLoading = false;
    boolean dataReady = false;
    boolean isVisiable;

    private List<ItemListBean> itemListBeans = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    /**
     * 初始化时，先将title设为透明，当recyclerview中viewPager界面移出屏幕时，设置title可见，并显示日期
     */
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.d(TAG, "initEventAndData");
        initRecyclerView();
        initSwipeRefresh();
        //设置为正在加载状态：隐藏状态栏等
        stateLoading();
        isVisiable = true;
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ");
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DailyRecyclerAdapter(mContext, itemListBeans);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getDailyData();
        //滑动监听，会加载更多数据，并且动态控制title的显示
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPositon = mLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions = mLayoutManager.getItemCount();
                if (totalPotions - lastItemPositon == 1) {
                    if (!isLoading) {
                        Log.d("DFT.Sd.2if:","getMoreData");
                        isLoading = true;
                        mPresenter.getMoreData();
                    }
                }
            }
        });
        //设置item点击事件
        mAdapter.setOnItemClickListener(new DailyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                //跳转item详情
                Intent intent = new Intent();
                intent.setClass(mContext, DetailActivity.class);
                ItemListBean itemListBean = itemListBeans.get(id);
                intent.putExtra("itemListBean", itemListBean);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisiable = isVisibleToUser;
        if (isVisibleToUser) {
            if (dataReady) {
                mAdapter.startText();
            }
        } else {
            if (mPresenter != null) {
            }
            if (mAdapter != null) {
                mAdapter.stopText();
            }
        }

    }

    private void initSwipeRefresh() {
        Log.d(TAG, "initSwipeRefresh: ");
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.BLACK, Color.BLACK);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemListBeans = new ArrayList<>();
                mPresenter.refreshAll();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }


    @Override
    public void showContent(List<ItemListBean> list) {
        for (ItemListBean itemListBean : list) {
            itemListBeans.add(itemListBean);
        }
        stateStart();
        isLoading = false;
        dataReady = true;
        mAdapter.addDailyData(itemListBeans);
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
