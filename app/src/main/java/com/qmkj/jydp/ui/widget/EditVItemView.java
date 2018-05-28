package com.qmkj.jydp.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;


/**
 * @author neo.duan
 * @date 2018/1/5 12:05
 * @desc 公共的可编辑的item：类似个登录界面的item
 */
public class EditVItemView extends LinearLayout {

    private View mContentView;
    private CharSequence mTitleText;
    private int mTitleTextColor;
    private float mTitleTextSize;
    private float mTitleTextPaddingTop;
    private CharSequence mContentEditHintText;
    private int mContentEditHintTextColor;
    private float mContentEditTextSize;
    private float mContentEditTopMargin;
    private CharSequence mContentRightText;
    private int mContentRightTextColor;
    private float mContentRightTextSize;
    private float mContentRightTextEndMargin;
    private boolean mContentRightTextVisible;
    private boolean mBottomLineVisible;
    private float mItemPaddingLeftRight;

    private TextView mEdit_title_tv;
    private View mEdit_line;
    private EditText mEdit_letf_et;
    private TextView mEdit_right_tv;
    private View mContent_ll;
    private LinearLayout mEdit_ll;
    private float mBottomLineTopMargin;
    private final SparseArray<View> mViews = new SparseArray<View>();
    private LinearLayout mEdit_title_ll;
    private TextView mEdit_title_end_tv;
    private CharSequence mTitleEndText;
    private int mTitleEndTextColor;
    private int mInputType;
    private CharSequence mDigits;
    private int mMaxLength;
    public static final int text = 1;
    public static final int number = 2;
    public static final int phone = 3;
    public static final int numberDecimal = 4;
    public static final int textPassword = 5;
    public static final int numberPassword = 6;
    public static final int textPersonName = 7;
    private boolean mTextCursorVisible;

