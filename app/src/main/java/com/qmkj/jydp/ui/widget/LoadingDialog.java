package com.qmkj.jydp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qmkj.jydp.R;


/**
 * Created by Yun on 2018/2/11 0011.
 * 加载dialog
 */
public class LoadingDialog extends Dialog {
    private ProgressBar mProgress;
    private TextView mMessage;
    private String mMessageStr;
    private Context mContext;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }


    //系统提供的 二个参数的
    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }


    //系统提供 带三个参数的
    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        setCanceledOnTouchOutside(false);
        setCancelable(cancelable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setTitle(null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_loading_dialog);
        initView();
    }

    private void initView() {
        mProgress = findViewById(R.id.progress);
        mMessage = findViewById(R.id.message);
        mMessage.setText(mMessageStr);
    }

    public void setMessage(String message) {
        mMessageStr = message;
    }

    public void setAlertDialogSize(int width, int height) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = width;
        lp.height = height;
        getWindow().setAttributes(lp);
    }
}
