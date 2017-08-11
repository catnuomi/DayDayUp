package com.song.daydayup.presenter.contract.zhihu.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.zhihu.SectionListBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.zhihu.SectionListContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class SectionListPresenter extends RxPresenter<SectionListContract.View> implements SectionListContract.Presenter {
    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionListPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void getData() {
        mView.showProgress();
        mRetrofitHelper.mZhihuDailyApiService.getSectionList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SectionListBean>() {
                    @Override
                    public void call(SectionListBean latestBean) {
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
