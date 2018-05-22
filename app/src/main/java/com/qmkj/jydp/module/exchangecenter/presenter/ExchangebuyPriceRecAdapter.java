package com.qmkj.jydp.module.exchangecenter.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.NumberUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangebuyPriceRecAdapter extends BaseRecylerAdapter<ExchangeCenterRes.TransactionPendOrderBuyListBean> {

    public ExchangebuyPriceRecAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, ExchangeCenterRes.TransactionPendOrderBuyListBean item, int
            position) {
        TextView priceStatusTv = helper.getView(R.id.price_status_tv);
        TextView priceAmountTv = helper.getView(R.id.price_amount_tv);
        TextView priceTotalMoneyTv = helper.getView(R.id.price_total_money_tv);
        priceStatusTv.setText(CommonUtil.getString(R.string.buy) + (position + 1));
        priceStatusTv.setTextColor(CommonUtil.getColor(R.color.color_red_3));
        if (item == null) return;
        priceTotalMoneyTv.setText(NumberUtil.format2Point(item.getPendingPrice()));
        priceAmountTv.setText(NumberUtil.format2Point(item.getPendingNumber()));

    }
}
