package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.OtcDealRecordRes;
import com.qmkj.jydp.module.mine.view.MineRecodeActivity;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideExchangeRecodeRecyAdapter extends XBaseAdapter<OtcDealRecordRes.OtcTransactionUserDealListBean> {
    private final Context mContext;
    private final int type_outside;
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public OutSideExchangeRecodeRecyAdapter(Context context,int type) {

        super(context);
        this.mContext = context;
        this.type_outside = type;

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

        order_num.setText(item.getOtcOrderNo()+"");
        name.setText(item.getCurrencyName());
        String text = null;
        if(item.getDealStatus()==4){
            text = "已完成";
            recode_status.setBackgroundResource(R.drawable.shape_shell_bg);
            recode_status.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
        }else if(item.getDealStatus()==5){
            text = "已撤销";
            recode_status.setBackgroundResource(R.drawable.shape_cancel_bg);
            recode_status.setTextColor(mContext.getResources().getColor(R.color.color_gray_2));
        }else {
            text = "待完成";
            recode_status.setBackgroundResource(R.drawable.shape_wite_bg);
            recode_status.setTextColor(mContext.getResources().getColor(R.color.color_blue_7));
        }
        recode_status.setText(text);
        amount.setText(item.getCurrencyNumber()+"");
        money.setText("$" + item.getCurrencyTotalPrice());
        String text_type = null;
        //交易状态：1.买入 2.卖出 3.撤销  (普通用户)
        comfirm_receivables.setVisibility(View.GONE);
        switch (item.getDealType()){
            case 1:
                if(type_outside == MineRecodeActivity.RECODE_TYPE_NORMAL){ //普通用户
                    text_type = "购买";
                    type.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
                }else if(type_outside == MineRecodeActivity.RECODE_TYPE_AGENCY){ //经销商
                    text_type = "出售";
                    type.setTextColor(mContext.getResources().getColor(R.color.color_red_3));

                    if(item.getDealStatus() != 4&&item.getDealStatus() != 4){//待确认收货
                        comfirm_receivables.setVisibility(View.VISIBLE);
                    }

                }
                break;
            case 2:
                if(type_outside == MineRecodeActivity.RECODE_TYPE_NORMAL){ //普通用户
                    text_type = "出售";
                    type.setTextColor(mContext.getResources().getColor(R.color.color_red_3));
                    if(item.getDealStatus() != 4&&item.getDealStatus() != 5){
                        comfirm_receivables.setVisibility(View.VISIBLE);
                    }
                }else if(type_outside == MineRecodeActivity.RECODE_TYPE_AGENCY){ //经销商
                    text_type = "回购";
                    type.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
                }
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
