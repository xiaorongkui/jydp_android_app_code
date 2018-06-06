package com.qmkj.jydp.ui.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.ui.widget.dialog.base.BaseDialog;

/**
 * Created by Yun on 2018/1/8.
 * 文本显示dialog
 */
public class CommonDialog extends BaseDialog {
    private TextView tvContent;
    private String contentText;

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public CommonDialog(Context context) {
        super(context);
    }

    @Override
    protected View setContentView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_common_content, null);
    }

    @Override
    protected void initContentView(View contentView) {
        if (TextUtils.isEmpty(titleStr)) {
            titleStr = "提示";
        }
        if (TextUtils.isEmpty(positiveButtonStr)) {
            positiveButtonStr = "确认";
        }
        if (TextUtils.isEmpty(negativeButtonStr)) {
            negativeButtonStr = "取消";
        }
        setTitleText(titleStr);
        setPositiveButtonText(positiveButtonStr);
        setNegativeButtonText(negativeButtonStr);
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText(contentText);
    }
}
