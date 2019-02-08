package com.rabbit.kaiyan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.ui.activity.SearchActivity;
import com.rabbit.kaiyan.ui.adapter.FragmentAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    SlidingTabLayout tabLayout;
    ViewPager viewPager;
    ImageView searchIv;

    private FragmentAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    //, "开胃", "创意", "运动", "音乐","搞笑", "萌宠"
    //            , "剧情", "科技", "旅游", "影视", "记录", "游戏", "综艺", "时尚"
    private final String[] mTitles = {
            "关注", "发现", "推荐" , "日报", "广告", "生活", "动画", "搞笑", "开胃", "创意", "运动", "音乐", "萌宠" , "剧情", "科技", "旅行", "影视", "记录", "游戏", "综艺", "时尚","集锦"
    };
    //"广告", "生活", "动画", "搞笑", "开胃", "创意", "运动", "音乐","搞笑", "萌宠" , "剧情", "科技", "旅游", "影视", "记录", "游戏", "综艺", "时尚"，"锦集"
    private final int[] mCategoryID = {99,99,99,99,14,36,10,28,4,2,18,20,26,12,32,6,8,22,30,38,24,34};

    public static HomeFragment getInstance(){
        HomeFragment hf = new HomeFragment();
        return hf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,null);
        tabLayout = v.findViewById(R.id.tl_home);
        viewPager = v.findViewById(R.id.vp_home);
        searchIv = v.findViewById(R.id.iv_home_search);

        mFragments.add(new FollowFragment());
        mFragments.add(new DiscoveryFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new DailyFragment());

        //各个分类的Fragment,ID区分
        for (int i = 4; i < mTitles.length; i++){
            mFragments.add(CategoryFragment.setCategoryID(mCategoryID[i]));
        }

        mAdapter = new FragmentAdapter(getChildFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(1);

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), SearchActivity.class);
                getContext().startActivity(intent);
            }
        });
        return v;
    }
}
