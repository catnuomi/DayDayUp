package com.song.daydayup.ui.zhihu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.song.daydayup.R;
import com.song.daydayup.base.BaseActivity;
import com.song.daydayup.di.component.DaggerActivityComponent;
import com.song.daydayup.model.bean.zhihu.ThemeDetailBean;
import com.song.daydayup.presenter.contract.zhihu.ThemeDetailContract;
import com.song.daydayup.presenter.contract.zhihu.impl.ThemeDetailPresenter;
import com.song.daydayup.ui.zhihu.adapter.ThemeDetailAdapter;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZhihuThemeActivity extends BaseActivity<ThemeDetailPresenter> implements ThemeDetailContract.View {


    @Bind(R.id.iv_cover)
    ImageView mIvCover;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ctl)
    CollapsingToolbarLayout mCtl;
    @Bind(R.id.rv_content)
    RecyclerView mRv;

    @Override
    protected void initInject() {
        DaggerActivityComponent.create().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_detail;
    }

    @Override
    protected void initEvent() {
        translucentStatus();
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
//        mCtl.setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.getData(getIntent().getStringExtra("id"));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(ThemeDetailBean data) {
        mCtl.setTitle(data.getDescription());
        Glide.with(this).load(data.getBackground()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).animate(R.anim.movie_list).into(mIvCover);
        mRv.setAdapter(new ThemeDetailAdapter(this,data));
        mRv.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    public static Intent buildIntent(Context context, String id) {
        Intent intent = new Intent(context, ZhihuThemeActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
