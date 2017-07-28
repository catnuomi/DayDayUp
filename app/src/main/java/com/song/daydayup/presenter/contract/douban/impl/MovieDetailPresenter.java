package com.song.daydayup.presenter.contract.douban.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.douban.MovieDetailBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.douban.DoubanMovieDetailContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/3/1.
 */
public class MovieDetailPresenter extends RxPresenter<DoubanMovieDetailContract.View> implements DoubanMovieDetailContract.Presenter {

    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public MovieDetailPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getData(String id) {
        mView.showProgress();
        addSubscribe(mRetrofitHelper.mDoubanApiService.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetailBean>() {
                    @Override
                    public void call(MovieDetailBean movieDetailBean) {
                        mView.dismissProgress();
                        mView.showContent(movieDetailBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                    }
                }));

    }
}
