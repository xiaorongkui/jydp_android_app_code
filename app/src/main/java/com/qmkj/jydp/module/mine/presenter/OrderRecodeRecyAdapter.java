package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.OrderRecodeRes;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class OrderRecodeRecyAdapter extends XBaseAdapter<OrderRecodeRes.TransactionPendOrderRecordListBean> {

    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public OrderRecodeRecyAdapter(Context context) {
        super(context);
    }
    public MyListener myListener;

    @Override
    protected int getLayoutResId(int viewType) {

        return R.layout.mine_order_reocde_item;
    }


    @Override
    protected void convert(XBaseViewHolder helper, OrderRecodeRes.TransactionPendOrderRecordListBean item) {
        ClickItemView business_phone = (ClickItemView)helper.getView(R.id.business_phone_civ);//订单
        TextView exchange_statu =(TextView)helper.getView(R.id.recode_order_exchange_status_tv);//买入
        TextView amount =(TextView)helper.getView(R.id.entrustment_amount_tv); //数量
        TextView price =(TextView)helper.getView(R.id.entrustment_price_tv); //单价
        TextView deal_amount =(TextView)helper.getView(R.id.deal_amount_tv); //成交数量
        TextView total_price =(TextView)helper.getView(R.id.entrustment_total_price_tv); //成交总价
        TextView available_amount =(TextView)helper.getView(R.id.available_amount_tv); //剩余数量
        TextView entrustment =(TextView)helper.getView(R.id.order_revocation_entrustment_tv); //撤销委托
        TextView detail =(TextView)helper.getView(R.id.order_see_detail_tv); //查看详情

        business_phone.setLeftText("委托时间"+DateUtil.longToTimeStr(item.getAddTime(),DateUtil.dateFormat2));
        String text= null;
        switch (item.getPendingStatus()){
            case 1:
                text = "未成交";
                break;
            case 2:
                text = "部分成交";
                break;
            case 3:
                text = "全部成交";
                break;
            case 4:
                text = "部分撤销";
                break;
            case 5:
                text = "全部撤销";
                break;
        }
        business_phone.setRightText(text);
        amount.setText(item.getPendingNumber()+"");
        price.setText(item.getPendingPrice()+"");
        deal_amount.setText(item.getDealNumber()+"");
        total_price.setText(item.getTotalPrice()+"");
        available_amount.setText(item.getRemainNum()+"");

        entrustment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myListener.entrustment_click();
            }
        });

    }

    public void setItemSomeListener(MyListener listener){
        this.myListener = listener;
    }
    public interface MyListener{
       void entrustment_click(int position);
       void detail_click(int position);
    }
}
