package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.OtcDealRecordRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideExchangeRecodeRecyAdapter extends XBaseAdapter<OtcDealRecordRes.OtcTransactionUserDealListBean> {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public OutSideExchangeRecodeRecyAdapter(Context context) {

        super(context);
        this.mContext = context;

    }


    @Override
    protected int getLayoutResId(int viewType) {

        return R.layout.mine_outside_exchange_recode_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, OtcDealRecordRes.OtcTransactionUserDealListBean item) {
        helper.addOnClickListener(R.id.outside_exchange_recode_see_detail_tv);
        helper.addOnClickListener(R.id.outside_exchange_recode_comfirm_receivables_tv);
        TextView outside_exchange_recode_see_detail_tv = helper.getView(R.id.outside_exchange_recode_see_detail_tv);
        TextView outside_exchange_recode_comfirm_receivables_tv = helper.getView(R.id
                .outside_exchange_recode_comfirm_receivables_tv);

        shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        outside_exchange_recode_see_detail_tv.setBackground(shapeSelector.create());

        shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_3))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        outside_exchange_recode_comfirm_receivables_tv.setBackground(shapeSelector.create());


        TextView order_num = helper.getView(R.id.outside_exchange_recode_order_num_tv);//交易订单号
        TextView name = helper.getView(R.id.outside_exchange_recode_name_tv);//名称
        TextView recode_status = helper.getView(R.id.outside_exchange_recode_status_tv);//成交状态
        TextView amount = helper.getView(R.id.outside_exchange_recode_amount_tv);//数量
        TextView money = helper.getView(R.id.outside_exchange_recode_money_tv);//金额
        TextView type = helper.getView(R.id.outside_exchange_recode_type_tv);//类型
        TextView area = helper.getView(R.id.outside_exchange_recode_area_tv);//地区
        TextView apply_time = helper.getView(R.id.outside_exchange_recode_apply_time_tv);//申请时间
        TextView finish_time = helper.getView(R.id.outside_exchange_recode_finish_time_tv);//完成时间

        TextView see_detail = helper.getView(R.id.outside_exchange_recode_see_detail_tv);//查看详情
        TextView comfirm_receivables = helper.getView(R.id.outside_exchange_recode_comfirm_receivables_tv);//确认收款

        if(item.getDealStatus() == 2){ //待确认收货
            if(item.getDealType()==1){  //买入
                comfirm_receivables.setVisibility(View.VISIBLE);
                comfirm_receivables.setText(CommonUtil.getString(R.string.comfirm_receivables_corn));
            }else if(item.getDealType()==2){
                comfirm_receivables.setVisibility(View.VISIBLE);
                comfirm_receivables.setText(CommonUtil.getString(R.string.comfirm_receivables));
            }

        }else {
            comfirm_receivables.setVisibility(View.GONE);
        }

        order_num.setText(item.getOtcOrderNo()+"");
        name.setText(item.getCurrencyName());
        String text = null;
        switch (item.getDealStatus()){
            case 1:
                text = "待付款";
                recode_status.setBackgroundResource(R.drawable.shape_buy_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_red_3));
                break;
            case 2:
                text = "已付款（待确认）";
                recode_status.setBackgroundResource(R.drawable.shape_buy_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_red_3));
                break;
            case 3:
                text = "已完成";
                recode_status.setBackgroundResource(R.drawable.shape_shell_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
                break;
            case 4:
                text = "用户取消";
                recode_status.setBackgroundResource(R.drawable.shape_cancel_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_gray_2));
                break;
            case 5:
                text = "商家取消";
                recode_status.setBackgroundResource(R.drawable.shape_cancel_bg);
                recode_status.setTextColor(mContext.getResources().getColor(R.color.color_gray_2));
                break;
        }
        recode_status.setText(text);
        amount.setText(item.getCurrencyName()+"");
        money.setText(item.getCurrencyTotalPrice()+"");
        String text_type = null;
        //交易状态：1.买入 2.卖出 3.撤销
        switch (item.getDealType()){
            case 1:
                text_type = "买入";
                type.setTextColor(mContext.getResources().getColor(R.color.color_red_3));
                break;
            case 2:
                text_type = "卖出";
                type.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
                break;
            case 3:
                text_type = "撤销";
                type.setTextColor(mContext.getResources().getColor(R.color.color_gray_3));
                break;
        }
        type.setText(text_type);
        area.setText(item.getArea());
        apply_time.setText(DateUtil.longToTimeStr(item.getAddTime(),DateUtil.dateFormat2));
        finish_time.setText(DateUtil.longToTimeStr(item.getUpdateTime(),DateUtil.dateFormat2));
    }
}
