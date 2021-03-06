package com.qmkj.jydp.module.exchangoutside.presenter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.response.OutSideExchangeRes;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * @author wujiangming
 * @date 2018/4/23
 */

public class OutsideExchangeAdapter extends BaseRecycleAdapter<OutSideExchangeRes.OtcTransactionPendOrderListBean> {

    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x2))
            .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_red_5))
            .setStrokeWidth((int) ResourcesManager.getDimen(R.dimen.x1))
            .setDefaultBgColor(ResourcesManager.getColor(R.color.color_red_6));

    public OutsideExchangeAdapter(int layoutResId, @Nullable List<OutSideExchangeRes.OtcTransactionPendOrderListBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, OutSideExchangeRes.OtcTransactionPendOrderListBean item,
                           int position) {

        TextView outsideExchangeCurrencyNameTv = helper.getView(R.id.outside_exchange_currency_name_tv);
        TextView exchangeOutsideBuyTv = helper.getView(R.id.exchange_outside_buy_tv);
        TextView exchangeOutsideGoExchangeTv = helper.getView(R.id.exchange_outside_go_exchange_tv);
        TextView dealerNameTv = helper.getView(R.id.dealer_name_tv);
        TextView scaleTv = helper.getView(R.id.scale_tv);
        TextView exchangeOutsideTradeQuotasTv = helper.getView(R.id.exchange_outside_trade_quotas_tv);
        TextView exchangeOutsideRegionTv = helper.getView(R.id.exchange_outside_region_tv);

        helper.addOnClickListener(R.id.exchange_outside_go_exchange_tv);

        shapeSelector.setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x12))
                .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_bule_3))
                .setDefaultBgColor(ResourcesManager.getColor(R.color.color_white_1));
        exchangeOutsideGoExchangeTv.setBackground(shapeSelector.create());

        outsideExchangeCurrencyNameTv.setText(item.getCurrencyName());
        dealerNameTv.setText(item.getDealerName());
        scaleTv.setText(String.format("%sUSD", item.getPendingRatio()));
        exchangeOutsideTradeQuotasTv.setText(String.format("%s～%s", item.getMinNumber(), item.getMaxNumber()));
        exchangeOutsideRegionTv.setText(item.getArea());

        switch (item.getOrderType()) {
            case 2://1：购买
                shapeSelector.setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x2))
                        .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_green_4))
                        .setDefaultBgColor(ResourcesManager.getColor(R.color.color_green_6));
                exchangeOutsideBuyTv.setBackground(shapeSelector.create());
                exchangeOutsideBuyTv.setText(ResourcesManager.getString(R.string.buy_2));
                exchangeOutsideBuyTv.setTextColor(ResourcesManager.getColor(R.color.color_green_4));
                exchangeOutsideGoExchangeTv.setText(ResourcesManager.getString(R.string.go_sell));
                break;
            case 1://2：卖出
                shapeSelector.setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x2))
                        .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_red_3))
                        .setDefaultBgColor(ResourcesManager.getColor(R.color.color_red_6));
                exchangeOutsideBuyTv.setBackground(shapeSelector.create());
                exchangeOutsideBuyTv.setText(ResourcesManager.getString(R.string.sell));
                exchangeOutsideBuyTv.setTextColor(ResourcesManager.getColor(R.color.color_red_3));
                exchangeOutsideGoExchangeTv.setText(ResourcesManager.getString(R.string.go_buy));
                break;
        }
    }

}
