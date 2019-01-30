package com.rabbit.kaiyan.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.BaseFragment;
import com.rabbit.kaiyan.base.contract.HotContract;
import com.rabbit.kaiyan.presenter.HotPresenter;
import com.rabbit.kaiyan.ui.adapter.FragmentAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class RankHomeFragment extends BaseFragment<HotPresenter> implements HotContract.View {


    @BindView(R.id.tl_rank)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.vp_rank)
    ViewPager viewPager;

    private FragmentAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"周排行","月排行","总排行"};
    private String[] mCycle = {"weekly","monthly","historical"};

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateStart() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    protected void initEventAndData() {
        mFragments.add(new WeekFragment());
        mFragments.add(new MonthFragment());
        mFragments.add(new AllHotFragment());
        mAdapter = new FragmentAdapter(getChildFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ranklist;
    }

}
