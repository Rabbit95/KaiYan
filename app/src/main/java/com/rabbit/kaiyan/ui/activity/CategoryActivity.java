package com.rabbit.kaiyan.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootActivity;
import com.rabbit.kaiyan.base.contract.CategoryActivityContract;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.CategoryInfo;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.presenter.CategoryActivityPresenter;
import com.rabbit.kaiyan.ui.adapter.CategoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends RootActivity<CategoryActivityPresenter> implements CategoryActivityContract.View {

    private static final String TAG = "CategoryActivity";

    @BindView(R.id.iv_category_back)
    ImageView ivCategoryBack;
    @BindView(R.id.iv_category_background)
    ImageView ivCategoryBackground;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.tv_category_title)
    TextView tvCategoryTitle;
    @BindView(R.id.tv_category_description)
    TextView tvCategoryDescription;

    private int categoryID;
    private List<ItemListBean> categoryBean;
    private LinearLayoutManager linearLayoutManager;
    private CategoryAdapter adapter;
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initData();
        initView();
    }

    private void initView() {
        ivCategoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        adapter = new CategoryAdapter(mContext,categoryBean);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        categoryID = getIntent().getIntExtra("CategoryID",0);
        Log.d(TAG, "Category ID: " + categoryID);
        mPresenter.getCategoryInfoByID(categoryID);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void showCategoryInfo(CategoryInfo categoryInfo) {
        Log.d(TAG, "showCategoryInfo: ");
        ImageLoader.load(mContext, categoryInfo.getCategoryInfo().getHeaderImage(), ivCategoryBackground);
        tvCategoryTitle.setText(categoryInfo.getCategoryInfo().getName());
        tvCategoryDescription.setText(categoryInfo.getCategoryInfo().getDescription());
    }

    @Override
    public void setPageData(CategoryBean categoryBean) {
        this.categoryBean = categoryBean.getItemList();
        adapter.addCategoryData(categoryBean.getItemList());
    }
}
