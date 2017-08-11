package com.song.daydayup.presenter.contract.zhihu.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.zhihu.SectionDetailBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.zhihu.SectionContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class SectionPresenter extends RxPresenter<SectionContract.View> implements SectionContract.Presenter {
    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void getData(String id) {
        mView.showProgress();
        mRetrofitHelper.mZhihuDailyApiService.getSectionDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SectionDetailBean>() {
                    @Override
                    public void call(SectionDetailBean data) {
                        mView.showContent(data);
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
