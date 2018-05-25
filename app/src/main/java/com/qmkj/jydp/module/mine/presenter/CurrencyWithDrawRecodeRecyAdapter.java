package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class CurrencyWithDrawRecodeRecyAdapter extends XBaseAdapter<PresentRecordRes.CoinOutRecordListBean> {
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public CurrencyWithDrawRecodeRecyAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutResId(int viewType) {

        return R.layout.mine_currency_withdraw_reocde_item;
    }


    @Override
    protected void convert(XBaseViewHolder helper, PresentRecordRes.CoinOutRecordListBean item) {
        TextView name = (TextView) helper.getView(R.id.currency_withdraw_name_tv);//名称
        TextView state = (TextView) helper.getView(R.id.currency_withdraw_status_tv);//状态
        TextView number = (TextView) helper.getView(R.id.currency_withdraw_number_tv);//总数
        TextView apply_mun = (TextView) helper.getView(R.id.currency_withdraw_apply_mun_tv);//申请时间
        TextView price = (TextView) helper.getView(R.id.entrustment_price_tv);//流水单号
        TextView remark = (TextView) helper.getView(R.id.currency_withdraw_remark_tv);//备注内容
        TextView retract = (TextView) helper.getView(R.id.currency_withdraw_retract_tv);//撤回
        helper.addOnClickListener(R.id.currency_withdraw_retract_tv);
        name.setText(item.getCurrencyName());

        String text =null;
        switch (item.getHandleStatus()){
            case 1:text = "待审核";
                retract.setVisibility(View.VISIBLE);
                break;
            case 2:text = "审核通过";
                retract.setVisibility(View.GONE);
                break;
            case 3:text = "审核拒绝";
                retract.setVisibility(View.GONE);
                break;
            case 4:text = "已撤回";
                retract.setVisibility(View.GONE);
                break;
        }
        state.setText(text);

        number.setText(item.getCurrencyNumber()+"");
        apply_mun.setText(DateUtil.longToTimeStr(item.getAddTime(),DateUtil.dateFormat2));
        price.setText(item.getCoinRecordNo()+"");
        remark.setText(item.getRemark()+"");

    }
}
