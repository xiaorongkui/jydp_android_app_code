package com.qmkj.jydp.base;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/29
 * email：dovexiaoen@163.com
 * description:recycleView的封装
 */

public abstract class BaseRecycleAdapter<T> extends BaseQuickAdapter<T, BaseRecyclerViewHolder> {

    private Unbinder unbinder;

    public BaseRecycleAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        init();
    }


    public BaseRecycleAdapter(@Nullable List<T> data) {
        super(data);
        init();
    }

    public BaseRecycleAdapter(int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, T item) {
        int position = helper.getAdapterPosition();
        convert(helper, item, position);
    }

    protected abstract void convert(BaseRecyclerViewHolder helper, T item, int position);

    public void setListData(List<T> list) {
        setNewData(list);
    }

    /**
     * 更新某个位置item
     */
    public void update(int position) {
        if (position != -1) {
            notifyItemChanged(position + getHeaderLayoutCount());
        }
    }

    public void remove(T t) {
        if (t != null && mData.contains(t)) {
            remove(mData.indexOf(t));
        }
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.mLayoutResId == 0) {
            this.mLayoutResId = getLayoutResId(viewType);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    /**
     * 需要子类去实现
     *
     * @param viewType the view type
     * @return the layout res id
     */
    protected int getLayoutResId(int viewType) {
        return 0;
    }

    protected void init() {
        //关闭item执行动画
        openLoadAnimation(view -> new Animator[0]);
        //设置打开动画并前10个数据不用执行动画
        setNotDoAnimationCount(10);
    }

    @Override
    protected BaseRecyclerViewHolder createBaseViewHolder(View view) {
        BaseRecyclerViewHolder baseViewHolder = super.createBaseViewHolder(view);
        unbinder = ButterKnife.bind(this, view);
        return baseViewHolder;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
