package com.rabbit.kaiyan.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ReplyBean;
import com.rabbit.kaiyan.util.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemReplyView extends LinearLayout {

    Context context;
    @BindView(R.id.tv_replytime)
    TextView replytime;
    @BindView(R.id.rl_parentreply)
    RelativeLayout parentreply;
    @BindView(R.id.iv_reply_avatar)
    ImageView replyAvatar;
    @BindView(R.id.tv_replyname)
    TextView replyName;
    @BindView(R.id.tv_replyparent)
    TextView relpyParent;
    @BindView(R.id.tv_replymessage)
    TextView replyMessage;
    @BindView(R.id.tv_replylike)
    TextView replyLike;
    @BindView(R.id.tv_parentreplymessage)
    TextView parentReplyMessage;
    @BindView(R.id.tv_parentreplyname)
    TextView parentReplyName;
    @BindView(R.id.iv_parentreply_avatar)
    ImageView parentreplyAvatar;

    public ItemReplyView(Context context) {
        this(context, null);
    }

    public ItemReplyView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public ItemReplyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.item_reply, this);
        ButterKnife.bind(this);
    }

    public void setData(ReplyBean.ItemListBean itemListBean) {
        replyLike.setText("" + itemListBean.getData().getLikeCount());
        replytime.setText(DateUtil.timeFormat(itemListBean.getData().getCreateTime()));
        ImageLoader.loadCircle(context, itemListBean.getData().getUser().getAvatar(), replyAvatar);
        replyName.setText(itemListBean.getData().getUser().getNickname());
        replyMessage.setText(itemListBean.getData().getMessage());
        if (itemListBean.getData().getParentReply() == null) {
            relpyParent.setVisibility(View.GONE);
            parentreply.setVisibility(View.GONE);
        } else {
            relpyParent.setText("回复:" + itemListBean.getData().getParentReply().getUser().getNickname());
            parentReplyName.setText(itemListBean.getData().getParentReply().getUser().getNickname());
            parentReplyMessage.setText(itemListBean.getData().getParentReply().getMessage());
            ImageLoader.loadCircle(context, itemListBean.getData().getParentReply().getUser().getAvatar(), parentreplyAvatar);
        }
    }
}
