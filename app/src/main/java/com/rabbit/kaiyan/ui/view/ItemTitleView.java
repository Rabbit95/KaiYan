package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTitleView extends RelativeLayout {
    Context context;
    @BindView(R.id.tv_single_title)
    TextView tvTitle;

    public ItemTitleView(Context context) {
        this(context, null);
    }

    public ItemTitleView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        View.inflate(context, R.layout.item_single_text, this);
        ButterKnife.bind(this);
    }


    public void setData(String title, int titlePosition){
        if(titlePosition  == 1){
            tvTitle.setGravity(Gravity.LEFT);
            tvTitle.setTextSize(20);
            tvTitle.setTextColor(context.getResources().getColor(R.color.colorBlack));
            tvTitle.setText(title);
        }else{
            tvTitle.setGravity(Gravity.RIGHT);
            tvTitle.setTextSize(18);
            tvTitle.setTextColor(context.getResources().getColor(R.color.colorLakeBlue));
            tvTitle.setText(title+" >");
        }
    }
}
