package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.DealerManagementRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description: 经销商管理Adapter
 */
public class DealerManagementRecyAdapter extends XBaseAdapter<DealerManagementRes.OtcTransactionPendOrderListBean> {
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public DealerManagementRecyAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.mine_dealer_managment_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, DealerManagementRes.OtcTransactionPendOrderListBean item) {
        TextView dealer_managment_go_exchange_tv = helper.getView(R.id.dealer_management_delete_tv);//删除
        TextView dealer_management_buy_tv = helper.getView(R.id.dealer_management_buy_tv);//交易类型
        TextView dealer_management_area_tv = helper.getView(R.id.dealer_management_area_tv);//地区
        TextView dealer_management_name_tv = helper.getView(R.id.dealer_management_name_tv);//链名称
        TextView dealer_management_ratio_tv = helper.getView(R.id.dealer_management_ratio_tv);//交易比例
        TextView dealer_management_number_tv = helper.getView(R.id.dealer_management_number_tv);//交易数量


        dealer_management_name_tv.setText(item.getCurrencyName());
        dealer_management_area_tv.setText(item.getArea());
        dealer_management_ratio_tv.setText(item.getPendingRatio()+"");
        dealer_management_number_tv.setText(item.getMinNumber()+"~"+item.getMaxNumber());

        //挂单类型 1：出售，2：回购
        switch (item.getOrderType()){
            case 1:
                dealer_management_buy_tv.setBackgroundResource(R.drawable.shape_buy_bg);
                dealer_management_buy_tv.setTextColor(mContext.getResources().getColor(R.color.color_red_3));
                dealer_management_buy_tv.setText(CommonUtil.getString(R.string.sell));
                break;
            case 2:
                dealer_management_buy_tv.setBackgroundResource(R.drawable.shape_shell_bg);
                dealer_management_buy_tv.setTextColor(mContext.getResources().getColor(R.color.color_green_3));
                dealer_management_buy_tv.setText(CommonUtil.getString(R.string.buy_2));
                break;
        }

        dealer_managment_go_exchange_tv.setBackground(shapeSelector.create());
        helper.addOnClickListener(R.id.dealer_management_delete_tv);
    }
}
