package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class CurrencyWithDrawRecodeRecyAdapter extends BaseRecycleAdapter {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public CurrencyWithDrawRecodeRecyAdapter(Context context, List datas, int layoutId) {

        super(layoutId, datas);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {

    }
}
