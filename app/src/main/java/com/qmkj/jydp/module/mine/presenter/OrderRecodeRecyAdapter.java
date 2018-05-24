package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class OrderRecodeRecyAdapter extends XBaseAdapter<OrderRecodeRes.TransactionPendOrderRecordListBean> {

    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public OrderRecodeRecyAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.mine_order_reocde_item;
    }


    @Override
    protected void convert(XBaseViewHolder helper, OrderRecodeRes.TransactionPendOrderRecordListBean item) {

    }
}
