package com.song.daydayup.ui.douban.adapter;

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
import com.song.daydayup.model.bean.douban.MovieListBean;
import com.song.daydayup.ui.douban.activity.MovieDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chen.Qingsong on 2017/2/24.
 */
public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<MovieListBean.SubjectsEntity> mData;
    private final Context mContext;
    private boolean gridLayout = false;
    private final static int TYPE_LIST = 0;
    private final static int TYPE_GRID = 1;

    public MovieListAdapter(Context context, List<MovieListBean.SubjectsEntity> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (gridLayout) {
            return TYPE_GRID;
        } else {
            return TYPE_LIST;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_GRID) {
            return new GridViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_grid, parent,false));
        } else {
            return new ListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_list, parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MovieListBean.SubjectsEntity subjectsEntity = mData.get(position);
        if (holder instanceof ListViewHolder) {
            ListViewHolder tempHolder = (ListViewHolder) holder;
            //海报
            Glide.with(mContext)
                    .load(mData.get(position).getImages().getLarge())
                    .into(tempHolder.mItemMovieIv);

            //主演
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < subjectsEntity.getCasts().size(); i++) {
                builder.append(subjectsEntity.getCasts().get(i).getName());
                if (i < subjectsEntity.getCasts().size() - 1) {
                    builder.append("、");
                }
            }
            tempHolder.mItemMovieCast.setText(builder);

            //导演
            builder = new StringBuilder();
            for (int i = 0; i < subjectsEntity.getDirectors().size(); i++) {
                builder.append(subjectsEntity.getDirectors().get(i).getName());
                if (i < subjectsEntity.getDirectors().size() - 1) {
                    builder.append("、");
                }
            }
            tempHolder.mItemMovieDirectors.setText(builder);

            //上映时间
            tempHolder.mItemMovieYear.setText(subjectsEntity.getYear());

            //电影类型
            builder = new StringBuilder();
            for (int i = 0; i < subjectsEntity.getGenres().size(); i++) {
                builder.append(subjectsEntity.getGenres().get(i));
                if (i < subjectsEntity.getGenres().size() - 1) {
                    builder.append("、");
                }
            }
            tempHolder.mItemMovieGenres.setText(builder);

            //影片名
            tempHolder.mItemMovieTitle.setText(subjectsEntity.getTitle());

            //电影原名
            tempHolder.mItemMovieOriginalTitle.setText(subjectsEntity.getOriginal_title());

            //评分
            tempHolder.mItemMovieRating.setText(subjectsEntity.getRating().getAverage() + "");
        } else {
            GridViewHolder tempHolder = (GridViewHolder) holder;
            //海报
            Glide.with(mContext)
                    .load(mData.get(position).getImages().getLarge())
                    .into(tempHolder.mItemMovieIv);
            //影片名
            tempHolder.mItemMovieTitle.setText(subjectsEntity.getTitle());
            //评分
            tempHolder.mItemMovieRating.setText(subjectsEntity.getRating().getAverage() + "");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("id", subjectsEntity.getId());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 改变item布局
     * @param gridLayout
     */
    public void changeLayout(boolean gridLayout) {
        this.gridLayout = gridLayout;
    }
    public class ListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_movie_title)
        TextView mItemMovieTitle;
        @Bind(R.id.item_movie_original_title)
        TextView mItemMovieOriginalTitle;
        @Bind(R.id.item_movie_cast)
        TextView mItemMovieCast;
        @Bind(R.id.item_movie_directors)
        TextView mItemMovieDirectors;
        @Bind(R.id.item_movie_genres)
        TextView mItemMovieGenres;
        @Bind(R.id.item_movie_rating)
        TextView mItemMovieRating;
        @Bind(R.id.item_movie_year)
        TextView mItemMovieYear;
        @Bind(R.id.item_movie_iv)
        ImageView mItemMovieIv;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_movie_iv)
        ImageView mItemMovieIv;
        @Bind(R.id.item_movie_title)
        TextView mItemMovieTitle;
        @Bind(R.id.item_movie_rating)
        TextView mItemMovieRating;
        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
