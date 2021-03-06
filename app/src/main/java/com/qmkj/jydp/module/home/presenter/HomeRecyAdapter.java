package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.response.HomeDataRes;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.util.NumberUtil;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeRecyAdapter extends BaseRecycleAdapter<HomeDataRes.TransactionUserDealListBean> {

    public HomeRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, HomeDataRes.TransactionUserDealListBean item, int position) {
        TextView home_currency_name_item_tv = helper.getView(R.id.home_currency_name_item_tv);
        TextView home_currency_us_name_item_tv_1 = helper.getView(R.id.home_currency_us_name_item_tv_1);
        TextView home_currency_exchange_amount_tv = helper.getView(R.id.home_currency_exchange_amount_tv);
        TextView home_current_price_tv = helper.getView(R.id.home_current_price_tv);
        ImageView home_exchange_updown_percent_iv = helper.getView(R.id.home_exchange_updown_percent_iv);
        TextView home_exchange_updown_percent_tv = helper.getView(R.id.home_exchange_updown_percent_tv);
        home_currency_name_item_tv.setText(item.getCurrencyName());
        home_currency_us_name_item_tv_1.setText(item.getCurrencyShortName());
        home_currency_exchange_amount_tv.setText(NumberUtil.doubleFormat(item.getVolume(), 2));
        home_current_price_tv.setText(NumberUtil.doubleFormat(item.getLatestPrice(), 2));
        double change = item.getChange();
        if (change >= 0) {
            home_exchange_updown_percent_iv.setImageResource(R.mipmap.exchange_up);
            home_exchange_updown_percent_tv.setTextColor(ResourcesManager.getColor(R.color.color_red_3));
        } else {
            home_exchange_updown_percent_iv.setImageResource(R.mipmap.exchange_down);
            home_exchange_updown_percent_tv.setTextColor(ResourcesManager.getColor(R.color.color_green_4));
        }
        home_exchange_updown_percent_tv.setText(String.format("%s%%", NumberUtil.doubleFormat(change, 2)));
    }
}
