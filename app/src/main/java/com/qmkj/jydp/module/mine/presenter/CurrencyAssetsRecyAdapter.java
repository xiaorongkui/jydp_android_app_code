package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.math.BigDecimal;


/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class CurrencyAssetsRecyAdapter extends XBaseAdapter<CurrencyAssetsRes.UserCurrencyAssetsBean> {
    private final SelectorFactory.ShapeSelector shapeSelector;

    public CurrencyAssetsRecyAdapter(Context context) {
        super(context);
        shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_3))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.mine_currency_assets_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, CurrencyAssetsRes.UserCurrencyAssetsBean item) {
        TextView mine_currency_assets_item_go_exchange_tv = helper.getView(R.id
                .mine_currency_assets_item_go_exchange_tv);

        TextView mine_assets_name_tv = helper.getView(R.id.mine_assets_name_tv);
        TextView currency_total_assets_tv = helper.getView(R.id.currency_total_assets_tv);
        TextView available_amount_tv = helper.getView(R.id.available_amount_tv);
        TextView frozen_amount_tv = helper.getView(R.id.frozen_amount_tv);

        helper.addOnClickListener(R.id.mine_currency_assets_item_go_exchange_tv);
        mine_currency_assets_item_go_exchange_tv.setBackground(shapeSelector.create());

        mine_assets_name_tv.setText(item.getCurrencyName());
        currency_total_assets_tv.setText(BigDecimal.valueOf(Double.parseDouble(item.getTotalCurrencyAssets())) + "");
        available_amount_tv.setText(BigDecimal.valueOf(Double.parseDouble(item.getCurrencyNumber())) + "");
        frozen_amount_tv.setText(BigDecimal.valueOf(Double.parseDouble(item.getCurrencyNumberLock())) + "");
    }
}
