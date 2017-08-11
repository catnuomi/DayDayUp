package com.song.daydayup.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.song.daydayup.utils.ToastUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Chen.Qingsong on 2017/3/1.
 */
public abstract class BaseActivity<T extends RxPresenter> extends AppCompatActivity implements BaseView{
    @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        translucentStatus();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initInject();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEvent();
        initData();
    }

    protected void translucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showError(String msg) {
        ToastUtil.showToast(msg);
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEvent();

    protected abstract void initData();
}
