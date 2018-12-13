package com.rabbit.kaiyan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rabbit.kaiyan.App.App;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

import butterknife.Unbinder;

public abstract class SimpleActivity extends SupportActivity {

    protected Context mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        onViewCreate();
        unbinder = ButterKnife.bind(this);
        mContext = this;
        App.getApp().addActivity(this);
        initEventAndData();
    }

    protected abstract void initEventAndData();

    protected void onViewCreate(){

    };

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        App.getApp().removeActivity(this);
    }
}
