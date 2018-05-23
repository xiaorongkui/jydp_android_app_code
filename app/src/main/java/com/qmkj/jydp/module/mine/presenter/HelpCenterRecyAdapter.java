package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.bean.HelpCenterBean;
import com.qmkj.jydp.module.mine.view.HelpCenterActivity;
import com.qmkj.jydp.ui.widget.ClickItemView;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class HelpCenterRecyAdapter extends BaseRecycleAdapter<HelpCenterBean> {
    private final Context mContext;

    public HelpCenterRecyAdapter(Context context, List<HelpCenterBean> datas, int layoutId) {

        super(layoutId, datas);
        this.mContext = context;

    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, HelpCenterBean item, int position) {
        ClickItemView clickItemView = helper.getView(R.id.person_info_help_center_civ);
        clickItemView.setLeftText(item.getName());

    }
}
