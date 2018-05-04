package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ContactServiceRecyAdapter extends BaseRecylerAdapter {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector();

    public ContactServiceRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
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
