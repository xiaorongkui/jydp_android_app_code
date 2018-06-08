package com.qmkj.jydp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.util.LogUtil;

public class JYDPSwipeRefreshLayout extends SuperSwipeRefreshLayout {
    private Context context;
    private ImageView iv_pull_refresh_in;
    private ImageView iv_pull_refresh_out;
    private TextView swipe_refresh_header_tv;
    private View viewRoot;
    private int mTouchSlop;
    private TextView swipe_refresh_footer_tv;


    public JYDPSwipeRefreshLayout(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public JYDPSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setHeaderView();
        setFooterView();
    }

    public void setFooterView() {
        viewRoot = LayoutInflater.from(context).inflate(R.layout.swipe_refresh_layout_footer, null);
        swipe_refresh_footer_tv = viewRoot.findViewById(R.id.swipe_refresh_footer_tv);
        super.setFooterView(viewRoot);
        setFooterViewBackgroundColor(ResourcesManager.getColor(R.color.item_line));
    }

    public void setHeaderView() {
        viewRoot = LayoutInflater.from(context).inflate(R.layout.swipe_refresh_layout_header, null);
        iv_pull_refresh_in = viewRoot.findViewById(R.id.iv_pull_refresh_in);
        iv_pull_refresh_out = viewRoot.findViewById(R.id.iv_pull_refresh_out);
        swipe_refresh_header_tv = viewRoot.findViewById(R.id.swipe_refresh_header_tv);
        super.setHeaderView(viewRoot);
        setHeaderViewBackgroundColor(ResourcesManager.getColor(R.color.item_line));
    }


    public boolean hcenable;

    public void setOnHCPullRefreshListener(final OnHCPullRefreshListener listener) {
        super.setOnPullRefreshListener(new OnPullRefreshListener() {

            @Override
            public void onRefresh() {
                setEnabled(false);
                swipe_refresh_header_tv.setText(ResourcesManager.getString(R.string.pull_to_refresh_1));
                listener.onRefresh();
                LogUtil.i("onRefresh=======>");
            }

            @Override
            public void onPullDistance(int distance) {
                LogUtil.i("distance=" + distance);
                listener.onPullDistance(distance);
            }

            @Override
            public void onPullEnable(boolean enable) {
                hcenable = enable;
                LogUtil.i("enable========" + enable);
                swipe_refresh_header_tv.setText(ResourcesManager.getString(enable ? R.string
                        .pull_to_refresh_2 : R.string.pull_to_refresh_3));
                listener.onPullEnable(enable);
            }
        });
    }

    public void setOnHCPushLoadMoreListener(final OnHCPushLoadMoreListener listener) {
        super.setOnPushLoadMoreListener(new OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                setEnabled(false);
                listener.onLoadMore();
                swipe_refresh_footer_tv.setText(ResourcesManager.getString(R.string.pull_to_refresh_1));
            }

            @Override
            public void onPushDistance(int distance) {
                listener.onPushDistance(distance);
            }

            @Override
            public void onPushEnable(boolean enable) {
                hcenable = enable;
                listener.onPushEnable(enable);
                swipe_refresh_footer_tv.setText(ResourcesManager.getString(enable ? R.string
                        .push_to_refresh_2 : R.string.push_to_refresh_3));
            }
        });
    }

    public interface OnHCPullRefreshListener {
        void onRefresh();

        void onPullDistance(int distance);

        void onPullEnable(boolean enable);
    }

    public interface OnHCPushLoadMoreListener {
        void onLoadMore();

        void onPushDistance(int distance);

        void onPushEnable(boolean enable);
    }

    private int downX;
    private int downY;
    private int mLastX;
    private int mLastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getX();
                downY = (int) e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = downX - mLastX;
                int deltaY = downY - mLastY;

                if (Math.abs(deltaY) > Math.abs(deltaX) + mTouchSlop) {
                    if (isRefreshing() && hcenable) {
                        return true;
                    }
                    if (isLoadMore() && hcenable) {
                        return true;
                    }
                }
        }
        mLastX = downX;
        mLastY = downY;
        return super.onInterceptTouchEvent(e);
    }

    public void setHCRefreshing(boolean refreshing) {
        setEnabled(!refreshing);
        super.setRefreshing(refreshing);
    }

    public void setHCLoadMore(boolean loadMore) {
        setEnabled(!loadMore);
        super.setLoadMore(loadMore);
    }
}
