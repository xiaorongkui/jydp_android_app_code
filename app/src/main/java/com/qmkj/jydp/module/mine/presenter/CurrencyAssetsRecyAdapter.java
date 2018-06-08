package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.XBaseAdapter;
import com.qmkj.jydp.base.XBaseViewHolder;
import com.qmkj.jydp.bean.response.CurrencyAssetsRes;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.SelectorFactory;


/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:链资产适配器
 */

public class CurrencyAssetsRecyAdapter extends XBaseAdapter<CurrencyAssetsRes.UserCurrencyAssetsBean> {
    private final SelectorFactory.ShapeSelector shapeSelector;

    public CurrencyAssetsRecyAdapter(Context context) {
        super(context);
        shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) ResourcesManager.getDimen(R.dimen.x12))
                .setDefaultStrokeColor(ResourcesManager.getColor(R.color.color_bule_3))
                .setStrokeWidth((int) ResourcesManager.getDimen(R.dimen.x1))
                .setDefaultBgColor(ResourcesManager.getColor(R.color.color_white_1));
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.mine_currency_assets_item;
    }

    @Override
    protected void convert(XBaseViewHolder helper, CurrencyAssetsRes.UserCurrencyAssetsBean item) {
        TextView mine_currency_assets_item_go_exchange_tv = helper.getView(R.id
                .mine_currency_assets_item_go_exchange_tv);  //去交易

        TextView mine_assets_name_tv = helper.getView(R.id.mine_assets_name_tv); //链名称
        TextView currency_total_assets_tv = helper.getView(R.id.currency_total_assets_tv);//总资产
        TextView available_amount_tv = helper.getView(R.id.available_amount_tv); //可用数量
        TextView frozen_amount_tv = helper.getView(R.id.frozen_amount_tv); //冻结数量

        helper.addOnClickListener(R.id.mine_currency_assets_item_go_exchange_tv);
        mine_currency_assets_item_go_exchange_tv.setBackground(shapeSelector.create());

        mine_assets_name_tv.setText(item.getCurrencyName());
        currency_total_assets_tv.setText(String.format("%s", NumberUtil.doubleFormat(Double.parseDouble(item
                .getTotalCurrencyAssets()), 4)));
        available_amount_tv.setText(String.format("%s", NumberUtil.doubleFormat(Double.parseDouble(item
                .getCurrencyNumber()), 4)));
        frozen_amount_tv.setText(String.format("%s", NumberUtil.doubleFormat(Double.parseDouble(item
                .getCurrencyNumberLock()), 4)));
    }
}
