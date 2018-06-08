package com.qmkj.jydp.module.exchangecenter.presenter;

import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeSoldPriceRecAdapter extends BaseRecycleAdapter<ExchangeCenterRes
        .TransactionPendOrderSellListBean> {

    private final List datas;

    public ExchangeSoldPriceRecAdapter(List<ExchangeCenterRes.TransactionPendOrderSellListBean> datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
    }


    @Override
    protected void convert(BaseRecyclerViewHolder helper, ExchangeCenterRes.TransactionPendOrderSellListBean item,
                           int position) {
        TextView priceStatusTv = helper.getView(R.id.price_status_tv);
        TextView priceAmountTv = helper.getView(R.id.price_amount_tv);
        TextView priceTotalMoneyTv = helper.getView(R.id.price_total_money_tv);
        priceStatusTv.setText(ResourcesManager.getString(R.string.sold) + (datas.size() - position));
        if (item == null) return;
        LogUtil.i("卖item=" + item.toString());
        priceTotalMoneyTv.setText(NumberUtil.format2Point(item.getPendingPrice()));
        priceAmountTv.setText(NumberUtil.format4Point(item.getRestNumber()));
    }
}
