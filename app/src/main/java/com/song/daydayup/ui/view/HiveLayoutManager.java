package com.song.daydayup.ui.view;

import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chen.Qingsong on 2017/3/22.
 */
public class HiveLayoutManager extends RecyclerView.LayoutManager {
    private int mode = 1;
    public static int MODE_VERTICAL = 1;
    public static int MODE_HORIZONTAL = 2;
    private RectF edge = new RectF();

    public HiveLayoutManager(int mode) {
        setAutoMeasureEnabled(true);
    }
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollHorizontally() {

        return mode == MODE_HORIZONTAL ? true : false;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
//        return mode == MODE_VERTICAL ? true : false;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        System.out.println(dx);
        if (0 > edge.left && dx < 0) {
            int max = (int) Math.max(edge.left, dx);
            edge.left -= max;
            edge.right -= max;
            offsetChildrenHorizontal(-max);
            return dx;
        }
        if (getWidth() < edge.right && dx > 0) {
            int min = (int) Math.min(edge.right -getWidth(), dx);
            edge.right -= min;
            edge.left -= min;
            offsetChildrenHorizontal(-min);
            return dx;
        }
        return 0;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        System.out.println(dy);
        offsetChildrenVertical(-dy);
        if (0 > edge.top && dy < 0) {
            int max = (int) Math.max(edge.top, dy);
            edge.top -= max;
            edge.bottom -= max;
            offsetChildrenVertical(-max);
            return dy;
        }
        if (getHeight() < edge.bottom && dy > 0) {
            int min = (int) Math.min(edge.bottom -getWidth(), dy);
            edge.bottom -= min;
            edge.top -= min;
            offsetChildrenVertical(-min);
            return dy;
        }
        return 0;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {//没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler);
            return;
        }
        detachAndScrapAttachedViews(recycler);

        for (int i = 0; i < state.getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);

            addView(view);
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            float l = width / 2;
            float h = (float) (Math.sqrt(3) / 2 * l);
            if (mode == MODE_VERTICAL) {
                layoutDecoratedWithMargins(view, (int) (i % 2 == 0 ? 0 : 1.5 * l), (int) (i * h), (int) (i % 2 == 0 ? 2 * l : 3.5 * l), (int) ((i + 2) * h));
            } else {
                layoutDecoratedWithMargins(view, (int) (i * h), (int) (i % 2 == 0 ? 0 : 1.5 * l), (int) ((i + 2) * h), (int) (i % 2 == 0 ? 2 * l : 3.5 * l));
            }
            System.out.println(view.getRight());
            edge.set(0, 0, view.getRight(), view.getBottom());

        }
        /*detachAndScrapAttachedViews(recycler); // 分离所有的itemView

        int offsetX = 0;
        int offsetY = 0;

        for (int i = 0; i < getItemCount(); i++) {
            View scrap = recycler.getViewForPosition(i); // 根据position获取一个碎片view，可以从回收的view中获取，也可能新构造一个

            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);  // 计算此碎片view包含边距的尺寸

            int width = getDecoratedMeasuredWidth(scrap);  // 获取此碎片view包含边距和装饰的宽度width
            int height = getDecoratedMeasuredHeight(scrap); // 获取此碎片view包含边距和装饰的高度height

            layoutDecorated(scrap, offsetX , offsetY, offsetX + width, offsetY + height); // Important！布局到RecyclerView容器中，所有的计算都是为了得出任意position的item的边界来布局

            offsetX += width;
            offsetY += height;
        }*/
    }

    private void initEdge() {
    }
}
