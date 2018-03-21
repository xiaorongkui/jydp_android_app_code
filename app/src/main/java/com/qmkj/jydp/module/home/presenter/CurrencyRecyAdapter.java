package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.common.CommonRecylerViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class CurrencyRecyAdapter extends BaseRecylerAdapter {
    private final List<String> datas;
    private final Context mContext;

    public CurrencyRecyAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {

    }

}
