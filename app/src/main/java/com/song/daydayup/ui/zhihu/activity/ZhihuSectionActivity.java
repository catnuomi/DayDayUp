package com.song.daydayup.ui.zhihu.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.song.daydayup.R;
import com.song.daydayup.base.BaseActivity;
import com.song.daydayup.di.component.DaggerActivityComponent;
import com.song.daydayup.model.bean.zhihu.SectionDetailBean;
import com.song.daydayup.presenter.contract.zhihu.SectionContract;
import com.song.daydayup.presenter.contract.zhihu.impl.SectionPresenter;
import com.song.daydayup.ui.zhihu.adapter.SectionDetailAdapter;

import butterknife.Bind;

public class ZhihuSectionActivity extends BaseActivity<SectionPresenter> implements SectionContract.View {


    @Bind(R.id.rv_content)
    RecyclerView mRv;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

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

        mToolbar.setTitle(getIntent().getStringExtra("title"));
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData(getIntent().getStringExtra("id"));
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(getIntent().getStringExtra("id"));
    }

    @Override
    public void showContent(SectionDetailBean data) {
        mRv.setAdapter(new SectionDetailAdapter(this, data));
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
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void dismissProgress() {
        mSwipeRefresh.setRefreshing(false);
    }


}
