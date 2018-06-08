package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.youth.banner.Banner;

/**
 * author：rongkui.xiao --2018/5/30
 * email：dovexiaoen@163.com
 * description:宽高比限定的banner
 */

public class AutoHeighBanner extends Banner {
    public AutoHeighBanner(Context context) {
        super(context);
    }

    public AutoHeighBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHeighBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = getLayoutParams();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        params.height = (int) (widthSize * 2.0 / 5);
        //        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
