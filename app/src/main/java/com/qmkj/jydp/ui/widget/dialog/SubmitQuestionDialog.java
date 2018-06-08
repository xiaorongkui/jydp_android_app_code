package com.qmkj.jydp.ui.widget.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.qmkj.jydp.R;
import com.qmkj.jydp.ui.widget.dialog.base.BaseDialog;

/**
 * 创建日期：2018/6/6
 *
 * @author Yi Shan Xiang
 *         文件名称： 联系客服弹出框
 *         email: 380948730@qq.com
 */
public class SubmitQuestionDialog extends BaseDialog {
    private EditText questionTitleEdt;
    private EditText questionContentEdt;
    private OnSubmitQuestionListener onSubmitQuestionListener;

    public void setOnSubmitQuestionListener(OnSubmitQuestionListener onSubmitQuestionListener) {
        this.onSubmitQuestionListener = onSubmitQuestionListener;
    }

    public interface OnSubmitQuestionListener {
        void onSubmitQuestion(String questionTitle, String questionContent);
    }

    public SubmitQuestionDialog(Context context) {
        super(context);
    }

    @Override
    protected View setContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_submit_question_content, null);
        return view;
    }

    @Override
    protected void initContentView(View contentView) {
        setTitleText("提交问题");
        questionTitleEdt = contentView.findViewById(R.id.question_title_edt);//标题
        questionContentEdt = contentView.findViewById(R.id.question_content_edt);//内容
        setOnPositiveButtonClickListener((dialog, view) -> {
            if (onSubmitQuestionListener != null) {
                onSubmitQuestionListener.onSubmitQuestion(questionTitleEdt.getText().toString(), questionContentEdt
                        .getText().toString());
            }
        });
    }
}
