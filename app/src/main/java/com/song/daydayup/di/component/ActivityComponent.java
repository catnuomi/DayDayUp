package com.song.daydayup.di.component;

import com.song.daydayup.ui.MainActivity;
import com.song.daydayup.ui.douban.activity.ActorActivity;
import com.song.daydayup.ui.douban.activity.MovieDetailActivity;
import com.song.daydayup.ui.zhihu.activity.ZhihuDailyActivity;

import dagger.Component;

/**
 * Created by Chen.Qingsong on 2017/3/1.
 */
@Component
public interface ActivityComponent {
    void inject(MovieDetailActivity activity);

    void inject(ActorActivity activity);

    void inject(MainActivity activity);

    void inject(ZhihuDailyActivity activity);
}
