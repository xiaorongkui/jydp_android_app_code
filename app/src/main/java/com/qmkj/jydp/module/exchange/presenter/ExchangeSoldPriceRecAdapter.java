package com.qmkj.jydp.module.exchange.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.common.CommonRecylerViewHolder;
import com.qmkj.jydp.util.CommonUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeSoldPriceRecAdapter extends BaseRecylerAdapter {

    private final List datas;
    @BindView(R.id.price_status_tv)
    TextView priceStatusTv;
    @BindView(R.id.price_amount_tv)
    TextView priceAmountTv;
    @BindView(R.id.price_total_money_tv)
    TextView priceTotalMoneyTv;

    public ExchangeSoldPriceRecAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    protected boolean isNeedInflateToParcent() {
        return true;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {
        priceStatusTv.setText(CommonUtil.getString(R.string.sold) + (datas.size() - position));
    }
}
