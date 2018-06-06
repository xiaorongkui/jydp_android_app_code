package com.qmkj.jydp.module.login.presenter;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.DoubleString;
import com.qmkj.jydp.ui.widget.ClickItemView;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SearchAreaRecyAdapter extends BaseRecycleAdapter<DoubleString> {

    public SearchAreaRecyAdapter(List<DoubleString> datas, int layoutId) {
        super(layoutId, datas);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, DoubleString item, int position) {
        if (item == null) return;
        ClickItemView clickItemView = helper.getView(R.id.searc_area_cv);
        clickItemView.setLeftText(item.str2);
        clickItemView.setRightText(item.str1);
    }
}
