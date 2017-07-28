package com.song.daydayup.base;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
