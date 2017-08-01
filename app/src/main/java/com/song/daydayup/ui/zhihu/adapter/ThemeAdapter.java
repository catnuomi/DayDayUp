package com.song.daydayup.ui.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.daydayup.R;
import com.song.daydayup.model.bean.zhihu.ThemeBean;

/**
 * Created by Chen.QingSong on 2017/7/31.
 */

public class ThemeAdapter extends BaseAdapter {

    private final Context mContext;
    private final ThemeBean mData;

    public ThemeAdapter(Context context, ThemeBean data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.getOthers().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_theme, viewGroup, false);
        }
        Glide.with(mContext).load(mData.getOthers().get(i).getThumbnail()).into((ImageView) view.findViewById(R.id.iv));
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(mData.getOthers().get(i).getName());
        return view;
    }
}
