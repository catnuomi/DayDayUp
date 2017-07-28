package com.song.daydayup.presenter.contract.douban;

import com.song.daydayup.base.BasePresenter;
import com.song.daydayup.base.BaseView;
import com.song.daydayup.model.bean.douban.MovieListBean;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public interface DoubanMovieHotContract {
    interface View extends BaseView {
        void showContent(MovieListBean data);

        void showProgress();

        void dismissProgress();

        void showMoreContent(MovieListBean data);

        void changeLayout(int layoutType);
    }

    interface Presenter extends BasePresenter<View>{
        /**
         * 获取数据
         */
        void getData();

        /**
         * 加载更多
         */
        void getMoreData(int start);

        /**
         * 刷新数据
         */
        void refreshData();

        /**
         * 更改布局方式
         */
        void changeLayout();
    }
}
