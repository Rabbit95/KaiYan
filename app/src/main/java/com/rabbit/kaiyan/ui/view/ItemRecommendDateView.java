package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemRecommendDateView extends RelativeLayout {

    @BindView(R.id.tv_Day)
    TextView mTextView;

    Context mContext;

    public ItemRecommendDateView(Context context) {
        this(context,null);
    }

    public ItemRecommendDateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ItemRecommendDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView(){
        View.inflate(mContext, R.layout.item_recommend_text,this);
        ButterKnife.bind(this);
    }

    public void setDate(String date){
        mTextView.setText(date);
    }
}
