package com.song.daydayup.ui.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.daydayup.R;
import com.song.daydayup.model.bean.zhihu.LatestBean;
import com.song.daydayup.ui.zhihu.activity.ZhihuDailyActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    private final Context mContext;
    private final LatestBean mData;

    public DailyAdapter(Context context, LatestBean data) {
        mContext = context;
        mData = data;
    }

    @Override
    public DailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_daily, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mData.getStories().get(position).getImages().get(0))
                .placeholder(R.mipmap.preload)
                .into(holder.mImageView);
        holder.mTvTitle.setText(mData.getStories().get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhihuDailyActivity.class);
                intent.putExtra("id", mData.getStories().get(position).getId() + "");
                intent.putExtra("title", mData.getStories().get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.getStories().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_image)
        ImageView mImageView;
        @Bind(R.id.tv_title)
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
