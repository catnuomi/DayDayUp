package com.song.daydayup.utils;

import android.widget.Toast;

import com.song.daydayup.App;

/**
 * Created by Chen.Qingsong on 2016/5/23.
 */
public class ToastUtil {
    public static Toast toast;

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        }
        //若吐司已经创建,那么直接更改吐司的文本
        toast.setText(text);
        toast.show();
    }
}
