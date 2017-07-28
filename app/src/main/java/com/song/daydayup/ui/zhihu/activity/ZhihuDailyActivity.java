package com.song.daydayup.ui.zhihu.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.song.daydayup.R;
import com.song.daydayup.base.BaseActivity;
import com.song.daydayup.di.component.DaggerActivityComponent;
import com.song.daydayup.model.bean.zhihu.DailyDetailBean;
import com.song.daydayup.presenter.contract.zhihu.DailyDetailContract;
import com.song.daydayup.presenter.contract.zhihu.impl.DailyDetailPresenter;
import com.song.daydayup.utils.HtmlUtil;

import butterknife.Bind;

/**
 * Created by Chen.Qingsong on 2017/4/11.
 */

public class ZhihuDailyActivity extends BaseActivity<DailyDetailPresenter> implements DailyDetailContract.View{
    @Bind(R.id.webview)
    WebView mWebView;
    @Bind(R.id.iv_cover)
    ImageView mIvCover;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private String mId;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInject() {
        DaggerActivityComponent.create().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_daily_detail;
    }

    @Override
    protected void initEvent() {
        mToolbar.setTitle(getIntent().getStringExtra("title"));
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        translucentStatus();
        mId = getIntent().getStringExtra("id");
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(mId);
    }

    @Override
    public void showContent(DailyDetailBean data) {

        String htmlData = HtmlUtil.createHtmlData(data.getBody(), data.getCss(), data.getJs());
        Glide.with(this).load(data.getImage()).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvCover);
        mWebView.loadData(htmlData,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
