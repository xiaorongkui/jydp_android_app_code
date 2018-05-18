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

public class ExchangeSoldPriceRecAdapter extends BaseRecylerAdapter<ExchangeCenterRes
        .TransactionPendOrderSellListBean> {

    private final List datas;
    @BindView(R.id.price_status_tv)
    TextView priceStatusTv;
    @BindView(R.id.price_amount_tv)
    TextView priceAmountTv;
    @BindView(R.id.price_total_money_tv)
    TextView priceTotalMoneyTv;

    public ExchangeSoldPriceRecAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
    }


    @Override
    protected void convert(BaseRecyclerViewHolder helper, ExchangeCenterRes.TransactionPendOrderSellListBean item,
                           int position) {
        priceStatusTv.setText(CommonUtil.getString(R.string.sold) + (datas.size() - position));
        if (item == null) return;
        priceTotalMoneyTv.setText(NumberUtil.format2Point(item.getPendingPrice()));
        priceAmountTv.setText(NumberUtil.format2Point(item.getPendingNumber()));
    }
}
