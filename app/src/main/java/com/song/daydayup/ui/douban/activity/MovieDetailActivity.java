package com.song.daydayup.ui.douban.activity;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.roger.gifloadinglibrary.GifLoadingView;
import com.song.daydayup.R;
import com.song.daydayup.base.BaseActivity;
import com.song.daydayup.di.component.DaggerActivityComponent;
import com.song.daydayup.model.bean.douban.MovieDetailBean;
import com.song.daydayup.presenter.contract.douban.DoubanMovieDetailContract;
import com.song.daydayup.presenter.contract.douban.impl.MovieDetailPresenter;
import com.song.daydayup.ui.anim.HeightAnim;
import com.song.daydayup.ui.view.HiveAdapter;
import com.song.daydayup.ui.view.HiveLayoutManager;

import butterknife.Bind;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity<MovieDetailPresenter> implements DoubanMovieDetailContract.View {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_original_title)
    TextView mTvOriginalTitle;
    @Bind(R.id.tv_director)
    TextView mTvDirector;
    @Bind(R.id.tv_rating)
    TextView mTvRating;
    @Bind(R.id.tv_des)
    TextView mTvDes;
    @Bind(R.id.iv_des_more)
    ImageView mIvDesMore;
    @Bind(R.id.iv_poster)
    ImageView mIvPoster;
    //@Bind(R.id.ll_cast_container)
    //LinearLayout mLlCastContainer;
    @Bind(R.id.tv_genres)
    TextView mTvGenres;
    @Bind(R.id.tv_year)
    TextView mTvYear;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv_cast)
    RecyclerView mRvCast;
    @Bind(R.id.content)
    View mContainer;
    @Bind(R.id.ll_info)
    View mLlInfo;
    @Bind(R.id.cv_des)
    View mCvDes;
    @Bind(R.id.cv_cast)
    View mCvCast;
    private GifLoadingView mLoadingView;

    @Override
    protected void initInject() {
        DaggerActivityComponent.create().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initEvent() {
        mLoadingView = new GifLoadingView();
        mLoadingView.setImageResource(R.mipmap.num11);
        mLoadingView.setCancelable(false);
        mLoadingView.setStyle(R.style.gif_dialog, 0);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
        mToolbar.setTitle("电影详情");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    protected void initData() {

        mPresenter.getData(getIntent().getStringExtra("id"));
    }

    @OnClick(R.id.iv_des_more)
    public void onClick() {
        if (isAniming) {
            return;
        }

        HeightAnim anim = null;
        if (isExtend) {
            //执行收缩
            anim = new HeightAnim(mTvDes, maxHeight, minHeight);
        } else {
            //执行展开动画
            anim = new HeightAnim(mTvDes, minHeight, maxHeight);
        }
        anim.start(350);

        //更改标记
        isExtend = !isExtend;

        //让箭头旋转
        ViewCompat.animate(mIvDesMore)
                .rotationBy(180)
                .setDuration(350)
                .setListener(new MyListener())
                .start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String msg) {

    }

    private int minHeight;//5行文本的高度
    private int maxHeight;//全部文本的高度
    private boolean isExtend = false;//是否展开了
    private boolean isAniming = false;//是否正在执行动画

    @Override
    public void showContent(final MovieDetailBean data) {
        mTvTitle.setText(data.getTitle());
        mTvOriginalTitle.setText(data.getOriginal_title());
        StringBuilder builder = new StringBuilder();
        mTvRating.setText(data.getRating().getAverage() + "");
        //导演
        for (int i = 0; i < data.getDirectors().size(); i++) {
            builder.append(data.getDirectors().get(i).getName());
            if (i < data.getDirectors().size() - 1) {
                builder.append("、");
            }
        }
        Glide.with(this).load(data.getImages().getLarge()).into(mIvPoster);
        mTvDirector.setText(builder);
        //简介
        mTvDes.setText(data.getSummary());
        if (mTvDes.getLineCount() > 5) {
            mIvDesMore.setVisibility(View.VISIBLE);
            mTvDes.setMaxLines(5);
            mTvDes.setEllipsize(TextUtils.TruncateAt.END);
            mTvDes.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            mTvDes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            //获取5行的高度
                            minHeight = mTvDes.getHeight();

                            //2.获取描述区域全部的高度,由于改变了mTvDes的高度，会引起它重新layout
                            mTvDes.setMaxLines(Integer.MAX_VALUE);//让TextView显示全部内容
                            mTvDes.getViewTreeObserver()
                                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                        @Override
                                        public void onGlobalLayout() {
                                            mTvDes.getViewTreeObserver()
                                                    .removeOnGlobalLayoutListener(this);
                                            //得到全部文本的高度
                                            maxHeight = mTvDes.getHeight();

                                            //3.让描述只显示5行的高度,
                                            //						tv_des.setMaxLines(5);//不要使用这种方式
                                            ViewGroup.LayoutParams params = mTvDes.getLayoutParams();
                                            params.height = minHeight;
                                            mTvDes.setLayoutParams(params);
                                        }
                                    });
                        }
                    });
        }

        //主演
       /* int width = SystemUtil.dp2px(this, 90);
        int height = SystemUtil.dp2px(this, 150);*/
       /* int margin = SystemUtil.dp2px(this, 8);
        for (int i = 0; i < data.getCasts().size(); i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.view_cast, null, false);
            mLlCastContainer.addView(inflate);
            final MovieDetailBean.CastsEntity castsEntity = data.getCasts().get(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) inflate.getLayoutParams();
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MovieDetailActivity.this, ActorActivity.class);
                    intent.putExtra(ActorActivity.ACTOR_ID, castsEntity.getId());
                    intent.putExtra(ActorActivity.ACTOR_NAME, castsEntity.getName());
                    intent.putExtra(ActorActivity.ACTOR_AVATAR, castsEntity.getAvatars()
                            .getLarge());
                    startActivity(intent);
                }
            });
            params.leftMargin = (i == 0 ? 0 : margin);
            inflate.setLayoutParams(params);
            ((TextView) inflate.findViewById(R.id.tv_cast_name)).setText(data.getCasts()
                    .get(i)
                    .getName());
            Glide.with(this)
                    .load(data.getCasts().get(i).getAvatars().getLarge())
                    .into((ImageView) inflate.findViewById(R.id.iv_cast_avatar));
        }*/
        mRvCast.setAdapter(new HiveAdapter(MovieDetailActivity.this, data.getCasts()));
        mRvCast.setLayoutManager(new HiveLayoutManager(HiveLayoutManager.MODE_VERTICAL));
        //类型
        builder = new StringBuilder();
        for (int i = 0; i < data.getGenres().size(); i++) {
            builder.append(data.getGenres().get(i));
            if (i < data.getGenres().size() - 1) {
                builder.append("、");
            }
        }
        mTvGenres.setText(builder);
        //上映时间
        mTvYear.setText(data.getYear());

       /* ViewAnimator.animate(mCvDes)
                .translationX(-1000)
                .andAnimate(mCvCast)
                .translationY(1000)
                .andAnimate(mLlInfo)
                .translationX(mLlInfo.getWidth())
                .duration(0)
                .thenAnimate(mIvPoster)
                .dp()
                .translationY(-300, 0)
                .duration(1000)
                .thenAnimate(mLlInfo)
                .translationX(mLlInfo.getWidth(), 0)
                .duration(700)

                .thenAnimate(mCvDes)
                .translationX(-1000, 0)
                .duration(700)
                .thenAnimate(mCvCast)
                .translationY(500, 0)
                .duration(500)
                .start();*/

    }

    @Override
    public void showProgress() {
        mLoadingView.show(getFragmentManager(), null);
    }

    @Override
    public void dismissProgress() {
        mLoadingView.dismiss();
        mContainer.setVisibility(View.VISIBLE);
    }

    class MyListener implements ViewPropertyAnimatorListener {
        @Override
        public void onAnimationStart(View view) {
            isAniming = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAniming = false;
        }

        @Override
        public void onAnimationCancel(View view) {

        }
    }

}
