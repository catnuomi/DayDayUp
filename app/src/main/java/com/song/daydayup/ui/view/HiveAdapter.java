package com.song.daydayup.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.daydayup.R;
import com.song.daydayup.model.bean.douban.MovieDetailBean;
import com.song.daydayup.ui.douban.activity.ActorActivity;

import java.util.List;

/**
 * Created by zjchai on 16/9/10.
 */
public class HiveAdapter extends RecyclerView.Adapter<HiveAdapter.ImageViewHolder> {


    private final Context mContext;
    private final List<MovieDetailBean.CastsEntity> mCasts;

    public HiveAdapter(Context context,List<MovieDetailBean.CastsEntity> casts) {
        mContext = context;
        mCasts = casts;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cast, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        final MovieDetailBean.CastsEntity castsEntity = mCasts.get(position);
        Glide.with(mContext).load(castsEntity.getAvatars().getLarge()).into(holder.imageView);
        holder.mTextView.setText(castsEntity.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActorActivity.class);
                intent.putExtra(ActorActivity.ACTOR_ID, castsEntity.getId());
                intent.putExtra(ActorActivity.ACTOR_NAME, castsEntity.getName());
                intent.putExtra(ActorActivity.ACTOR_AVATAR, castsEntity.getAvatars()
                        .getLarge());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        HexagonView imageView;
        TextView mTextView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (HexagonView) itemView.findViewById(R.id.iv_avatar);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
            //mTextView = (TextView) itemView.findViewById(R.id.number);
        }

    }
}
