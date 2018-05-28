package com.qmkj.jydp.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author neo.duan
 * @date 2018/1/11 5:07
 * @desc 控制输入小数位位数
 */
public class MyTextWatcher implements TextWatcher {
    private int DECIMAL_DIGITS;//小数的位数

    private EditText editText;

    public MyTextWatcher(EditText editText, int DECIMAL_DIGITS) {
        this.editText = editText;
        this.DECIMAL_DIGITS = DECIMAL_DIGITS;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + DECIMAL_DIGITS + 1);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
