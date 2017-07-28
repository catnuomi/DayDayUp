package com.song.daydayup.ui;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;
import com.song.daydayup.R;
import com.song.daydayup.base.BaseActivity;
import com.song.daydayup.di.component.DaggerActivityComponent;
import com.song.daydayup.presenter.MainPresenter;
import com.song.daydayup.presenter.contract.MainContract;
import com.song.daydayup.ui.douban.fragment.DoubanMainFragment;
import com.song.daydayup.ui.zhihu.fragment.ZhihuMainFragment;
import com.song.daydayup.utils.ToastUtil;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.content)
    FrameLayout mContent;
    @Bind(R.id.navigation)
    NavigationView mNavigation;
    @Bind(R.id.drawer)
    DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ZhihuMainFragment mZhihuMainFragment;
    private DoubanMainFragment mDoubanMainFragment;
    private Fragment mLastFragment;
    private Fragment mShowFragment;
    private MenuItem mLastItem;

    @Override
    protected void initInject() {
        DaggerActivityComponent.create().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, mDrawer, getResources()
                .getColor(R.color.colorPrimaryDark));
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mNavigation.setItemIconTintList(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDoubanMainFragment = new DoubanMainFragment();
        mShowFragment = mDoubanMainFragment;
        mLastFragment = mDoubanMainFragment;
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.zhihu:
                        if (mZhihuMainFragment == null) {
                            mZhihuMainFragment = new ZhihuMainFragment();
                        }
                        mShowFragment = mZhihuMainFragment;
                        break;
                    case R.id.gank:
                        ToastUtil.showToast("gank");
                        break;
                    case R.id.weibo:
                        ToastUtil.showToast("weibo");
                        break;
                    case R.id.douban:
                        mShowFragment = mDoubanMainFragment;
                        break;
                }
                if (mLastItem != null) {
                    mLastItem.setChecked(false);
                }

                item.setChecked(true);
                mLastItem = item;
                mDrawer.closeDrawers();
                if (mShowFragment == mLastFragment) {

                    return true;
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (!mShowFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content, mShowFragment);
                }
                fragmentTransaction.show(mShowFragment).hide(mLastFragment).commit();
                mLastFragment = mShowFragment;
                return true;
            }
        });
        mLastItem = mNavigation.getMenu().findItem(R.id.douban).setChecked(true);
        mDrawer.addDrawerListener(mDrawerToggle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, mDoubanMainFragment)
                .commit();


    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showError(String msg) {
        ToastUtil.showToast(msg);
    }
}
