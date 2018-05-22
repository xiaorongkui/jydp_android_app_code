package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;


/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class CurrencyAssetsRecyAdapter extends BaseRecycleAdapter<CurrencyAssetsRes.UserCurrencyAssetsBean> {
    private final Context mContext;
    private final SelectorFactory.ShapeSelector shapeSelector;

    public CurrencyAssetsRecyAdapter(Context context, List<CurrencyAssetsRes.UserCurrencyAssetsBean> datas, int layoutId) {
        super(layoutId, datas);
        this.mContext = context;
        shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x12))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_3))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper,CurrencyAssetsRes.UserCurrencyAssetsBean item, int position) {

        TextView mine_currency_assets_item_go_exchange_tv = helper.getView(R.id
                .mine_currency_assets_item_go_exchange_tv);

        TextView mine_assets_name_tv = helper.getView(R.id.mine_assets_name_tv);
        TextView currency_total_assets_tv = helper.getView(R.id.currency_total_assets_tv);
        TextView available_amount_tv = helper.getView(R.id.available_amount_tv);
        TextView frozen_amount_tv = helper.getView(R.id.frozen_amount_tv);

        mine_currency_assets_item_go_exchange_tv.setBackground(shapeSelector.create());

        mine_assets_name_tv.setText(item.getCurrencyName());
        currency_total_assets_tv.setText(item.getTotalCurrencyAssets()+"");
        available_amount_tv.setText(item.getCurrencyNumber()+"");
        frozen_amount_tv.setText(item.getCurrencyNumberLock()+"");
    }
}
