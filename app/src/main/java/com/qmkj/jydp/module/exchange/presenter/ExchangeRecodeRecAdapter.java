package com.qmkj.jydp.module.exchange.presenter;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.common.CommonRecylerViewHolder;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeRecodeRecAdapter extends BaseRecylerAdapter {

    private final List datas;

    public ExchangeRecodeRecAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    protected boolean isNeedInflateToParcent() {
        return true;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {
        ClickItemView clickItemView = (ClickItemView) holder.getView(R.id.exchange_entrust_recode_cv);
        clickItemView.setLeftText("交易记录");
        clickItemView.setRightText("1000万");
    }
}
