package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideExchangeRecodeRecyAdapter extends BaseRecylerAdapter {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
            .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
            .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
            .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
            .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

    public OutSideExchangeRecodeRecyAdapter(Context context, List datas, int layoutId) {

        super(layoutId, datas);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
        helper.addOnClickListener(R.id.outside_exchange_recode_see_detail_tv);
        helper.addOnClickListener(R.id.outside_exchange_recode_comfirm_receivables_tv);
        TextView outside_exchange_recode_see_detail_tv = helper.getView(R.id.outside_exchange_recode_see_detail_tv);
        TextView outside_exchange_recode_comfirm_receivables_tv = helper.getView(R.id
                .outside_exchange_recode_comfirm_receivables_tv);

        shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        outside_exchange_recode_see_detail_tv.setBackground(shapeSelector.create());

        shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_3))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        outside_exchange_recode_comfirm_receivables_tv.setBackground(shapeSelector.create());


    }
}
