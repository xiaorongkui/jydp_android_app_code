package com.qmkj.jydp.ui.widget.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;

public abstract class BaseBottomDialog extends Dialog {
    protected TextView tvTitle;
    protected ImageView imgClose;
    private FrameLayout flContent;
    protected String titleStr;
    private View contentView;


    public void setTitleText(String titleStr) {
        this.titleStr = titleStr;
    }


    protected abstract View setContentView();

    protected abstract void initContentView(View contentView);

    public BaseBottomDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setWindowAnimations(R.style.DialogAnimation);
        }
        setTitle(null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_base_below);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        initView();
    }

    private void initView() {
        contentView = setContentView();
        tvTitle = findViewById(R.id.dialog_title_tv);
        imgClose = findViewById(R.id.dialog_close_iv);
        flContent = findViewById(R.id.fl_content);
        imgClose.setOnClickListener(v -> dismiss());
        initContentView(contentView);
        if (titleStr != null && !titleStr.equals("")) {
            tvTitle.setText(titleStr);
        }
        if (contentView != null) {
            flContent.removeAllViews();
            flContent.addView(contentView);
        }
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window == null) {
            super.show();
            return;
        }
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.getDecorView().setPadding(0, 0, 0, 0);
        super.show();
    }
}
