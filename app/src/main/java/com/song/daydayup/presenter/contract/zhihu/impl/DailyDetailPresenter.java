package com.song.daydayup.presenter.contract.zhihu.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.zhihu.DailyDetailBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.zhihu.DailyDetailContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/4/11.
 */

public class DailyDetailPresenter extends RxPresenter<DailyDetailContract.View> implements DailyDetailContract.Presenter {
    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public DailyDetailPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void getData(String id) {
        mRetrofitHelper.mZhihuDailyApiService.getDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DailyDetailBean>() {
                    @Override
                    public void call(DailyDetailBean dailyDetailBean) {
                        mView.showContent(dailyDetailBean);
                    }
                },new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                    }
                });
    }
}
