package com.song.daydayup.ui.zhihu.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.song.daydayup.R;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.di.component.DaggerFragmentComponent;
import com.song.daydayup.model.bean.zhihu.LatestBean;
import com.song.daydayup.presenter.contract.zhihu.LatestContract;
import com.song.daydayup.presenter.contract.zhihu.impl.LatestPresenter;
import com.song.daydayup.ui.zhihu.adapter.DailyAdapter;
import com.song.daydayup.utils.ToastUtil;

import butterknife.Bind;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class ZhihuDailyFragment extends SubpageFragment<LatestPresenter> implements LatestContract.View {
    @Bind(R.id.rv_daily)
    RecyclerView mRecyclerView;

    @Override
    public void showContent(LatestBean data) {
        mRecyclerView.setAdapter(new DailyAdapter(getActivity(), data));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public String getPageTitle() {
        return "日报";
    }

    @Override
    public void showError(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    protected void initInject() {
        DaggerFragmentComponent.create().inject(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily;
    }
}
