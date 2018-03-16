package com.qmkj.jydp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;

/**
 * Created by Rongkui.xiao on 2017/4/11.
 *
 * @description
 */

public class NetProgressDialog extends Dialog {


    private Context ctt;

    public static NetProgressDialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.body);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.message);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        NetProgressDialog loadingDialog = new NetProgressDialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(v);// 设置布局
        return loadingDialog;

    }

    //系统提供的 一个参数的
    public NetProgressDialog(Context context) {
        super(context);
        this.ctt = context;
    }


    //系统提供的 二个参数的
    public NetProgressDialog(Context context, int theme) {
        super(context, theme);
        this.ctt = context;
    }


    //系统提供 带三个参数的
    protected NetProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.ctt = context;
    }

    public void setAlertDialogSize(int width, int height) {
        getWindow().setLayout(width, height);
    }
}
