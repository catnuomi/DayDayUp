package com.song.daydayup.presenter.contract.zhihu;

import com.song.daydayup.base.BasePresenter;
import com.song.daydayup.base.BaseView;
import com.song.daydayup.model.bean.zhihu.LatestBean;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public interface LatestContract {
    interface View extends BaseView {
        void showContent(LatestBean data);

        void showProgress();

        void dismissProgress();
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
