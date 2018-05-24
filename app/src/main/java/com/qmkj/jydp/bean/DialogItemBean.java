package com.qmkj.jydp.bean;

/**
 * author：rongkui.xiao --2018/4/27
 * email：dovexiaoen@163.com
 * description:
 */

public class DialogItemBean {
    private String certifyName;
    private int leftImageViewId;
    private boolean isSelect;

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

    public void setCertifyName(String certifyName) {
        this.certifyName = certifyName;
    }

    public void setLeftImageViewId(int leftImageViewId) {
        this.leftImageViewId = leftImageViewId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
