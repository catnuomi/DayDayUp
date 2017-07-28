package com.song.daydayup.presenter.contract.douban.impl;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.model.bean.douban.ActorBean;
import com.song.daydayup.network.RetrofitHelper;
import com.song.daydayup.presenter.contract.douban.ActorContract;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen.Qingsong on 2017/3/6.
 */
public class ActorPresenter extends RxPresenter<ActorContract.View> implements ActorContract.Presenter {

    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public ActorPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getData(String id) {
        mView.showProgress();
        addSubscribe(mRetrofitHelper.mDoubanApiService.getActorDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ActorBean>() {
                    @Override
                    public void call(ActorBean actorBean) {
                        mView.showContent(actorBean);
                        mView.dismissProgress();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败");
                        mView.dismissProgress();
                    }
                }));
    }
}
