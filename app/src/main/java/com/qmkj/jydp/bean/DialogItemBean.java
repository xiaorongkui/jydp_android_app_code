package com.qmkj.jydp.bean;

/**
 * author：rongkui.xiao --2018/4/27
 * email：dovexiaoen@163.com
 * description:
 */

public class DialogItemBean {
    public String certifyName;
    public int leftImageViewId;
    public boolean isSelect;

    public DialogItemBean(String certifyName, int leftImageViewId, boolean isSelect) {
        this.certifyName = certifyName;
        this.leftImageViewId = leftImageViewId;
        this.isSelect = isSelect;
    }

    public String getCertifyName() {
        return certifyName;
    }

    public int getLeftImageViewId() {
        return leftImageViewId;
    }

    public boolean isSelect() {
        return isSelect;
    }
}
