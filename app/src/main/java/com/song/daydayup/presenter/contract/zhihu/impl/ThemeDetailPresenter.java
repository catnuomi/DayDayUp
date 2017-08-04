package com.song.daydayup.presenter.contract.zhihu.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.zhihu.ThemeDetailBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.zhihu.ThemeDetailContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class ThemeDetailPresenter extends RxPresenter<ThemeDetailContract.View> implements ThemeDetailContract.Presenter {
    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemeDetailPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void getData(String id) {
        mView.showProgress();
        mRetrofitHelper.mZhihuDailyApiService.getThemeDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ThemeDetailBean>() {
                    @Override
                    public void call(ThemeDetailBean bean) {
                        mView.showContent(bean);
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
