package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;

import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.common.CommonRecylerViewHolder;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class MineRecyAdapter extends BaseRecylerAdapter {
    private final List<String> datas;
    private final Context mContext;

    public MineRecyAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {

    }

}
