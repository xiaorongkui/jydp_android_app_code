package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.SystemHotRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemHotRecyAdapter extends XBaseAdapter<SystemHotRes.SystemHotListBean> {
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x10))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_8));

    public SystemHotRecyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout
                .mine_system_notice_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, SystemHotRes.SystemHotListBean item) {
        TextView system_notice_item_time_tv = helper.getView(R.id.system_notice_item_time_tv);
        TextView notice_title_item_tv = helper.getView(R.id.notice_title_item_tv);
        TextView notice_content_item_tv = helper.getView(R.id.notice_content_item_tv);

        helper.addOnClickListener(R.id.system_notice_item_detail_civ);
        system_notice_item_time_tv.setText(DateUtil.longToTimeStr(item.getAddTime(),DateUtil.dateFormat2));
        notice_title_item_tv.setText(Html.fromHtml(item.getNoticeTitle())+"");
        notice_content_item_tv.setText(Html.fromHtml(item.getContent())+"");


        system_notice_item_time_tv.setBackground(shapeSelector.create());
    }
}
