package com.song.daydayup.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;

    CompositeSubscription mCompositeSubscription;
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubsribe();
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubsribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
