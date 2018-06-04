package com.qmkj.jydp.bean.event;

/**
 * Created by Rongkui.xiao on 2017/5/15.
 *
 * @description 用于事件传递
 */

public class ExchangeEvent {
    public boolean isFocusUpdate;

    public boolean isFocusUpdate() {
        return isFocusUpdate;
    }

    public void setFocusUpdate(boolean focusUpdate) {
        isFocusUpdate = focusUpdate;
    }
}
