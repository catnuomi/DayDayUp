package com.song.daydayup.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.song.daydayup.R;
import com.song.daydayup.base.BaseFragment;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.ui.douban.adapter.SubpageAdapter;
import com.song.daydayup.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class ZhihuMainFragment extends BaseFragment {
    @Bind(R.id.tab_zhihu_main)
    TabLayout mTabZhihuMain;
    @Bind(R.id.vp_zhihu_main)
    ViewPager mVpZhihuMain;
    private ZhihuDailyFragment mZhihuDailyFragment;
    private SubpageAdapter mSubpageAdapter;
    private ZhihuThemeFragment mZhihuThemeFragment;

    @Override
    public void showError(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    protected void initInject() {
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        mZhihuDailyFragment = new ZhihuDailyFragment();
        mZhihuThemeFragment = new ZhihuThemeFragment();
        mTabZhihuMain.setupWithViewPager(mVpZhihuMain);
        ArrayList<SubpageFragment> fragments = new ArrayList<>();
        fragments.add(mZhihuDailyFragment);
        fragments.add(mZhihuThemeFragment);
        mSubpageAdapter = new SubpageAdapter(getChildFragmentManager(), fragments);
        mVpZhihuMain.setAdapter(mSubpageAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }
}
