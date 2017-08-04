package com.song.daydayup.ui.zhihu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.song.daydayup.R;
import com.song.daydayup.base.SubpageFragment;
import com.song.daydayup.di.component.DaggerFragmentComponent;
import com.song.daydayup.model.bean.zhihu.ThemeBean;
import com.song.daydayup.presenter.contract.zhihu.ThemeContract;
import com.song.daydayup.presenter.contract.zhihu.impl.ThemePresenter;
import com.song.daydayup.ui.zhihu.activity.ZhihuThemeActivity;
import com.song.daydayup.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class ZhihuThemeFragment extends SubpageFragment<ThemePresenter> implements ThemeContract.View {


    @Override
    public void showContent(ThemeBean data) {
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
   /*     if (isVisibleToUser && isFirst) {
            isFirst = false;
            mPresenter.getData();
        }*/
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public String getPageTitle() {
        return "分类";
    }

    @Override
    public void showError(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    protected void initInject() {
        DaggerFragmentComponent.create().inject(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_theme;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_psychology, R.id.btn_recommend, R.id.btn_movie, R.id.btn_find_interesting, R.id.btn_design, R.id.btn_big_company, R.id.btn_finance, R.id.btn_internet, R.id.btn_game, R.id.btn_music, R.id.btn_acg, R.id.btn_sports})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_psychology:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"13"));
                break;
            case R.id.btn_recommend:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"12"));
                break;
            case R.id.btn_movie:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"3"));
                break;
            case R.id.btn_find_interesting:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"11"));
                break;
            case R.id.btn_design:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"4"));
                break;
            case R.id.btn_big_company:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"5"));
                break;
            case R.id.btn_finance:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"6"));
                break;
            case R.id.btn_internet:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"10"));
                break;
            case R.id.btn_game:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"2"));
                break;
            case R.id.btn_music:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"7"));
                break;
            case R.id.btn_acg:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"9"));
                break;
            case R.id.btn_sports:
                startActivity(ZhihuThemeActivity.buildIntent(getActivity(),"8"));
                break;
        }
    }
}
