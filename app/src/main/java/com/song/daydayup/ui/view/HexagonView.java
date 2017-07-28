package com.song.daydayup.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.song.daydayup.R;

/**
 * Created by Chen.Qingsong on 2017/3/22.
 */
public class HexagonView extends ImageView {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private Paint mPaint;
    private Path mPath;
    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private final Matrix mShaderMatrix;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private Rect mDrawableRect = new Rect();
    private boolean mReady;
    private boolean mSetupPending;
    private int mMeasuredHeight;
    private int mMeasuredWidth;


    private int mode;
    public static int MODE_VERTICAL = 1;
    public static int MODE_HORIZONTAL = 2;

    public HexagonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setAntiAlias(true);
        mShaderMatrix = new Matrix();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HexagonView);
        mode = ta.getInt(R.styleable.HexagonView_mode, MODE_HORIZONTAL);
        ta.recycle();
        init();
    }

    private void init() {
        super.setScaleType(ScaleType.CENTER_CROP);
        mReady = true;
        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        System.out.println("setImageDrawable -- setup");
        setup();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            //通常来说 我们的代码就是执行到这里就返回了。返回的就是我们最原始的bitmap
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }
        if (mSetupPending) {
            return;
        }
        if (mBitmap == null) {
            return;
        }
        mDrawableRect.set(0, 0, getWidth(), getHeight());
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();
        mPaint.setShader(mBitmapShader);
        updateShaderMatrix();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            return;
        }
        float l = getWidth() / 2;
        float h = (float) (Math.sqrt(3) / 2 * l);
        if (mode == MODE_VERTICAL) {
            mPath.moveTo(l / 2, 0);
            mPath.lineTo(l * 3 / 2, 0);
            mPath.lineTo(2 * l, h);
            mPath.lineTo(l * 3 / 2, 2 * h);
            mPath.lineTo(l / 2, 2 * h);
            mPath.lineTo(0, h);
            mPath.lineTo(l / 2, 0);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        } else {
            mPath.moveTo(h, 0);
            mPath.lineTo(2 * h, l / 2);
            mPath.lineTo(2 * h, l * 3 / 2);
            mPath.lineTo(h, 2 * l);
            mPath.lineTo(0, l * 3 / 2);
            mPath.lineTo(0, l / 2);
            mPath.lineTo(h, 0);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;

    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);
        // 这里不好理解 这个不等式也就是(mBitmapWidth / mDrawableRect.width()) > (mBitmapHeight / mDrawableRect.height())
        //取最小的缩放比例
        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            //y轴缩放 x轴平移 使得图片的y轴方向的边的尺寸缩放到图片显示区域（mDrawableRect）一样）
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            //x轴缩放 y轴平移 使得图片的x轴方向的边的尺寸缩放到图片显示区域（mDrawableRect）一样）
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小。
        mShaderMatrix.setScale(scale, scale);
        // 平移
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mDrawableRect.left, (int) (dy + 0.5f) + mDrawableRect.top);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }
}
