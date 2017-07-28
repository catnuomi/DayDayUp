package com.song.daydayup;

import android.app.Application;

/**
 * Created by Chen.Qingsong on 2017/2/21.
 */
public class App extends Application {
    private static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static App getInstance() {
        return mInstance;
    }
}
