package com.qmkj.jydp.module.exchangecenter.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
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

public class ExchangebuyPriceRecAdapter extends BaseRecycleAdapter<ExchangeCenterRes.TransactionPendOrderBuyListBean> {
    @BindView(R.id.price_status_tv)
    TextView priceStatusTv;
    @BindView(R.id.price_amount_tv)
    TextView priceAmountTv;
    @BindView(R.id.price_total_money_tv)
    TextView priceTotalMoneyTv;

    public ExchangebuyPriceRecAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, ExchangeCenterRes.TransactionPendOrderBuyListBean item, int
            position) {
        priceStatusTv.setText(CommonUtil.getString(R.string.buy) + (position + 1));
        if (item == null) return;

        priceStatusTv.setTextColor(CommonUtil.getColor(R.color.color_red_3));
        priceTotalMoneyTv.setText(NumberUtil.format2Point(item.getPendingPrice()));
        priceAmountTv.setText(NumberUtil.format2Point(item.getPendingNumber()));

    }
}
