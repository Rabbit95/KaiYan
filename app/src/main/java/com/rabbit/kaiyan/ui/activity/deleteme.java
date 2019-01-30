package com.rabbit.kaiyan.ui.activity;

import android.content.Context;
import android.util.AttributeSet;

import com.rabbit.kaiyan.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class deleteme extends StandardGSYVideoPlayer {

    public deleteme(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public deleteme(Context context) {
        super(context);
    }

    public deleteme(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.test3;
    }
}
