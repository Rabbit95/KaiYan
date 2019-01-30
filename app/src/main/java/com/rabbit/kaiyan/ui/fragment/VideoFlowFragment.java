package com.rabbit.kaiyan.ui.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;
import com.rabbit.kaiyan.App.App;
import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.VideoFlowContract;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.VideoFlowBean;
import com.rabbit.kaiyan.presenter.VideoFlowPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VideoFlowFragment extends RootFragment<VideoFlowPresenter> implements VideoFlowContract.View {

    private static final String TAG = "VideoFlowFragment";

    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    Unbinder unbinder;


    private VideoFlowBean videoFlowBean = new VideoFlowBean();
    private MyAdapter myAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private boolean ok = false;
    private HttpProxyCacheServer proxy;
    private String proxyUrl;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getVideoFlowData();
    }

    private void initListener() {

        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Log.e(TAG,"onInitComplete");
                playVideo(0);
                ok = true;
            }

            @Override
            public void onPageRelease(boolean isNext,int position) {
                Log.e(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {
                Log.e(TAG,"选中位置:"+position+"  是否是滑动到底部:"+isBottom);
                if(isBottom) {
                    mPresenter.getMoreVideoFlowData();
                }
                playVideo(0);
            }
        });
    }

    private void initRecyclerView() {
        myAdapter = new MyAdapter(videoFlowBean.getVideoFlowList());
        mLayoutManager = new ViewPagerLayoutManager(mContext, OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_videoflow;
    }



    private void playVideo(int position) {
        Log.d(TAG, "playVideo: ");
        View itemView = recyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_player);
        final ImageView videoThumb = itemView.findViewById(R.id.video_thumb);
        final ImageView videoPlay = itemView.findViewById(R.id.video_play);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                videoThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        videoPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    videoPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        View itemView = recyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_player);
        final ImageView videoThumb = itemView.findViewById(R.id.video_thumb);
        final ImageView videoPlay = itemView.findViewById(R.id.video_play);
        videoView.stopPlayback();
        videoThumb.animate().alpha(1).start();
        videoPlay.animate().alpha(0f).start();
    }

    private void pauseVideo(int index){
        View itemView = recyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_player);
        final ImageView videoThumb = itemView.findViewById(R.id.video_thumb);
        final ImageView videoPlay = itemView.findViewById(R.id.video_play);
        videoThumb.animate().alpha(1).start();
        videoPlay.animate().alpha(1f).start();
        videoView.pause();
    }

    private void startVideo(int index){
        View itemView = recyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_player);
        final ImageView videoThumb = itemView.findViewById(R.id.video_thumb);
        final ImageView videoPlay = itemView.findViewById(R.id.video_play);
        videoThumb.animate().alpha(0).start();
        videoPlay.animate().alpha(0f).start();
        videoView.start();
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

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        List<VideoFlowBean.VideoFlow> mVideoFlows = new ArrayList<>();
        public MyAdapter(List<VideoFlowBean.VideoFlow> videoFlows){
            this.mVideoFlows = videoFlows;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videoflow,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.videoDesc.setText(mVideoFlows.get(position).getDescription());
            ImageLoader.load(mContext,mVideoFlows.get(position).getImage(),holder.videoThumb);
            proxy = App.getProxy(mContext);
            proxyUrl = proxy.getProxyUrl((mVideoFlows.get(position).getPlayurl()));
            holder.videoView.setVideoPath(proxyUrl);
//            holder.videoView.setVideoURI(Uri.parse(mVideoFlows.get(position).getPlayurl()));
        }

        @Override
        public int getItemCount() {
            return mVideoFlows.size();
        }

        public void addData(List<VideoFlowBean.VideoFlow> videoFlows){
            for (VideoFlowBean.VideoFlow videoFlow : videoFlows) {
                this.mVideoFlows.add(videoFlow);
            }
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            VideoView videoView;
            TextView videoDesc;
            ImageView videoThumb;
            ImageView videoPlay;
            public ViewHolder(View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.video_player);
                videoDesc = itemView.findViewById(R.id.video_des);
                videoThumb = itemView.findViewById(R.id.video_thumb);
                videoPlay = itemView.findViewById(R.id.video_play);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(ok) {
            if (isVisibleToUser) {
                Log.d(TAG, "videofragment resume");
                startVideo(0);
            } else {
                Log.d(TAG, "videofragment onpause");
                pauseVideo(0);
            }
        }
    }

    @Override
    public void setVideoData(VideoFlowBean videoData) {
        this.videoFlowBean = videoData;
        initRecyclerView();
        initListener();
    }

    @Override
    public void setMoreVideoData(VideoFlowBean videoData) {
        myAdapter.addData(videoData.getVideoFlowList());
    }
}
