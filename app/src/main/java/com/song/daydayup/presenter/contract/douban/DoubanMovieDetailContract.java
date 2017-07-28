package com.song.daydayup.presenter.contract.douban;

import com.song.daydayup.base.BasePresenter;
import com.song.daydayup.base.BaseView;
import com.song.daydayup.model.bean.douban.MovieDetailBean;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public interface DoubanMovieDetailContract {
    interface View extends BaseView {
        void showContent(MovieDetailBean data);

        void showProgress();

        void dismissProgress();

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取数据
         */
        void getData(String id);
    }
}
