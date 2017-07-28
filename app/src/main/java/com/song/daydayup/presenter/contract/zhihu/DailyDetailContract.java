package com.song.daydayup.presenter.contract.zhihu;

import com.song.daydayup.base.BasePresenter;
import com.song.daydayup.base.BaseView;
import com.song.daydayup.model.bean.zhihu.DailyDetailBean;

/**
 * Created by Chen.Qingsong on 2017/4/11.
 */

public interface DailyDetailContract {
    interface Presenter extends BasePresenter<View>{
        void getData(String id);
    }

    interface View extends BaseView {
        void showContent(DailyDetailBean data);

        void showProgress();

        void dismissProgress();
    }
}
