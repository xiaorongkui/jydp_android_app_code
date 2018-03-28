package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

import com.qmkj.jydp.util.LogUtil;

/**
 * 解决scrollview嵌套recycleview滑动不流畅
 */
public class SmoothScrollView extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;
    private ScrollListener mListener;
    private boolean isRefresh;

    public interface ScrollListener {//声明接口，用于传递数据

        void scrollOritention(int x, int y, int oldx, int oldy);
    }

    public SmoothScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SmoothScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public SmoothScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        LogUtil.i("x=" + l + "y=" + t + "x1=" + oldl + "y1=" + oldt);
        setTop(t == 0 || oldt == 0);
        if (mListener != null) {
            mListener.scrollOritention(l, t, oldl, oldt);
        }
    }

    public void setOnScrollListener(ScrollListener l) {
        this.mListener = l;
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {

        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }


    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    private boolean isTop = true;//默认scrollview已经滑到顶端

}
