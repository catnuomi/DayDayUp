package com.song.daydayup.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public abstract class SubpageFragment<T extends BasePresenter> extends BaseFragment {
    @Inject
    protected T mPresenter;
    public abstract String getPageTitle();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {

            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {

            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}
