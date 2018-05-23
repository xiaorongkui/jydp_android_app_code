package com.qmkj.jydp.base;

import android.animation.Animator;
import android.content.Context;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * @author neo.duan
 * @date 2018/1/5 12:05
 * @desc 再次封装BaseViewHolder
 */
public abstract class XBaseAdapter<T> extends BaseQuickAdapter<T, XBaseViewHolder> {

    //holder.addOnClickListener();需要注意顺序，先add子View的Id，再add父View的id，否则父类会覆盖点击子View事件

    public XBaseAdapter(Context context) {
        super(null);
        this.mContext = context;
        this.mLayoutResId = getLayoutResId(0);

        //关闭item执行动画
        openLoadAnimation(view -> new Animator[0]);
        //设置打开动画并前10个数据不用执行动画
        setNotDoAnimationCount(10);
    }

    public void update(List<T> list) {
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

    @Override
    public XBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mLayoutResId = getLayoutResId(viewType);
        return super.onCreateViewHolder(parent, viewType);
    }

    protected abstract int getLayoutResId(int viewType);
}
