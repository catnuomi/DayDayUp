package com.song.daydayup.ui.douban.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.song.daydayup.R;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.di.component.DaggerFragmentComponent;
import com.song.daydayup.model.bean.douban.MovieListBean;
import com.song.daydayup.presenter.contract.douban.DoubanMovieHotContract;
import com.song.daydayup.presenter.contract.douban.impl.MovieHotPresenter;
import com.song.daydayup.presenter.contract.douban.impl.MovieTop250Presenter;
import com.song.daydayup.ui.douban.adapter.MovieListAdapter;
import com.song.daydayup.ui.view.CardConfig;
import com.song.daydayup.ui.view.OverLayCardLayoutManager;
import com.song.daydayup.ui.view.RenRenCallback;
import com.song.daydayup.ui.view.SwipeRefreshLayout;
import com.song.daydayup.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public class MovieHotFragment extends SubpageFragment<MovieHotPresenter> implements DoubanMovieHotContract.View, MovieListAdapter.OnSwipeListener {
    @Bind(R.id.rv_movie)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.fab_change_layout)
    FloatingActionButton mFabChangeLayout;
    @Bind(R.id.iv_background)
    ImageView mIvBackground;
    private LinearLayoutManager mLinearLayoutManager;
    private MovieListAdapter mAdapter;
    private List<MovieListBean.SubjectsEntity> mData = new ArrayList<>();
    //服务器传来总共有多少条数据
    private int total;
    private OverLayCardLayoutManager mOverLayCardLayoutManager;
    private ItemTouchHelper.Callback mCallback;
    private ItemTouchHelper mItemTouchHelper;

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
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_blue), getResources().getColor(R.color.refresh_red), getResources().getColor(R.color.refresh_green));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });
        mSwipeRefreshLayout.setBottomColorSchemeColors(getResources().getColor(R.color.refresh_blue), getResources()
                .getColor(R.color.refresh_red), getResources().getColor(R.color.refresh_green));
        mSwipeRefreshLayout.setOnBottomRefreshListenrer(new SwipeRefreshLayout.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                if (mData.size() < total) {
                    mPresenter.getMoreData(mData.size());
                } else {
                    ToastUtil.showToast("没有更多数据了");
                    dismissProgress();
                }
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new MovieListAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_douban_movie_hot;
    }

    @Override
    public String getPageTitle() {
        return "正在热映";
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
        mSwipeRefreshLayout.setBottomRefreshing(false);
    }

    @Override
    public void showMoreContent(MovieListBean data) {
        mData.addAll(data.getSubjects());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeLayout(int layoutType) {
        if (layoutType == MovieTop250Presenter.LAYOUT_LIST) {
            mIvBackground.setVisibility(View.INVISIBLE);
            if (mLinearLayoutManager == null) {
                mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            }
            mAdapter.changeLayout(MovieListAdapter.TYPE_LIST);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        } else {
            mIvBackground.setVisibility(View.VISIBLE);
            if (mOverLayCardLayoutManager == null) {
                mOverLayCardLayoutManager = new OverLayCardLayoutManager();
            }
            onSwipe(mData.get(mData.size() - 1).getImages().getLarge());
            mAdapter.changeLayout(MovieListAdapter.TYPE_CARD);
            mAdapter.setOnSwipeListener(this);
            mRecyclerView.setLayoutManager(mOverLayCardLayoutManager);
            CardConfig.initConfig(getActivity());
            mCallback = new RenRenCallback(mRecyclerView, mAdapter);
            mItemTouchHelper = new ItemTouchHelper(mCallback);
            mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSwipe(String imageUrl) {
        Glide.with(this).load(imageUrl).animate(R.anim.movie_list).placeholder(mIvBackground.getDrawable()).diskCacheStrategy(DiskCacheStrategy.RESULT).bitmapTransform(new BlurTransformation(getContext(), 10)).into(mIvBackground);
    }


}
