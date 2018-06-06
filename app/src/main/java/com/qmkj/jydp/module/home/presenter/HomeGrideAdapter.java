package com.qmkj.jydp.module.home.presenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.response.HomeDataRes;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/16
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeGrideAdapter extends BaseAdapter {

    private final Context context;
    private final int layoutId;
    private final List<HomeDataRes.SystemBusinessesPartnerListBean> data;

    public HomeGrideAdapter(Context mContext, int layoutId, List<HomeDataRes.SystemBusinessesPartnerListBean> data) {
        this.context = mContext;
        this.layoutId = layoutId;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeDataRes.SystemBusinessesPartnerListBean dataItem = data.get(position);
        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(context, layoutId, null);
            holder = new Holder();
            holder.tv = convertView.findViewById(R.id.gride_text);
            holder.img = convertView.findViewById(R.id.gride_image);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (dataItem != null) {
            holder.tv.setText(dataItem.getBusinessesName());
            GlideApp.with(context).load(dataItem.getBusinessesImageUrlFormat()).placeholder(R.mipmap.compare)
                    .into(holder.img);
        }

        return convertView;
    }

    private class Holder {
        TextView tv;
        ImageView img;
    }
}
