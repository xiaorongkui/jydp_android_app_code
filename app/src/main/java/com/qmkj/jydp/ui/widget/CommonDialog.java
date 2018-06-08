package com.qmkj.jydp.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qmkj.jydp.R;

/**
 * Created by Rongkui.xiao on 2017/5/9.
 *
 * @description 统一的对话框生成工具类
 */

public class CommonDialog extends Dialog {
    Activity activity;
    TextView messageView;
    TextView oneBtnView;        //只有一个确认键的情况
    View twoBtnView;            //确认和取消的情况
    TextView confirmBtn;
    TextView cancelBtn;
    private final View mView;

    public CommonDialog(Activity context, int theme) {
        super(context, theme);
        this.activity = context;
        mView = LayoutInflater.from(getContext()).inflate(R.layout.common_dialog, null);
        messageView = mView.findViewById(R.id.message);
        oneBtnView = mView.findViewById(R.id.only_confirm_btn);
        twoBtnView = mView.findViewById(R.id.two_btn_layout);
        confirmBtn = mView.findViewById(R.id.yes);
        cancelBtn = mView.findViewById(R.id.no);
        setContentView(mView);
        super.setContentView(mView);
    }

    //layoutResId请在原common_dialog上修改
    public CommonDialog(Activity context, int theme, int layoutResId) {
        super(context, theme);
        this.activity = context;
        mView = LayoutInflater.from(getContext()).inflate(layoutResId, null);
        messageView = mView.findViewById(R.id.message);
        oneBtnView = mView.findViewById(R.id.only_confirm_btn);
        twoBtnView = mView.findViewById(R.id.two_btn_layout);
        confirmBtn = mView.findViewById(R.id.yes);
        cancelBtn = mView.findViewById(R.id.no);
        setContentView(mView);
        super.setContentView(mView);
    }


    public void setTitle(String title) {
        TextView mTitle = mView.findViewById(R.id.title);
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(title);
    }

    public <T extends View> T getView(int viewId, Class<T> clazz) {
        return (T) mView.findViewById(viewId);
    }

    public void setAlertDialogSize(int width, int height) {
//        getWindow().setLayout(width, height);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        if (getWindow() != null) {
            lp.copyFrom(getWindow().getAttributes());
            lp.width = width;
            lp.height = height;
            getWindow().setAttributes(lp);
        }
    }

    /**
     * @param width 对话框的宽度
     */
    public void setAlertDialogWidth(int width) {
//        //定义宽度
//        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
//        getWindow().setLayout(width, attrs.height);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        if (getWindow() != null) {
            lp.copyFrom(getWindow().getAttributes());
            lp.width = width;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(lp);
        }

    }

    /**
     * @param height 对话框的高度
     */
    public void setAlertDialogHight(int height) {
        if (height <= 0) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            if (getWindow() != null) {
                lp.copyFrom(getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                getWindow().setAttributes(lp);
            }
        } else {
            if (getWindow() != null) {
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = height;
                getWindow().setAttributes(lp);
            }

        }

    }

    /**
     * @param gravity 对话框的位置 Gravity.BOTTOM
     */
    public void setAlertDialogGravity(int gravity) {
        //定义宽度
        if (getWindow() != null) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.gravity = gravity;
            getWindow().setAttributes(attrs);
        }
    }

    /**
     * 设置按键类型
     *
     * @param one true 只有一个确认按键 ； false 显示 确认 和取消 按键
     */
    public void setOneOrTwoBtn(boolean one) {
        if (one) {
            oneBtnView.setVisibility(View.VISIBLE);
            twoBtnView.setVisibility(View.INVISIBLE);
        } else {
            oneBtnView.setVisibility(View.INVISIBLE);
            twoBtnView.setVisibility(View.VISIBLE);
        }
    }

    public void setMessage(int resid) {
        messageView.setText(resid);
    }

    public void setMessage(String message) {
        messageView.setText(message);
    }

    public void setOneConfirmBtn(int resid, View.OnClickListener listener) {
        setOneOrTwoBtn(true);
        if (resid > 0) {
            oneBtnView.setText(resid);
        }
        oneBtnView.setOnClickListener(listener);
    }

    public void setOneConfirmBtn(String text, View.OnClickListener listener) {
        setOneOrTwoBtn(true);
        if (text != null) {
            oneBtnView.setText(text);
        }
        oneBtnView.setOnClickListener(listener);
    }

    public void setTwoConfirmBtn(int resid, View.OnClickListener listener) {
        setOneOrTwoBtn(false);
        if (resid > 0) {
            confirmBtn.setText(resid);
        }
        confirmBtn.setOnClickListener(listener);
    }

    public void setTwoConfirmBtn(String text, View.OnClickListener listener) {
        setOneOrTwoBtn(false);
        if (text != null) {
            confirmBtn.setText(text);
        }
        confirmBtn.setOnClickListener(listener);
    }

    public void setTwoCancelBtn(int resid, View.OnClickListener listener) {
        setOneOrTwoBtn(false);
        if (resid > 0) {
            cancelBtn.setText(resid);
        }
        cancelBtn.setOnClickListener(listener);
    }

    public void setTwoCancelBtn(String text, View.OnClickListener listener) {
        setOneOrTwoBtn(false);
        if (text != null) {
            cancelBtn.setText(text);
        }
        cancelBtn.setOnClickListener(listener);
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        super.dismiss();
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1.0f;
        lp.dimAmount = 1.0f;
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public void show() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        lp.dimAmount = 0.5f;
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        super.show();
    }

    public void setAnimation(int style) {
        Window w = getWindow();
        if (w != null) {
            w.setWindowAnimations(style);
        }
    }
}
