package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.bean.response.HomeDataRes;
import com.qmkj.jydp.util.CommonUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeRecyAdapter extends BaseRecylerAdapter<HomeDataRes.TransactionUserDealListBean> {
    private final List<String> datas;
    private final Context mContext;
    @BindView(R.id.common_line)
    View homeExchanageBottomLine;

    public HomeRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
        this.mContext = context;
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
        home_currency_exchange_amount_tv.setText(item.getVolume() + "");
        home_current_price_tv.setText(item.getLatestPrice() + "");
        double change = item.getChange();
        if (change >= 0) {
            home_exchange_updown_percent_iv.setImageResource(R.mipmap.exchange_up);
            home_exchange_updown_percent_tv.setTextColor(CommonUtil.getColor(R.color.color_red_3));
        } else {
            home_exchange_updown_percent_iv.setImageResource(R.mipmap.exchange_down);
            home_exchange_updown_percent_tv.setTextColor(CommonUtil.getColor(R.color.color_green_4));
        }
        home_exchange_updown_percent_tv.setText(change + "%");
    }
}
