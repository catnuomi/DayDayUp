package com.song.daydayup.ui.douban.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.song.daydayup.R;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.di.component.DaggerFragmentComponent;
import com.song.daydayup.model.bean.douban.MovieListBean;
import com.song.daydayup.presenter.contract.douban.impl.MovieTop250Presenter;
import com.song.daydayup.presenter.contract.douban.impl.MovieUpcomingPresenter;
import com.song.daydayup.presenter.contract.douban.DoubanMovieUpcomingContract;
import com.song.daydayup.ui.douban.adapter.MovieListAdapter;
import com.song.daydayup.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public class MovieUpcomingFragment extends SubpageFragment<MovieUpcomingPresenter> implements DoubanMovieUpcomingContract.View {
    @Bind(R.id.rv_movie)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.fab_change_layout)
    FloatingActionButton mFabChangeLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private MovieListAdapter mAdapter;
    private List<MovieListBean.SubjectsEntity> mData = new ArrayList<>();
    //服务器传来总共有多少条数据
    private int total;

    @Override
    protected void initInject() {
        DaggerFragmentComponent.create().inject(this);
    }

    @Override
    protected void initEvent() {
        mFabChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.changeLayout();
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_blue), getResources()
                .getColor(R.color.refresh_red), getResources().getColor(R.color.refresh_green));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mAdapter.getItemCount() - 1) {
                    if (mData.size() < total) {
                        mPresenter.getMoreData(mData.size());
                    } else {
                        ToastUtil.showToast("没有更多数据了");
                    }
                }
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new MovieListAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(mAdapter);
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
        return R.layout.fragment_douban_movie_upcoming;
    }

    @Override
    public String getPageTitle() {
        return "即将上映";
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(MovieListBean data) {
        mData.clear();
        total = data.getTotal();

        mData.addAll(data.getSubjects());

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMoreContent(MovieListBean data) {
        mData.addAll(data.getSubjects());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeLayout(int layoutType) {
        if (layoutType == MovieTop250Presenter.LAYOUT_LIST) {
            if (mLinearLayoutManager == null) {
                mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            }
            mAdapter.changeLayout(false);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        } else {
            if (mGridLayoutManager == null) {
                mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
            }
            mAdapter.changeLayout(true);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
        }
        mAdapter.notifyDataSetChanged();
    }
}
