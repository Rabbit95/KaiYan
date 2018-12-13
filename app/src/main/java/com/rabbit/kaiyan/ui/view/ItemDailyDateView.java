package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDailyDateView extends RelativeLayout {
    @BindView(R.id.text_date)
    TextView textView;
    Context context;

    public ItemDailyDateView(Context context) {
        this(context, null);
    }

    public ItemDailyDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public ItemDailyDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }


    private void initView() {
        View.inflate(context, R.layout.item_daily_date, this);
        ButterKnife.bind(this);
    }

    public void setData(String date) {
        textView.setText(date);
    }
}
