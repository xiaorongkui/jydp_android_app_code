package com.qmkj.jydp.module.home.presenter;

import android.content.Context;

import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:币种选择
 */

public class CurrencyRecyAdapter extends BaseRecylerAdapter {
    private final List<String> datas;
    private final Context mContext;

    public CurrencyRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {

    }
}
