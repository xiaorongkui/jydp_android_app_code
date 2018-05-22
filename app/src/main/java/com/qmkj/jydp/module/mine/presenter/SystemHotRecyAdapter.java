package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.bean.response.SystemHotRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemHotRecyAdapter extends BaseRecycleAdapter<SystemHotRes.SystemHotListBean> {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x10))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_8));

    public SystemHotRecyAdapter(Context context, List<SystemHotRes.SystemHotListBean> datas, int layoutId) {
        super(layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, SystemHotRes.SystemHotListBean item, int position) {
        TextView system_notice_item_time_tv = helper.getView(R.id.system_notice_item_time_tv);
        TextView notice_title_item_tv = helper.getView(R.id.notice_title_item_tv);
        TextView notice_content_item_tv = helper.getView(R.id.notice_content_item_tv);

        helper.addOnClickListener(R.id.system_notice_item_detail_civ);
        system_notice_item_time_tv.setText(DateUtil.longToTimeStr(item.getAddTime(),DateUtil.dateFormat2));
        notice_title_item_tv.setText(item.getNoticeTitle()+"");
        notice_content_item_tv.setText(item.getContent()+"");


        system_notice_item_time_tv.setBackground(shapeSelector.create());
    }
}
