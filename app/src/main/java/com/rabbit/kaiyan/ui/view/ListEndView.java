package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListEndView extends FrameLayout {

    @BindView(R.id.text_end)
    public TextView textEnd;
    Context context;

    public ListEndView(@NonNull Context context) {
        this(context, null);
    }

    public ListEndView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public ListEndView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.item_listend, this);
        ButterKnife.bind(this);
    }

    public void setData(int loadState){
        switch (loadState){
            case 1:
                textEnd.setText(R.string.recycleview_loding);
                break;
            case 3:
                textEnd.setText(R.string.recycleview_loding_end);
                break;
            case 4:
                textEnd.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                textEnd.setText(R.string.recycleView_end);
                break;
        }
    }
}
