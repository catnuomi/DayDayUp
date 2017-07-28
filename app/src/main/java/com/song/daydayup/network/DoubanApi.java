package com.song.daydayup.network;

import com.song.daydayup.model.bean.douban.ActorBean;
import com.song.daydayup.model.bean.douban.MovieDetailBean;
import com.song.daydayup.model.bean.douban.MovieListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public interface DoubanApi {
    String HOST = "https://api.douban.com/v2/";
    //top250
    @GET("movie/top250")
    Observable<MovieListBean> getTop250(@Query("start") int start,@Query("count") int count);
    //正在热映
    @GET("movie/in_theaters")
    Observable<MovieListBean> getHot(@Query("start") int start,@Query("count") int count);
    //即将上映
    @GET("movie/coming_soon")
    Observable<MovieListBean> getUpcoming(@Query("start") int start,@Query("count") int count);
    //电影条目信息
    @GET("movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);
    //演员详情
    @GET("movie/celebrity/{id}")
    Observable<ActorBean> getActorDetail(@Path("id") String id);
}
