package com.qmkj.jydp.module.login.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.bean.DoubleString;
import com.qmkj.jydp.bean.MinelistInfo;
import com.qmkj.jydp.common.CommonRecylerViewHolder;
import com.qmkj.jydp.ui.widget.ClickItemView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SearchAreaRecyAdapter extends BaseRecylerAdapter<DoubleString> {
    private final List<DoubleString> datas;
    private final Context mContext;

    public SearchAreaRecyAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {
        DoubleString doubleString = datas.get(position);
        if (doubleString == null) return;
        ClickItemView clickItemView = (ClickItemView) holder.getView(R.id.searc_area_cv);
        clickItemView.setLeftText(doubleString.str2);
        clickItemView.setRightText(doubleString.str1);
    }

}
