package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.SystemNoticeRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemNoticeRecyAdapter extends XBaseAdapter<SystemNoticeRes.SystemNoticeListBean> {
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x10))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_8));

    public SystemNoticeRecyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.mine_system_notice_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, SystemNoticeRes.SystemNoticeListBean item) {
        TextView system_notice_item_time_tv = helper.getView(R.id.system_notice_item_time_tv);
        system_notice_item_time_tv.setText(DateUtil.longToTimeStr(item.getAddTime(), DateUtil.dateFormat2));
        system_notice_item_time_tv.setBackground(shapeSelector.create());
        helper.setText(R.id.notice_title_item_tv, item.getNoticeTitle() + "");
        helper.setText(R.id.notice_content_item_tv, item.getContent() + "");
    }
}
