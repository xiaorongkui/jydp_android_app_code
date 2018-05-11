package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;


/**
 * @author neo.duan
 * @date 2018/1/5 12:05
 * @desc 公共的可点击的item：类似个人中心单条带箭头item
 */
public class ClickItemView extends LinearLayout {

    private static final String TAG_LINE = "tag_line";

    private boolean mTopLineEnable;
    private boolean mBottomLineEnable;
    private int mLeftIconId;
    private CharSequence mLeftText;
    private float mLeftTextSize;
    private int mRightIconId;
    private CharSequence mRightText;
    private float mRightTextSize;
    private int mLeftTextColorId;
    private int mRightTextColorId;
    private boolean mRightIconEnable;

    private View mTopLine;
    private View mBottomLine;

    private ImageView mIvLeft;
    private TextView mTvLeft;
    private ImageView mIvRight;
    private TextView mTvRight;
    private View mViewRedPoint;
    private ViewGroup mContainer; //中间布局容器

    private View mContentView;
    private float mRightTextPaddingLeft;
    private float mBottomLineMarginTop;
    private TextView mTvLeftEnd;
    private CharSequence mLeftEndText;
    private float mLeftEndTextSize;

    public ClickItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCustomAttr(context, attrs);
        initView();
    }

    public ClickItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickItemView(Context context) {
        this(context, null);
    }

    private void initCustomAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ClickItem);
        // 获取自定义属性资源ID
        mTopLineEnable = a.getBoolean(R.styleable.ClickItem_topLine, false);
        mBottomLineEnable = a.getBoolean(R.styleable.ClickItem_bottomLine, true);
        mLeftIconId = a.getResourceId(R.styleable.ClickItem_leftIcon, -1);
        mLeftText = a.getText(R.styleable.ClickItem_leftText);
        mLeftEndText = a.getText(R.styleable.ClickItem_leftEndText);
        mLeftTextSize = a.getDimension(R.styleable.ClickItem_leftTextSize, -1);
        mLeftEndTextSize = a.getDimension(R.styleable.ClickItem_leftEndTextSize, -1);
        mRightIconId = a.getResourceId(R.styleable.ClickItem_rightIcon, R.mipmap.ic_common_arrow);
        mRightText = a.getText(R.styleable.ClickItem_rightText);
        mRightTextSize = a.getDimension(R.styleable.ClickItem_rightTextSize, -1);
        mLeftTextColorId = a.getColor(R.styleable.ClickItem_leftTextColor, Color.parseColor("#33383b"));
        mRightTextColorId = a.getColor(R.styleable.ClickItem_rightTextColor, Color.parseColor("#9d9d9d"));
        mRightIconEnable = a.getBoolean(R.styleable.ClickItem_rightIconVisible, true);
        mRightTextPaddingLeft = a.getDimension(R.styleable.ClickItem_rightTextPaddingLeft, -1);
        mBottomLineMarginTop = a.getDimension(R.styleable.ClickItem_bottomLineMarginTop, -1);
        a.recycle();
    }

    /**
     * 初始化布局信息
     */
    private void initView() {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);

        //添加顶部线
        addView(mTopLine = createLine());
        //添加中间内容:weight = 1
        View itemView = View.inflate(getContext(), R.layout.layout_click_item_view, null);
        itemView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, 1f));
        addView(itemView);
        //添加底部线
        addView(mBottomLine = createLine());

        setBackgroundResource(android.R.color.white);

        mIvLeft = findViewById(R.id.iv_item_view_left);
        mTvLeft = findViewById(R.id.tv_item_view_left);
        mTvLeftEnd = findViewById(R.id.tv_item_view_left_end_tv);
        mIvRight = findViewById(R.id.iv_item_view_right);
        mTvRight = findViewById(R.id.tv_item_view_right);
        mViewRedPoint = findViewById(R.id.view_item_view_red_point);
        mContainer = findViewById(R.id.rl_item_view_container);

        enableTopLine(mTopLineEnable);
        enableBottomLine(mBottomLineEnable);
        setLeftIcon(mLeftIconId);
        setLeftText(String.valueOf(mLeftText));
        setLeftTextSize(mLeftTextSize);

        etLeftEndText(String.valueOf(mLeftEndText));
        setLeftEndTextSize(mLeftEndTextSize);

        setRightIcon(mRightIconId);
        setRightText(String.valueOf(mRightText));
        setRightTextSize(mRightTextSize);
        setLeftTextColor(mLeftTextColorId);
        setRightTextColor(mRightTextColorId);
        enableRightIcon(mRightIconEnable);

        mTvRight.setPadding((int) mRightTextPaddingLeft, 0, 0, 0);
    }

    private void setLeftEndTextSize(float size) {
        if (size < 0) {
            return;
        }
        mTvLeftEnd.setTextSize(px2sp(size));
    }

    private void etLeftEndText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mTvLeftEnd.setText(text);
    }

    /**
     * 创建线条
     *
     * @return line
     */
    private View createLine() {
        View lineView = new View(getContext());
        lineView.setTag(TAG_LINE);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.topMargin = (int) mBottomLineMarginTop;

        lineView.setLayoutParams(layoutParams);
        lineView.setBackgroundColor(getResources().getColor(R.color.item_line));
        return lineView;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount <= 0) {
            return;
        }
        View lastChildView = getChildAt(childCount - 1);

        if (!TAG_LINE.equals(lastChildView.getTag())) {
            this.removeView(lastChildView);
            setContentView(lastChildView);
        }
    }

    /**
     * 设置左边文本的颜色
     *
     * @param color color
     */
    private void setRightTextColor(int color) {
        mTvRight.setTextColor(color);
    }

    /**
     * 设置右边文本的颜色
     *
     * @param color color
     */
    private void setLeftTextColor(int color) {
        mTvLeft.setTextColor(color);
    }

    /**
     * 左边icon是否可用
     *
     * @param enable enable
     * @param resId  resId
     */
    public void enableLeftIcon(boolean enable, int resId) {
        if (enable) {
            mIvLeft.setImageResource(resId);
            mIvLeft.setScaleType(ScaleType.FIT_CENTER);
        }
        mIvLeft.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    public void enableLeftIcon(boolean enable) {
        enableLeftIcon(enable, R.mipmap.ic_launcher);
    }

    /**
     * 设置左边图片
     *
     * @param resId resId
     */
    public void setLeftIcon(int resId) {
        if (resId < 0) {
            enableLeftIcon(false);
            return;
        }
        enableLeftIcon(true, resId);
    }

    /**
     * 设置左边文本
     *
     * @param text text
     */
    public void setLeftText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mTvLeft.setText(text);
    }

    public void setLeftTextSize(float size) {
        if (size < 0) {
            return;
        }
        mTvLeft.setTextSize(px2sp(size));
    }

    public void setLeftText(int resId) {
        if (resId < 0) {
            mTvLeft.setText("");
            return;
        }
        mTvLeft.setText(getResources().getString(resId));
    }

    /**
     * 设置顶部线条是否可用
     *
     * @param enable enable
     */
    public void enableTopLine(boolean enable) {
        mTopLine.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置底部线条是否可用
     *
     * @param enable enable
     */
    public void enableBottomLine(boolean enable) {
        mBottomLine.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置是否显示小红点
     *
     * @param enable enable
     */
    public void enableRedPoint(boolean enable) {
        mViewRedPoint.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    /**
     * 右边icon是否可用
     *
     * @param enable enable
     * @param resId  resId
     */
    public void enableRightIcon(boolean enable, int resId) {
        if (enable) {
            if (resId > 0) {
                mIvRight.setImageResource(resId);
            }
        }
        mIvRight.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    public void enableRightIcon(boolean enable) {
        enableRightIcon(enable, R.mipmap.ic_common_arrow);
    }

    /**
     * 设置右边图标
     *
     * @param resId resId
     */
    public void setRightIcon(int resId) {
        if (resId < 0) {
            enableRightIcon(false);
            return;
        }
        mIvRight.setImageResource(resId);
    }

    /**
     * 设置右边文本
     *
     * @param resId resId
     */
    public void setRightText(int resId) {
        if (resId < 0) {
            mTvRight.setText("");
            return;
        }
        mTvRight.setText(getResources().getString(resId));
    }

    /**
     * 设置右边文本
     */
    public void setRightText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mTvRight.setText(text);
    }

    public void setRightTextSize(float size) {
        if (size < 0) {
            return;
        }
        mTvRight.setTextSize(px2sp(size));
    }

    /**
     * 设置中间容器view
     *
     * @param layoutResID 资源id
     */
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(getContext(), layoutResID, null));
    }

    /**
     * 设置中间容器view
     *
     * @param contentView view
     */
    public void setContentView(View contentView) {
        this.mContentView = contentView;
        mContainer.removeAllViews();
        mContainer.addView(contentView);
    }

    /**
     * 获取中间容器填充的View
     *
     * @return contentView
     */
    public View getContentView() {
        return mContentView;
    }

    public int px2sp(float pxValue) {
        float fontScale = getContext().getResources().getDisplayMetrics()
                .scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
