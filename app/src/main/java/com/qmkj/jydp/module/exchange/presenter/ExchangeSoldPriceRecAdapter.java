package com.qmkj.jydp.module.exchange.presenter;

import android.content.Context;

import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.common.CommonRecylerViewHolder;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeSoldPriceRecAdapter extends BaseRecylerAdapter {

    public ExchangeSoldPriceRecAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected boolean isNeedInflateToParcent() {
        return false;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {

    }
}
