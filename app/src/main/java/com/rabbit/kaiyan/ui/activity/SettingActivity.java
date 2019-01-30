package com.rabbit.kaiyan.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rabbit.kaiyan.App.Constants;
import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.BaseActivity;
import com.rabbit.kaiyan.base.contract.SettingContract;
import com.rabbit.kaiyan.component.Acache;
import com.rabbit.kaiyan.presenter.SettingPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.View {
    @BindView(R.id.iv_setting_back)
    ImageView ivSettingBack;
    @BindView(R.id.tv_setting_title)
    TextView tvSettingTitle;
    @BindView(R.id.rl_toolsbar)
    RelativeLayout rlToolsbar;
    @BindView(R.id.tv_setting_play_open)
    TextView tvSettingPlayOpen;
    @BindView(R.id.tv_setting_play_close)
    TextView tvSettingPlayClose;
    @BindView(R.id.tv_setting_download_open)
    TextView tvSettingDownloadOpen;
    @BindView(R.id.tv_setting_download_close)
    TextView tvSettingDownloadClose;
    @BindView(R.id.tv_setting_clearcache)
    TextView tvSettingClearcache;
    @BindView(R.id.tv_setting_cachesize)
    TextView tvSettingCachesize;

    private boolean downloadSetting;
    private boolean playSetting;
    private File cacheFile;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        cacheFile = new File(Constants.PATH_CACHE);
        downloadSetting = mPresenter.getDownloadSetting();
        playSetting = mPresenter.getPlaySetting();
        tvSettingCachesize.setText(Acache.getCacheSize(cacheFile));
        tvSettingTitle.setText("我的设置");
        setSelected();
    }

    private void setSelected() {
        tvSettingPlayOpen.setSelected(playSetting);
        tvSettingPlayClose.setSelected(!playSetting);
        tvSettingDownloadOpen.setSelected(downloadSetting);
        tvSettingDownloadClose.setSelected(!downloadSetting);
    }

    @OnClick(R.id.tv_setting_play_open)
    public void setPlaySettingToOpen(){
        if (!playSetting){
            playSetting = true;
            mPresenter.setPlaySetting(playSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_setting_play_close)
    public void setPlaySettingToClose(){
        if (playSetting){
            playSetting = false;
            mPresenter.setPlaySetting(playSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_setting_download_open)
    public void setDownloadSettingToOpen(){
        if (!downloadSetting){
            downloadSetting = true;
            mPresenter.setDownloadSetting(downloadSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_setting_download_close)
    public void setDownloadSettingToClose(){
        if (downloadSetting){
            downloadSetting = false;
            mPresenter.setDownloadSetting(downloadSetting);
            setSelected();
        }
    }

    @OnClick(R.id.tv_setting_clearcache)
    public void clearCache(){
        Acache.deleteDir(cacheFile);
        tvSettingCachesize.setText(Acache.getCacheSize(cacheFile));
        showToast("缓存已清理");
    }

    @OnClick(R.id.iv_setting_back)
    public void closeActivity(){
        finish();
    }


    private void showToast(String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
