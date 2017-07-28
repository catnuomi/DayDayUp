package com.song.daydayup.presenter.contract.douban;

import com.song.daydayup.base.BasePresenter;
import com.song.daydayup.base.BaseView;
import com.song.daydayup.model.bean.douban.ActorBean;

/**
 * Created by Chen.Qingsong on 2017/3/6.
 */
public interface ActorContract {
    interface View extends BaseView{
        void showContent(ActorBean data);

        void showProgress();

        void dismissProgress();
    }

    interface Presenter extends BasePresenter<View>{
       void getData(String id);
    }
}
