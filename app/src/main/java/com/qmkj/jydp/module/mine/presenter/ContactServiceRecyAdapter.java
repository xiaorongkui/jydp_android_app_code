package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ContactServiceRecyAdapter extends XBaseAdapter<CustomerServiceRes.UserFeedbackListBean> {
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector();


    public ContactServiceRecyAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.mine_contact_service_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, CustomerServiceRes.UserFeedbackListBean item) {
        shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x10))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_gray_8))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_8));
        TextView system_contact_service_item_time_tv = helper.getView(R.id.system_contact_service_item_time_tv);
        system_contact_service_item_time_tv.setBackground(shapeSelector.create());

        shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x9))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_red_5))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_red_6));
        TextView contact_service_deal_status_tv = helper.getView(R.id.contact_service_deal_status_tv);
        contact_service_deal_status_tv.setBackground(shapeSelector.create());
    }
}
