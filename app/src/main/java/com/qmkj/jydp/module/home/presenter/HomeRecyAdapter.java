package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeRecyAdapter extends BaseRecylerAdapter {
    private final List<String> datas;
    private final Context mContext;
    @BindView(R.id.home_exchange_item_iv)
    ImageView homeExchangeItemIv;
    @BindView(R.id.home_exchange_name_tv)
    TextView homeExchangeNameTv;
    @BindView(R.id.home_exchange_name_dollar_tv)
    TextView homeExchangeNameDollarTv;
    @BindView(R.id.home_exchange_volume_tv)
    TextView homeExchangeVolumeTv;
    @BindView(R.id.home_exchange_volume_dollar_tv)
    TextView homeExchangeVolumeDollarTv;
    @BindView(R.id.home_exchange_updown_tv)
    TextView homeExchangeUpdownTv;
    @BindView(R.id.home_exchange_updown_percent_tv)
    TextView homeExchangeUpdownPercentTv;
    @BindView(R.id.common_line)
    View homeExchanageBottomLine;

    public HomeRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
        if (position == datas.size() - 1) homeExchanageBottomLine.setVisibility(View.GONE);
        else homeExchanageBottomLine.setVisibility(View.VISIBLE);
    }
}
