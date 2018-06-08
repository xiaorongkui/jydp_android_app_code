package com.qmkj.jydp.module.mine.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.MinelistInfo;

import java.util.List;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description: 个人中心适配器
 */

public class MineRecyAdapter extends BaseRecycleAdapter<MinelistInfo> {
    private final Context mContext;

    public MineRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, MinelistInfo item, int position) {
        if (item == null) return;

        ImageView mine_icon_iv = helper.getView(R.id.mine_item_icon_iv); //图标
        TextView mine_name_tv = helper.getView(R.id.mine_item_name_tv); //名称
        ImageView mine_person_iv = helper.getView(R.id.mine_item_more_iv);
        View mine_item_line = helper.getView(R.id.mine_item_line);

        mine_icon_iv.setImageResource(item.leftIcon);
        mine_person_iv.setImageResource(item.rightIcon);
        mine_name_tv.setText(item.name);

        if (position == getItemCount() - 1) {
            mine_item_line.setVisibility(View.INVISIBLE);
        } else {
            mine_item_line.setVisibility(View.VISIBLE);
        }
    }
}
