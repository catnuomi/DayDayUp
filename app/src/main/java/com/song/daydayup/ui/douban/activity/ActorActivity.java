package com.song.daydayup.ui.douban.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.daydayup.R;
import com.song.daydayup.base.BaseActivity;
import com.song.daydayup.di.component.DaggerActivityComponent;
import com.song.daydayup.model.bean.douban.ActorBean;
import com.song.daydayup.presenter.contract.douban.ActorContract;
import com.song.daydayup.presenter.contract.douban.impl.ActorPresenter;

import butterknife.Bind;

public class ActorActivity extends BaseActivity<ActorPresenter> implements ActorContract.View {
    public static final String ACTOR_ID = "id";
    public static final String ACTOR_NAME = "name";
    public static final String ACTOR_AVATAR = "avatar";

    @Bind(R.id.iv_avatar)
    ImageView mIvAvatar;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.ctl)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private String mId;

    @Override
    protected void initInject() {
        DaggerActivityComponent.create().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_actor;
    }

    @Override
    protected void initEvent() {
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mId = getIntent().getStringExtra(ACTOR_ID);
        Glide.with(this).load(getIntent().getStringExtra(ACTOR_AVATAR)).into(mIvAvatar);
        mToolbar.setTitle(getIntent().getStringExtra(ACTOR_NAME));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.getData(mId);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(ActorBean data) {
        tv.setText(data.toString());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

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
