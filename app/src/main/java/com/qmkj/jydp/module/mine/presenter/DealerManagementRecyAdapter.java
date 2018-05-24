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
        TextView dealer_managment_go_exchange_tv = helper.getView(R.id.dealer_management_delete_tv);
        TextView dealer_management_area_tv = helper.getView(R.id.dealer_management_area_tv);
        TextView dealer_management_name_tv = helper.getView(R.id.dealer_management_name_tv);
        TextView dealer_management_ratio_tv = helper.getView(R.id.dealer_management_ratio_tv);
        TextView dealer_management_number_tv = helper.getView(R.id.dealer_management_number_tv);


        dealer_management_name_tv.setText(item.getCurrencyName());
        dealer_management_area_tv.setText(item.getArea());
        dealer_management_ratio_tv.setText(item.getPendingRatio()+"");
        dealer_management_number_tv.setText(item.getMinNumber()+"~"+item.getMaxNumber());

        dealer_managment_go_exchange_tv.setBackground(shapeSelector.create());
        helper.addOnClickListener(R.id.dealer_management_delete_tv);
    }
}
