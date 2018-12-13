package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ReplyBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemReplyTitleView extends RelativeLayout {

    Context context;
    @BindView(R.id.tv_replytitle)
    TextView replyTitle;

    public ItemReplyTitleView(Context mContext) {
        this(mContext, null);
    }

    public ItemReplyTitleView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemReplyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        View.inflate(context, R.layout.item_replytitle, this);
        ButterKnife.bind(this);
    }

    public void setData(ReplyBean.ItemListBean itemListBean) {
        if (itemListBean.getType().equals("leftAlignTextHeader")) {
            replyTitle.setText(itemListBean.getData().getText());
        }
    }


}
