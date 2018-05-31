package com.qmkj.jydp.module.exchangecenter.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.bean.response.ExchangeEntrustRecodeRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.NumberUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:委托记录
 */

public class EntrustRecodeRecAdapter extends BaseRecycleAdapter<ExchangeCenterRes.TransactionPendOrderListBean> {

    private final List datas;

    public EntrustRecodeRecAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
    }


    @Override
    protected void convert(BaseRecyclerViewHolder helper, ExchangeCenterRes.TransactionPendOrderListBean item,
                           int position) {
        helper.addOnClickListener(R.id.entrust_cancel_tv);
        TextView entrustStatusTv = helper.getView(R.id.entrust_status_tv);
        TextView entrustPriceTv = helper.getView(R.id.entrust_price_tv);
        TextView entrustAmountTv = helper.getView(R.id.entrust_amount_tv);
        TextView entrustExchangeTv = helper.getView(R.id.entrust_exchange_tv);
        TextView entrustCancelTv = helper.getView(R.id.entrust_cancel_tv);

        helper.getView(R.id.common_line).setVisibility(datas.size() - 1 == position ? View.INVISIBLE : View
                .VISIBLE);
        if (item == null) return;
        int paymentType = item.getPaymentType();
        switch (paymentType) {
            case 1://买入
                entrustStatusTv.setText(CommonUtil.getString(R.string.buy_input));
                entrustStatusTv.setTextColor(CommonUtil.getColor(R.color.color_red_3));

                break;
            case 2://卖出
                entrustStatusTv.setText(CommonUtil.getString(R.string.sold_out));
                entrustStatusTv.setTextColor(CommonUtil.getColor(R.color.colorGreen_6));
                break;
        }
        entrustPriceTv.setText(NumberUtil.format2Point(item.getPendingPrice()));
        entrustAmountTv.setText(NumberUtil.format4Point(item.getPendingNumber()));
        entrustExchangeTv.setText(NumberUtil.format2Point(item.getDealNumber()));
    }
}
