package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.bean.response.HomeDataRes;

import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/20
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeRecyAdapter extends BaseRecylerAdapter<HomeDataRes.TransactionUserDealListBean> {
    private final List<String> datas;
    private final Context mContext;
    @BindView(R.id.common_line)
    View homeExchanageBottomLine;

    public HomeRecyAdapter(Context context, List datas, int layoutId) {
        super(layoutId, datas);
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, HomeDataRes.TransactionUserDealListBean item, int position) {
    }
}
