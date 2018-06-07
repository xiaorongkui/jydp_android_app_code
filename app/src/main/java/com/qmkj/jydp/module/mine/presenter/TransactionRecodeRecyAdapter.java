package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.AccountRecordRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.SelectorFactory;
import com.qmkj.jydp.util.StringUtil;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:成交记录适配器
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
        TextView transaction_recode_name_tv = (TextView) helper.getView(R.id.transaction_recode_name_tv); //名称
        TextView order_num = (TextView) helper.getView(R.id.transsaction_order_num_tv); //订单号
        TextView recode_status = (TextView)helper.getView(R.id.transaction_recode_status_tv); //买入
        TextView amount = (TextView)helper.getView(R.id.entrustment_amount_tv); //数量
        TextView price = (TextView)helper.getView(R.id.transaciton_price_tv); //单价
        TextView deal_amount = (TextView)helper.getView(R.id.deal_amount_tv); //手续费
        TextView total_price = (TextView)helper.getView(R.id.transaction_total_price_tv); //总价
        TextView actual_account = (TextView)helper.getView(R.id.transaction_actual_account_tv); //实际到帐
        TextView time = (TextView)helper.getView(R.id.transaction_time_tv); //完成时间

        TextView transaction_actual_account_title_tv = (TextView)helper.getView(R.id.transaction_actual_account_title_tv); //实际到帐

        order_num.setText(item.getOrderNo());
        String text =null;
        //	收支类型,1：买入，2：卖出，3：撤销
        switch (item.getPaymentType()){
            case 1:text = "买入";
                recode_status.setBackgroundResource(R.drawable.shape_buy_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_red_3));
                recode_status.setText(CommonUtil.getString(R.string.buy_1));
                transaction_actual_account_title_tv.setText(CommonUtil.getString(R.string.actual_account2_xt));

                break;
            case 2: text = "卖出";
                recode_status.setBackgroundResource(R.drawable.shape_shell_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
                transaction_actual_account_title_tv.setText(CommonUtil.getString(R.string.actual_account_xt));
                recode_status.setText(CommonUtil.getString(R.string.sell));
                break;
            case 3: text = "撤销";
                recode_status.setBackgroundResource(R.drawable.shape_cancel_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_gray_2));
                recode_status.setText(CommonUtil.getString(R.string.cancel_order));
                break;
        }
        recode_status.setText(text);
        transaction_recode_name_tv.setText(item.getCurrencyName());
        if(!StringUtil.isNull(item.getCurrencyNumber())){
            amount.setText(NumberUtil.doubleFormat(Double.parseDouble(item.getCurrencyNumber()),4)+"");
        }else {
            amount.setText("0");
        }
        if(!StringUtil.isNull(item.getTransactionPrice())){
            price.setText(NumberUtil.doubleFormat(Double.parseDouble(item.getTransactionPrice()),4)+"");
        }else {
            amount.setText("0");
        }
        if(!StringUtil.isNull(item.getFeeForWap())){
            deal_amount.setText(NumberUtil.doubleFormat(Double.parseDouble(item.getFeeForWap()),4)+"");
        }else {
            deal_amount.setText("0");
        }
        if(!StringUtil.isNull(item.getCurrencyTotalPrice())){
            total_price.setText(NumberUtil.doubleFormat(Double.parseDouble(item.getCurrencyTotalPrice()),4)+"");
        }else {
            total_price.setText("0");
        }
        if(!StringUtil.isNull(item.getActualPriceForWap())&&Double.parseDouble(item.getActualPriceForWap())!=0){
            actual_account.setText(item.getActualPriceForWap()+"");
            transaction_actual_account_title_tv.setVisibility(View.VISIBLE);
            actual_account.setVisibility(View.VISIBLE);
        }else {
            transaction_actual_account_title_tv.setVisibility(View.INVISIBLE);
            actual_account.setVisibility(View.INVISIBLE);
        }
        time.setText(DateUtil.longToTimeStr(item.getAddTime(),DateUtil.dateFormat2));
    }
}
