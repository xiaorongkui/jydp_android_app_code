package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;

import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class HelpCenterRecyAdapter extends BaseRecycleAdapter {
    private final Context mContext;

    public HelpCenterRecyAdapter(Context context, List datas, int layoutId) {

        super(layoutId, datas);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {

    }
}
