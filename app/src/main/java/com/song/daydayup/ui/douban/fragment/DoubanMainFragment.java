package com.song.daydayup.ui.douban.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.song.daydayup.R;
import com.song.daydayup.base.BaseFragment;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.ui.douban.adapter.SubpageAdapter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoubanMainFragment extends BaseFragment {


    @Bind(R.id.tab_douban_main)
    TabLayout mTabDoubanMain;
    @Bind(R.id.vp_douban_main)
    ViewPager mVpDoubanMain;
    private MovieHotFragment mHotFragment;
    private MovieTop250Fragment mMovieTop250;
    private MovieUpcomingFragment mUpcomingFragment;
    private SubpageAdapter mDoubanMainAdapter;

    public DoubanMainFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initInject() {

    }

    @Override
    protected void initEvent() {
        mVpDoubanMain.setOffscreenPageLimit(3);
        mHotFragment = new MovieHotFragment();
        mMovieTop250 = new MovieTop250Fragment();
        mUpcomingFragment = new MovieUpcomingFragment();

        mTabDoubanMain.setupWithViewPager(mVpDoubanMain);
        ArrayList<SubpageFragment> fragments = new ArrayList<>();
        fragments.add(mHotFragment);
        fragments.add(mMovieTop250);
        fragments.add(mUpcomingFragment);
        mDoubanMainAdapter = new SubpageAdapter(getChildFragmentManager(), fragments);
        mVpDoubanMain.setAdapter(mDoubanMainAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_douban_main;
    }

    @Override
    public void showError(String msg) {

    }
}
