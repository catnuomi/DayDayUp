package com.song.daydayup.network;

import com.song.daydayup.model.bean.zhihu.DailyDetailBean;
import com.song.daydayup.model.bean.zhihu.LatestBean;
import com.song.daydayup.model.bean.zhihu.SectionDetailBean;
import com.song.daydayup.model.bean.zhihu.SectionListBean;
import com.song.daydayup.model.bean.zhihu.ThemeBean;
import com.song.daydayup.model.bean.zhihu.ThemeDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public interface ZhihuDailyApi {
    String HOST = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<LatestBean> getLatest();

    @GET("news/{id}")
    Observable<DailyDetailBean> getDetail(@Path("id") String id);
    @GET("themes")
    Observable<ThemeBean> getTheme();

    @GET("theme/{id}")
    Observable<ThemeDetailBean> getThemeDetail(@Path("id") String id);
    @GET("sections")
    Observable<SectionListBean> getSectionList();
    @GET("section/{id}")
    Observable<SectionDetailBean> getSectionDetail(@Path("id") String id);
}
