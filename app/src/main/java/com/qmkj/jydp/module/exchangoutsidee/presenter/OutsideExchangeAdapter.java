package com.qmkj.jydp.module.exchangoutsidee.presenter;

import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * @author wujiangming
 * @date 2018/4/23
 * @desc
 */

public class OutsideExchangeAdapter extends BaseRecylerAdapter {

    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x2))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_red_5))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_red_6));

    public OutsideExchangeAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
        helper.addOnClickListener(R.id.exchange_outside_go_exchange_tv).addOnClickListener(R.id
                .exchange_outside_buy_tv);

        TextView exchange_outside_buy_tv = helper.getView(R.id.exchange_outside_buy_tv);
        TextView exchange_outside_go_exchange_tv = helper.getView(R.id.exchange_outside_go_exchange_tv);
        shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x2))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_red_5))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_red_6));
        exchange_outside_buy_tv.setBackground(shapeSelector.create());

        shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_3))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        exchange_outside_go_exchange_tv.setBackground(shapeSelector.create());

    }

}
