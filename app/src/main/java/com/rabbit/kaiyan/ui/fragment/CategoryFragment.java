package com.rabbit.kaiyan.ui.fragment;

import android.graphics.Color;
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
import com.rabbit.kaiyan.base.contract.CategoryFragmentContract;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.presenter.CategoryFragmentPresenter;
import com.rabbit.kaiyan.ui.adapter.CategoryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryFragment extends RootFragment<CategoryFragmentPresenter> implements CategoryFragmentContract.View {

    private static final String TAG = "CategoryFragment";

    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.srl_category)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int categoryID = 0;
    private CategoryBean mCategoryData = new CategoryBean();
    private CategoryAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public static CategoryFragment setCategoryID(int categoryID) {
//        Log.d(TAG, "setCategoryID: "+ categoryID);
        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.categoryID = categoryID;
        return categoryFragment;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.d(TAG, "categoryID:" + this.categoryID);
        mPresenter.getCategoryDataByID(categoryID);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.BLACK, Color.BLACK);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCategoryData = new CategoryBean();
                mPresenter.getCategoryDataByID(categoryID);
            }
        });
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        adapter = new CategoryAdapter(mContext, mCategoryData.getItemList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setCategoryData(CategoryBean categoryData) {
        this.mCategoryData = categoryData;
        adapter.addCategoryData(categoryData.getItemList());
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
