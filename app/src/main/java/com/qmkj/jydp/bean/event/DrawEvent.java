package com.qmkj.jydp.bean.event;

/**
 * author：rongkui.xiao --2018/3/21
 * email：dovexiaoen@163.com
 * description:
 */

public class DrawEvent {
    private boolean isOpenDrawer;

    public DrawEvent(boolean isOpenDrawer) {
        this.isOpenDrawer = isOpenDrawer;
    }

    public boolean isOpenDrawer() {
        return isOpenDrawer;
    }

    public void setOpenDrawer(boolean openDrawer) {
        isOpenDrawer = openDrawer;
    }
}
