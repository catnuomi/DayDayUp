package com.song.daydayup.presenter.contract.zhihu;

import com.song.daydayup.base.BasePresenter;
import com.song.daydayup.base.BaseView;
import com.song.daydayup.model.bean.zhihu.ThemeBean;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public interface ThemeContract {
    interface View extends BaseView {
        void showContent(ThemeBean data);

        void showProgress();

        void dismissProgress();
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
