package com.song.daydayup.presenter.contract.douban.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.douban.MovieListBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.douban.DoubanMovieTop250Contract;
import com.song.daydayup.utils.ToastUtil;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public class MovieTop250Presenter extends RxPresenter<DoubanMovieTop250Contract.View> implements DoubanMovieTop250Contract.Presenter {
    RetrofitHelper mRetrofit;
    public final static int LAYOUT_LIST = 1;
    public final static int LAYOUT_GRID = 2;
    private int currentLayout = 1;
    private boolean isLoadMoreData = false;
    public static int PER_LOAD = 20;

    @Inject
    public MovieTop250Presenter(RetrofitHelper retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public void getData() {
        mView.showProgress();
        addSubscribe(mRetrofit.mDoubanApiService.getTop250(0, PER_LOAD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieListBean>() {
                    @Override
                    public void call(MovieListBean movieListBean) {
                        mView.showContent(movieListBean);
                        mView.dismissProgress();
                    }
                },new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                    }
                }));
    }

    @Override
    public void getMoreData(int start) {
        if (isLoadMoreData) {
            ToastUtil.showToast("正在加载...");
            return;
        }
        isLoadMoreData = true;
        mView.showProgress();
        addSubscribe(mRetrofit.mDoubanApiService.getTop250(start, PER_LOAD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieListBean>() {
                    @Override
                    public void call(MovieListBean movieListBean) {
                        mView.showMoreContent(movieListBean);
                        isLoadMoreData = false;
                        mView.dismissProgress();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                    }
                }));
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void changeLayout() {
        if (currentLayout == LAYOUT_GRID) {
            mView.changeLayout(LAYOUT_LIST);
            currentLayout = LAYOUT_LIST;
        } else {
            mView.changeLayout(LAYOUT_GRID);
            currentLayout = LAYOUT_GRID;
        }

    }
}
