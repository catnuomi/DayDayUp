package com.song.daydayup.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public abstract class BaseFragment extends Fragment implements BaseView {
    private View mView;
    protected boolean isFirst =true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        initInject();
        return mView;
    }

    /**
     * 依赖注入
     */
    protected abstract void initInject();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initEvent();

    }

    @Override
    public void onResume() {
        if (getUserVisibleHint()) {
            initData();
        }
        super.onResume();
    }


    /**
     * 初始化事件注册
     */
    protected abstract void initEvent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

}
