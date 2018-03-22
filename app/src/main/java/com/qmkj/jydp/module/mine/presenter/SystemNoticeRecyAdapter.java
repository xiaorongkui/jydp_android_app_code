package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.bean.MinelistInfo;
import com.qmkj.jydp.common.CommonRecylerViewHolder;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class SystemNoticeRecyAdapter extends BaseRecylerAdapter {
    private final List datas;
    private final Context mContext;

    public SystemNoticeRecyAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {
    }

}
