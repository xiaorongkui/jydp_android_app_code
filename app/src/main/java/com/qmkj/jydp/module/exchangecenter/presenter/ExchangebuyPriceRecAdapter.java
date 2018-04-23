package com.qmkj.jydp.module.exchangecenter.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangebuyPriceRecAdapter extends BaseRecylerAdapter {
    private final List datas;
    @BindView(R.id.price_status_tv)
    TextView priceStatusTv;
    @BindView(R.id.price_amount_tv)
    TextView priceAmountTv;
    @BindView(R.id.price_total_money_tv)
    TextView priceTotalMoneyTv;

    public ExchangebuyPriceRecAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
        priceStatusTv.setText(CommonUtil.getString(R.string.buy) + (datas.size() - position));
        priceStatusTv.setTextColor(CommonUtil.getColor(R.color.colorRed_4));
    }
}
