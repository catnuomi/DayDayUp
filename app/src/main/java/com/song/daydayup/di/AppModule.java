package com.song.daydayup.di;

import com.song.daydayup.network.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Chen.Qingsong on 2017/2/23.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    public RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }
}
