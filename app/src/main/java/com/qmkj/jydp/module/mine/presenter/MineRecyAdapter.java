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

public class MineRecyAdapter extends BaseRecylerAdapter {
    private final List<MinelistInfo> datas;
    private final Context mContext;

    public MineRecyAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public void convert(CommonRecylerViewHolder holder, int position) {
        MinelistInfo minelistInfo = datas.get(position);
        if (minelistInfo == null) return;
        ImageView mine_icon_iv = holder.getImageView(R.id.mine_item_icon_iv);
        TextView mine_name_tv = holder.getTextView(R.id.mine_item_name_tv);
        ImageView mine_person_iv = holder.getImageView(R.id.mine_item_more_iv);
        View mine_item_line = holder.getView(R.id.mine_item_line);
        mine_icon_iv.setImageResource(minelistInfo.leftIcon);
        mine_person_iv.setImageResource(minelistInfo.rightIcon);
        mine_name_tv.setText(minelistInfo.name);
        if (position == datas.size() - 1) {
            mine_item_line.setVisibility(View.INVISIBLE);
        } else {
            mine_item_line.setVisibility(View.VISIBLE);
        }
    }

}
