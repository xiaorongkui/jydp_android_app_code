package com.qmkj.jydp.module.outsideexchange.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;

import java.util.List;

/**
 * @author wujiangming
 * @date 2018/4/23
 * @desc
 */

public class OutsideExchangeAdapter extends BaseRecylerAdapter {

    public OutsideExchangeAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
//        helper.setText(R.id.dealer_name_tv,"");
//        helper.setText(R.id.scale_tv,"");
//        helper.setText(R.id.trade_quotas_tv,"");
//        helper.setText(R.id.region_tv,"");
    }

}
