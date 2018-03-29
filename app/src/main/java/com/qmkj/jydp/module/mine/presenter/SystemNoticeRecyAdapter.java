package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;

import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemNoticeRecyAdapter extends BaseRecylerAdapter {
    private final Context mContext;

    public SystemNoticeRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {

    }
}
