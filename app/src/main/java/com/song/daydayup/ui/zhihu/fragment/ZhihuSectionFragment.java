package com.song.daydayup.ui.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.song.daydayup.R;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.di.component.DaggerFragmentComponent;
import com.song.daydayup.model.bean.zhihu.SectionListBean;
import com.song.daydayup.presenter.contract.zhihu.SectionListContract;
import com.song.daydayup.presenter.contract.zhihu.impl.SectionListPresenter;
import com.song.daydayup.ui.zhihu.adapter.SectionListAdapter;
import com.song.daydayup.utils.ToastUtil;

import butterknife.Bind;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class ZhihuSectionFragment extends SubpageFragment<SectionListPresenter> implements SectionListContract.View {

    @Bind(R.id.rv_section_list)
    RecyclerView mRvSectionList;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @Override
    public void showContent(SectionListBean data) {
        mRvSectionList.setAdapter(new SectionListAdapter(getActivity(), data));
        mRvSectionList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showProgress() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void dismissProgress() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public String getPageTitle() {
        return "专栏";
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

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isFirst) {
            isFirst = false;
            mPresenter.getData();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_section_list;
    }
}
