package com.song.daydayup.presenter.contract.zhihu.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.zhihu.ThemeBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.zhihu.ThemeContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter {
    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemePresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void getData() {
        mView.showProgress();
        mRetrofitHelper.mZhihuDailyApiService.getTheme()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ThemeBean>() {
                    @Override
                    public void call(ThemeBean latestBean) {
                        mView.showContent(latestBean);
                        mView.dismissProgress();
                    }
                },new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                        mView.dismissProgress();
                    }
                });
    }
}
