package com.song.daydayup.di.component;

import com.song.daydayup.ui.douban.fragment.MovieHotFragment;
import com.song.daydayup.ui.douban.fragment.MovieTop250Fragment;
import com.song.daydayup.ui.douban.fragment.MovieUpcomingFragment;
import com.song.daydayup.ui.zhihu.fragment.ZhihuDailyFragment;
import com.song.daydayup.ui.zhihu.fragment.ZhihuSectionFragment;
import com.song.daydayup.ui.zhihu.fragment.ZhihuThemeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
@Singleton
@Component
public interface FragmentComponent {
    void inject(MovieTop250Fragment fragment);

    void inject(MovieHotFragment fragment);

    void inject(MovieUpcomingFragment fragment);

    void inject(ZhihuDailyFragment fragment);

    void inject(ZhihuThemeFragment fragment);

    void inject(ZhihuSectionFragment fragment);

}
