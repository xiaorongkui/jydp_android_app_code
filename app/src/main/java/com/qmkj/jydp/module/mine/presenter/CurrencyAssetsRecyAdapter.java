package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class CurrencyAssetsRecyAdapter extends BaseRecylerAdapter {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector;

    public CurrencyAssetsRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.mContext = context;
        shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_3))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {

        TextView mine_currency_assets_item_go_exchange_tv = helper.getView(R.id
                .mine_currency_assets_item_go_exchange_tv);
        mine_currency_assets_item_go_exchange_tv.setBackground(shapeSelector.create());
    }
}