    public EditVItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCustomAttr(context, attrs);
        initView();
    }

    public EditVItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditVItemView(Context context) {
        this(context, null);
    }

    private void initCustomAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditItem);
        // 获取自定义属性资源ID
        if (a != null) {
            mTitleText = a.getText(R.styleable.EditItem_titleText);
            mTitleEndText = a.getText(R.styleable.EditItem_titleEndText);
            mTitleTextColor = a.getColor(R.styleable.EditItem_titleTextColor, context.getResources().getColor(R.color
                    .color_black_1));
            mTitleEndTextColor = a.getColor(R.styleable.EditItem_titleEndTextColor, context.getResources().getColor(R
                    .color
                    .color_red_3));
            mTitleTextSize = a.getDimension(R.styleable.EditItem_titleTextSize, context.getResources()
                    .getDimensionPixelOffset(R.dimen.text_size_14));
            mTitleTextPaddingTop = a.getDimension(R.styleable.EditItem_titleTopMargin, context.getResources()
                    .getDimension(R.dimen.x15));

            mContentEditHintText = a.getText(R.styleable.EditItem_contentEditHintText);
            mContentEditHintTextColor = a.getColor(R.styleable.EditItem_contentEditHintTextColor, context
                    .getResources().getColor(R.color.color_gray_2));
            mContentEditTextSize = a.getDimension(R.styleable.EditItem_contentEditTextSize, context.getResources()
                    .getDimensionPixelOffset(R.dimen.text_size_14));
            mContentEditTopMargin = a.getDimension(R.styleable.EditItem_contentEditTopMargin, 0);


            mContentRightText = a.getText(R.styleable.EditItem_contentRightText);
            mContentRightTextColor = a.getColor(R.styleable.EditItem_contentRightTextColor, context.getResources()
                    .getColor(R.color.color_bule_3));
            mContentRightTextSize = a.getDimension(R.styleable.EditItem_contentRightTextSize, context.getResources()
                    .getDimensionPixelOffset(R.dimen.text_size_14));
            mContentRightTextEndMargin = a.getDimension(R.styleable.EditItem_contentRightTextEndMargin, context
                    .getResources().getDimension(R.dimen.x1));

            mContentRightTextVisible = a.getBoolean(R.styleable.EditItem_contentRightTextVisible, false);
            mBottomLineVisible = a.getBoolean(R.styleable.EditItem_bottomLineVisible, true);
            mBottomLineTopMargin = a.getDimension(R.styleable.EditItem_bottomLineTopMargin, 0);

            mItemPaddingLeftRight = a.getDimension(R.styleable.EditItem_paddingLeftRight, 0);

            mTextCursorVisible = a.getBoolean(R.styleable.EditItem_textCursorVisible, true);
            mInputType = a.getInt(R.styleable.EditItem_inputType, -1);
            mDigits = a.getText(R.styleable.EditItem_digits);
            mMaxLength = a.getInt(R.styleable.EditItem_maxLength, 0);
            a.recycle();
        }
    }

    /**
     * 初始化布局信息
     */
    private void initView() {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        //添加中间内容:weight = 1
        View itemView = View.inflate(getContext(), R.layout.layout_edit_item_view_v, null);
        itemView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));

        addView(itemView);

        mEdit_title_tv = findViewById(R.id.edit_title_tv);
        mEdit_title_end_tv = findViewById(R.id.edit_title_end_tv);
        mEdit_title_ll = findViewById(R.id.edit_title_ll);
        mEdit_letf_et = findViewById(R.id.edit_letf_et);
        mEdit_right_tv = findViewById(R.id.edit_right_tv);
        mEdit_line = findViewById(R.id.edit_line);
        mContent_ll = findViewById(R.id.content_ll);
        mEdit_ll = findViewById(R.id.edit_ll);

        mEdit_ll.setPadding((int) mItemPaddingLeftRight, 0, (int) mItemPaddingLeftRight, 0);

        enableRightText(mContentRightTextVisible);
        enableBottomLine(mBottomLineVisible);

        initTitleLayout();
        setTitleText(String.valueOf(mTitleText));
        setTitleTextColor(mTitleTextColor);
        setTitleTextSize(mTitleTextSize);

        setTitleEndText(String.valueOf(mTitleEndText));
        setTitleEndTextColor(mTitleEndTextColor);

        initContentEditLayout();
        setContentEditHintText(String.valueOf(mContentEditHintText));
        setContentEditHintColor(mContentEditHintTextColor);
        setContentEditSize(mContentEditTextSize);
        setContentEditTextCursorVisible(mTextCursorVisible);

        initContentRightLayout();
        setContentRightText(String.valueOf(mContentRightText));
        setContentRightTextColor(mContentRightTextColor);
        setContentRightTextSize(mContentRightTextSize);

        initBottomLineLayout();
        initEditText();
        initListener();
    }

    private void setContentEditTextCursorVisible(boolean mTextCursorVisible) {
        mEdit_letf_et.setCursorVisible(mTextCursorVisible);
    }

    private void initEditText() {
        switch (mInputType) {
            case text:
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case number:
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case phone:
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case numberDecimal:
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case textPassword:
                mEdit_letf_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case numberPassword:
                mEdit_letf_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                break;
            case textPersonName:
                mEdit_letf_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
        }


        if (!TextUtils.isEmpty(mDigits) && !TextUtils.isEmpty(mDigits.toString())) {
            mEdit_letf_et.setKeyListener(DigitsKeyListener.getInstance(mDigits.toString()));
        }
        if (mMaxLength > 0) mEdit_letf_et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        mEdit_letf_et.setOnTouchListener((View v, MotionEvent event) -> {
            mEdit_letf_et.setFocusable(true);
            mEdit_letf_et.setFocusableInTouchMode(true);
            mEdit_letf_et.requestFocus();
            mEdit_letf_et.setCursorVisible(true);
            return false;
        });
    }

    private void setTitleEndTextColor(int mTitleTextColor) {
        mEdit_title_end_tv.setTextColor(mTitleTextColor);
    }

    private void setTitleEndText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mEdit_title_end_tv.setText(text);
    }

    private void initBottomLineLayout() {
        LinearLayout.LayoutParams layoutParams = (LayoutParams) mEdit_line.getLayoutParams();
        layoutParams.topMargin = (int) mBottomLineTopMargin;
        mEdit_line.setLayoutParams(layoutParams);
    }

    private void initContentRightLayout() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = (int) mContentRightTextEndMargin;
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mEdit_right_tv.setLayoutParams(layoutParams);
    }

    private void initContentEditLayout() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = (int) mContentEditTopMargin;
        mContent_ll.setLayoutParams(layoutParams);
    }

    private void initTitleLayout() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = (int) mTitleTextPaddingTop;
        mEdit_title_ll.setLayoutParams(layoutParams);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount <= 0) {
            return;
        }
        View lastChildView = getChildAt(childCount - 1);

    }

    /**
     * 设置右边文本的颜色
     *
     * @param color color
     */
    private void setContentRightTextColor(int color) {
        mEdit_right_tv.setHintTextColor(color);
    }

    /**
     * 设置左边文本的颜色
     *
     * @param color color
     */
    private void setContentEditHintColor(int color) {
        mEdit_letf_et.setHintTextColor(color);
    }

    /**
     * 设置上面主题文本的颜色
     *
     * @param color color
     */
    private void setTitleTextColor(int color) {
        mEdit_title_tv.setTextColor(color);
    }


    /**
     * 设置左边文本
     *
     * @param text text
     */
    public void setTitleText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mEdit_title_tv.setText(text);
    }

    public void setTitleTextSize(float size) {
        if (size < 0) {
            return;
        }
        mEdit_title_tv.setTextSize(px2sp(size));
    }

    /**
     * 右边文字是否可用
     *
     * @param enable enable
     */
    public void enableRightText(boolean enable) {
        mEdit_right_tv.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置底部线条是否可用
     *
     * @param enable enable
     */
    public void enableBottomLine(boolean enable) {
        mEdit_line.setVisibility(enable ? View.VISIBLE : View.GONE);
    }


    /**
     * 设置右边文本
     *
     * @param resId resId
     */
    public void setContentRightText(int resId) {
        if (resId < 0) {
            mEdit_right_tv.setText("");
            return;
        }
        mEdit_right_tv.setText(getResources().getString(resId));
    }

    /**
     * 设置左边文本
     */
    public void setContentEditHintText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mEdit_letf_et.setHint(text);
    }

    /**
     * 设置左边文本大小
     */
    public void setContentEditSize(float size) {
        if (size < 0) {
            return;
        }
        mEdit_letf_et.setTextSize(px2sp(size));
    }

    /**
     * 设置右边文本
     */
    public void setContentRightText(String text) {
        if (TextUtils.isEmpty(text) || "null".equals(text)) {
            text = "";
        }
        mEdit_right_tv.setText(text);
    }

    /**
     * 设置右边文本大小
     */
    public void setContentRightTextSize(float size) {
        if (size < 0) {
            return;
        }
        mEdit_right_tv.setTextSize(px2sp(size));
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
//        mContainer.removeAllViews();
//        mContainer.addView(contentView);
    }

    /**
     * 获取中间容器填充的View
     *
     * @return contentView
     */
    public View getContentView() {
        return mContentView;
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return T
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public EditText getEditTextView() {
        return mEdit_letf_et;
    }

    public void setEditTextView(String text) {
        if (text != null) {
            mEdit_letf_et.setText(text);
        }
    }

    public void setEditTextInputType(int type) {
        if (mEdit_letf_et != null) {
            mEdit_letf_et.setInputType(type);
        }
    }

    public void setmEditTextViewFocuseAble(boolean enable) {
        if (mEdit_letf_et != null) {
            mEdit_letf_et.setFocusable(enable);
        }
    }

    public void setOnEditClickListener(OnClickListener listener) {
        mEdit_letf_et.setOnClickListener(listener);
    }


    public String getEditTextString() {
        return mEdit_letf_et.getText().toString().trim();
    }

    public int px2sp(float pxValue) {
        float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
