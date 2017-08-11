package com.song.daydayup.ui.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.daydayup.R;
import com.song.daydayup.model.bean.zhihu.SectionListBean;
import com.song.daydayup.ui.zhihu.activity.ZhihuSectionActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chen.Qingsong on 2017/4/1.
 */

public class SectionListAdapter extends RecyclerView.Adapter<SectionListAdapter.ViewHolder> {

    private final Context mContext;
    private final SectionListBean mData;

    public SectionListAdapter(Context context, SectionListBean data) {
        mContext = context;
        mData = data;
    }

    @Override
    public SectionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_section_list, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mData.getData().get(position).getThumbnail())
                .placeholder(R.mipmap.preload)
                .into(holder.mImageView);
        holder.mTvTitle.setText(mData.getData().get(holder.getAdapterPosition()).getName());
        if (!TextUtils.isEmpty(mData.getData().get(holder.getAdapterPosition()).getDescription())) {
            holder.mTvDescription.setVisibility(View.VISIBLE);
            holder.mTvDescription.setText(mData.getData().get(holder.getAdapterPosition()).getDescription());
        } else {
            holder.mTvDescription.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhihuSectionActivity.class);
                intent.putExtra("id", mData.getData().get(holder.getAdapterPosition()).getId() + "");
                intent.putExtra("title", mData.getData().get(holder.getAdapterPosition()).getName());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_image)
        ImageView mImageView;
        @Bind(R.id.tv_title)
        TextView mTvTitle;
        @Bind(R.id.tv_description)
        TextView mTvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
