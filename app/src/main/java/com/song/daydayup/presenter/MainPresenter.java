package com.song.daydayup.presenter;

import com.song.daydayup.base.RxPresenter;
import com.song.daydayup.presenter.contract.MainContract;

import javax.inject.Inject;

/**
 * Created by Chen.Qingsong on 2017/4/2.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {

    }
}
