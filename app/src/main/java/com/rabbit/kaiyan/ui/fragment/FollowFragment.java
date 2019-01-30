package com.rabbit.kaiyan.ui.fragment;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.FollowContract;
import com.rabbit.kaiyan.model.beans.Data;
import com.rabbit.kaiyan.model.beans.FollowBean;
import com.rabbit.kaiyan.presenter.FollowPresenter;
import com.rabbit.kaiyan.ui.adapter.FollowRecyclerAdapter;
import com.rabbit.kaiyan.ui.adapter.RecyclerItemNormalHolder;
import com.rabbit.kaiyan.util.ScrollCalculatorHelper;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FollowFragment extends RootFragment<FollowPresenter> implements FollowContract.View {


    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    Unbinder unbinder;

    FollowRecyclerAdapter adapter;
    FollowBean followList = new FollowBean();
    LinearLayoutManager linearLayoutManager;
    ScrollCalculatorHelper scrollCalculatorHelper;

    boolean mFull = false;

    List<Data> dataList = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getFollowData();
        initData();
    }

    private void initData() {
        Data d1 = new Data();
        Data d2 = new Data();
        d1.setVideoImgURL("http://img.kaiyanapp.com/08b42acdd83a5ca3a0ff70c50f7f0696.png?imageMogr2/quality/60/format/jpg");
        d1.setVideoPlayURL("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146091&resourceType=video&editionType=default&source=aliyun");
        d2.setVideoImgURL("http://img.kaiyanapp.com/adb39e4904f00e487b843411983108ce.jpeg?imageMogr2/quality/60/format/jpg");
        d2.setVideoPlayURL("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=146310&resourceType=video&editionType=default&source=aliyun");
        dataList.add(d1);
        dataList.add(d2);
    }


    private void initRecycleView() {
        //限定范围为屏幕一半的上下偏移180
        int playTop = CommonUtil.getScreenHeight(mContext) / 2 - CommonUtil.dip2px(mContext, 180);
        int playBottom = CommonUtil.getScreenHeight(mContext) / 2 + CommonUtil.dip2px(mContext, 180);

        //自定播放帮助类
        scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video_player, playTop, playBottom);

        adapter = new FollowRecyclerAdapter(mContext,followList.getItemList(),dataList);

        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                //这是滑动自动播放的代码
                if (!mFull) {
                    scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
                }
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecyclerItemNormalHolder.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        if(!GSYVideoManager.isFullState(getActivity())) {
                            GSYVideoManager.releaseAllVideos();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (newConfig.orientation != ActivityInfo.SCREEN_ORIENTATION_USER) {
            mFull = false;
        } else {
            mFull = true;
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
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
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void addFollowData(FollowBean followList) {
        this.followList = followList;
        initRecycleView();
        adapter.setListData(followList.getItemList());
//        adapter.notifyDataSetChanged();
//        adapter.setListData(followList.getItemList());
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }
}
