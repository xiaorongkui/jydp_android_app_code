package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.ui.widget.viewbean.IRollItem;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义轮播图
 */
public class AutoRollLayout extends RelativeLayout implements OnPageChangeListener {
    private static final long ROLL_TIME = 2000;
    private final Context mContext;

    ViewPager vp;

    LinearLayout dotContainer;
    private int dotDrawableRes = R.drawable.arl_dot_selector;
    private int dotWidth = CommonUtil.dp2px(7);
    private int dotMargin = CommonUtil.dp2px(9);
    private OnAutoItemClickListener listener;

    public AutoRollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public AutoRollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoRollLayout(Context context) {
        this(context, null);
    }

    private void init() {
        View.inflate(getContext(), R.layout.arl_layout, this);
        vp = (ViewPager) findViewById(R.id.arl_vp);
        dotContainer = (LinearLayout) findViewById(R.id.arl_dot_container);
        vp.addOnPageChangeListener(this);
        vp.setOnTouchListener(touchListener);
        gestureDetector = new GestureDetector(getContext(), gestureListener);
    }

    private List<? extends IRollItem> items;

    public void setDotBackground(int resId, int dotWidth, int dotMargin) {
        this.dotDrawableRes = resId;
        this.dotWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dotWidth,
                getResources().getDisplayMetrics());
        this.dotMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dotMargin,
                getResources().getDisplayMetrics());
    }

    public void setItems(List<? extends IRollItem> items) {
        this.items = items;
        vp.setAdapter(adapter);
        dotContainer.removeAllViews();
        addDots();
        onPageSelected(0);
        adapter.notifyDataSetChanged();
    }

    static Handler handler = new Handler();
    private boolean autoRoll;

    public void setAutoRoll(boolean autoRoll) {
        Log.e("setAutoRoll", "" + autoRoll);
        this.autoRoll = autoRoll;
        handler.postDelayed(rollRunnable, ROLL_TIME);
    }

    public boolean getAutoRoll() {
        return autoRoll;
    }

    private Runnable rollRunnable = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(this);
            if (!autoRoll) {
                return;
            }
            if (!isTouching) {
                goNextPager();
            }
            handler.postDelayed(this, ROLL_TIME);
        }

    };

    public int getCurrentItemIndex() {
        return vp.getCurrentItem();
    }

    private PagerAdapter adapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView iv = (ImageView) object;
            container.removeView((View) iv);

            iv.setImageBitmap(null);
            cache.add(iv);
        }

        List<ImageView> cache = new ArrayList<ImageView>();

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (cache.isEmpty()) {
                ImageView iv = new ImageView(getContext());
                iv.setScaleType(ScaleType.FIT_XY);
                cache.add(iv);
            }
            ImageView iv = cache.remove(0);

            String imagetUrl = items.get(position).getImagetUrl();
            LogUtil.i("AutoRollLayout imagetUrl=" + imagetUrl);

            if (!TextUtils.isEmpty(imagetUrl)) {
                GlideApp.with(mContext).load(imagetUrl).placeholder(R.mipmap.logo)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
            } else {
                GlideApp.with(mContext).load(items.get(position).getResId()).placeholder(R.mipmap
                        .logo).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
            }
            iv.setOnClickListener(v -> {
                if (listener != null) {
                    listener.OnAutoItemClick(v, position);
                }
            });
            container.addView(iv);
            return iv;
        }

    };

    private void addDots() {
        if (items == null || items.isEmpty()) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            View dotView = new View(getContext());

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotWidth, dotWidth);
            lp.setMargins(0, 0, dotMargin, 0);

            dotView.setLayoutParams(lp);
            dotView.setBackgroundResource(dotDrawableRes);

            dotContainer.addView(dotView);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        if (items == null || items.isEmpty()) {
            return;
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            dotContainer.getChildAt(i).setEnabled(position != i);
        }

    }

    boolean toRight = true;

    private void goNextPager() {
        if (vp.getCurrentItem() == 0) {
            toRight = true;
        } else if (vp.getCurrentItem() == adapter.getCount() - 1) {
            toRight = false;
        }
        int currentIndex = vp.getCurrentItem();

        int nextIndex = toRight ? currentIndex + 1 : currentIndex - 1;
        vp.setCurrentItem(nextIndex);

    }

    boolean isTouching = false;
    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            LogUtil.i("onTouch= ；isTouching=" + event.getAction());
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    float startX = event.getRawX();
                    float startY = event.getRawY();
                    isTouching = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    isTouching = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("onTouch", "UP");
                    isTouching = true;
                    break;
            }
            // v.onTouchEvent(event);
            // return true;

            return false;
        }
    };

    private OnGestureListener gestureListener = new OnGestureListener() {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // Toast.makeText(getContext(), "点击了", 0).show();
            AutoRollLayout.this.performClick();
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
    };

    private GestureDetector gestureDetector;

    public void destory() {
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = getLayoutParams();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (widthSize * 7.6 / 16);
        params.height = height;
        //        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int getMesureHeight() {
        return getLayoutParams().height;
    }


    public void setOnAutoItemClickListener(OnAutoItemClickListener listenser) {
        listener = listenser;
    }

    public interface OnAutoItemClickListener {
        void OnAutoItemClick(View view, int position);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //子View的所有父ViewGroup都会跳过onInterceptTouchEvent的回调
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaY) > Math.abs(deltaX) + 5) {//水平滑动，使得父类可以执行onInterceptTouchEvent
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
