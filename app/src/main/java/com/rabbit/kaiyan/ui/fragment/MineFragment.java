package com.rabbit.kaiyan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.RootFragment;
import com.rabbit.kaiyan.base.contract.MineContract;
import com.rabbit.kaiyan.model.beans.UserInfoBean;
import com.rabbit.kaiyan.presenter.MinePresenter;
import com.rabbit.kaiyan.ui.activity.DownloadActivity;
import com.rabbit.kaiyan.ui.activity.HistoryActivity;
import com.rabbit.kaiyan.ui.activity.LikeActivity;
import com.rabbit.kaiyan.ui.activity.LoginActivity;
import com.rabbit.kaiyan.ui.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends RootFragment<MinePresenter> implements MineContract.View {

    private static final String TAG = "MineFragment";

    @BindView(R.id.iv_mine_avatars)
    ImageView ivMineAvatars;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.bt_mine_like)
    Button btMineLike;
    @BindView(R.id.bt_mine_cache)
    Button btMineCache;
    @BindView(R.id.tv_mine_history)
    TextView tvMineHistory;
    @BindView(R.id.tv_mine_setting)
    TextView tvMineSetting;
    @BindView(R.id.tv_mine_exit)
    TextView tvMineExit;
    @BindView(R.id.tv_mine_about)
    TextView tvMineAbout;
    Unbinder unbinder;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.checkUserStatus();
    }

    @OnClick(R.id.tv_mine_about)
    public void showAbout() {

    }

    @OnClick(R.id.tv_mine_exit)
    public void exitAccount() {
        if (mPresenter.checkUserStatus()) {
            mPresenter.exitAccount();
            mPresenter.checkUserStatus();
            showActivity(LoginActivity.class);
        }
    }


    @OnClick({R.id.iv_mine_avatars, R.id.tv_mine_name, R.id.bt_mine_like, R.id.bt_mine_cache, R.id.tv_mine_history, R.id.tv_mine_setting})
    public void everyThing(View view) {
        if (mPresenter.checkUserStatus()) {
            switch (view.getId()) {
                case R.id.iv_mine_avatars:
                    showToast("Nothing here！");
                    break;
                case R.id.tv_mine_name:
                    showToast("Nothing here too！");
                    break;
                case R.id.bt_mine_like:
                    showActivity(LikeActivity.class);
                    break;
                case R.id.bt_mine_cache:
                    showActivity(DownloadActivity.class);
                    break;
                case R.id.tv_mine_history:
                    showActivity(HistoryActivity.class);
                    break;
                case R.id.tv_mine_setting:
                    showActivity(SettingActivity.class);
                    break;
            }
        } else {
            showActivity(LoginActivity.class);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void showUserInfo(UserInfoBean user) {
        tvMineName.setText(user.getUsername());
    }

    @Override
    public void showActivity(Class cls) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        mContext.startActivity(intent);
    }

    @Override
    public void setExitAccount(int visibility) {
        tvMineExit.setVisibility(visibility);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.checkUserStatus();
    }
}
