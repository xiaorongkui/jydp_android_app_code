package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.AccountRecordRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class TransactionRecodeRecyAdapter extends XBaseAdapter<AccountRecordRes.DealRecordListBean> {

    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public TransactionRecodeRecyAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutResId(int viewType) {

        return R.layout.mine_transaction_reocde_item;
    }


    @Override
    protected void convert(XBaseViewHolder helper, AccountRecordRes.DealRecordListBean item) {
        TextView order_num = (TextView) helper.getView(R.id.transsaction_order_num_tv); //订单号
        TextView recode_status = (TextView)helper.getView(R.id.transaction_recode_status_tv); //买入
        TextView amount = (TextView)helper.getView(R.id.entrustment_amount_tv); //数量
        TextView price = (TextView)helper.getView(R.id.transaciton_price_tv); //单价
        TextView deal_amount = (TextView)helper.getView(R.id.deal_amount_tv); //手续费
        TextView total_price = (TextView)helper.getView(R.id.transaction_total_price_tv); //总价
        TextView actual_account = (TextView)helper.getView(R.id.transaction_actual_account_tv); //实际到帐
        TextView time = (TextView)helper.getView(R.id.transaction_time_tv); //完成时间

        order_num.setText(item.getOrderNo());
        String text =null;
        switch (item.getPaymentType()){
            case 1:text = "买入";
                recode_status.setTextColor(Color.RED);
                break;
            case 2: text = "卖出";
                recode_status.setTextColor(Color.GREEN);
                break;
            case 3: text = "撤销";
                recode_status.setTextColor(Color.GRAY);
                break;
        }
        recode_status.setText(text);

        amount.setText(item.getCurrencyNumber()+"");
        price.setText(item.getTransactionPrice()+"");
        deal_amount.setText(item.getFee()+"");
        total_price.setText(item.getCurrencyTotalPrice()+"");
        actual_account.setText(item.getActualPrice()+"");
        time.setText(DateUtil.longToTimeStr(time.getDrawingTime(),DateUtil.dateFormat2));
    }
}
