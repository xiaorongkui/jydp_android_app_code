package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.qmkj.jydp.R;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/4/25
 * email：dovexiaoen@163.com
 * description:自定义阴影效果，控件阴影效果，只是最外层空间加阴影，不处理内部子控件
 * 注意：(1)不要给此控件的子view设置任何背景，否则圆角不生效
 */

public class CustomShadowView extends FrameLayout {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mRectF = new RectF();

    /**
     * 阴影的颜色,
     */
    private int mShadowColor = CommonUtil.getColor(R.color.color_gray_4);

    /**
     * 阴影的大小范围 radius越大越模糊，越小越清晰
     */
    private float mShadowRadius = CommonUtil.getDimen(R.dimen.x25);

    /**
     * 阴影的宽度，比如底部的阴影，那就是底部阴影的高度
     */
    private float mShadowWidth = 10;

    /**
     * 阴影 x 轴的偏移量, 计算padding时需要计算在内
     */
    private float mShadowDx = CommonUtil.getDimen(R.dimen.x1);

    /**
     * 阴影 y 轴的偏移量，计算padding时需要计算在内，比如想底部的阴影多一些，这个设置值就可以了
     */
    private float mShadowDy = CommonUtil.getDimen(R.dimen.x1);

    public CustomShadowView(Context context) {
        this(context, null);
    }

    public CustomShadowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    /**
     * 为 ShadowLayout 设置 Padding 以为显示阴影留出空间
     */
    private void resetShadowPadding() {
        float rectLeft = 0;
        float rectTop = 0;
        float rectRight = 0;
        float rectBottom = 0;
        int paddingLeft = 10;
        int paddingTop = 10;
        int paddingRight = 1;
        int paddingBottom = 1;

        // todo 测试代码，待完善，暂时是右侧和底部的阴影
        rectRight = this.getWidth() - mShadowWidth - mShadowDx;
        paddingRight = (int) mShadowWidth + (int) mShadowDx;

        rectBottom = this.getHeight() - mShadowWidth - mShadowDy;
        paddingBottom = (int) mShadowWidth + (int) mShadowDy;

        mRectF.left = rectLeft;
        mRectF.top = rectTop;
        mRectF.right = rectRight;
        mRectF.bottom = rectBottom;
        this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 决定View在ViewGroup中的位置 , 此处left ，top...是相对于父视图
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        resetShadowPadding();

        int childCount = getChildCount();
        StateListDrawable stateListDrawable = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x5))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_white_1))
                .setStrokeWidth(0)
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1))
                .create();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            Drawable background = view.getBackground();
            if (background != null) {
                view.setBackground(null);
            }
            view.setBackground(stateListDrawable);
        }
    }

    /**
     * 决定View的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 如何绘制这个View, 真正绘制阴影的方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mRectF, (int) CommonUtil.getDimen(R.dimen.x5), (int) CommonUtil.getDimen(R.dimen.x5),
                mPaint);
    }

    /**
     * 读取设置的阴影的属性
     *
     * @param attrs 从其中获取设置的值
     */
    private void init(AttributeSet attrs) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);  // 关闭硬件加速,setShadowLayer 才会有效
        this.setWillNotDraw(false);                    // 调用此方法后，才会执行 onDraw(Canvas) 方法
        // todo 从AttributeSet获取设置的值
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.TRANSPARENT);
//        mPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, mShadowColor);
        mPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, mShadowColor);
    }
}
