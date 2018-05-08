package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * author：rongkui.xiao --2018/3/21
 * email：dovexiaoen@163.com
 * description:避免RecyclerView与ScrollView冲突,导致两者各自滑动各自的事件
 */

public class ScrollRecycleView extends RecyclerView {
    public ScrollRecycleView(Context context) {
        super(context);
    }

    public ScrollRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);

        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();

        setNestedScrollingEnabled(false);
    }
}
