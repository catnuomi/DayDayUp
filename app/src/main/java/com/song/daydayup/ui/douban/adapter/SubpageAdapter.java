package com.song.daydayup.ui.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.song.daydayup.base.SubpageFragment;

import java.util.List;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public class SubpageAdapter extends FragmentPagerAdapter {
    List<SubpageFragment> mFragments;
    public SubpageAdapter(FragmentManager fm,List<SubpageFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getPageTitle();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
