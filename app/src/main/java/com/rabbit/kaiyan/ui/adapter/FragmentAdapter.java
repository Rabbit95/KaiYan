package com.rabbit.kaiyan.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private String[] titls;
    private List<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments,String [] titles) {
        super(fm);
        this.fragments = fragments;
        this.titls = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titls[position];
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
