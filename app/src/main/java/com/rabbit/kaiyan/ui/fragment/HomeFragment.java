package com.rabbit.kaiyan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.ui.adapter.FragmentAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

//    @BindView(R.id.tl_home)
    SlidingTabLayout tabLayout;
//    @BindView(R.id.vp_home)
    ViewPager viewPager;

    private FragmentAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    //, "开胃", "创意", "运动", "音乐","搞笑", "萌宠"
    //            , "剧情", "科技", "旅游", "影视", "记录", "游戏", "综艺", "时尚"
    private final String[] mTitles = {
            "关注", "发现", "推荐" , "日报", "广告", "生活", "动画", "搞笑"
    };
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

        mFragments.add(OtherFragment.getInstance(mTitles[0]));
        mFragments.add(OtherFragment.getInstance(mTitles[1]));
        mFragments.add(new RecommendFragment());
//        mFragments.add(OtherFragment.getInstance(mTitles[3]));
        mFragments.add(new DailyFragment());
        for (int i = 4; i < 8; i++){
            mFragments.add(OtherFragment.getInstance(mTitles[i]));
        }

        mAdapter = new FragmentAdapter(getChildFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(2);
        return v;

    }
}
