package com.rabbit.kaiyan.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootActivity;
import com.rabbit.kaiyan.base.contract.DetailContract;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.component.SimpleListener;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.presenter.DetailPresenter;
import com.rabbit.kaiyan.ui.adapter.DetailAdapter;
import com.rabbit.kaiyan.ui.view.ReplyView;
import com.rabbit.kaiyan.util.SystemUtil;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;

/**
     * @type
     * @explain 视频详细Activity
     * @author Rabbit.
     * @creat time 2018/11/27 16:41.
**/
public class DetailActivity extends RootActivity<DetailPresenter> implements DetailContract.View {

    private static final String TAG = "DetaiActivity" ;
    @BindView(R.id.video_player)
    StandardGSYVideoPlayer videoPlayer;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    String Url;
    ImageView imageView;
    LinearLayoutManager mLinearLayoutManager;
    boolean isPlay;
    boolean isPause;
    int id;
    boolean islike = false;
    boolean isloading = false;
    boolean notShowPlayTip;
    boolean notShowDownloadTip;
    DetailAdapter mAdapter;

    //评论相关
    ReplyView mReplyView;
    RelativeLayout.LayoutParams mLayoutParams;
    RelativeLayout root;
    Stack<ReplyView> replyViews = new Stack<ReplyView>();
    private ItemListBean itemListBean;
    private OrientationUtils orientationUtils;
    private List<ItemListBean> listBeans = new ArrayList<>();

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        itemListBean = (ItemListBean) getIntent().getSerializableExtra("itemListBean");
        getIntentData();
        initRecyclerView();
        stateLoading();
        notShowPlayTip = mPresenter.getPlaySetting();
        notShowDownloadTip = mPresenter.getDownloadSetting();
        mPresenter.getVideoData(id);
        initVideo();
        root = (RelativeLayout) findViewById(R.id.detail_root);
    }

    /**
    * @explain 初始化Video
    **/
    private void initVideo() {
        //不需要视频播放器的Title栏和Back按钮
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getBackButton().setVisibility(View.GONE);
        //旋转控制工具
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化时不打开外部的旋转
        orientationUtils.setEnable(false);
        GSYVideoOptionBuilder builder = new GSYVideoOptionBuilder();

        builder.setThumbImageView(imageView)//设置封面
                .setIsTouchWiget(true)//设置是否可以滑动界面改变进度，声音等
                .setRotateViewAuto(true)//设置是否开启自动旋转
                .setRotateWithSystem(false)//设置是否更新系统旋转，false的话，系统禁止旋转也会跟着旋转
                .setThumbPlay(true)//设置是否点击封面可以播放
                .setLockLand(true)//一全屏就锁屏横屏，默认false竖屏
                .setShowFullAnimation(true)//设置是否使用全屏动画效果
                .setNeedLockFull(true)//设置是否需要全屏锁定屏幕功能
                .setNeedShowWifiTip(!notShowPlayTip)//设置是否需要显示流量提示
                .setSeekRatio(1)//调整触摸滑动快进的比例
                .setUrl(Url)//视频URL
                .setCacheWithPlay(false)//设置播放时是否缓存
                .setVideoAllCallBack(new SimpleListener() {//设置播放过程中的回调
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            orientationUtils.setEnable(!lock);
                        }
                    }
                })
                .setGSYVideoProgressListener(new GSYVideoProgressListener() {
                    @Override
                    public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
                    }
                })
                .build(videoPlayer);

        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
                videoPlayer.startWindowFullscreen(DetailActivity.this, true, true);
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //设置为线性布局
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DetailAdapter(mContext, listBeans);
        mPresenter.isLike(id);
        mAdapter.getItemData(itemListBean);
        recyclerView.setAdapter(mAdapter);
        //为相关视频推荐设置监听事件，会重新加载整个detailActivity
        mAdapter.setOnItemClickListener(new DetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                itemListBean = listBeans.get(id - 2);
                stateLoading();
                getIntentData();
                mAdapter.getItemData(itemListBean);
                mPresenter.isLike(id);
                listBeans.clear();
                initVideo();
                mPresenter.getVideoData(itemListBean.getData().getId());
            }
        });
        //为button设置点击事件
        mAdapter.setOnButtonClickListener(new DetailAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                switch (position) {
                    case 0:
                        //like按钮
                        if (islike) {
                            mPresenter.deleteLikeId(id);
                            islike = false;
                        } else {
                            mPresenter.insertLikeId(itemListBean);
                            islike = true;
                        }
                        break;
                    case 1:
                        //分享按钮，未实现
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, itemListBean.getData().getWebUrl().getRaw());
                        mContext.startActivity(Intent.createChooser(intent, "分享给……"));
                        break;
                    case 2:
                        //评论按钮
                        mPresenter.getReplyData(itemListBean.getData().getId());
                        break;
                    case 3:
                        //下载按钮
                        if (SystemUtil.isWifiConnected() || notShowDownloadTip) {
                            mPresenter.download(Url, itemListBean);
                        } else {
                            showDownloadDialog();
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void showContent(List<ItemListBean> itemListBeans) {
        stateStart();
        for (ItemListBean itemListBean : itemListBeans) {
            if (itemListBean.getType().equals("videoSmallCard")) {
                listBeans.add(itemListBean);
            }
        }
        mAdapter.getData(listBeans);
    }

    /**
    * @explain 根据点赞记录设置icon
    **/
    @Override
    public void setLike(boolean islike) {
        mAdapter.setlike(islike);
        this.islike = islike;
    }


    /**
    * @explain  获取启动此activity时，intent所传递过来的数据
    **/
    private void getIntentData() {
        if(itemListBean.getType().equals("followCard")){
            Url = itemListBean.getData().getContent().getData().getPlayUrl();
            id = itemListBean.getData().getContent().getData().getId();
            imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.load(this, itemListBean.getData().getContent().getData().getCover().getFeed(), imageView);
        }else {
            Url = itemListBean.getData().getPlayUrl();
            id = itemListBean.getData().getId();
            imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.load(this, getImageUrl(), imageView);
        }
        if (!mPresenter.isRead(id)) {
            mPresenter.addToHistory(itemListBean);
        } else {
            mPresenter.deleteReadId(id);
            mPresenter.addToHistory(itemListBean);
        }
    }

    /**
    * @explain  获取databean中的image信息
    **/
    private String getImageUrl() {
        if (itemListBean.getData().getCover() == null) {
            return itemListBean.getData().getCoverForFeed();
//            return null;
        } else {
            return itemListBean.getData().getCover().getFeed();
        }
    }

    /**
    * @explain 显示评论
    **/
    @Override
    public void showReply(final ReplyBean replyBean) {
        mReplyView = new ReplyView(mContext);
        mLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        //在父控件mLayoutParams的底部添加vedio_player，RelativeLayout.BELOW设置到底部
        mLayoutParams.addRule(RelativeLayout.BELOW,R.id.video_player);
        mReplyView.setLayoutParams(mLayoutParams);
        mReplyView.getData(replyBean);
        root.addView(mReplyView);
        replyViews.push(mReplyView);
        mReplyView.replyClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeReply();
            }
        });
        mReplyView.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPositon = mReplyView.linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int totalPotions = mReplyView.linearLayoutManager.getItemCount();
                if (lastItemPositon > totalPotions - 4 && dy > 0 && totalPotions < replyBean.getTotal()) {
                    if (!isloading) {
                        isloading = true;
                        mPresenter.getMoreReplyData(itemListBean.getData().getId());
                    }
                }
            }
        });
    }

    /**
    * @explain 关闭评论
    **/
    private boolean closeReply() {
        if (replyViews.size() == 0) {
            return false;
        }
        root.removeView(mReplyView);
        replyViews.pop();
        return true;
    }

    /**
    * @explain 上拉显示更多数据
    **/

    @Override
    public void showMoreReply(ReplyBean replyBean) {
        mReplyView.getMoreData(replyBean);
        isloading = false;
    }

    @Override
    public void showDownload() {
        showToast("盘他！开始下载了~");
    }

    @Override
    public void showIsDownload() {
        showToast("别点了！已经在盘了~");
    }

    @Override
    public void showHadDownload() {
        showToast("这个盘过了！换一个盘吧~");
    }

    private void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
    * @explain Dialog询问是否使用流量下载视频
    **/

    @Override
    public void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("确定用流量下载吗？")
                .setMessage("当前未连接WIFI，确定要用流量下载吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.setDownloadSetting(true);
                        mPresenter.download(Url, itemListBean);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }


    private GSYVideoPlayer getPlayer() {
        if (videoPlayer.getFullWindowPlayer() != null) {
            return videoPlayer.getFullWindowPlayer();
        }
        return videoPlayer;
    }

    @Override
    public void onBackPressedSupport() {
        if(orientationUtils != null){
            orientationUtils.backToProtVideo();
        }
        if (closeReply()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        getPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getPlayer().onVideoPause();
        super.onResume();
        isPause = false;
    }
    @Override
    protected void onDestroy() {

        if (isPlay) {
            getPlayer().release();
        }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils);
        }
    }

    @Override
    public void showErrorMsg(String s) {

    }
}
