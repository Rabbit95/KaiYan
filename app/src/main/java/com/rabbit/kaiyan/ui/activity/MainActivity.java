package com.rabbit.kaiyan.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.BaseActivity;
import com.rabbit.kaiyan.base.contract.MainContract;
import com.rabbit.kaiyan.model.beans.TabEntity;
import com.rabbit.kaiyan.presenter.MainPresenter;
import com.rabbit.kaiyan.ui.adapter.FragmentAdapter;
import com.rabbit.kaiyan.ui.fragment.HomeFragment;
import com.rabbit.kaiyan.ui.fragment.MineFragment;
import com.rabbit.kaiyan.ui.fragment.RankHomeFragment;
import com.rabbit.kaiyan.ui.fragment.VideoFlowFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
     * @type
     * @explain MainActivity : ViewPager + TabLayout + Fragment; TabLayout与ViewPager做关联，不同的Tab对应不同的Fragment,Fragment拥有自己的Presenter;
     * @author Rabbit.
     * @creat time 2018/11/26 16:51.
**/
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    @BindView(R.id.tl_main)
    CommonTabLayout mTabLayout;

    String[] tabTitle = new String[]{"首页", "热门", "流", "我"};
    int[] tabIcon = new int[]{
            R.mipmap.ic_tab_strip_icon_feed, R.mipmap.ic_tab_strip_icon_follow, R.mipmap.ic_tab_strip_icon_category, R.mipmap.ic_tab_strip_icon_profile
    };
    int[] tabIconSelect = new int[]{
            R.mipmap.ic_tab_strip_icon_feed_selected, R.mipmap.ic_tab_strip_icon_follow_selected, R.mipmap.ic_tab_strip_icon_category_selected, R.mipmap.ic_tab_strip_icon_profile_selected
    };

    FragmentAdapter mAdapter;
    List<Fragment> fragments = new ArrayList<Fragment>();
    ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private RelativeLayout.LayoutParams layoutParams;
    private long exitTime;

    @Override
    protected void initEventAndData() {
        FileDownloader.setup(mContext);
        fragments.add(new HomeFragment());
        fragments.add(new RankHomeFragment());
        fragments.add(new VideoFlowFragment());
        fragments.add(new MineFragment());
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments,tabTitle);

        for (int i = 0; i < tabTitle.length; i++) {
            tabEntities.add(new TabEntity(tabTitle[i],tabIconSelect[i],tabIcon[i]));
        }


        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments,tabTitle));
        mTabLayout.setTabData(tabEntities);


        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                if(position == 2){
                    mTabLayout.setBackgroundResource(R.color.transparent);
                    layoutParams = new RelativeLayout.LayoutParams(mViewPager.getLayoutParams());
                    layoutParams.removeRule(RelativeLayout.ABOVE);
                    mViewPager.setLayoutParams(layoutParams);
                }else{
                    mTabLayout.setBackgroundResource(R.color.colorWhite);
                    layoutParams = new RelativeLayout.LayoutParams(mViewPager.getLayoutParams());
                    layoutParams.addRule(RelativeLayout.ABOVE,R.id.tl_main);
                    mViewPager.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 按两次返回，退出APP
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(mContext, "连按两次退出哦~", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            App.exitApp();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}