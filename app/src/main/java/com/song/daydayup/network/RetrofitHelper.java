package com.song.daydayup.network;

import com.song.daydayup.Constant;
import com.song.daydayup.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient;
    public DoubanApi mDoubanApiService;
    public ZhihuDailyApi mZhihuDailyApiService;
    @Inject
    public RetrofitHelper(){
        init();
    }

    private void init() {
        initOkHttp();
        mDoubanApiService = getDoubanApiService();
        mZhihuDailyApiService = getZhihuApiService();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File(Constant.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        builder.cache(cache);
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    private static DoubanApi getDoubanApiService() {
        return new Retrofit.Builder().baseUrl(DoubanApi.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(DoubanApi.class);
    }

    private static ZhihuDailyApi getZhihuApiService() {
        return new Retrofit.Builder().baseUrl(ZhihuDailyApi.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ZhihuDailyApi.class);
    }

}
