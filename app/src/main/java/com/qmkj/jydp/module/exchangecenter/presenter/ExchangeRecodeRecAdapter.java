package com.qmkj.jydp.module.exchangecenter.presenter;

import android.content.Context;
import android.view.View;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.ui.widget.ClickItemView;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class ExchangeRecodeRecAdapter extends BaseRecylerAdapter {

    private final List datas;

    public ExchangeRecodeRecAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
    }


    @Override
    protected void convert(BaseRecyclerViewHolder helper, Object item, int position) {
        helper.getView(R.id.common_line).setVisibility(datas.size() - 1 == position ? View.INVISIBLE : View
                .VISIBLE);
    }
}
