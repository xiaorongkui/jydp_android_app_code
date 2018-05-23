package com.qmkj.jydp.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.qmkj.jydp.R;

/**
 * 创建日期：2018/5/23
 * @author Yi Shan Xiang
 * 文件名称： 客服提交问题弹出框
 * email: 380948730@qq.com
 */

public class ContactServiceDialog extends Dialog{

    private  TextView cancelBtn;
    private  TextView confirmBtn;
    private  EditText content_et;
    private  EditText tittle_et;
    private Activity activity;
    private  View mView;

    public ContactServiceDialog(@NonNull Context context) {
        super(context);
    }

    public ContactServiceDialog(Activity context,int theme) {
        super(context, theme);
        this.activity = context;
        mView = LayoutInflater.from(getContext()).inflate(R.layout.contact_service_dialog, null);
        tittle_et = (EditText)mView.findViewById(R.id.tittle_et);
        content_et = (EditText)mView.findViewById(R.id.content_et);
        confirmBtn = (TextView) mView.findViewById(R.id.conform_btn);
        cancelBtn = (TextView) mView.findViewById(R.id.cancel_btn);
        setContentView(mView);
        super.setContentView(mView);
    }

    /**
     * @param width 对话框的宽度
     */
    public void setAlertDialogWidth(int width) {
//        //定义宽度
//        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
//        getWindow().setLayout(width, attrs.height);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
    }

    /**
     * @param height 对话框的高度
     */
    public void setAlertDialogHight(int height) {
        if (height <= 0) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(lp);
        } else {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = height;
            getWindow().setAttributes(lp);
        }

    }

    /**
     * @param gravity 对话框的位置 Gravity.BOTTOM
     */
    public void setAlertDialogGravity(int gravity) {
        //定义宽度
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.gravity = gravity;
        getWindow().setAttributes(attrs);
    }

    public String getTittleText() {
        if(tittle_et.getText() ==null){
            return null;
        }
        return tittle_et.getText().toString();
    }

    public String getContentText() {
        if(content_et.getText() ==null){
            return null;
        }
        return content_et.getText().toString();
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

    public void setConfirmBtnListener(View.OnClickListener listener) {
        confirmBtn.setOnClickListener(listener);
    }
    public void setCancelBtnListener(View.OnClickListener listener) {
        cancelBtn.setOnClickListener(listener);
    }
}
