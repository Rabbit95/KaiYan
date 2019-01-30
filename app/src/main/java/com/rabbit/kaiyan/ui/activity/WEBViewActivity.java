package com.rabbit.kaiyan.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.model.beans.ItemListBean;
import com.rabbit.kaiyan.util.UrlUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WEBViewActivity extends AppCompatActivity {

    private static final String TAG = "WEBViewActivity";
    @BindView(R.id.wv)
    WebView wv;
    String url;
    ItemListBean mBananerData = new ItemListBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        //Title
//        UrlUtil.UrlDecoder(mBananerData.getData().getActionUrl()).getTitle();
        //URL
//        UrlUtil.UrlDecoder(mBananerData.getData().getActionUrl()).getUrl();
        mBananerData = (ItemListBean) getIntent().getSerializableExtra("itemListBean");
        Log.d(TAG, "getUrl :"+ mBananerData.getData().getActionUrl());
        url = UrlUtil.UrlDecoder(mBananerData.getData().getActionUrl()).getUrl();
        Log.d(TAG, "url: "+ url);
    }

    private void initView() {
        if (url == null || url.equals("")) {
            Toast.makeText(getApplicationContext(),R.string.error_tips,Toast.LENGTH_LONG).show();
            finish();
        } else {
            wv.loadUrl(url);

            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wv != null){
            wv.destroy();
            wv = null;
        }
    }
}
