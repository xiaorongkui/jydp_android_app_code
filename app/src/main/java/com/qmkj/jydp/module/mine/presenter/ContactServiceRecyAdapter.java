package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;
import com.qmkj.jydp.util.StringUtil;

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
        //time
        shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x10))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_gray_8))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_8));
        TextView system_contact_service_item_time_tv = helper.getView(R.id.system_contact_service_item_time_tv);
        system_contact_service_item_time_tv.setBackground(shapeSelector.create());
        system_contact_service_item_time_tv.setText(DateUtil.longToTimeStr(item.getAddTime(), DateUtil.dateFormat2));
        //status 1：待处理，2：处理中，3：已处理
        TextView contact_service_deal_status_tv = helper.getView(R.id.contact_service_deal_status_tv);
        switch (item.getHandleStatus()) {
            case 1:
                shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x9))
                        .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                        .setDefaultStrokeColor(CommonUtil.getColor(R.color.colorBlack_13))
                        .setDefaultBgColor(CommonUtil.getColor(R.color.color_gray_13));
                contact_service_deal_status_tv.setBackground(shapeSelector.create());
                contact_service_deal_status_tv.setText("待处理");
                contact_service_deal_status_tv.setTextColor(mContext.getResources().getColor(R.color.color_black_10));
                break;
            case 2:
                shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x9))
                        .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                        .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_blue_7))
                        .setDefaultBgColor(CommonUtil.getColor(R.color.color_blue_6));
                contact_service_deal_status_tv.setBackground(shapeSelector.create());
                contact_service_deal_status_tv.setText("处理中");
                contact_service_deal_status_tv.setTextColor(mContext.getResources().getColor(R.color.color_bule_3));

                break;
            case 3:
                shapeSelector.setCornerRadius((int) CommonUtil.getDimen(R.dimen.x9))
                        .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                        .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_red_5))
                        .setDefaultBgColor(CommonUtil.getColor(R.color.color_red_6));
                contact_service_deal_status_tv.setBackground(shapeSelector.create());
                contact_service_deal_status_tv.setText("已处理");
                contact_service_deal_status_tv.setTextColor(mContext.getResources().getColor(R.color.color_red_5));

                break;
        }
        //title
        helper.setText(R.id.contact_service_title_item_tv, Html.fromHtml(item.getFeedbackTitle())+"");
        //content
        helper.setText(R.id.contact_service_content_item_tv, Html.fromHtml(item.getFeedbackContent())+"");
        //feedback
        TextView answer = helper.getView(R.id.contact_service_answer_tv);
        View line = helper.getView(R.id.common_line);
        answer.setText(item.getHandleContent());
        if (StringUtil.isNull(item.getHandleContent())) {
            line.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);
        }
    }
}
