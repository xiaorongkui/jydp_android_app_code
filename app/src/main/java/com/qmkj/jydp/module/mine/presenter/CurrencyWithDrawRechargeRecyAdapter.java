package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.PresentRechargeRes;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.SelectorFactory;

/**
 * 创建日期：2018/5/30
 * @author Yi Shan Xiang
 * 文件名称： CurrencyWithDrawRechargeRecyAdapter
 * email: 380948730@qq.com
 */

public class CurrencyWithDrawRechargeRecyAdapter extends XBaseAdapter<PresentRechargeRes.UserRechargeCoinRecordListBean> {

    public CurrencyWithDrawRechargeRecyAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutResId(int viewType) {

        return R.layout.mine_currency_withdraw_reocde_item;
    }


    @Override
    protected void convert(XBaseViewHolder helper, PresentRechargeRes.UserRechargeCoinRecordListBean item) {
        TextView name = (TextView) helper.getView(R.id.currency_withdraw_name_tv);//名称
        TextView number = (TextView) helper.getView(R.id.currency_withdraw_number_tv);//总数
        TextView apply_mun = (TextView) helper.getView(R.id.currency_withdraw_apply_mun_tv);//申请时间
        TextView price = (TextView) helper.getView(R.id.entrustment_price_tv);//流水单号
        TextView remark = (TextView) helper.getView(R.id.currency_withdraw_remark_tv);//备注内容
        helper.addOnClickListener(R.id.currency_withdraw_retract_tv);
        name.setText(item.getCurrencyName());


        number.setText(item.getCurrencyNumber()+"");
        apply_mun.setText(DateUtil.longToTimeStr(item.getOrderTime(),DateUtil.dateFormat2));
        price.setText(item.getWalletOrderNo()+"");
        remark.setText(item.getRemark()+"");

    }
}
